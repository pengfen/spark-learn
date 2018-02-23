package scala.basic

object MetaDemo {

}
//scala> val func1 = (x: Int, y: Double) => (y, x)
//func1: (Int, Double) => (Double, Int) = <function2>
//
//scala> func1(2, 3.0)
//res0: (Double, Int) = (3.0,2)
//
//scala> val func2:(Int, Double) => (Double, Int) = {
//| (a, b) => (b, a)
//| }
//func2: (Int, Double) => (Double, Int) = <function2>
//
//scala> func2(2, 3.0)
//res1: (Double, Int) = (3.0,2)

// _ 将方法转换成下划线
//scala> def m2(x: Int, y: Int): Int = x + y
//m2: (x: Int, y: Int)Int
//
//scala> val f2 = (a: Int, b: Int) => a + b
//f2: (Int, Int) => Int = <function2>
//
//scala> val f2 = m2 _
//f2: (Int, Int) => Int = <function2>
//
//scala> f2(1, 2)
//res2: Int = 3
//
//scala> m2(1, 3)
//res3: Int = 4

