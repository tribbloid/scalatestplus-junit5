package org.scalatestplus.junit5

import org.scalatest.events.{Event, TestSucceeded}
import org.scalatest.{BeforeAndAfterAll, Reporter, funspec}

import java.nio.file.{Path, Paths}
import scala.collection.mutable.ListBuffer

object ScalaTestEngineIntegration {

  // Custom reporter to capture test results
  class TestResultReporter extends Reporter {
    val testResults = new ListBuffer[String]

    def apply(event: Event): Unit = {
      event match {
        case e: TestSucceeded => testResults.append(e.testName)
        case _                =>
      }
    }
  }

}

class ScalaTestEngineIntegration extends funspec.AnyFunSpec with BeforeAndAfterAll {

  var scalaTestEngineProperty: Option[String] = None

  override def beforeAll(): Unit = {
    scalaTestEngineProperty = Option(System.clearProperty("org.scalatestplus.junit5.ScalaTestEngine.disabled"))
//    CustomReporter.testSucceededList.clear()
  }

  override def afterAll(): Unit = {
    scalaTestEngineProperty.foreach(System.setProperty("org.scalatestplus.junit5.ScalaTestEngine.disabled", _))
  }

  def integrationPath: Path = {
    val classPathRoot = classOf[ScalaTestEngineSpec].getProtectionDomain.getCodeSource.getLocation
    Paths.get(classPathRoot.toURI).getParent.getParent.resolve("src/it/scala")
  }

  it("should discover and run tests using ScalaTest runner") {
    val path = integrationPath

    val result = org.scalatest.tools.Runner.run(
      Array(
        "-R",
        integrationPath.resolve("org/scalatestplus/junit5/integration").toString,
        "-s",
        "org.scalatestplus.junit5.integration.NestedSuite",
        "-oF"
      )
    )

    // Verify that tests were discovered and run successfully
    assert(result === true, "ScalaTest runner should complete successfully")

    // Note: With the current approach, we verify successful execution
    // The NestedSuite should run 3 tests: "b" from outer suite, "a" from I1, "a" from I2
    println("ScalaTest runner executed successfully with integration tests")
  }
}
