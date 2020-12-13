package net.svil.bootcamp.electricity.Models

object Solver{
  def solve(h: History, planCollection: Seq[Plan]): Seq[(String, Long)] =
    planCollection.map(plan => (plan.name, Calculator.accumelate(h, plan, 20).total)).sortBy(_._2)
}

