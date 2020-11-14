package net.svil.bootcamp.electricity.Models

sealed abstract class Plan(name:String){
    val basicCharge:Int = 0
    val rate:Double = 0
}

case class FlatRatePlan(name:String, override val basicCharge:Int, override val rate:Double) extends Plan(name)

//https://looop-denki.com/low-v/plan/ouchi/
case class Looop(name:String, area:String, currentCapacity:Int) extends Plan(name){
    override val basicCharge:Int = currentCapacity match {
        case _ => 0
        }
    override val rate:Double = area match {
        case a if List("hokkaido") contains a  => 29.5
        case a if List("tohoku","tokyo","chubu") contains a  => 26.4
        case a if List("hokuriku") contains a  => 21.3
        case a if List("kansai") contains a  => 22.4
        case a if List("chubu","sikoku") contains a  => 24.4
        case a if List("kyusyu") contains a  => 23.4
        case a if List("okinawa") contains a  => 23.4
        case _ => 0.0
        }
}