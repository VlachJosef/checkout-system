package com.foldright

package checkout

import cats.{ Monoid, Show }

case class Checked(sum: BigDecimal)

object Checked {
  implicit val m = new Monoid[Checked] {
    def combine(c1: Checked, c2: Checked): Checked = Checked(c1.sum + c2.sum)
    def empty: Checked = Checked(0)
  }
}

sealed trait Fruit
case object Apple extends Fruit
case object Orange extends Fruit

object CheckoutSystem {

  def price(f: Fruit): BigDecimal = f match {
    case Apple => 60
    case Orange => 25
  }

  def toChecked(f: Fruit): Checked = Checked(price(f))

  def checkout(fruits: List[Fruit]): BigDecimal = {
    Monoid[Checked].combineAll(fruits.map(toChecked)).sum
  }
}

package object convertors {
  implicit val bigDecimal = new Show[BigDecimal] {
    def show(f: BigDecimal): String = "£" + (f / 100.0)
  }
}
