
/**
  * 集合： Array数组，List，Set，Map，Option&Some&None，Tuple
  *
  */

class S7 {
  /**
    * Array 数组
    */
  def m1(): Unit = {

    // 长度不可变数组
    val arr = new Array[String](5)
    arr(0) = "a" // 索引用() 获取!! OMG

    val arr_b = Array("d", "e", "f") // 底层其实是通过调用了object Array.apply() 方法,
    arr_b(1) = "g" // 声明的val, 但是修改的只是 数组里面的内容,不能改的是 数组的内存地址
    arr_b.foreach(println(_))

    val arr_c = Array(1, 2, 3, 4, 5, 6)
    println(arr_c.mkString) // 数组转字符串： "123456"
    println(arr_c.mkString("-")) // "1-2-3-4-5-6"
    println(arr_c.mkString("《", "-", "》")) //《1-2-3-4-5-6》

  }

  def m2(): Unit = {
    // 长度可变数组
    var arr = scala.collection.mutable.ArrayBuffer[String]()
    arr += ("a", "b", "c") // 数组append
    arr ++= Array("d", "e", "f") // 合并数组
    arr.insert(n = 0, elems = "g", "h", "i")
    arr.remove(n = 1, count = 2) // 从索引1开始删除两个
    arr.trimEnd(n = 2) // 删除后 2位

    println(arr)
    // 遍历
    arr.foreach(println(_))

    for (a <- arr) {
      print(a)
    }

    println() // 换行看数据

    for (i <- 0 to arr.length - 1) { // to 需要减一，until 不用
      println(arr(i))
    }

    for (j <- (0 until arr.length).reverse) { // 反向
      print(arr(j))
    }


  }

  /**
    * List         // Nil 就是一个不可变的空的List: scala.collection.immutable.Nil.type = List()
    * 有序可重复
    */

  def m3(): Unit = {
    val la = List(1, 2, 3, 3, 4, 4)
    println(la.head)
    println(la.tail) // 一个 List = head+tail, head 是第一位，tail 是除去第一位的所有

    val lb = "a" :: Nil // 合并List  => head::tail(合并时 head和 tail 的类型需要一致),  tail = Nil；Nil是 一个 不可变的空的List

  }

  def m4(): Unit = {
    // 可变List
    val lb = scala.collection.mutable.ListBuffer[Int]()
    lb += 1
    lb += (2, 3, 4)
    lb ++= List(5, 6, 7)
    lb -= (0, 2)

    val lc = lb.toArray // 转成Array
    for (i <- lc) {
      println(i)
    }

  }

  /**
    * Set
    * 无序不可重复
    */

  def m5(): Unit = {
    val set = Set(1, 2, 3, 4, 1)
    val set2 = scala.collection.mutable.Set[Int]()

  }

  /***
    * tuple 元组
    */

  def m6(): Unit ={
    val tp = (1,2,3)
    // println(tp(1))     // 报错
    println(tp._2)
  }


}


object S7 {
  def main(args: Array[String]): Unit = {

    val s7 = new S7()
    s7.m2()

    s7.m4()

    s7.m6()

  }
}