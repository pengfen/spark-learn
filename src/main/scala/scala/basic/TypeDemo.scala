package scala.basic

/**
  * Scala和Java一样 有七种数值类型Byte Char Short Int Long Float Double
  *
  * 无包装类型
  *
  * if 判断
  * val a = if (x > 0) 1 else -1
  * // 混合类型
  * val b = if (x > 0) 1 else "error"
  * // ()表示"无有用值"
  * val c = if (x > 0) 1 else ()
  *
  */
object TypeDemo {

  def main(args: Array[String]): Unit = {
    // 条件表达式
    val x = 1
    // 判断x的值 将结果赋给y
    val y = if (x > 0) 1 else -1
    // 打印y的值
    println(y) // 1

    // 支持混合类型表达式 （z有可能是int也可能是string）
    val z = if (x > 1) 1 else "error"
    println(z) // error

    // 如果缺失else 相当于 if(x > 2) 1 else ()
    val m = if (x > 2) 1
    println(m) // ()

    // 在scala中每个表达式都有值 scala中有个Unit类 写做() 相当于java中的void
    val n = if (x > 2) 1 else ()
    println(n) // ()

    // if 和 else if
    val k = if (x < 0) 0 else if (x >= 1) 1 else -1
    println(k) // 1
  }
}

//scala> val x = 10
//x: Int = 10
//
//scala> val y = if (x > 0) 1 else -1
//y: Int = 1
//
//scala> val z = if (x > 1) 1 else "error"
//z: Any = 1
//
//scala> val x = 0
//x: Int = 0
//
//scala> val z = if (x > 1) 1 else "error"
//z: Any = error
// 
//scala> val m = if (x > 2) 1
//m: AnyVal = ()
//
//scala> val k = if (x < 0) 0 else if (x >= 1) 1 else -1
//k: Int = -1
