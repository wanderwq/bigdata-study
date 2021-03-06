package pers.nebo.scala.demo

/**
  * @ author fnb
  * @ email nebofeng@gmail.com
  * @ date  2019/11/20
  * @ des :
  */

/**
  * 偏函数，只能匹配一个值，匹配上了返回某个值
  * PartialFunction[A,B]  A是匹配的类型，B是匹配上返回的类型
  */
object Lession_PartialFun {
  def MyTest :PartialFunction[String,Int] ={
    case "abc"=>2
    case "a" => 1
    case _ => 200
  }
  def main(args: Array[String]): Unit = {
    val result: Int = MyTest("abcd")
    println(result)
  }
}
