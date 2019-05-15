import scala.actors.Actor

/** *
  * 异步并发 Actor  (scala 2.10 之前版本)
  * 1、 不共享数据，避免锁
  * 2、继承Actor，重写act方法
  * 3、使用start()方法启动actor,使用!符号，向actor发送消息；actor内部使用receive和模式匹配接收消息
  *
  *
  *
  * 异步并发 Akka (scala 2.11之后版本已经废弃了Actor,而是直接将Akka作为默认并发,需添加Akka 依赖)
  */

class HiActio extends Actor {
  override def act(): Unit = {
    while (true) {
      receive(
        {
          case name: String => println("hi " + name)
        }
      )
    }
  }
}


object S13 {
  def main(args: Array[String]): Unit = {

    val hiActio = new HiActio
    hiActio.start()
    hiActio ! "jock"


  }
}