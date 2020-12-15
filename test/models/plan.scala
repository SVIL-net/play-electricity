import net.svil.bootcamp.electricity.Models._
import org.scalatestplus.play._

class PlanCollectionTest extends PlaySpec{
    val plans = PlanCollection.get
    for (plan <- plans){
        plan match {
            case FlatRatePlan(name, flatRate) => println(name)
            case FlatRateWithCurrentLimitBaseCharge(name, flatRate, base) => println(name)
            case StageRateWithCurrentLimitBaseCharge(name, stageTotalF, base) => println(name)
            case DayNightWithCurrentLimitBaseCharge(name, hourRate, base) => println(name)
            case _ => print("mismatch")
        }
    }
}