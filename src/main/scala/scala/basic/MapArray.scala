package scala.basic

object MapArray {
  def main(args: Array[String]): Unit = {
    // 定义一个数组
    val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9);
    // 将偶数取出乘以 10 后再生成一个新的数组
    val res = for (e <- arr if e % 2 == 0) yield e * 10
    println(res.toBuffer)

    // 更高级的写法 用着更爽
    // filter是过滤　接收一个返回值为boolean的函数
    // map相当于将数组中的每一个元素取出来　应用传进去的函数
    val r = arr.filter(_ % 2 == 0).map(_ * 10)
    println(r.toBuffer)
  }
}
//scala> val a = Array(1, 2, 3, 4, 5)
//a: Array[Int] = Array(1, 2, 3, 4, 5)
//
//scala> for (i <- a) yield i * 10
//res0: Array[Int] = Array(10, 20, 30, 40, 50)
//
//scala> a
//res1: Array[Int] = Array(1, 2, 3, 4, 5)
//
//scala> a.map((x:Int) => x * 10)
//res2: Array[Int] = Array(10, 20, 30, 40, 50)
//
//scala> a
//res3: Array[Int] = Array(1, 2, 3, 4, 5)
//
//scala> a.map(x => x * 10)
//res4: Array[Int] = Array(10, 20, 30, 40, 50)
//
//scala> a.map(_ * 10)
//res5: Array[Int] = Array(10, 20, 30, 40, 50)

//scala> val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
//arr: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
// 求数组中的偶数
//scala> arr.filter((x: Int) => x % 2 == 0)
//res6: Array[Int] = Array(2, 4, 6, 8)
//
//scala> arr.filter(x => x % 2 == 0)
//res7: Array[Int] = Array(2, 4, 6, 8)
//
//scala> arr.filter(_ % 2 == 0)
//res8: Array[Int] = Array(2, 4, 6, 8)
//
//scala> arr.filter(_ % 2 == 0).map(_ * 10)
//res9: Array[Int] = Array(20, 40, 60, 80)

//scala> val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
//arr: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
// 求和
//scala> arr.sum
//res10: Int = 45
