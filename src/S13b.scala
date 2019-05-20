import java.lang.{InterruptedException, Thread}
import java.util.concurrent.ExecutorCompletionService


/**
  * scala 调用java多线程API
  * 1、扩展Thread类达到多线程
  * 2、实现Runnable接口达到多线程
  * 3、线程池
  * 4、多线程返回值
  * 5、线程锁
  *
  */


class MyThread(val name: String) extends Thread {

  def say(): Unit = {
    println("hi " + name)
  }

  override def run(): Unit = {
    say()
    println("MyThread-线程名：" + this.getName)
    println("MyThread-线程优先级：" + this.getPriority)
  }

}

//Runnable 无返回值
class MyRunnable(val name: String) extends Runnable {

  override def run(): Unit = {
    println("MyRunnable-hi " + name)
    // println(this.getName)    // Runnable 没有这方法,被Thread "包"一次才有

  }
}

import java.util.concurrent.Callable

class MyCallable(val name: String) extends Callable[String] {
  override def call(): String = {
    return "MyCallable-hi:" + name
  }

}

// 线程锁-synchronized同步锁
class Mysynchronized {
  var flag = true
  var money = 10

  def sub() = {
    this.synchronized { // 同步锁
      while (!flag) {
        try {
          this.wait()  // 等待
        } catch {
          case e: InterruptedException => println(e.printStackTrace())
        }
      }

      for (i <- 1 to 10) {
        money -= 1
        println(s"$i: money - 1 =" + money.toString) // 减10次1


      }

      flag = false
      this.notify()   // 唤醒

    }
  }

  def add() = {
    this.synchronized {
      while (flag) {
        try {
          this.wait()
        } catch {
          case e: InterruptedException => println(e.printStackTrace())
        }
      }

      for (j <- 1 to 10) {
        money += 1
        println(s"$j: money +1 =" + money.toString)


      }

      flag = true
      this.notify()
    }
  }


}


object S13b {
  def main(args: Array[String]): Unit = {

    val myThread1 = new MyThread("myThread1") // 只能通过实例化方式传参
    myThread1.start()
    myThread1.setName("myThread1")
    myThread1.setPriority(Thread.MIN_PRIORITY) //优先级


    val myRunnable1 = new MyRunnable("myRunnable1")
    val t = new Thread(myRunnable1) // Runnable 需要Thread“包”一次
    t.start()
    //t.setName("myRunnable1")


    // 线程池
    import java.util.concurrent.{Callable, FutureTask, Executors, ExecutorService}
    val threadPool = Executors.newFixedThreadPool(5)
    try {


      threadPool.execute(new MyRunnable("dog")) // Runnable


      // 线程返回值-Callable
      val myCallable1 = new MyCallable("myCallable1")
      val myCallable2 = new MyCallable("myCallable2")

      val myCallable1_future1 = threadPool.submit(myCallable1)
      val myCallable2_future2 = threadPool.submit(myCallable2)

      println(myCallable1_future1.get())
      println(myCallable2_future2.get())


      // 线程返回结果-Callable
      val future2 = threadPool.submit(new Callable[String]() {
        override def call(): String = {
          return "oooooo"
        }
      })

      println("线程返回结果-Callable：" + future2.get())


      // 线程返回值-FutureTask+Callable: 好处：全部生成task任务组
      val futureTask = new FutureTask[String](new Callable[String] {
        override def call(): String = {
          return "hhhhhh"
        }
      })

      threadPool.execute(futureTask) //  FutureTask
      println("线程返回值-futureTask：" + futureTask.get())


      // 线程返回值-ExecutorCompletionService ： 通过 ExecutorCompletionService 提交task任务组
      val executorCompletionService = new ExecutorCompletionService[String](threadPool)
      for (i <- 0 until 2) {
        val task_id = i
        executorCompletionService.submit(new Callable[String] {
          override def call(): String = {
            return s"kkkkkk-$task_id"
          }
        })
      }

      for (j <- 0 until 2) {
        println("executorCompletionService 线程返回值：" + executorCompletionService.take().get())
      }


    } finally {
      threadPool.shutdown()
    }

    // 线程锁(加减3次顺序执行)
    val mysynchronized = new Mysynchronized()
    new Thread(new Runnable {
      override def run(): Unit = {
        for (i <- 0 until 3) {
          mysynchronized.sub()
        }
      }
    }).start()

    new Thread(new Runnable {
      override def run(): Unit = {
        for (j <- 0 until 3) {
          mysynchronized.add()
        }
      }
    }).start()


  }
}
