package scala.basic

import java.util

import scala.collection.immutable.HashSet.HashSet1
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Collect {

  def main(args: Array[String]): Unit = {
    // 创建一个不可变的集合
    val lst1 = List(1, 2, 3)
    // 将0插入到lst1的前面生成一个新的List
    val lst2 = 0::lst1
    val lst3 = lst1.::(0)
    val lst4 = 0 +: lst1
    val lst5 = lst1 .+:(0)

    // 将一个元素添加到lst1的后面产生一个新的集合
    val lst6 = lst1 :+ 3

    val lst0 = List(4, 5, 6)
    // 将2个list合并成一个新list
    val lst7 = lst1 ++ lst0
    // 将lst1插入到lst0前面生成一个新的集合
    val lst8 = lst1 ++: lst0
    // 将lst0插入到lst1前面生成一个新的集合
    val lst9 = lst1.:::(lst0)

    println(lst9)
  }
}

object Demo extends App {
  // 构建一个可变列表 初始有三个元素 1,2,3
  val lst0 = ListBuffer[Int](1, 2, 3)
  // 创建一个空的可变列表
  val lst1 = new ListBuffer[Int]
  // 向lst1中追加元素 注意 没有生成新的集合
  lst1 += 4
  lst1.append(5)

  // 将 lst1 中的元素最近到lst0中 注意 没有生成新的集合
  lst0 ++= lst1

  // 将 lst0 和 lst1 合并成一个新的ListBuffer 注意 生成了一集合
  val lst2 = lst0 ++ lst1

  // 将元素追加到lst0的后右生成一个新的集合
  val lst3 = lst0 :+ 5
}

object ImmutSetDemo extends App {

  val set1 = new scala.collection.immutable.HashSet[Int]()
  // 将元素和set1 合并生成一个新的set 原有set不变
  val set2 = set1 + 4
  // set 中元素不能重复
  val set3 = set1 ++ Set(5, 6, 7)
  val set0 = Set(1, 3, 4)++ set1
  println(set0.getClass())
}

object MutSetDemo extends App {
  // 创建一个可变的HashSet
  val set1 = new mutable.HashSet[Int]()
  // 向HashSet中添加元素
  set1 += 2
  // add 等价于 +=
  set1 ++= Set(1, 3, 5)
  println(set1)

  // 删除一个元素
  set1 -= 5
  set1.remove(2)
  println(set1)
}

object MutMapDemo extends App {

  val map1 = new mutable.HashMap[String, Int]()
  // 向 map 中添加数据
  map1("spark") = 1
  map1 += (("hadoop", 2))
  map1.put("storm", 3)
  println(map1)

  // 从 map 中移除元素
  map1 -= "spark"
  map1.remove("hadoop")
  println(map1)
}
