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
      case _ => 0L
    }    
  }
}