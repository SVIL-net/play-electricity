package net.svil.bootcamp.electricity.Models

sealed abstract class Plan(name:String){
    val basicCharge:Int = 0
    val rate:Double = 0
    val rateTable:Map[Int,Double] =  Map(0->0.0)
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

//https://www.tepco.co.jp/ep/private/plan/old01.html
case class TepcoB(name:String, area:String, currentCapacity:Int) extends Plan(name){
    override val basicCharge:Int = currentCapacity match {
        case 10 => 286
        case 15 => 429
        case 20 => 572
        case 30 => 858
        case 40 => 1144
        case 50 => 1430
        case 60 => 1716
        }
}

//https://www.eneos.co.jp/denki/charge/
case class EneosB(name:String, area:String, currentCapacity:Int) extends Plan(name){
    override val basicCharge:Int = currentCapacity match {
        case 10 => 286
        case 15 => 429
        case 20 => 572
        case 30 => 858
        case 40 => 1144
        case 50 => 1430
        case 60 => 1716
        }
}
// https://home.tokyo-gas.co.jp/power/ryokin/menu_waribiki/menu1.html
case class TokyoGas1(name:String, area:String, currentCapacity:Int) extends Plan(name){
    override val basicCharge:Int = currentCapacity match {
        case 30 => 858
        case 40 => 1144
        case 50 => 1430
        case 60 => 1716
        }
}
// https://home.tokyo-gas.co.jp/power/ryokin/menu_waribiki/menu1s.html
case class TokyoGas1S(name:String, area:String, currentCapacity:Int) extends Plan(name){
    override val basicCharge:Int = currentCapacity match {
        case 10 => 286
        case 15 => 429
        case 20 => 572
        case 30 => 858
        case 40 => 1144
        case 50 => 1430
        case 60 => 1716
        }
}