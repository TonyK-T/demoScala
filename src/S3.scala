
/**
  * 构造器
  *   回归： 方法传参只会： ff(name:String) | ff(name:String ="jock") | ff(name:String*) ; 不可出现 val|var
  *
  *   构造器只会是： class Name(val/var name:String) | class Name(val/var name:String="jock") | class Name(name:String)—不推荐 | class Name(name:String="jock")-不推荐
  */



// 主构造器
class S3(name: String="jock", sex: String, val age: Int, private val salary: Int = 100) {  // 参数赋值 跟Python 不同;scala 不用在意 默认值参数前后 顺序
  val _name = name // 无辅构造器的时候不能同名

  private[this] val id = 1024

  var b_name: String = _

  // 附属构造器
  def this(name: String, sex: String, age: Int, salary: Int, b_name: String) {
    /**
      * 注意：附属构造器 第一行代码 必须要调用 主构造器 或者 其他附属构造器
      * 注意：该主构造函数的最后一个属性salary 给了默认值 则辅构造器 salary属性和追加的b_name属性就不能再给默认值了：
      * 既 附构造器 salary: Int = 110,b_name: String="别名" 会报错(和 private 以及 val 无关);反之, 主构造器 salary 没有给定默认值的话，辅构造器b_name是可以给默认值的
      */

    this(name, sex, age, salary)
    this.b_name = b_name

  }

  def say(msg: String = "hi"): Unit = {
    println(msg + ":" + _name + ":" + sex + ":" + age + ":" + salary + ":" + b_name)

    println(id) // [this] 修饰，只限class{} 内部访问
  }

}

object S3 {
  def main(args: Array[String]): Unit = {

    val s3 = new S3("jock", "boy", 18, 120)

    println(s3._name)
    //println(s3.sex)   // 未声明(未使用val|var) sex 属性,单纯的类似 方法传参而已, 注意： 这种做法是不推荐的，构造器的意义就是构造对象属性，而不是作为参数传入
    println(s3.age) // 声明 val|var 即声明了对象属性,  像name 是分两步写的

    println(s3.salary) // 私有属性，伴生类才可访问

    s3.say("hello")

    val s4 = new S3("rose", "girl", 16, 110, "玫瑰") // 辅构造器的作用： 创建实例对象的时候可以多给几个属性赋值
    s4.say("hi")

  }
}


/**
  * 正常构造器应该如下
  */

// 主构造器
class S3b(val name: String, val sex: String, val age: Int, private val salary: Int = 100) {

  var b_name: String = _

  // 附构造器
  def this(name: String, sex: String, age: Int, salary: Int, b_name: String) {
    this(name, sex, age, salary) // 附属构造器 第一行代码 必须要调用 主构造器 或者 其他附属构造器
    this.b_name = b_name

  }
}



