package net.svil.bootcamp.electricity.Models
case class Fee(val baseCharge: Long, val usageCharge:Long){
  def total: Long = baseCharge + usageCharge/1000
}

object Calculator{
  def accumelate[P <: Plan](h: History, plan: P, currentLimit: Int): Fee = {
    plan match {
      case FlatRatePlan(name, flatRate) => new Fee(0L, h.data.foldLeft(0.0){ case (a, (k, v)) => a+v*4*flatRate}.toLong)
      case FlatRateWithCurrentLimitBaseCharge(name, flatRate, base) => {
        new Fee(base(currentLimit).toLong, h.data.foldLeft(0.0){ case (a, (k, v)) => a+v*4*flatRate}.toLong)
      }
      case StageRateWithCurrentLimitBaseCharge(name, stageTotalF, base) => {
        val total:Long = h.data.foldLeft(0L){case (a, (k,v)) => a+v*4}
        new Fee(base(currentLimit).toLong, (stageTotalF(total)).toLong)
      }
      case DayNightWithCurrentLimitBaseCharge(name, hourRate, base) => {
        val totalPayAsYouGo:Long = h.data.foldLeft(0L){
          case (a, (k,v)) => {
                  a+ (hourRate(k.getHour())*v*4).toLong
          }
        }
        new Fee(base(currentLimit).toLong, totalPayAsYouGo)
      }
      case _ => new Fee(0L, 0L)
    }
  }
}
