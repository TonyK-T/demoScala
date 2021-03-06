import scala.collection.mutable

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

    val arr_c = Array(7,8,1, 2, 3, 4, 5, 6)
    println(arr_c.mkString) // 数组转字符串： "123456"
    println(arr_c.mkString("-")) // "1-2-3-4-5-6"
    println(arr_c.mkString("《", "-", "》")) //《1-2-3-4-5-6》
    println(arr_c.toBuffer)

    val arr_d =for(ele <- arr_c if ele %2 ==0) yield ele*ele
    println(arr_d)

    val arr_e = arr_c.filter(_%2==0).map(2*_)
    arr_e.foreach(print)

  }

  def m2(): Unit = {
    // 长度可变数组
    var arr = scala.collection.mutable.ArrayBuffer[String]()
    arr += "X"
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

    println("-----------")

    for (i <- 0 to arr.length - 1) { // to 需要减一，until 不用
      println(arr(i))
    }

    for (j <- (0 until arr.length).reverse) { // 反向
      print(arr(j))
    }

    for (k <- 0 until(arr.length,2)){   // 跳跃步长=2 遍历
      println(arr(k))
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
    val lc = la +: lb // List(List(1, 2, 3, 3, 4, 4), a)
    println(lc)
    val ld = 3::la
    println(ld ++ la)  // List(3, 1, 2, 3, 3, 4, 4, 1, 2, 3, 3, 4, 4)



  }

  def m4(): Unit = {
    // 可变List
    val lb = scala.collection.mutable.ListBuffer[Int]()
    lb += 1
    lb += (2, 3, 4)
    lb ++= List(5, 6, 7)
    lb -= (0, 2)
    lb.append(9)

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

    val set2 = scala.collection.mutable.Set[Int]()  // 可变
    val set3 = set2+5
    val set4 = set3++set
    set4.add(9)
    set4.remove(1)
    println(set4)

  }

  /** *
    * tuple 元组
    */

  def m6(): Unit = {
    val tp = (1, 2, 3)
    // println(tp(1))     // 报错,不能这样取
    println(tp._2) // 元组取值用下划线从._1开始取 !! OMG


  }

  /**
    * Map
    */

  def m7() = {
    val stu = Map("jock" -> "11", "rose" -> "12") // 不可变
    println(stu("jock"))
    val name = if (stu.contains("rose")) stu("rose") else " "

    println(stu.getOrElse("dog", " "))

    val stu2 = mutable.Map("rose" -> "13") // 可变
    stu2 += ("dog" -> "1","cat"->"2")
    stu2 -="jock"
    println(stu2)

    val arr = Array(("jock", 2), ("rose", 1))
    println(arr.toMap)

    for ((k,v)<-stu2){
      println(k+":"+v)
    }

    for (v <- stu2.values)println(v)
    for ((k,v) <- stu2)yield (v,k)




  }


}


object S7 {
  def main(args: Array[String]): Unit = {

    val s7 = new S7()
    s7.m1()
    //s7.m2()

    //s7.m4()

    s7.m5()

  }
}