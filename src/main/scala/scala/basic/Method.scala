package scala.basic

/**
  * 方法和函数的区别
  *
  * 在函数式编程语言中 函数是 "头等公民" 它可以像任何其他数据类型一样被传递和操作
  * def m(f: (Int, Int) => Int) = f(2,6)
  * def f = (x: Int, y: Int) => x - y
  * m(f)
  */
object Method {

  /**
    * 注意 方法的返回值类型可以不写 编译器可以自动推断出来 但是对于递归函数 必须指定返回值类型
    *
    * def 定义方法使用def关键字
    * me 方法名
    * @param x 参数
    * @param y 参数
    * @return Int 返回值类型
    */
  def me(x: Int, y: Int) : Int = x * y

  // 定义函数
  val fe = (m: Int, n: Int) => m * n


  // 定义一个方法
  // 方法m1参数要求是一个函数 函数的参数必须是两个Int类型
  // 返回值类型是Int类型
  def m1(f: (Int, Int) => Int): Int = {
      f(2,6)
  }

  // 定义一个函数f1 参数是两个Int类型 返回值是一个Int类型
  val f1 = (x: Int, y: Int) => x + y
  // 再定义一个函数
  val f2 = (m: Int, n: Int) => m * n

  def main(args: Array[String]): Unit = {
    // 调用m1方法 并传入f1函数
    val r1 = m1(f1)
    println(r1) // 8

    // 调用m1方法 并传入f2函数
    val r2 = m1(f2)
    println(r2)
  }

  // 神奇的下划线 将方法转换成函数 (隐匿转换)
  val ff = m1 _
}
