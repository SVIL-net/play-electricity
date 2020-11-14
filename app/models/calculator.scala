package net.svil.bootcamp.electricity.Models

object Calculator{
  def accumelate[P <: Plan](h: History, plan: P): Long = {
    // TODO
    plan match{
      case FlatRatePlan(name, basicCharge, rate) => {
        basicCharge.toLong + (h.data.foldLeft(0L){ case (a, (k, v)) => a+(v*rate).toLong}).toLong
      }
      case Looop(name, area, currentCapacity) => {
        plan.basicCharge.toLong + (h.data.foldLeft(0L){ case (a, (k, v)) => a+(v*plan.rate).toLong}).toLong
      }
      case TepcoB(name, area, currentCapacity) => {
        val rate:Double = h.totalConsumption match {
        case c if 0 until 120 contains c => 19.88
        case c if 120 until 300 contains c => 26.48
        case c if 300 until 99999 contains c => 30.57
        case _ => 0L
        }
        val deduction:Double = h.totalConsumption match {
        case c if 0 until 120 contains c => 0
        case c if 120 until 300 contains c => 120*(26.48-19.88)
        case c if 300 until 99999 contains c => 120*(30.57-19.88) + 180*(30.57-26.48)
        case _ => 0L
        }        
        plan.basicCharge.toLong + (h.totalConsumption*rate).toLong - deduction.toLong
      }
      case _ => 0L
    }    
  }
}