/**
  * case class
  *     1、必须显示的声明构造器
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
  *   class: new className();   需要new也需要一个()。 加new()() 的默认是调用 class{ apply() }
  *
  *   abstract(抽象类):  有构造器，子类extends继承 需要重写所有的属性和方法
  *
  *   trait(特质类)： 有无参数的构造器，子类extends Atrait with Btrait ； 继承with 需要 重写 或者重新定义 所有的属性和方法
  *
  *   ps: 类都有构造器
  *
  */

case class S6() { // case class 必须 显示声明构造器

  def get_name(name: String): Unit = {
    println(name)
  }

}


// Trait
trait Person { // trait 有 无参数的构造器,不能+()
  val name: String
  val age: Int

  def get_name(): String

  def get_age():Int

  def say()      // 不写默认 Unit

}

trait PersonEx {
  var card = ""

  def get_card(): String

  // def edit_card()
}

class Me(override val name: String, val age: Int, var cd: String) extends Person with PersonEx { // 第一个trait 用 extends,后面的 trait 都用 with

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




/**
  *  trait 无参构造器—补充
  *  无参构造:
  *  1、和 类的构造器不同 trait trait_name  后面不能 +()
  *  2、在 子类创建实例对象的时候 会执行 trait类里面 已实现&&已调用 的方法（就是子类继承trait；在由trait自己来实现方法和调用，子类不在实现(重写或者重新声明)该方法）
  */

trait S6a{

  m6a             // 调用执行
  add(1,9)        // 调用执行

  def m6a(): Unit ={           // 实例方法
    println("method: m6a() ")

  }

  def add(x:Int,y:Int): Unit ={
    println(x+y)
  }

  def sum(x:Int,y:Int):Int

}

class S6ac extends S6a{
  override def sum(x: Int, y: Int): Int = {    // trait 没有声明调用，子类必须 重写|重新声明（ps:sum()没有调用执行，则子类重写）
    x+y
  }

}


// trait  继承 trait


trait S6b{

  def get(age:Int):Int
  def ms6b(msg:String)

}

trait S6bc extends S6b{
  override def get(age: Int): Int = {
    age
  }

  override def ms6b(msg: String): Unit = {
    println(msg)
  }
}


// trait 链条式调用：继承多个trait后，依次调用多个trait中的同一个方法，只要让多个trait的同一个方法中，在最后都执行super.方法，即可形成一个调用链条-会从最右边的trait的方法开始执行，然后依次往左执行

trait Handler{
  def handler(name:String){}
}

trait OneHandler extends Handler{
  override def handler(name: String): Unit = {
    println("OneHandler"+name)
    super.handler(name)             // 在最后都执行super.方法

  }

}

trait TwoHandler extends Handler{
  override def handler(name: String): Unit = {
    println("TwoHandler"+name)

    super.handler(name) // 在最后都执行super.方法
  }
}

class Student(val name:String) extends TwoHandler with OneHandler{
  def say()={
    println("hi"+name)

    handler(name)
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

    // 特质构造器补充
    val s6ac = new S6ac()   // 在创建实例对象的时候，由trait自己去"实现方法"


    val s=new Student("jock")    // trait 链条调用
    s.say

  }

}

