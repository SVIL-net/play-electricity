import net.svil.bootcamp.electricity.Models._

import org.scalatestplus.play._
import java.time.LocalDateTime

class SimpleFlatRateComputation extends PlaySpec{
  "Calculator" must {
    "return correct value in FlatRate" in {
      val flatRatePlan = new FlatRatePlan("test", 1000, 0.1)
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 100,
                                  LocalDateTime.parse("2020-11-01T01:00:00") -> 200
                                ))
      Calculator.accumelate(history, flatRatePlan) must be (1030)     
    }
    "return correct value in 20A/Tokyo with Looop" in {
      val plan = new Looop("test", "tokyo", 20)
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 100,
                                  LocalDateTime.parse("2020-11-01T01:00:00") -> 200
                                ))
      Calculator.accumelate(history, plan) must be (0 + 26.4*300)     
    }
    "return correct value in 40A/Kansai with Looop" in {
      val plan = new Looop("test", "kansai", 40)
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 100,
                                  LocalDateTime.parse("2020-11-01T01:00:00") -> 200
                                ))
      Calculator.accumelate(history, plan) must be (0 + 22.4*300)     
    }
  }
}
