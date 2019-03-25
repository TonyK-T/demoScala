/**
  * 简写
  */

object S1b {


  //
  List(2).foreach(x => println(x))
  List(2).foreach(println(_)) // 一个参数可省
  List(2).foreach(println)


  List(2).map(_ match { case a: Int => println("ok") })
  List(2).map { case 2 => println("ok") }


  def main(args: Array[String]): Unit = {
    println(" ")

  }


}


