/** 泛型
  * 上下边界
  * 逆变和协变-略
  */


// 泛型方法
class Chooser{
  def choose[T<:Comparable[T]](first:T,second:T)={   // Comparable 比较类; 上界：T必须是Comparable类或其子类
      if(first.compareTo(second) >  0) first else second
  }
}

// 泛型类
class LowIQPerson[T<: Comparable[T]]{
  def choose(one:T,two:T)={                      // 上界：T 必须是Comparable类或其子类
    if(one.compareTo(two) <0 ) one else two
  }

}


class Boy(val name:String,val IQ:Int) extends Comparable[Boy]{
  override def compareTo(o: Boy): Int = {                 // 重写Comparable类的方法
    this.IQ-o.IQ
  }
}




class Person12(val name:String){

  def say(msg:String="hello"): Unit ={
    println(s"$msg my name is "+name)
  }

  def mk_friend(p:Person12)={
    say()
    p.say(msg = "hi")
  }
}

class Student12(name:String) extends Person12(name:String)

class Dog(val name:String)


class Party[T <% Person12](p1:Person12,p2:Person12){    //  上界, T必须是 Person12本身或其子类
  p1.mk_friend(p2)
}


class GranderFather
class Father extends GranderFather
class Son extends Father
class BadMan
class Home{
  def go[T >: Son](p:T)={       // 下界，T 必须大于等于Son(既Son本身及Son父类)
    println("i can go home")
  }

}



object S12 {
  def main(args: Array[String]): Unit = {

    println("泛型")
    val chooser = new Chooser
    val boy1 = new Boy("小明",85)
    val boy2 = new Boy("爱因斯坦",120)
    val the_boy = chooser.choose(boy1,boy2)
    println("HIQ_name: "+the_boy.name+"\nIQ: "+the_boy.IQ)

    val lowIQPerson = new LowIQPerson[Boy]
    val low_boy = lowIQPerson.choose(boy1,boy2)
    println("LIQ_name: "+low_boy.name+"\nIQ: "+the_boy.IQ)



    println("上边距")
    implicit def dog2Person(dog: Dog) = new Person12(dog.name)  // 隐式转换
    val p1 = new Person12("jock")
    val s1 = new Student12(name = "rose")
    new Party[Person12](p1,s1)

    val dog = new Dog("dog")
    new Party[Person12](dog,s1)

    println("--下边界--")
    val home = new Home()
    home.go[GranderFather](new GranderFather)
    home.go[Father](new Father)
    home.go[GranderFather](new Son)
    // home.go[GranderFather](new BadMan)   // 不符合边界报错


  }

}