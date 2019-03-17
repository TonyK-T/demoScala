/**
  * 伴生类class_name 和 伴生对象_object_name  使用补充
  *    class_name 和 object_name 同名 既 class S4 : object S4  ===  伴生类 ： 伴生对象
  *    1、object 是一个 单例对象，不需要new，可直接用 object_name. 调用
  *    2、object 可以访问 class 非[this]修饰的私有属性和方法
  *
  * apply() 方法使用
  *
  *
  */


class S4 {
  val ci = 2
  private[this] val id = "123456"

  def add_c(num: Int): Unit = {
    println(ci + num)
  }

  def apply(): Unit = {
    println("method: class.apply()__________ ")
  }

}


object S4 {
  val i = 1

  // println(new S4().id)   // 伴生类无法访问[this] 修饰的属性， [this] 只可在 class{} 内部访问

  def add(num: Int): Unit = {
    println(i + num)
  }


  /**
    * 特殊方法之 apply()
    * 实例对象+()时 默认调用
    */

  //演示写法
  def apply(): Unit = {
    println("method: object.apply()..........")
  }

  // 最佳实战写法如下：在 object 的apply()方法中去 new 一个class
  // def apply: S4 = new S4()   // 详细骚操作参考：

  // 分两步的写法
  //  def apply(): S4 = {
  //    S4()
  //  }


}

object Run {
  def main(args: Array[String]): Unit = {

    // object 伴生对象(单例对象) 可直接 调用伴生对象内的方法_ 不用+()
    S4.add(1)

    val s4o = S4() // object_name() 执行过程：object + () 默认调用的是 object下的 apply() 方法 =》 object_name.apply()

    val s4c = new S4()() // new class_name() 执行过程： new + class + ()()  默认调用的是 类下 的 apply() 方法 =》 class_name.apply()
    /**
      * 分两步写就是： 先创建一个实例对象，由实例对象+()  默认调用 类下的 apply() 方法
      * val s4c_o = new S4()
      * s4c_o()
      *
      * apply() 方法总结： 一定是 一个对象+() 的形式默认调用（这个对象可以是 object伴生对象，也可以是 class new一个实例对象）
      *
      */

  }

}

