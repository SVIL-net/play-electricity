package net.svil.bootcamp.electricity.Models

object Calculator{
  def accumelate[P <: Plan](h: History, plan: P, currentLimit: Int): Long = {
    plan match {
      case FlatRatePlan(name, flatRate) => h.data.foldLeft(0.0){ case (a, (k, v)) => a+v*flatRate}.toLong
      case FlatRateWithCurrentLimitBaseCharge(name, flatRate, base) => {
        base(currentLimit).toLong + h.data.foldLeft(0.0){ case (a, (k, v)) => a+v*flatRate}.toLong
      }
      case StageRateWithCurrentLimitBaseCharge(name, stageTotalF, base) => {
        val total:Long = h.data.foldLeft(0L){case (a, (k,v)) => a+v}
        base(currentLimit).toLong + (stageTotalF(total)).toLong
      }
      case DayNightWithCurrentLimitBaseCharge(name, hourRate, base) => {
        val totalPayAsYouGo:Long = h.data.foldLeft(0L){
          case (a, (k,v)) => {
                  a+ (hourRate(k.getHour())*v).toLong
          }
        }
        base(currentLimit).toLong + totalPayAsYouGo
      }
      case _ => 0L
    }
  }
}
