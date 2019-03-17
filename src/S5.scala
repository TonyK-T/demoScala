/**
  * 单继承和重写
  *     继承 或者 重写 属性和方法
  *
  * 抽象类
  *     必须重写所有的属性和方法
  *
  */

class S5(val name: String, val age: Int) {
  val salary = 100

  def get_name(): Unit = {
    println(name)
  }


}

class S5Ex(name: String, age: Int, sex: String) extends S5(name: String, age: Int) { // 因为继承,子类name 和 age 可以不需要加上 val|var 可直接作为属性

  override val salary: Int = 200 // override重写属性

  override def toString: String = super.toString // override重写 toString方法

  override def get_name(): Unit = {
    println(name + "_son")
  }


}

/**
  * abstract 抽象类
  */

abstract class Abs5(val salary: Int) { // 抽象类有构造器
  val name: String
  val age: Int

  def eat()

  def drink(): String // 需要指明 返回值类型，不写默认为 Unit

}

class S5Exa(override val salary: Int) extends Abs5(salary) { // 必须重写 抽象类的所有方法和属性
  override val name: String = "stringk"
  override val age: Int = 20

  override def eat(): Unit = {
    println(salary)

  }

  override def drink(): String = {
    "drink"
  }


}


object S5 {
  def main(args: Array[String]): Unit = {

    // 继承重写
    val S5ex = new S5Ex("jock", 18, "boy") // 执行过程：会先执行父类的构造器，在执行子类的构造器
    println(S5ex.name)
    //println(S5ex.sex)  // 未 val|var 声明，非对象属性
    println(S5ex.toString)

    //抽象类
    val S5exa = new S5Exa(1300)
    S5exa.eat()

    //
    new Abs5(salary = 100000) {
      override val name: String = "匿名类"
      override val age: Int = 19

      override def eat(): Unit = {println(salary)}

      override def drink(): String = {"drinking"}
    }
  }

}
