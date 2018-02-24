package scala.basic

/**
  * scala 变量
  *
  * 注意 能用val的地方就用val
  */
object VariDemo {
  def main(args: Array[String]): Unit = {
    // 使用val定义的变量值是不可变的 相当于java里面final修饰的变量
    val i = 1

    // 使用var定义的变量是可变 在scala中鼓励使用val
    var s = "welcome"

    // scala 编译器会自动推断变量的类型 必要的时候可以指定类型
    // 变量名在前 类型在后
    val str : String = "to scala world"

    println(i + " " + s + " " + str); // 1 welcome to scala world
  }
}

//scala> val i = 1
//i: Int = 1
// var定义的变量可以改变值
//scala> var j = 1
//j: Int = 1
//
//scala> j = 2
//j: Int = 2
// 定义时可以强制定义类型
//scala> val str : String = "welcome"
//str: String = welcome
//
//scala> val str1 = "welcome"
//str1: String = welcome
