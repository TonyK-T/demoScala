/**
  * 基本语法
  *
  *
  */


class S1 {
  val a1 = 10
  val a2 = a1.asInstanceOf[Double] // Int 转 Double   isInstanceOf
  lazy val a3 = "懒加载"


  def m1(name: String = "方法默认值"): Unit = {
    println(name)

  }

  def m2(name: String, age: Int): String = { // 返回值类型为String
    name + age
    // return name + age   // 可以不写return, 默认返回 最后一行
  }


  def m3(number: Int*): Unit = {
    //可变参数
    println(number)
  }

  /**
    * 方法总结：
    * 方法传参只会： ff(name:String) | ff(name:String ="jock") | ff(name:String*) ; 不可出现 val|var  如： ff(val name:String="jock") ps: val|var 在 ()里面 出现都是在 构造器中
    *
    */

  def m4(age: Int): Unit = {

    // var i =0  if(i>1) i不能在()里面声明
    val i = 18
    if (age > i) {
      println(age)
    } else if (age < 15) {
      println(age)
    } else {
      println(if (age < 1) true else false)

    }

  }


  def m5(age: Int = 16): Unit = {
    // 循环 (for j ) j 不需要先声明,默认从0 开始
    for (i <- age to 25; if i > 18) {
      println(i)
    }

    // to 和 until  都是调用的Range(): 区别就是 to 包含了20,until只包含19
    for (j <- 0 to 20) {
      println(j)
    }
    val k = 1
    println(k until 20)


    while (if (age < 1) true else false) { // 跳出循环只能是 return | 改变判断条件 | 使用Breaks类的break方法
      val arr = Array("a", "b", "c")
      for (j <- arr) {
        println(j)

      }

    }

    // foreach 循环
    val arr = Array("a", "b", "c")
    arr.foreach(x => println(x)) // _ 参数, println() 为函数
    arr.foreach(println(_)) // 默认一个入参时，可直接传 函数+占位符

  }

}

object S1 {
  def main(args: Array[String]): Unit = {

    val s1 = new S1()
    println(s1.m2("a", 1))
    s1.m3(1, 2)

  }
}