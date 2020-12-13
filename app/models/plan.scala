package net.svil.bootcamp.electricity.Models

sealed abstract class Plan (val name: String)

case class FlatRatePlan(override val name:String, flatRate:Double) extends Plan(name)
case class FlatRateWithCurrentLimitBaseCharge(override val name:String, flatRate:Double, base:Int=>Double) extends Plan(name)
case class StageRateWithCurrentLimitBaseCharge(override val name:String, stageTotalF:Long=>Long, base:Int=>Double) extends Plan(name)
case class DayNightWithCurrentLimitBaseCharge(override val name:String, hourRate:Int=>Double, base:Int=>Double) extends Plan(name)


object PlanCollection{
    val stageTotalFTepcoB: Long=>Long = {
        case c if c <= 120000 => (19.88*c).toLong
        case c if c > 120000 && c <= 300000 => (19.88*120000+26.48*(c-120000)).toLong
        case c => (19.88*120000+26.48*300000+30.57*(c-300000)).toLong
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

    val hourRateTepcoYoru8: Int=>Double = hour => if (hour>=7 && hour <23) 32.74 else 21.16
    val baseTepcoYoru8:Int=>Double = current => current /10*214.5

    def get: Seq[Plan] = {
      Seq(
        FlatRatePlan("test", 1),
        StageRateWithCurrentLimitBaseCharge("TepcoB", stageTotalFTepcoB, baseTepcoB),
        DayNightWithCurrentLimitBaseCharge("夜トク8", hourRateTepcoYoru8, baseTepcoYoru8)
      )
    }
}
