import net.svil.bootcamp.electricity.Models._

import org.scalatestplus.play._
import java.time.LocalDateTime

class SimpleFlatRateComputation extends PlaySpec{
  "Calculator" must {
    "return correct value in FlatRate" in {
      val plan = new Flat30()
      val powerCapacity = 20 // 20A
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 100,
                                  LocalDateTime.parse("2020-11-01T01:00:00") -> 200
                                ))
      Calculator.accumelate(history, powerCapacity, plan) must be (9000)
    }
    "return correct value in TepcoB Plan" in {
      val plan = new TepcoB() // Tepco Plan B
      val powerCapacity = 20 // 20A
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 100,
                                  LocalDateTime.parse("2020-11-08T00:00:00") -> 100,
                                  LocalDateTime.parse("2020-11-15T00:00:00") -> 100,
                                  LocalDateTime.parse("2020-11-22T00:00:00") -> 100,
                                  LocalDateTime.parse("2020-11-29T00:00:00") -> 100,
                                )) // 500kWh per month
      Calculator.accumelate(history, powerCapacity, plan) must be (572+500*19.88)
    }
  }
}
