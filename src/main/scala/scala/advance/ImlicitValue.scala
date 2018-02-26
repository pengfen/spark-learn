package scala.advance

/**
  * 隐式转换
  *
  * 隐式转换和隐式参数是scala中两个非常强大的功能　利用隐式转换和隐式参数 可以提供优雅的类库　对类库的使用隐匿掉那些枯燥乏味的结节
  *
  * 作用 隐式的对类的方法进行增强　丰富现有类库的功能
  *
  * 以implicit关键字声明的带有单个参数的函数
  *
  *
  * 所有的隐式值和隐式方法必须放到object
  */
object Context {
  implicit val aaa = "ricky"
}

object ImlicitValue {
  def sayWel()(implicit name: String = "caopeng"): Unit = {
    println(s"welcome $name")
  }

  def main(args: Array[String]): Unit = {
    import Context._
    sayWel() // 不加隐式转换 welcome caopeng  ---> 添加隐式转换 welcome ricky
  }
}
