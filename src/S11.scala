/**
  * 隐式转换
  */


class Man(val name: String)

class SuperMan {
  def fly(name: String) = {
    println(s"$name can fly")
  }
}


class SpecialPerson(var name: String)

class Children(var name: String)

class OldMan(var name: String)

class BigMan(val name: String)


class Ticket {
  def buy(p: SpecialPerson) = {
    println(p.name+" can buy ticket")
  }
}

object MyImplicit {
  implicit def childer2SpecialPerson(c: Children) = new SpecialPerson(c.name)

  implicit def oldMan2SpecialPerson(o: OldMan) = new SpecialPerson(o.name)
}


object S11 {

  // Man --> SuperMan
  implicit def man2SuperMan(man: Man) = new SuperMan

  def main(args: Array[String]): Unit = {

    val man = new Man("jock")
    man.fly(man.name)

    import MyImplicit._

    val children = new Children("c_childer")
    val oldMan = new OldMan("o_childer")
    val ticket = new Ticket()
    ticket.buy(children)
    ticket.buy(oldMan)



  }


}