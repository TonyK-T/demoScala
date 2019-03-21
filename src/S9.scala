/**
  * 文件操作
  */

import java.io.{File, PrintWriter}

import scala.io.Source

class S9 {

  def m1(): Unit = {
    val file = Source.fromFile("src/a.txt", "UTF-8")   // fromURL | fromString
    //file.getLines().foreach(println(_)) // 返回Iterator遍历
    //file.toArray.foreach(println(_)) // toArray | toBuffer
    println(file.mkString) // 字符串
    file.close()
  }

  def m2(): Unit ={
    // 写入文本借助java PrintWriter
    val out = new PrintWriter("src/a.txt")
    for(i<- Array(1,2,3,4)){
      out.write(i)
    }
    out.close()
  }

}

object S9 {
  def main(args: Array[String]): Unit = {

    val s9 = new S9()
    s9.m1()
    s9.m2()

  }

}
