package net.svil.bootcamp.electricity.Models

sealed abstract class Plan (val name: String)

case class FlatRatePlan(override val name:String, flatRate:Double) extends Plan(name)
case class FlatRateWithCurrentLimitBaseCharge(override val name:String, flatRate:Double, base:Int=>Double) extends Plan(name)
case class StageRateWithCurrentLimitBaseCharge(override val name:String, stageTotalF:Long=>Long, base:Int=>Double) extends Plan(name)
case class DayNightWithCurrentLimitBaseCharge(override val name:String, hourRate:Int=>Double, base:Int=>Double) extends Plan(name)
