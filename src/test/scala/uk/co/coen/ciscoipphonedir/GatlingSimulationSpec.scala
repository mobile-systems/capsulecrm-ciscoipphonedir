package uk.co.coen.ciscoipphonedir

import org.scalatest.FlatSpecLike
import io.gatling.core.scenario.Simulation
import io.gatling.core.config.GatlingConfiguration
import io.gatling.core.runner.{Runner, Selection}
import io.gatling.core.assertion.Assertion
import io.gatling.core.result.reader.DataReader
import io.gatling.app.GatlingStatusCodes
import io.gatling.core.config.GatlingConfiguration._

trait GatlingSimulationSpec extends Simulation with FlatSpecLike {
  val props = scala.collection.mutable.Map.empty[String, String]
  for { p <- sys.props if p._1.startsWith("gatling") } yield { props += p }
  GatlingConfiguration.setUp(props)

  val simClass = getClass.asInstanceOf[Class[Simulation]]
  val outputDirectoryName = configuration.core.outputDirectoryBaseName.getOrElse(simClass.getSimpleName)
  var selection = new Selection(simClass, outputDirectoryName, "no description")

  def describe(desc: String) = selection = new Selection(simClass, outputDirectoryName, desc)

  "Simulation" should "complete without assertion failures" in {
    val runOutcome = new Runner(selection).run
    val result = runOutcome._2 match {
      case sim: Simulation if !sim.assertions.isEmpty => {
        if (Assertion.assertThat(sim.assertions, DataReader.newInstance(runOutcome._1))) GatlingStatusCodes.Success
        else GatlingStatusCodes.AssertionsFailed
      }
      case _ => GatlingStatusCodes.Success
    }
    assertResult(GatlingStatusCodes.Success, "simulation has assertion errors")(result)
  }
}