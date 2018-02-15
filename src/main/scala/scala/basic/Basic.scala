package scala.basic

object Basic {
  def main(args: Array[String]): Unit = {
    // 使用val定义的变量值是不可变的 相当于java里面final修饰的变量
    val i = 1

    // 使用var定义的变量是可变 在scala中鼓励使用val
    var s = "hello"

    // scala 编译器会自动推断变量的类型 必要的时候可以指定类型
    // 变量名在前 类型在后
    val str:String = "welcome"

    // Scala和Java一样 有七种数值类型Byte Char Short Int Long Float Double

    // 条件表达式
    val x = 1
    // 判断x的值 将结果赋给y
    val y = if (x > 0) 1 else -1
    // 打印y的值
    println(y) // 1

    // 支持混合类型表达式
    val z = if (x > 1) 1 else "error"
    println(z) // error

    // 如果缺失else 相当于 if(x > 2) 1 else ()
    val m = if (x > 2) 1
    println(m) // ()

    // 在scala中每个表达式都有值 scala中有个Unit类 写做() 相当于java中的void
    val n = if (x > 2) 1 else ()
    println(n) // ()

    // if 和 else if
    val k = if (x < 0) 0
    else if (x >= 1) 1 else -1
    println(k) // 1

    // 块表达式
    val x0 = 0
    // 在scala中{}包含一系列表达式 块中最后一个表达式的值就是块的值
    // 下面就是一个块表达式
    val result = {
      if (x0 < 0) {
        -1
      } else if (x0 >= 1) {
        1
      } else {
        "error"
      }
    }
    // result的值就是块表达式的结果
    println(result) // error



    // 循环
    // for(i <- 表达式）表达式 1 to 10 返回一个Range(区间)
    // 每次循环将区间中的一个值赋给i
    for (i <- 1 to 10)
      println(i)

    // for (i <- 数组)
//    val arr = Array("a", "b", "c")
//    for (i <- arr)
//      println(i) // a b c
//
//    // 高级for循环
//    // 每个生成器都可以带一个条件 注意 if前面没有分号
//    for (i <- 1 to 3; j <- 1 to 3 if i != j)
//      print((10 * i + j) + " ")
//    println() // 12 13 21 23 31 32
//
//    // for 推导式 如果for循环的循环体以yield开始 则该循环会构建出一个集合
//    // 每次迭代生成集合中的一个值
//    val v = for (i <- 1 to 10) yield i * 10
//    println(v) // Vector(10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
  }

}
