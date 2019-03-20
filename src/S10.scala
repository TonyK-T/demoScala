/**
  * 高阶函数 map,filter,reduceLeft,scanLeft
  */

class S10 {

  def m1(): Unit ={
    (1 to 9).map("*" * _).foreach(println(_))      // 一个参数的时候可以不写入参直接写方法体;*是乘法
    (1 to 9).map(x => "*" * x ).foreach(println(_)) // 以上原型

    (1 to 9).filter(_ %2 == 0).foreach(println(_))
    println((1 to 9).reduceLeft(_ * _))    // 1*2*3*4

    println((1 to 9).scanLeft(0)(_ + _))


  }


  def m2(lst:List[Int]): Int = lst match {
    case Nil =>0
    case h::t => h  //  h == lst.head,t==lst.tail
  }

  def m3(lst:List[Int]): Int ={
    val value = lst.reduceLeft(_ - _)
    println(value)
    value
  }

  def m4 (list: List[Int]): Int ={
    val value = list.foldRight(0)(_ - _)
    println(value)
    value
  }

}


object S10 {
  def main(args: Array[String]): Unit = {



  }


}