/**
  * 简写
  */

object S1b {


  //
  List(2).foreach(_ =>println(_))
  List(2).foreach(println(_))     // 一个参数可省
  List(2).foreach(println)



  List(2).map(_ match {case 1 => "ok"})
  List(2).map{case 1 => "ok"}








}
