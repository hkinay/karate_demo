package performanceRunners

import com.intuit.karate.gatling.PreDef.{karateFeature, karateProtocol}
import io.gatling.core.Predef.{Simulation, constantUsersPerSec, openInjectionProfileFactory, scenario}

import scala.concurrent.duration.{Duration, SECONDS}

// run command line: mvn clean test-compile gatling:test -Dgatling.simulationClass=performanceRunners.ConstantApiSimulation -DconstantUser=5 -DconstantDuration=10
// constantUser(5) constantUsersPerSec(60) during (600 seconds)- 10 dakika boyunca her saniye 60 kullanıcıya enjekte eder.
class ConstantApiSimulation extends Simulation {

  val protocol = {
    karateProtocol()
  }

  val constantDuration = System.getProperty("constantDuration")
  val constantUser = System.getProperty("constantUser")
  val servicePath = System.getProperty("servicePath")
  val serviceName = System.getProperty("serviceName")
  val featureName = System.getProperty("featureName")
  val tagName = System.getProperty("tagName")
  val getTest = scenario(featureName).exec(karateFeature("classpath:features/" + servicePath + "/" + serviceName + "/" + featureName + ".feature@" + tagName + ""))

  setUp(
    getTest.inject(constantUsersPerSec(constantUser.toInt) during Duration(constantDuration.toInt, SECONDS)).protocols(protocol)
  )

}
