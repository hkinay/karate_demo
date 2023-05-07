package performanceRunners

import com.intuit.karate.gatling.PreDef.{karateFeature, karateProtocol}
import io.gatling.core.Predef.{Simulation, constantUsersPerSec, openInjectionProfileFactory, rampUsers, scenario}

import scala.concurrent.duration.{Duration, SECONDS}

// run command line: mvn test-compile gatling:test -Dgatling.simulationClass=performanceRunners.APISimulation -D userCount=5 -D duration=10 -D constantDuration= 10 -D constantUser= 5
class APISimulation extends Simulation{


  val protocol = {
    karateProtocol()
  }
  // val csvFeed = csv("csvdata.csv").circular
  val userCount = System.getProperty("userCount")
  val duration = System.getProperty("duration")
  val constantDuration = System.getProperty("constantDuration")
  val constantUser = System.getProperty("constantUser")
  // val getConsumption = scenario("exampleScenario").feed(csvFeed).exec(karateFeature("classpath:features/example.feature@name=load"))
  val getConsumption = scenario("contractDepth").exec(karateFeature("classpath:features/gunici-trading-service/contract-controller/contractDepth.feature@name=load"))

  setUp(
    getConsumption.inject(rampUsers(userCount.toInt) during Duration(duration.toInt, SECONDS),
      constantUsersPerSec(constantUser.toInt) during Duration(constantDuration.toInt, SECONDS)).protocols(protocol)
  )

}
