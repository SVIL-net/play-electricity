package net.svil.bootcamp.electricity.Models

class Plan(val name:String){
 // TODO
 //val chargeRule = if (name == "TepcoB") new TepcoB() else new Flat30()
    val basicCharge = Map(0 -> 0)
    val payAsYouGoCharge = Map(0 -> 0)
}

// https://www.tepco.co.jp/ep/private/plan/old01.html
class TepcoB() extends Plan("hoge"){
    override val basicCharge = Map(10 -> 286, 20 -> 572, 30->858) // (A -> Yen)
    override val payAsYouGoCharge = Map(0 -> 1988, 129 -> 2648, 300 -> 3057) // (kWh -> 0.01Yen)
}

class Flat30() extends Plan("hoge"){
    override val basicCharge = Map(10 -> 0, 20 -> 0, 30->0) // (A -> Yen)
    override val payAsYouGoCharge = Map(0 -> 3000) // (kWh -> 0.01Yen)
}
