package net.svil.bootcamp.electricity.Models

//class Plan(val flatRate:Long){

sealed abstract class Plan(name:String)

case class FlatRatePlan(name:String, flatRate:Double) extends Plan(name)
case class FlatRateWithCurrentLimitBaseCharge(name:String, flatRate:Double, base:Int=>Double) extends Plan(name)
case class StageRateWithCurrentLimitBaseCharge(name:String, stageTotalF:Long=>Long, base:Int=>Double) extends Plan(name)
case class DayNightWithCurrentLimitBaseCharge(name:String, hourRate:Int=>Double, base:Int=>Double) extends Plan(name)




class PlanCollection{
    val base0: Int => Double = {
      case _ => 0
    }
    val baseFlat30: Int => Double = {
      case c if c <= 10 => 286
      case c if c > 10 && c <= 20  => 572
      case _ => 858
    }
    val baseTepcoB: Int => Double = {
      case 10 => 286.0
      case 15 => 429.0
      case 20 => 572.0
      case 30 => 858.0
      case 40 => 1144.0
      case 50 => 1430.0
      case 60 => 1716.0
        case _ => 0.0
    }
    val baseTokyoGasZuttomo1: Int => Double = {
      case 30 => 858.0
      case 40 => 1144.0
      case 50 => 1430.0
      case 60 => 1716.0
        case _ => 0.0
    }

    val baseKumamotoOuchiB: Int => Double = {
      case 40 => 915.2
      case 50 => 1144.0
      case 60 => 1372.76
        case _ => 0.0
    }



    val baseHisPrime50: Int => Double = {
      case 50 => 715.0
      case 60 => 858.0
        case _ => 0.0
    }

    val baseEneosTokyoV: Int => Double = {
      case 10 => 286.0
      case 15 => 429.0
      case 20 => 572.0
      case 30 => 858.0
      case 40 => 1144.0
      case 50 => 1430.0
      case 60 => 1716.0
        case _ => 0.0
    }

    val baseLpioS: Int => Double = {
      case 40 => 1086.8
      case 50 => 1344.2
      case 60 => 1613.04
        case _ => 0.0
    }

    val baseAshitaDenkiTappuri: Int => Double = {
        case _ => 3000
    }

    val stageTotalFTepcoB: Long=>Long = {
      case c if c <= 120 => (19.88*c).toLong
      case c if c > 120 && c <= 300 => (19.88*120+26.48*(c-120)).toLong
      case c => (19.88*120+26.48*180+30.57*(c-300)).toLong
    }

    val stageTotalFTokyoGasZuttomo1: Long=>Long = {
      case c if c <= 140 => (23.67*c).toLong
      case c if c > 140 && c <= 350 => (23.67*140+23.88*(c-140)).toLong
      case c => (23.67*140+23.88*210+26.41*(c-350)).toLong
    }


    val stageTotalFJuryoDentoA: Long=>Long = {
     case c if c <= 11 => (411.4).toLong
     case c if c > 11 && c <= 120 => (411.4+20.37*(c-11)).toLong
     case c if c > 120 && c <= 300 => (411.4+20.37*109+26.99*(c-120)).toLong
     case c => (411.4+20.37*109+26.99*180+30.57*(c-300)).toLong
    }


    val stageTotalFSimpleCourse: Long=>Long = {
     case c if c <= 15 => (227.37).toLong
     case c if c > 15 && c <= 120 => (227.37+20.79*(c-15)).toLong
     case c if c > 120 && c <= 300 => (227.37+20.79*105+27.47*(c-120)).toLong
     case c => (227.37+20.79*105+27.47*80+29.59*(c-300)).toLong
    }

    val stageTotalFSmartCourse: Long=>Long = {
     case c if c <= 64 => (1650).toLong
     case c if c > 64 => (1650+20.79*(c-64)).toLong
    }

    val stageTotalFKumamotoOuchiB: Long=>Long = {
      case c if c <= 120 => (23.83*c).toLong
      case c if c > 120 && c <= 200 => (23.83*120+23.83*(c-120)).toLong
      case c if c > 200 && c <= 300 => (23.83*120+23.83*200+17.88*(c-200)).toLong
      case c => (23.83*120+23.83*80+17.88*100+23.85*(c-300)).toLong
    }

    val stageTotalFEneosTokyoV: Long=>Long = {
      case c if c <= 120 => (19.88*c).toLong
      case c if c > 120 && c <= 300 => (19.88*120+24.54*(c-120)).toLong
      case c => (19.88*120+26.48*180+26.22*(c-300)).toLong
    }

    val stageTotalFLpioS: Long=>Long = {
      case c if c <= 120 => (18.84*c).toLong
      case c if c > 120 && c <= 300 => (18.84*120+23.03*(c-120)).toLong
      case c => (18.84*120+23.03*180+25.78*(c-300)).toLong
    }

    val stageTotalFJapanDenryokuKurashi: Long=>Long = {
      case c if c <= 250 => (26.00*c).toLong
      case c if c > 250 && c <= 400 => (26.00*250+25.5*(c-250)).toLong
      case c => (26.00*250+25.5*150+24.50*(c-400)).toLong
    }


    val hourRateTepcoYoru8: Int=>Double = hour => if (hour>=7 && hour <23) 32.74 else 21.16

    val baseTepcoYoru8:Int=>Double = current => current /10*214.5


    val planCollection = Seq(FlatRatePlan("LooopOuchi",26.4)
                         FlatRatePlan("AshitaDenkiStandard",26.0)
                         FlatRatePlan("PitaDen",25.71)
                         FlatRateWithCurrentLimitBaseCharge("HisPrime50", 23.54, baseHisPrime50),
                         FlatRateWithCurrentLimitBaseCharge("AshitaDenkiTappuri", 21.5, baseAshitaDenkiTappuri),
                         StageRateWithCurrentLimitBaseCharge("TepcoB", stageTotalFTepcoB, baseTepcoB),
                         StageRateWithCurrentLimitBaseCharge("EneosTokyoV", stageTotalFEneosTokyoV, baseEneosTokyoV),
                         StageRateWithCurrentLimitBaseCharge("TokyoGasZuttomo1", stageTotalFTokyoGasZuttomo1, baseTokyoGasZuttomo1),
                         StageRateWithCurrentLimitBaseCharge("KumamotoOuchiB", stageTotalFKumamotoOuchiB, baseKumamotoOuchiB),
                         StageRateWithCurrentLimitBaseCharge("LpioS", stageTotalFLpioS, baseLpioS),
                         DayNightWithCurrentLimitBaseCharge("夜トク8", hourRateTepcoYoru8, baseTepcoYoru8)
                         StageRateWithCurrentLimitBaseCharge("JuryoDentoA",stageTotalFJuryoDentoA, base0)
                         StageRateWithCurrentLimitBaseCharge("SmartCourse",stageTotalFSmartCourse, base0)
                         StageRateWithCurrentLimitBaseCharge("SimpleCourse",stageTotalFSimpleCourse, base0)
                         StageRateWithCurrentLimitBaseCharge("JapanDenryokuKiurashi",stageTotalFJapanDenryokuKurashi, base0)
                         )
}


//　https://looop-denki.com/low-v/plan/ouchi/
//　https://ashita-denki.jp/lpa/general
//　https://htb-energy.co.jp//ultraprime/202004af.html?atnct=htb-energy_0100nd4i00g1fh-13901d2d2a3ade9df63c5672dc51d5a8
//　https://ashita-denki.jp/lpa/generals
//　https://www.tepco.co.jp/ep/private/plan/yorutoku/index-j.html
//  https://www.eneos.co.jp/denki/charge/
//　https://home.tokyo-gas.co.jp/power/ryokin/menu_waribiki/menu1.html　　　　
//　https://kumamoto-energy.co.jp/teiatsu/
//  https://lpio.jp/electrical/ele_tokyo/standard_s/
//　https://www.tepco.co.jp/ep/private/plan/old01.html
//　https://www.yonden.co.jp/customer/price/plan/juryo_a.html
//  https://www.energia-support.com/pricemenu/smart.html
//  https://www.energia-support.com/pricemenu/simple.html
//  https://www.japaden.jp/buy/low/?a8=KrA0DrUo2LBFMMRDZj_Frp66CRzJBpT6UjB0CpTK_F4o2LArcMayGM_rkLZsasTBcFbMIvAycrA01s00000020354002


// まとめサイト　https://power-hikaku.info/choice/area/tokyo.php