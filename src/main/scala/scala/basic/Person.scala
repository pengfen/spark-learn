package scala.basic

/**
  * 在 scala 中 类并不用声明为public
  *
  * scala 源文件中可以包含多个类 所有这些类都具有公有可见性
  *
  * 包可见性 private[scala] class Person ---> scala 包下可见
  *
  * 私有构造器 (只有伴生对象可以访问) class Person privete {}
  */
class Person {
  // 用val修饰的变量是只读属性 有getter但没有setter (相当与java中用final修饰的变量)
  val id = "66688" // val 修饰的只有get方法 (getId)
  // 用var修饰的变量既有getter又有setter ---> 类私有字段 只能在类的内部使用
  var name = "caopeng" // var 修饰的只有get方法和set方法 (getName setName)
  private var gender: String = "male" // 只能在伴生对象中使用
  private[this] var pop: String = _ // (_表示未初始化)

  def printPop: Unit = {
    println(pop)
  }

  // 对象私有字段 访问权限更加严格的 Person类的方法只能访问到当前对象的字段
  private[this] val pet = "小强"
}

// 伴生对象
object Person {
  def main(args: Array[String]): Unit = {
    val p = new Person
    println(p.id + " " + p.name)

    p.name = "ricky"
    println(p.id + " " + p.name)

    println(p.gender)
    p.gender = "男"
    println(p.gender)

    p.printPop
  }
}
