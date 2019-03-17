/**
  * case class
  *     1、必须声明构造器
  *     2、case class 不用new，直接通过 类名+() 调用
  *     3、case class 常用使用场景：模式匹配，见 S8
  *
  * Trait 类似java接口,比java接口更强大
  *     1、第一个trait 用 extends,后面的 trait 都用 with
  *     2、必须 override重写方法 或者 重新声明方法(所有的方法)
  *
  */


/**
  * 总结：
  *   object： object.直接调用;  不用new也不用()。 加 () 的默认调用的是 object{ apply() }
  *
  *   case class: className();  不用 new,只要()。
  *
  *   class: new className();   需要new也需要一个()。 加()() 的默认是调用 class{ apply() }
  *
  *   abstract:  有构造器，子类extends继承 需要重写所有的属性和方法
  *
  *   trait： 无构造器，子类extends Atrait with Btrait 继承with 需要 重写 或者重新定义 所有的属性和方法
  */

case class S6() { // case class 必须 声明构造器

  def get_name(name: String): Unit = {
    println(name)
  }

}


// Trait
trait Persion { // trait 没有构造器？
  val name: String
  val age: Int

  def get_name(): String

  def get_age(): Int

  def say()

}

trait PersionEx {
  var card = ""

  def get_card(): String

  // def edit_card()
}

class Me(override val name: String, val age: Int, var cd: String) extends Persion with PersionEx { // 第一个trait 用 extends,后面的 trait 都用 with

  override def get_name(): String = { // override重写的方式
    name
  }

  def get_age(): Int = { // 重新声明的方式
    age
  }

  override def say(): Unit = {
    println("hi~" + name)
  }

  def get_card(): String = {
    card = cd
    return card
  }

}


object S6 {
  def main(args: Array[String]): Unit = {

    // case class
    S6().get_name("jock")

    // trait

    val me = new Me("jock", 18, "123456")
    println(me.get_name())
    println(me.get_age())
    me.say()
    println(me.get_card())

  }

}





