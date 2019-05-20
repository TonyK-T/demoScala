import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.{Exchanger, Executors, Semaphore, TimeUnit}

import scala.util.Random

/**
  * 多线程补充_java-API
  */


// newFixedThreadPool :定长线程池,控制线程最大并发数,超出的线程会在队列中等待
class My_newFixedThreadPool {
  val newFixedThreadPool = Executors.newFixedThreadPool(3)
  for (i <- 0 until 5) {
    newFixedThreadPool.execute(new Runnable {
      override def run(): Unit = {
        println("newFixedThreadPool" + Thread.currentThread().getName + s"$i")
      }
    })
  }
  newFixedThreadPool.shutdown()
}

// newCachedThreadPool : 动态创建线程池中的个数
class My_newCachedThreadPool {
  val newCachedThreadPool = Executors.newCachedThreadPool()
  for (i <- 0 until 5) {
    newCachedThreadPool.execute(new Runnable {
      override def run(): Unit = {
        println("newCachedThreadPool" + Thread.currentThread().getName + s"$i")
      }
    })
  }

  newCachedThreadPool.shutdown()
}

// newSingleThreadExecutor : 创建只有一个线程的线程池 当线程池中的线程die 了 会重新创建一个线程, 顺序执行
class My_newSingleThreadExecutor {
  val newSingleThreadExecutor = Executors.newSingleThreadExecutor()
  for (i <- 0 until 5) {
    newSingleThreadExecutor.execute(new Runnable {
      override def run(): Unit = {
        println("newSingleThreadExecutor" + Thread.currentThread().getName + s"$i")
      }
    })
  }
  newSingleThreadExecutor.shutdown()

}

// newScheduledThreadPool
class My_newScheduledThreadPool {
  val newScheduledThreadPool = Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable {
    override def run(): Unit = {
      println("newScheduledThreadPool" + Thread.currentThread().getName)

    }
  }, 1, 2, TimeUnit.SECONDS)

}


// 多线程锁-ReentrantLock.newCondition : 需要 lock() and unlock()
class MyReentrantLock {
  var flag = 1
  var num = 0

  val lock = new ReentrantLock()

  val condition1 = lock.newCondition() // 条件1
  val condition2 = lock.newCondition()
  val condition3 = lock.newCondition()

  def add() = {
    lock.lock()
    try {
      while (flag != 1) {
        try {
          condition1.await()
        } catch {
          case e: Exception => println(e.printStackTrace())
        }
      }

      for (i <- 1 to 10) {
        num += 2
        println(s"$i: num +2 =" + num.toString)
      }

      flag = 2
      condition2.signal() // 通知条件2

    } finally {
      lock.unlock()

    }
  }

  def sub1() = {
    lock.lock()
    try {

      while (flag != 2) {
        try {
          condition2.wait()
        } catch {
          case e: Exception => println(e.printStackTrace())
        }

      }

      for (j <- 1 to 10) {
        num -= 1
        println(s"$j: num -1 =" + num.toString)
      }

      flag = 3
      condition3.signal()


    } finally {
      lock.unlock()
    }

  }


  def sub2() = {
    lock.lock()
    try {

      while (flag != 3) {
        try {
          condition3.wait()
        } catch {
          case e: Exception => println(e.printStackTrace())
        }

      }

      for (j <- 1 to 10) {
        num -= 1
        println(s"$j: num -1 =" + num.toString)
      }

      flag = 1
      condition1.signal()


    } finally {
      lock.unlock()
    }


  }


}

// 限制线程数Semaphore
class MySemaphore {
  val cachedThreadPool = Executors.newCachedThreadPool()
  val sem = new Semaphore(3)
  for (i <- 1 to 10) {
    val runnable = new Runnable {
      override def run(): Unit = {
        try {
          sem.acquire()
        } catch {
          case e: Exception => println(e.printStackTrace())
        }

        println("线程名：" + Thread.currentThread().getName)
        println("当前并发数：" + (3 - sem.availablePermits()))
        Thread.sleep(Random.nextInt(10))

        sem.release()
        println("离开线程名：" + Thread.currentThread().getName)
        println("当前并发数：" + (3 - sem.availablePermits()))

      }

    }
    cachedThreadPool.execute(runnable)

  }

  cachedThreadPool.shutdown()
}



object S13c {


  def main(args: Array[String]): Unit = {

    new My_newCachedThreadPool()


    val myReentrantLock = new MyReentrantLock()
    new Thread(new Runnable {
      override def run(): Unit = {
        myReentrantLock.add()
      }
    }).start()

    new Thread(new Runnable {
      override def run(): Unit = {
        myReentrantLock.sub1()
      }
    }).start()


    new Thread(new Runnable {
      override def run(): Unit = {
        myReentrantLock.sub2()
      }
    }).start()



    new MySemaphore()


  }


}
