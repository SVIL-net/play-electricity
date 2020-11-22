package net.svil.bootcamp.electricity.Models

sealed abstract class Plan (name: String)

case class FlatRatePlan(name:String, flatRate:Double) extends Plan(name)
case class FlatRateWithCurrentLimitBaseCharge(name:String, flatRate:Double, base:Int=>Double) extends Plan(name)
case class StageRateWithCurrentLimitBaseCharge(name:String, stageTotalF:Long=>Long, base:Int=>Double) extends Plan(name)
case class DayNightWithCurrentLimitBaseCharge(name:String, hourRate:Int=>Double, base:Int=>Double) extends Plan(name)
