package net.svil.bootcamp.electricity.Models

object PlanCollection {
  private[this] val baseFlat30: Int => Double = {
    case c if c <= 10 => 286
    case c if c > 10 && c <= 20  => 572
    case _ => 858
  }
  private[this] val baseZero: Int => Double = {
    case _ => 0.0
  }

  private[this] val baseTepcoB: Int => Double = {
    case 10 => 286.0
    case 15 => 429.0
    case 20 => 572.0
    case 30 => 858.0
    case 40 => 1144.0
    case 50 => 1430.0
    case 60 => 1716.0
    case _ => 0.0
  }

  private[this] val baseTokyoGasZuttomo1: Int => Double = {
    case 30 => 858.0
    case 40 => 1144.0
    case 50 => 1430.0
    case 60 => 1716.0
    case _ => 0.0
  }

  private[this] val baseKumamotoOuchiB: Int => Double = {
    case 40 => 915.2
    case 50 => 1144.0
    case 60 => 1372.76
    case _ => 0.0
  }

  private[this] val baseHisPrime50: Int => Double = {
    case 50 => 715.0
    case 60 => 858.0
    case _ => 0.0
  }

  private[this] val baseEneosTokyoV: Int => Double = {
    case 10 => 286.0
    case 15 => 429.0
    case 20 => 572.0
    case 30 => 858.0
    case 40 => 1144.0
    case 50 => 1430.0
    case 60 => 1716.0
    case _ => 0.0
  }

  private[this] val baseLpioS: Int => Double = {
    case 40 => 1086.8
    case 50 => 1344.2
    case 60 => 1613.04
    case _ => 0.0
  }

  private[this] val baseAshitaDenkiTappuri: Int => Double = {
    case _ => 3000
  }

  private[this] val stageTotalFTepcoB: Long=>Long = {
    case c if c <= 120 => (19.88*c).toLong
    case c if c > 120 && c <= 300 => (19.88*120+26.48*(c-120)).toLong
    case c => (19.88*120+26.48*180+30.57*(c-300)).toLong
  }

  private[this] val stageTotalFTokyoGasZuttomo1: Long=>Long = {
    case c if c <= 140 => (23.67*c).toLong
    case c if c > 140 && c <= 350 => (23.67*140+23.88*(c-140)).toLong
    case c => (23.67*140+23.88*210+26.41*(c-350)).toLong
  }

  private[this] val stageTotalFJuryoDentoA: Long=>Long = {
    case c if c <= 11 => (411.4).toLong
    case c if c > 11 && c <= 120 => (411.4+20.37*(c-11)).toLong
    case c if c > 120 && c <= 300 => (411.4+20.37*109+26.99*(c-120)).toLong
    case c => (411.4+20.37*109+26.99*180+30.50*(c-300)).toLong
  }

  private[this] val stageTotalFSmartCourse: Long=>Long = {
    case c if c <= 15 => (227.37).toLong
    case c if c > 15 && c <= 120 => (227.37+20.79*(c-15)).toLong
    case c if c > 120 && c <= 300 => (227.37+20.79*105+27.47*(c-120)).toLong
    case c => (227.37+20.79*105+27.47*80+29.59*(c-300)).toLong
  }

  private[this] val stageTotalFSimpleCourse: Long=>Long = {
    case c if c <= 64 => (1650).toLong
    case c if c > 64 => (1650+25.78*(c-64)).toLong
  }

  private[this] val stageTotalFKumamotoOuchiB: Long=>Long = {
    case c if c <= 120 => (23.83*c).toLong
    case c if c > 120 && c <= 200 => (23.83*120+23.83*(c-120)).toLong
    case c if c > 200 && c <= 300 => (23.83*120+23.83*200+17.88*(c-200)).toLong
    case c => (23.83*120+23.83*80+17.88*100+23.85*(c-300)).toLong
  }

  private[this] val stageTotalFEneosTokyoV: Long=>Long = {
    case c if c <= 120 => (19.88*c).toLong
    case c if c > 120 && c <= 300 => (19.88*120+24.54*(c-120)).toLong
    case c => (19.88*120+26.48*180+26.22*(c-300)).toLong
  }

  private[this] val stageTotalFLpioS: Long=>Long = {
    case c if c <= 120 => (18.84*c).toLong
    case c if c > 120 && c <= 300 => (18.84*120+23.03*(c-120)).toLong
    case c => (18.84*120+23.03*180+25.78*(c-300)).toLong
  }

  private[this] val stageTotalFJapanDenryokuKurashi: Long=>Long = {
    case c if c <= 250 => (26.00*c).toLong
    case c if c > 250 && c <= 400 => (26.00*250+25.5*(c-250)).toLong
    case c => (26.00*250+25.5*150+24.50*(c-400)).toLong
  }

  private[this] val hourRateTepcoYoru8: Int=>Double = hour => if (hour>=7 && hour <23) 32.74 else 21.16

  private[this] val baseTepcoYoru8:Int=>Double = current => current /10*214.5

  def apply():Seq[Plan] = Seq(
    FlatRatePlan("Looop電気 おうちプラン",26.4),
    FlatRatePlan("あしたでんき 標準プラン",26.0),
    FlatRatePlan("ピタでん 使った分だけ",25.71),
    FlatRateWithCurrentLimitBaseCharge("HIS電気 プライム50東京", 23.54, baseHisPrime50),
    FlatRateWithCurrentLimitBaseCharge("あしたでんき たっぷりプラン", 21.5, baseAshitaDenkiTappuri),
    StageRateWithCurrentLimitBaseCharge("TEPCO 従量電灯B", stageTotalFTepcoB, baseTepcoB),
    StageRateWithCurrentLimitBaseCharge("ENEOSでんき 東京Vプラン従量電灯B", stageTotalFEneosTokyoV, baseEneosTokyoV),
    StageRateWithCurrentLimitBaseCharge("TOKYO-GAS ずっとも電気1", stageTotalFTokyoGasZuttomo1, baseTokyoGasZuttomo1),
    StageRateWithCurrentLimitBaseCharge("熊本電力 おうち電気B", stageTotalFKumamotoOuchiB, baseKumamotoOuchiB),
    StageRateWithCurrentLimitBaseCharge("LPIO スタンダードプランS", stageTotalFLpioS, baseLpioS),
    DayNightWithCurrentLimitBaseCharge("TEPCO 夜トク8", hourRateTepcoYoru8, baseTepcoYoru8),
    StageRateWithCurrentLimitBaseCharge("四国電力 従量電灯A",stageTotalFJuryoDentoA, baseZero),
    StageRateWithCurrentLimitBaseCharge("中国電力 スマートコース",stageTotalFSmartCourse, baseZero),
    StageRateWithCurrentLimitBaseCharge("中国電力 シンプルコース",stageTotalFSimpleCourse, baseZero),
    StageRateWithCurrentLimitBaseCharge("Japan電力 くらしプラン",stageTotalFJapanDenryokuKurashi, baseZero)
  )
}
