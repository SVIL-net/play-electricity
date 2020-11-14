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
    "return correct value in 100kWh/20A/Tokyo with TepcoB" in {
      val plan = new TepcoB("test", "tokyo", 20)
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 50,
                                  LocalDateTime.parse("2020-11-01T01:00:00") -> 50
                                 ))
      Calculator.accumelate(history, plan) must be (572 + 19.88*100)     
    }
    "return correct value in 200kWh/30A/Tokyo with TepcoB" in {
      val plan = new TepcoB("test", "tokyo", 30)
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 100,
                                  LocalDateTime.parse("2020-11-01T01:00:00") -> 100
                                 ))
      Calculator.accumelate(history, plan) must be (858 + 19.88*120 + 26.48*80)     
    }
    "return correct value in 400kWh/40A/Tokyo with TepcoB" in {
      val plan = new TepcoB("test", "tokyo", 40)
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 200,
                                  LocalDateTime.parse("2020-11-01T01:00:00") -> 200
                                 ))
      Calculator.accumelate(history, plan) must be (1144 + 19.88*120 + 26.48*180 + 30.57*100)     
    }
    "return correct value in 100kWh/20A/Tokyo with EneosB" in {
      val plan = new EneosB("test", "tokyo", 20)
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 50,
                                  LocalDateTime.parse("2020-11-01T01:00:00") -> 50
                                 ))
      Calculator.accumelate(history, plan) must be (572 + 19.88*100)     
    }
    "return correct value in 200kWh/30A/Tokyo with EneosB" in {
      val plan = new EneosB("test", "tokyo", 30)
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 100,
                                  LocalDateTime.parse("2020-11-01T01:00:00") -> 100
                                 ))
      Calculator.accumelate(history, plan) must be ((858 + 19.88*120 + 24.54*80).toLong)    
    }
    "return correct value in 400kWh/40A/Tokyo with EneosB" in {
      val plan = new EneosB("test", "tokyo", 40)
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 200,
                                  LocalDateTime.parse("2020-11-01T01:00:00") -> 200
                                 ))
      Calculator.accumelate(history, plan) must be ((1144 + 19.88*120 + 24.54*180 + 26.22*100).toLong)
    }
    "return correct value in 200kWh/30A/Tokyo with TokyoGas1" in {
      val plan = new TokyoGas1("test", "tokyo", 30)
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 100,
                                  LocalDateTime.parse("2020-11-01T01:00:00") -> 100
                                 ))
      Calculator.accumelate(history, plan) must be ((858 + 23.67*140 + 23.88*60).toLong)    
    }
    "return correct value in 400kWh/40A/Tokyo with TokyoGas1" in {
      val plan = new TokyoGas1("test", "tokyo", 40)
      val history = new History( Map(
                                  LocalDateTime.parse("2020-11-01T00:00:00") -> 200,
                                  LocalDateTime.parse("2020-11-01T01:00:00") -> 200
                                 ))
      Calculator.accumelate(history, plan) must be ((1144 + 23.67*140 + 23.88*210 + 26.41*50).toLong)
    }
  }
}
