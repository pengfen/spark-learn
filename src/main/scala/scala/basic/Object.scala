package scala.basic

import scala.collection.mutable.ArrayBuffer
import scala.sys.process.processInternal.IOException

object Object {

}

// 在 scala 中 类并不用声明为public
// scala 源文件中可以包含多个类 所有这些类都具有公有可见性
class Person {
  // 用val修饰的变量是只读属性 有getter但没有setter
  // (相当与java中用final修饰的变量)
  val id = "9527"
  // 用var修饰的变量既有getter又有setter
  val age:Int = 10
  // 类私有字段 只能在类的内部使用
  private var name: String = "你好"

  // 对象私有字段 访问权限更加严格的 Person类的方法只能访问到当前对象的字段
  private[this] val pet = "小强"
}

// 每个类都有主构造器 主构造器的参数直接放置类名后面 与类交织在一起
class Student(val name: String, val age: Int) {
  // 主构造器会执行类定义中的所有语句
  println("执行主构造器")

  try {
    println("读取文件")
    throw new IOException("io exception")
  } catch {
    case e:NullPointerException => println("打印异常Exception:" + e)
    case e:IOException => println("打印异常Exception:" + e)
  } finally {
    println("执行finall部分")
  }

  private var gender = "male"
  // 用this关键字定义辅助构造器
  def this(name: String, age: Int, gender: String) {
    // 每个辅助构造器必须以主构造器或其他的辅助构造器的调用开始
    this(name, age)
    println("执行辅助构造器")
    this.gender = gender
  }
}

/**
  *构造器参数可以不带val或var，如果不带val或var的参数至少被一个方法所使用，
  *那么它将会被提升为字段
  */
//在类名后面加private就变成了私有的
class Queen private(val name: String, prop: Array[String], private var age: Int = 18){

  println(prop.size)

  //prop被下面的方法使用后，prop就变成了不可变得对象私有字段，等同于private[this] val prop
  //如果没有被方法使用该参数将不被保存为字段，仅仅是一个可以被主构造器中的代码访问的普通参数
  def description = name + " is " + age + " years old with " + prop.toBuffer
}

object Queen{
  def main(args: Array[String]) {
    //私有的构造器，只有在其伴生对象中使用
    val q = new Queen("hatano", Array("蜡烛", "皮鞭"), 20)
    println(q.description())
  }
}

object SingletonDemo {
  def main(args: Array[String]) {
    //单例对象，不需要new，用【类名.方法】调用对象中的方法
    val session = SessionFactory.getSession()
    println(session)
  }
}

object SessionFactory{
  //该部分相当于java中的静态块
  var counts = 5
  val sessions = new ArrayBuffer[Session]()
  while(counts > 0){
    sessions += new Session
    counts -= 1
  }

  //在object中的方法相当于java中的静态方法
  def getSession(): Session ={
    sessions.remove(0)
  }
}

class Session {}


class Dog {
  val id = 1
  private var name = "itcast"

  def printName(): Unit ={
    //在Dog类中可以访问伴生对象Dog的私有属性
    println(Dog.CONSTANT + name )
  }
}

/**
  * 伴生对象
  */
object Dog {

  //伴生对象中的私有属性
  private val CONSTANT = "汪汪汪 : "

  def main(args: Array[String]) {
    val p = new Dog
    //访问私有的字段name
    p.name = "123"
    p.printName()
  }
}


object ApplyDemo {
  def main(args: Array[String]) {
    //调用了Array伴生对象的apply方法
    //def apply(x: Int, xs: Int*): Array[Int]
    //arr1中只有一个元素5
    val arr1 = Array(5)
    println(arr1.toBuffer)

    //new了一个长度为5的array，数组里面包含5个null
    var arr2 = new Array(5)
  }
}


object AppObjectDemo extends App{
  //不用写main方法
  println("I love you Scala")
}


object ClazzDemo {
  def main(args: Array[String]) {
    //val h = new Human
    //println(h.fight)
  }
}

trait Flyable{
  def fly(): Unit ={
    println("I can fly")
  }

  def fight(): String
}


abstract class Animal {
  def run(): Int
  val name: String
}

class Human extends Animal with Flyable{

  val name = "abc"

  //打印几次"ABC"?
  val t1,t2,(a, b, c) = {
    println("ABC")
    (1,2,3)
  }

  println(a)
  println(t1._1)

  //在Scala中重写一个非抽象方法必须用override修饰
  override def fight(): String = {
    "fight with 棒子"
  }

  //在子类中重写超类的抽象方法时，不需要使用override关键字，写了也可以
  def run(): Int = {
    1
  }
}