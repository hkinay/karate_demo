package performanceRunners

import com.intuit.karate.gatling.PreDef.{karateFeature, karateProtocol}
import io.gatling.core.Predef.{Simulation, atOnceUsers, configuration, csv, openInjectionProfileFactory, scenario}

// run command line: mvn clean test-compile gatling:test -Dgatling.simulationClass=performanceRunners.AtOnceApiSimulation -DusersCount=10
// atOnceUsers(10) - Aynı anda 10 kullanıcıya enjekte eder.(Aynı anda 10 request atar)
class AtOnceApiSimulation extends Simulation{
  val protocol = {
    karateProtocol()
  }
  // Test altındaki resourcesdan okumaktadır.
  val csvFeed = csv("Onayli_Sayac_Verileri_Okunmadi_649.csv").circular
  val usersCount = System.getProperty("usersCount")
  val servicePath = System.getProperty("servicePath")
  val serviceName = System.getProperty("serviceName")
  val featureName = System.getProperty("featureName")
  val tagName = System.getProperty("tagName")
  val getTest = scenario(featureName).exec(karateFeature("classpath:features/" + servicePath + "/" + serviceName + "/" + featureName + ".feature@" + tagName + ""))

  setUp(
    getTest.inject(atOnceUsers(usersCount.toInt)).protocols(protocol)
  )

}
