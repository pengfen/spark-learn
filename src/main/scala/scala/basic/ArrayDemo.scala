package scala.basic

import scala.collection.mutable.ArrayBuffer

object ArrayDemo {

  def main(args: Array[String]): Unit = {
    // 1. 定长数组和变长数组 (ArrayBufferDemo)

    // 初始化一个长度为8的定长数组 其所有元素均为0
    val arr1 = new Array[Int](8)
    // 直接打印定长数据 内容为数据的hashcode值
    println(arr1)

    // 将数组转换成数组缓冲 就可以看到原数组中的内容
    // toBuffer 会将数组转换长数组缓冲
    println(arr1.toBuffer)

    // 注意 如果new 相当于调用了数组的apply方法 直接为数组赋值
    // 初始化一个长度为1的定长数组
    val arr2 = Array[Int](10)
     println(arr2.toBuffer)

     //定义一个长度为3的定长数组
    val arr3 = Array("hadoop", "storm", "spark")
    // 使用()来访问元素
    println(arr3(2))

    // 2. 遍历数组 (ForArray)

//    scala> val arr = Array(2, 5, 1, 4, 3)
//    arr: Array[Int] = Array(2, 5, 1, 4, 3)
//
//    scala> arr.sum
//    res1: Int = 15
//
//    scala> arr.max
//    res2: Int = 5
//
//    scala> arr.sorted
//    res3: Array[Int] = Array(1, 2, 3, 4, 5)

//    scala> val scores = Map("tom" -> 85, "jerry" -> 99, "kitty" -> 90) // 使用箭头
//    scores: scala.collection.immutable.Map[String,Int] = Map(tom -> 85, jerry -> 99, kitty -> 90)
//
//    scala> val scores = Map(("tom", 85), ("jerry", 99), ("kitty", 90)) // 使用元组
//    scores: scala.collection.immutable.Map[String,Int] = Map(tom -> 85, jerry -> 99, kitty -> 90)

//    scala> scores("jerry") // 获取映射中的值
//    res4: Int = 99

    // 如果映射中有值 返回映射中的值 没有返回默认值
//    scala> scores.getOrElse("suke", 0)
//    res5: Int = 0
//
//    scala> scores.getOrElse("tom", 0)
//    res6: Int = 85

    // 在Scala中 有两种Map 一个是immutable包下的Map 该Map中的内容不可变 另一个是mutable包下的Map 该Map中的内容可变
//    scala> val scores = Map("tom" -> 80, "jerry" -> 90) // 导入mutable包
//    scores: scala.collection.mutable.Map[String,Int] = Map(tom -> 80, jerry -> 90) // val定义的scores变量意味着引用不变 但是Map中的内容可变
//
//    scala> scores("tom") - 88 // 修改Map中的内容
//    res7: Int = -8
//
//    scala> scores += ("kitty" -> 99, "suke" -> 60) // 用+=向原来的Map中追加元素
//    res9: scores.type = Map(tom -> 80, kitty -> 99, jerry -> 90, suke -> 60)

    // 元素
//    scala> val t = ("hadoop", 3.14, 65535) // 定义元组时用小括号将多个元素包起来 元素之间用逗号分隔 元素的类型可以不一样 元素个数可以任意多个
//    t: (String, Double, Int) = (hadoop,3.14,65535)

//    scala> val t,(a,b,c) = ("hadoop", 3.14, 65535)
//    t: (String, Double, Int) = (hadoop,3.14,65535)
//    a: String = hadoop
//    b: Double = 3.14
//    c: Int = 65535
//
//    scala> val r1 = t._1 // 获取元组中的元素可以使用下划线加脚标 需要注意的是元组中的元素脚标是从1开始的
//    r1: String = hadoop
//
//    scala> val r2 = t._2
//    r2: Double = 3.14

//    scala> val arr = Array(("tom", 88), ("jerry", 95))
//    arr: Array[(String, Int)] = Array((tom,88), (jerry,95))
//
//    scala> arr.toMap // toMap可以将对偶的集合转换成映射
//    res10: scala.collection.immutable.Map[String,Int] = Map(tom -> 88, jerry -> 95)

//    scala> val scores = Map("tom" -> 80, "jerry" -> 90)
//    scores: scala.collection.mutable.Map[String,Int] = Map(tom -> 80, jerry -> 90)
//
//    scala> val names = Array(88, 95, 80)
//    names: Array[Int] = Array(88, 95, 80)
//
//    scala> val ns = names.zip(scores) // 使用zip将对应的值绑定到一起
//    ns: Array[(Int, (String, Int))] = Array((88,(tom,80)), (95,(jerry,90)))
//
//    scala> ns.toMap
//    res11: scala.collection.immutable.Map[Int,(String, Int)] = Map(88 -> (tom,80), 95 -> (jerry,90))

  }
}
//scala> val arr = new Array[Int](10)
//arr: Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
//
//scala> arr(0) = 1
//
//scala> arr(1) = 2
//
//scala> arr
//res6: Array[Int] = Array(1, 2, 0, 0, 0, 0, 0, 0, 0, 0)
//
//scala>
//
//scala> val arr1 = Array(1, 3, 4, 5, 6)
//arr1: Array[Int] = Array(1, 3, 4, 5, 6)

//scala> val arr = new Array[Int](10)
//arr: Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
//scala> val arr1 = Array[Int](10)
//arr1: Array[Int] = Array(10)

