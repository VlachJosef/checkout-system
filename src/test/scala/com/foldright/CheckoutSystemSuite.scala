package com.foldright.checkout

import org.scalatest.{ FlatSpec, Matchers }

class CheckoutSystemSuite extends FlatSpec with Matchers {

  "Checkout System" should "be able to handle checkout of empty List" in {
    val empty = CheckoutSystem.checkout(List.empty[Fruit])
    empty should be(BigDecimal(0L))
  }

  it should "checkout one orange" in {
    val res = CheckoutSystem.checkout(List(Orange))
    res should be(BigDecimal(25L))
  }

  it should "checkout one apple" in {
    val res = CheckoutSystem.checkout(List(Apple))
    res should be(BigDecimal(60L))
  }

  import convertors._
  import cats.syntax.show._

  it should "checkout List of Fruits and print result in pounds" in {
    val fruits = List(Apple, Apple, Orange, Apple)
    val res = CheckoutSystem.checkout(fruits).show
    res should be("£2.05")
  }
}
