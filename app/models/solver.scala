package net.svil.bootcamp.electricity.Models

object Solver{
  val collection: Seq[Plan] = PlanCollection()
  def solve(h: History): Seq[(String, Long)] =
    planCollection.map(plan => (plan.name, Calculator.accumelate(h, plan, 20).total)).sortBy(_._2)
}
