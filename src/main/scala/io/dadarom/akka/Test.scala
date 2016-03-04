package io.dadarom.akka

/**
 * Created by leo on 15-12-8.
 */
class Test {

}
class C {
      var acc = 0
      def minc = { acc += 1 }
      val finc = { () => acc += 1 }
    }

class Calculator(brand: String) {
  /**
   * A constructor.
   */
  val color: String = if (brand == "TI") {
    "blue"
  } else if (brand == "HP") {
    "black"
  } else {
    "white"
  }

  // An instance method.
  def add(m: Int, n: Int): Int = m + n
}

class ScientificCalculator(brand: String) extends Calculator(brand) {
  def log(m: Double, base: Double) = math.log(m) / math.log(base)
}

abstract class Shape(i : Int) {
  def getArea():Int    // subclass should define this
}

trait Cache[K, V] {
  def get(key: K): V
  def put(key: K, value: V)
  def delete(key: K)
//  def delete[K](key: K)
  def remove[K](key: K)
//  def remove(key: K)
}

class MyCache extends Cache[String,Int] {
  override def get(key: String): Int = ???

  override def put(key: String, value: Int): Unit = ???

  override def remove[K](key: K): Unit = ???

//  override def remove(key: String): Unit = ???

  override def delete(key: String): Unit = ???

//  override def delete[K](key: K): Unit = ???
}

class Foo {}
class Bar {
     def apply() = 0
   }
object FooMaker {
      def apply() = new Foo
}

object Test {
  def main(args: Array[String]) {

    println(FooMaker())
//    println(new FooMaker)

    val bar = new Bar
    println(bar)
    println(bar())


    val c = new C

    println(c.acc)
    println(c.minc)
    println(c.finc)
    println(c.finc())

    val cache = new MyCache
    cache.delete("1")
//    cache.delete[String]("2")
  }
}
