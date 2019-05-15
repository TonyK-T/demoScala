package N1

import java.util

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.util.Random

class N1 {


  def xj(string: String): Long = {
    var result = 1L
    for (s <- string) result *= s
    result
  }

  def xj2(string: String): Long = {
    var result = 1L
    string.foreach(result *= _)
    result
  }

  /**
    * 递归
    */

  def dg(string: String): String = {
    var result = ""
    if (string.length == 1) string
    else string.head + dg(string.drop(1))
  }


  // 随机
  def random(n: Int): Array[Int] = {
    val arr = new Array[Int](n)

    for (a <- arr) yield Random.nextInt(n)

  }

  // arr 换位
  def exchange(array: Array[Int]) = {
    for (i <- 0 until array.length) {
      val t = array(i)
      array(i) = array(i + 1)
      array(i + 1) = t

    }
    array.foreach(println)
    array
  }

  // arr反转
  def reverse_arr(array: Array[Int]) = {
    array.reverse
  }

  def reverse_arr2(array: Array[Int]) = {
    for (i <- 0 until array.length / 2) {
      val tem = array(i)
      array(i) = array(array.length - 1 - i)
      array(array.length - 1 - i) = tem
    }
    array
  }

  // arr去重
  def distance_arr(array: Array[Int]) = {
    val arr2 = ArrayBuffer[Int]()
    arr2 ++= array.distinct
    arr2.toArray
  }


  // Source.fromfile()
  def read_file(file_path: String) = {
    val file = Source.fromFile(name = file_path).mkString
    val tokens = file.split(",")
    val hmap = new mutable.HashMap[String, Int]()
    for (token <- tokens) {
      hmap(token) = hmap.getOrElse(token, 0) + 1 //  getOrElse 来加入新的元素或 以有元素加1
    }

    var map = mutable.Map[String, Int]()
    for (token <- tokens) {
      map += (token -> (map.getOrElse(token, 0) + 1)) // 用 += 来获取包含新更新的“新映射”
    }

  }


}
