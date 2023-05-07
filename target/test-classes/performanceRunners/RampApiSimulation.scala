package performanceRunners

import com.intuit.karate.gatling.PreDef.{karateFeature, karateProtocol}
import io.gatling.core.Predef.{Simulation, openInjectionProfileFactory, rampUsers, scenario}

import scala.concurrent.duration.{Duration, SECONDS}

// run command line: mvn clean test-compile gatling:test -Dgatling.simulationClass=performanceRunners.prereconciliation.meter.OsfImportRampApiSimulation -DrampUsersCount=5 -Dduration=10
// rampUsers(5) during(10 seconds)- 10 saniyede 5 kullanıcıya enjekte eder.(10 saniyede servise 5 request atar)

class RampApiSimulation extends Simulation{
  val protocol = {
    karateProtocol()
  }
  val rampUsersCount = System.getProperty("rampUsersCount")
  val duration = System.getProperty("duration")
  val servicePath = System.getProperty("servicePath")
  val serviceName = System.getProperty("serviceName")
  val featureName = System.getProperty("featureName")
  val tagName = System.getProperty("tagName")
  val getTest = scenario(featureName).exec(karateFeature("classpath:features/" + servicePath + "/" + serviceName + "/" + featureName + ".feature@" + tagName + ""))

  setUp(
    getTest.inject(rampUsers(rampUsersCount.toInt) during Duration(duration.toInt, SECONDS)).protocols(protocol)
  )

}
