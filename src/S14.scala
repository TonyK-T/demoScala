import scala.collection.mutable.ListBuffer

object S14 {
  /** *
    * 经典算法
    * from : https://blog.csdn.net/wsong_14/article/details/78218944
    *
    * @param args
    */

  def main(args: Array[String]): Unit = {
    // Rabbit.func()
    //    Rabbit.func2()
    //    BuyChicken
    //    PrimeNumber
    //    NarcissusNumber
    //    ResolvePrimeFactor
    //    CommonDiviser
    SameNumberSum
    WangShu
    FreeFall
    NumberRangeNotDuplicate

  }
}


object Rabbit {
  /**
    * 题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
    * 1.程序分析： 兔子的规律为数列1,1,2,3,5,8,13,21
    */

  def func() = {

    var (f, f1, f2) = (1L, 1L, 1L)
    val month = 12L
    for (i <- 1L to month) {
      if (i < 3) {
        f2 = 1
      } else {
        f = f1 + f2;
        f1 = f2;
        f2 = f
      }

      println(s"第$i 个月Rabbit个数：" + f)
    }

  }

  def func2() = {
    def fun(x: Long): Long = {
      if (x == 1 || x == 2) return 1L else fun(x - 1) + fun(x - 2)
    }

    (1L to 12L).map {
      x => println(s"第$x 个月Rabbit个数：" + fun(x))
    }

  }


}


object BuyChicken {
  /**
    * 公鸡3元一只,母鸡2元一只,小鸡1元3只.有100元要买100只鸡,必须花光钱,怎样买
    */
  for (x <- 1 to 100) {
    for (y <- 1 to 100) {
      for (z <- 1 to 100) {
        if ((x + y + 3 * z == 100) && (3 * x + 2 * y + z == 100)) {
          println(s"公鸡：$x ,母鸡：$y,小鸡：$z")

        }
      }
    }
  }

}


object PrimeNumber {
  /**
    * 判断101-200之间有多少个素数，并输出所有素数。
    * * 1.程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，
    * * 则表明此数不是素数，反之是素数
    */
  def isPrime(p: Int): Boolean = {
    for (i <- 2 to Math.sqrt(p).toInt) {
      if (p % i == 0) return false
    }
    true
  }

  val primeList = for (x <- (101 to 200) if isPrime(x)) yield x
  println("101到200之间的素数个数为：%s个".format(primeList.length))
  println("素数列表为：" + primeList.mkString(","))


}


object NarcissusNumber {
  /**
    * 题目：打印出所有的"水仙花数(narcissus number)"，所谓"水仙花数"是指一个三位数，
    * * 其各位数字的立方的和等于该数本身。例如：153是一个"水仙花数"，因为153=1的三次方＋5的三次方＋3的三次方
    *
    * @param x
    * @return
    */
  def isNarcissusNumber(x: Int): Boolean = {
    val f: Int = x / 100
    val s: Int = x % 100 / 10
    val t: Int = x % 100 % 10
    if (f * f * f + s * s * s + t * t * t == x) true else false
  }

  println("所有水仙花数列表为:" + (100 to 999).filter(isNarcissusNumber).mkString(","))
}


object ResolvePrimeFactor {
  /**
    *  * 题目：将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
    *  * 程序分析：对n进行分解质因数，应先找到一个最小的质数k，然后按下述步骤完成：
    *  * (1)如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。
    *  * (2)如果k<n，但n能被k整除，则应打印出k的值，并用n除以k的商,作为新的正整数n,重复执行第一步。
    *  * (3)如果n不能被k整除，则用k+1作为k的值,重复执行第一步。
    */
  var boolean = true
  var k = 2
  val primeListBuffer = ListBuffer[Int]()


  def findPrimeFactor(n: Int): Unit = {
    while (k <= n && boolean) {
      n match {
        case n if (n == k) => primeListBuffer.append(k)
          boolean = false
        case n if (n > k && n % k == 0) =>
          primeListBuffer.append(k)
          findPrimeFactor(n / k)
        case n if (n > k && n % k != 0) =>
          k += 1
          findPrimeFactor(n)
      }
    }
  }

  findPrimeFactor(90)
  println("90=" + primeListBuffer.mkString("*"))

}

object CommonDiviser {

  val a = 6
  val b = 3
  val MaxCommonDiviser = getCommonDiviser(a, b)
  println("的最大公约数为:" + MaxCommonDiviser)
  println("的最小公倍数为:" + (a * b) / MaxCommonDiviser)

  def getCommonDiviser(left: Int, right: Int): Int = {
    var (l, r, result, tmp) = (left, right, 0, 0)
    if (l < r) {
      tmp = l;
      l = r;
      r = tmp
    }
    while (r > 0) {
      tmp = l % r
      if (tmp != 0) {
        l = r
        r = tmp
      } else {
        result = r
        r = 0
      }
    }
    result
  }
}

object SameNumberSum {
  /**
    * * 题目：求s=a+aa+aaa+aaaa+aa...a的值，其中a是一个数字。例如2+22+222+2222+22222(此时共有5个数相加)，几个数相加有键盘控制。
    * * 算法： 定义一个变量b， 赋初值为0；定义一变量sum， 赋初值为0，
    * * 进入循环后，将a + b 的值赋给b，将sum + b 的值赋给sum；
    * * 同时，将a 增加十倍， ++ i； 继续循环；
    * * 循环结束后，输出sum 的值。
    */

  val args = List(2, 5)
  args.length match {
    case 2 =>
      val total = dataHandle(args(0), args(1))
      println("=" + total)
    case _ =>
      println("请输入两个参数值！！")
      sys.exit(-1)
  }

  def dataHandle(number: Int, num: Int): Int = {
    var total = 0
    var b = 0
    for (i <- 1 to num) {
      b += number
      i match {
        case 5 => print(b)
        case _ => print(b + "+")
      }
      total += b
      b *= 10
    }
    total
  }
}


object WangShu {
  /**
    * 题目：一个数如果恰好等于它的因子之和，这个数就称为 "完数 "。例如6=1＋2＋3.编程 找出1000以内的所有完数。
    */
  println("1到1000的完数有：")
  val iList = for (i <- 1 to 1000 if isWangShu(i)) yield i
  println(iList.mkString(""))

  def isWangShu(i: Int): Boolean = {
    var num = 0
    for (j <- 1 to i / 2) {
      if (i % j == 0) num += j
    }
    if (i == num) true else false
  }
}


object FreeFall {
  /**
    * 题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，
    * 求它在 第10次落地时，共经过多少米？第10次反弹多高？
    */
  var distance: Double = 0D
  var high: Double = 100D
  for (i <- 1 to 10) {
    i match {
      case 1 => distance += high
      case _ => distance += high * 2
    }
    high /= 2
  }
  println("distance=" + distance)
  println("high=" + high)
}


object NumberRangeNotDuplicate {
  /**
    * 题目：有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
    * * 1.程序分析：可填在百位、十位、个位的数字都是1、2、3、4。组成所有的排列后再去 掉不满足条件的排列。
    */
  val iListBuffer = ListBuffer[Int]()
  for (i <- 1 to 4) {
    for (j <- 1 to 4) {
      for (k <- 1 to 4) {
        if (i != j && j != k && i != k) iListBuffer += i * 100 + j * 10 + k
      }
    }
  }
  println("能组成互不相同且无重复数字的三位数有：" + iListBuffer.length)
  iListBuffer.foreach(println)
}