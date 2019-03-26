
/**
  * 类和方法
  *    1.方法
  *    方法传参只会： ff(name:String) | ff(name:String ="jock") | ff(name:String*) ; 不可出现 val|var  如： ff(val name:String="jock") 另外ps: val|var 在() 里面出现都是在 构造器中
  *
  *
  * 构造器 见_S3
  *
  * 伴生类 和 伴生对象
  *    class_name 和 object_name 同名 》 class S5 : object S5  ===  伴生类 ： 伴生对象
  *    object 本身就是一个 单例对象(单例_对象_不需要new；S4补充)
  */
class S2 {
  var name = "stringk"
  var age = 20
  val id = 1 // val 是常量，不可 对象.id =21 重新赋值

  private var sex = "boy"

  private[this] var money = 1000 // [this] 修饰作用域只限 当前 class{} 内部

  private [S2] var salary = 2000   //  [S2] 修饰作用域只限  class S2{} 内部，class S2实例对象 以及 包名为 S2 下面的所有的 类和对象使用!!


  def eat(): String = {
    name + ":eating"
  }

  def drink(dk: String = "colo"): Unit = {
    println(name + dk)
  }

  def change_money(salary: Int = 0): Int = {
    money + salary
  }

  // 匿名函数
  val nm = (x:Int) => x+1

  def nm2(x:Int): Int ={   // 匿名函数原型
    x+1
  }

  def add(x:Int,y:Int) = x+y   //


  def m(x:Int)(y:Int) = x+y    // 柯理化

}

object S2 {
  def main(args: Array[String]): Unit = {

    val s2 = new S2()


    println(s2.eat())
    s2.drink("tea")
    println(s2.sex) // sex 私有属性，只有 object S2 伴生类下 才能访问到：既 object S2{} 内可访问
    // println(s2.money)    // [this]修饰，只限class{} 内部访问，无法在其他位置 对象.属性 获取属性

    println(s2.salary)

    println(s2.change_money(100))

  }
}

object D2 {
  val s2 = new S2()
  s2.change_money(200)
  // s2.sex    非伴生类无法访问私有属性

}


