import net.svil.bootcamp.electricity.Models._

import org.scalatestplus.play._
import java.time.LocalDateTime

class SimpleFlatRateComputation extends PlaySpec{
  "Calculator" must {
    val history = new History( Map(
                                LocalDateTime.parse("2020-11-01T00:00:00") -> 100,
                                LocalDateTime.parse("2020-11-01T09:00:00") -> 150
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
      Calculator.accumelate(history, plan, 10) must be (25.0)
    }
    "return correct value in FlatRateWithCurrentBaseCharge" in {
      val plan = new FlatRateWithCurrentLimitBaseCharge("test", 0.01, baseFlat30)
      Calculator.accumelate(history, plan, 20) must be (572+2)
    }
    "return correct value in StagedRateWithCurrentLimitBaseCharge(TepcoB)" in {
      val plan = new StageRateWithCurrentLimitBaseCharge("TepcoB", stageTotalFTepcoB, baseTepcoB)
      Calculator.accumelate(history, plan, 20) must be (572+(19.88*120+26.48*(250-120)).toLong)
    }
    "return correct value in DayNightWithCurrentLimitBaseCharge(夜トク8)" in {
      val plan = new DayNightWithCurrentLimitBaseCharge("夜トク8", hourRateTepcoYoru8, baseTepcoYoru8)
      Calculator.accumelate(history, plan, 20) must be ((214.5*2 + 100*21.16+150*32.74).toLong)
    }
  }
}

class SolverTest extends PlaySpec{
  "Solver" must {
    "return correct value in Solver.solve" in {
      val history = new History( Map(
                                LocalDateTime.parse("2020-11-01T00:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T01:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T02:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T03:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T04:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T05:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T06:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T07:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T08:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T09:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T10:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T11:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T12:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T13:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T14:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T15:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T16:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T17:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T18:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T19:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T20:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T21:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T22:00:00") -> 500,
                                LocalDateTime.parse("2020-11-01T23:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T00:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T01:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T02:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T03:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T04:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T05:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T06:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T07:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T08:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T09:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T10:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T11:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T12:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T13:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T14:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T15:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T16:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T17:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T18:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T19:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T20:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T21:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T22:00:00") -> 500,
                                LocalDateTime.parse("2020-11-02T23:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T00:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T01:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T02:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T03:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T04:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T05:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T06:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T07:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T08:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T09:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T10:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T11:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T12:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T13:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T14:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T15:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T16:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T17:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T18:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T19:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T20:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T21:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T22:00:00") -> 500,
                                LocalDateTime.parse("2020-11-03T23:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T00:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T01:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T02:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T03:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T04:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T05:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T06:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T07:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T08:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T09:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T10:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T11:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T12:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T13:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T14:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T15:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T16:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T17:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T18:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T19:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T20:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T21:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T22:00:00") -> 500,
                                LocalDateTime.parse("2020-11-04T23:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T00:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T01:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T02:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T03:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T04:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T05:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T06:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T07:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T08:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T09:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T10:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T11:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T12:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T13:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T14:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T15:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T16:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T17:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T18:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T19:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T20:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T21:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T22:00:00") -> 500,
                                LocalDateTime.parse("2020-11-05T23:00:00") -> 500, 
                                LocalDateTime.parse("2020-11-06T00:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T01:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T02:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T03:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T04:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T05:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T06:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T07:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T08:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T09:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T10:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T11:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T12:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T13:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T14:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T15:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T16:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T17:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T18:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T19:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T20:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T21:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T22:00:00") -> 500,
                                LocalDateTime.parse("2020-11-06T23:00:00") -> 500, 
                                LocalDateTime.parse("2020-11-07T00:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T01:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T02:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T03:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T04:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T05:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T06:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T07:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T08:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T09:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T10:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T11:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T12:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T13:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T14:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T15:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T16:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T17:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T18:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T19:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T20:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T21:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T22:00:00") -> 500,
                                LocalDateTime.parse("2020-11-07T23:00:00") -> 500
                              ))
      val plans = PlanCollection.get
      val out = Solver.solve(history, plans)
      println(out)
      out must be (Seq(("test", ((500.0/1000)*(24*7*4)*0.1).toLong),
                      ("夜トク8", (214.5*20/10 + (500.0/1000)*(8*7*4)*21.16+(500.0/1000)*(16*7*4)*32.74).toLong),
                      ("TepcoB", (572+(19.88*120000+26.48*300000+30.57*(500*24*7*4-300000))/1000).toLong)
                    ))
    }
  }
}
