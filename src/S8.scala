

import scala.util.Random

/**
  * 模式匹配,类似java的 switch
  *     [基本数据类型，Array，List，case class, Some&None] 模式匹配
  *
  *  scala异常处理
  *
  */

class S8 {

  def m1(): Unit = {
    val names = Array("python", "java", "PHP", "Scala")
    val name = names(Random.nextInt(names.length))
    println(name)

    name match {
      case "python" => println("人生苦短 我用Python") // 匹配到就直接返回
      case "java" => println("没事就new一个对象玩玩吧 反正也孤独终老")
      case _ if (name == "PHP") => println("PHP 是世界上最好的语言") // 可以在加条件 if
      case _ => println("太嘎好 哦系Scala")

    }

  }

  // Array 匹配
  def m2(array: Array[String]): Unit = {
    array match {
      case Array("python") => println("数组里只有 Python") // 用 Array("") 而不是用 声明的array变量 取值!!
      case Array(x, y) => println("数组里有两位：" + x + "&" + y)
      case Array("python", _*) => println("数组的 Python+其他")
      case _ => println("出来接客啦")
    }

  }

  //List 匹配
  def m3(list: List[String]): Unit = {
    list match {
      case "python" :: Nil => println("数组里只有 Python 一位") // Nil：不可变的空的List，详见S7
      case x :: y :: Nil => println("数组里有两位：" + x + "&" + y) // head::tail
      case "python" :: tail => println("数组的 Python+其他")
      case _ => println("出来接客啦")
    }

  }

  // 类型 匹配
  def m4(any: Any): Unit = {
    any match {
      case a: Int => println(" 你是 Int 类型")
      case b: String => println(" 你是 String 类型")
      case c: Map[_, _] => println("你是 Map 类型")
      case _ => println("什么鸡巴类型")
    }
  }

  // 异常处理
  def m5(): Unit = {
    try {
      val i = 10 / 0
    } catch {
      case e: ArithmeticException => println("除数不能为0")
      case e: Exception => println(e.getMessage)

    } finally {
      println("执行完毕")
    }
  }

  // case class 匹配 类似 Python的isinstance() |
  def m6(animal: S8.Animal): Unit = {
    animal match {
      case S8.Dog(name, cate) => println(name, cate)
      case S8.Cat(name, cate) => println(name, cate)
      case S8.Brid(name, cate) => println(name, cate)
      case _ => println("没有这个类")

    }

  }

}

object S8 {

  class Animal(val name: String)

  case class Dog(override val name: String, val cate: String) extends Animal(name) // S6 详解 case class

  case class Cat(override val name: String, val cate: String) extends Animal(name)

  case class Brid(override val name: String, val cate: String) extends Animal(name)


  def main(args: Array[String]): Unit = {
    val s8 = new S8()
    s8.m1()
    s8.m2(Array("python", "Scala"))
    s8.m3(List("python", "Scala", "java"))
    s8.m4(Map("name" -> "python")) // 字典这种 key->value 写法是真的无语了

    s8.m5()

    s8.m6(Cat("猫", "软体"))   // case class 不用new，直接通过 类名+() 调用


  }


}