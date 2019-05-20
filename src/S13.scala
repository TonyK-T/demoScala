import scala.actors.{Actor, Future}
import scala.collection.mutable.ArrayBuffer

/** *
  * 异步并发+通信 Actor  (scala 2.10 之前版本)
  * 1、 不共享数据，避免锁
  * 2、继承Actor，重写act方法
  * 3、使用start()方法启动actor,使用!符号，向actor发送消息；actor内部使用receive和模式匹配接收消息
  *
  *   ! 发送异步消息,没有返回值
  *   !? 发送同步消息,等待返回值
  *   !! 发送异步消息,返回值是Future[Any]
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


case class WordCount(word: String)

class WordCountActor extends Actor {
  override def act(): Unit = {
    while (true) {
      receive(
        {
          case WordCount(word) => {
            val word_list = word.split(",")
            val word_count = word_list.map((_, 1))
            val word_group = word_count.groupBy(_._1)
            val result = word_group.mapValues(_.size) // Map(bb -> 1, cc -> 1, dd -> 1, aa -> 1)
            sender ! result
          }
        }
      )
    }
  }
}

// 相互通信
case class MyRequest(request:String)
case class MyResponse(response: String)

class Client(server: Server) extends Actor {
  override def act(): Unit = {

    loop{
      receive(

        {
          case MyRequest(request) => server ! MyRequest(request)  // server ! 才会到 Server类的receive下case匹配
          case MyResponse(response) => println("Client"+response)

        }
      )
    }

  }

}


class Server extends Actor{
  override def act(): Unit = {
    loop{
      receive(
        {
          case MyRequest(request) => println(request)

          //this ! MyResponse({"status"->"200"}.toString)   // this ! 只会到当前Server类的 receive 去case匹配(既client接受不到)
          sender ! MyResponse({"status"->"200"}.toString)  // sender !

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

    println("------------")

    val futures = new ArrayBuffer[Future[Any]]()

    val words = Array("task-1,a,b", "task-2,c,d", "task-1,a,c")
    for (word <- words) {

      val wc_actor = new WordCountActor() // 创建多个Actor
      wc_actor.start()
      val future: actors.Future[Any] = wc_actor !! WordCount(word) // 异步返回值

      futures += future

    }

    val wc_result = new ArrayBuffer[Map[String,Int]]()
    while (futures.size > 0) {
      val d_futures = futures.filter(_.isSet) // 去空
      for (d_future <- d_futures) {
        val d_result = d_future.apply().asInstanceOf[Map[String,Int]]   // Any 转 Map
        wc_result += d_result
        futures -= d_future                // 跳出while

      }
    }
    println(wc_result)

  }

  println("------------")

  val server = new Server()
  server.start()

  val client = new Client(server)
  client.start()

  client ! MyRequest("get Server")


}