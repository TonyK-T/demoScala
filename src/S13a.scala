import java.util.{Timer, TimerTask}

/** *
  * 定时器_java-API：Timer, TimerTask
  *
  *
  *
  */

class MyTimerTask(var count:Int=0) extends TimerTask {

  override def run(): Unit = {
    count += 1
    println(s"MyTimerTask--$count：第一次2秒,第二次4秒,第三次1秒")
    new Timer().schedule(new MyTimerTask(),2000,2000+2000*(count%2))   // 第一次2秒,第二次4秒
  }

}

object S13a {

  def main(args: Array[String]): Unit = {

    new Timer().schedule(new TimerTask {
      override def run(): Unit = {
        println("hhh:第一次1秒之后每3秒")
      }
    }, 1000, 3000) // 第一次1秒之后每3秒

    // new MyTimerTask()   // 这样直接调用拉不起来！！

    new Timer().schedule(new MyTimerTask(),2000)  // 这样才对

  }

}
