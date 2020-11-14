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
        case _ => 30.57
        }
        val deduction:Double = h.totalConsumption match {
        case c if 0 until 120 contains c => 0
        case c if 120 until 300 contains c => 120*(26.48-19.88)
        case _ => 120*(30.57-19.88) + 180*(30.57-26.48)
        }        
        plan.basicCharge.toLong + (h.totalConsumption*rate - deduction).toLong
      }
      case EneosB(name, area, currentCapacity) => {
        val rate:Double = h.totalConsumption match {
        case c if 0 until 120 contains c => 19.88
        case c if 120 until 300 contains c => 24.54
        case _ => 26.22
        }
        val deduction:Double = h.totalConsumption match {
        case c if 0 until 120 contains c => 0
        case c if 120 until 300 contains c => 120*(24.54-19.88)
        case _ => 120*(26.22-19.88) + 180*(26.22-24.54)
        }        
        plan.basicCharge.toLong + (h.totalConsumption*rate - deduction).toLong
      }
      case TokyoGas1(name, area, currentCapacity) => {
        val rate:Double = h.totalConsumption match {
        case c if 0 until 140 contains c => 23.67
        case c if 140 until 350 contains c => 23.88
        case _ => 26.41
        }
        val deduction:Double = h.totalConsumption match {
        case c if 0 until 140 contains c => 0
        case c if 140 until 350 contains c => 140*(23.88-23.67)
        case _ => 140*(26.41-23.67) + 210*(26.41-23.88)
        }        
        plan.basicCharge.toLong + (h.totalConsumption*rate - deduction).toLong
      }
      case _ => 0L
    }    
  }
}