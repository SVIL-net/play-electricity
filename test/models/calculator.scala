import net.svil.bootcamp.electricity.Models._

import org.scalatestplus.play._
import java.time.LocalDateTime

class SimpleFlatRateComputation extends PlaySpec{
  "Calculator" must {
    val history = new History( Map(
                                LocalDateTime.parse("2020-11-01T00:00:00") -> 1000,
                                LocalDateTime.parse("2020-11-01T09:00:00") -> 1500
                              ))
    val baseFlat30: Int => Double = {
      case c if c <= 10 => 286
      case c if c > 10 && c <= 20  => 572
      case _ => 858
    }
    val baseTepcoB: Int => Double = {
      case 10 => 286.0
      case 15 => 429.0
      case 20 => 572.0
      case 30 => 858.0
      case 40 => 1144.0
      case 50 => 1430.0
      case 60 => 1716.0
        case _ => 0.0
    }
    val stageTotalFTepcoB: Long=>Long = {
      case c if c <= 120 => (19.88*c).toLong
      case c if c > 120 && c <= 300 => (19.88*120+26.48*(c-120)).toLong
      case c => (19.88*120+26.48*300+30.57*(c-300)).toLong
    }
    val hourRateTepcoYoru8: Int=>Double = hour => if (hour>=7 && hour <23) 32.74 else 21.16
    val baseTepcoYoru8:Int=>Double = current => current /10*214.5
   "return correct value in FlatRate" in {
      val plan = new FlatRatePlan("test", 0.1)
      Calculator.accumelate(history, plan, 10).total must be (1)
    }
    "return correct value in FlatRateWithCurrentBaseCharge" in {
      val plan = new FlatRateWithCurrentLimitBaseCharge("test", 0.01, baseFlat30)
      Calculator.accumelate(history, plan, 20).total must be (572L)
    }
    "return correct value in StagedRateWithCurrentLimitBaseCharge(TepcoB)" in {
      val plan = new StageRateWithCurrentLimitBaseCharge("TepcoB", stageTotalFTepcoB, baseTepcoB)
      Calculator.accumelate(history, plan, 20).total must be (878L)
    }
    "return correct value in DayNightWithCurrentLimitBaseCharge(夜トク8)" in {
      val plan = new DayNightWithCurrentLimitBaseCharge("夜トク8", hourRateTepcoYoru8, baseTepcoYoru8)
      Calculator.accumelate(history, plan, 20).total must be (710)
    }
  }
}

