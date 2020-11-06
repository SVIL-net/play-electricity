package net.svil.bootcamp.electricity.Models

object Calculator{
  def accumelate(h: History, powerCapacity: Int, p: Plan): Long = {
    // TODO
    // data.foldLeft(0L){ case (a, (k, v)) => a+v*p.flatRate}
    p.basicCharge(powerCapacity) + h.totalConsumption*p.payAsYouGoCharge(0)/100
  }
}
