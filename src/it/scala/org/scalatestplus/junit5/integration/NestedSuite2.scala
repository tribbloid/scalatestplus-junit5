package org.scalatestplus.junit5.integration

import org.scalatest.Suite
import org.scalatest.funspec.AnyFunSpec

object NestedSuite2 {

  class Inner extends AnyFunSpec {
    it("a") {}
  }

  object I1 extends Inner
  object I2 extends Inner
}
class NestedSuite2 extends AnyFunSpec {

  override def nestedSuites: IndexedSeq[Suite] = {
    println("hit!")

    IndexedSeq(
      NestedSuite.I1,
      NestedSuite.I2
    )
  }

  it("b") {}
}
