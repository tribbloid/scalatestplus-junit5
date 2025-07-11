//package org.scalatestplus.junit5
//
//import org.scalatest.events.{Event, TestSucceeded}
//import org.scalatest.{BeforeAndAfterAll, Reporter, funspec}
//
//import scala.collection.mutable.ListBuffer
//
////object CustomReporter {
////  val testSucceededList = new ListBuffer[String]
////}
//
////class CustomReporter extends Reporter {
////  def apply(event: Event): Unit = {
////    event match {
////      case e: TestSucceeded => CustomReporter.testSucceededList.append(e.testName)
////      case _                =>
////    }
////  }
////}
//
//class ScalaTestEngineIT extends funspec.AnyFunSpec with BeforeAndAfterAll {
//
//  var scalaTestEngineProperty: Option[String] = None
//
//  override def beforeAll(): Unit = {
//    scalaTestEngineProperty = Option(System.clearProperty("org.scalatestplus.junit5.ScalaTestEngine.disabled"))
////    CustomReporter.testSucceededList.clear()
//  }
//
//  override def afterAll(): Unit = {
//    scalaTestEngineProperty.foreach(System.setProperty("org.scalatestplus.junit5.ScalaTestEngine.disabled", _))
//  }
//
//  it("should discover and run tests using ScalaTest runner") {
//    org.scalatest.tools.Runner.run(
//      Array(
//        "-R",
//        "src/test/scala/org/scalatestplus/junit5/integration",
//        "-oF",
//        "org.scalatestplus.junit5.CustomReporter"
//      )
//    )
//
//    val expected = List(
//      "b",
//      "a",
//      "a"
//    )
//
//    import org.scalatest.matchers.should.Matchers._
////    CustomReporter.testSucceededList.toList should contain theSameElementsAs expected
//
//    println("Executed test IDs:")
////    CustomReporter.testSucceededList.foreach(println)
//  }
//}
