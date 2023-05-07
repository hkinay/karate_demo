package performanceRunners

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

class CDArticleSimulation extends Simulation {

  val protocol = karateProtocol()

 // protocol.nameResolver = (req, ctx) => req.getHeader("karate-name")


  val createArticle = scenario("Create An Article").exec(karateFeature("classpath:features/performanceFeatures/createArticle.feature@load"))

  setUp(
    createArticle.inject(rampUsers(5) during (5.seconds)).protocols(protocol)
  )
  // mvn clean test-compile gatling:test -Dgatling.simulationClass=performanceRunners.CDArticleSimulation
  // mvn clean test-compile gatling:test -DsimulationClass=performanceRunners.CDArticleSimulation



}