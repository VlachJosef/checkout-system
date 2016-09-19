package com.foldright

package checkout

import cats.{ Monoid, Show }
import cats.implicits._

sealed trait Fruit {
  def price: BigDecimal = this match {
    case Apple => 60
    case Orange => 25
  }
}
case object Apple extends Fruit
case object Orange extends Fruit

case class Checked(sum: BigDecimal, map: Map[Fruit, Int]) {
  private def calculatePrice(f: Fruit, buy: Int, free: Int): BigDecimal = {
    val size = map.getOrElse(f, 0)
    ((size % buy) + ((size / buy) * free)) * f.price
  }

  def totalSum: BigDecimal = {
    List((Apple, 2, 1), (Orange, 3, 2)).map { case (f, buy, free) => calculatePrice(f, buy, free) }.sum
  }
}

object Checked {
  implicit val m = new Monoid[Checked] {
    def combine(c1: Checked, c2: Checked): Checked = Checked(c1.sum + c2.sum, c1.map.combine(c2.map))
    def empty: Checked = Checked(0, Map.empty[Fruit, Int])
  }
}

object CheckoutSystem {

  def toChecked(f: Fruit): Checked = Checked(f.price, Map(f -> 1))

  def checkout(fruits: List[Fruit]): BigDecimal = {
    Monoid[Checked].combineAll(fruits.map(toChecked)).totalSum
  }
}

package object convertors {
  implicit val bigDecimal = new Show[BigDecimal] {
    def show(f: BigDecimal): String = "£" + (f / 100.0)
  }
}
