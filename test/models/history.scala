import net.svil.bootcamp.electricity.Models._
import org.scalatestplus.play._

class CsvLoaderTest extends PlaySpec{
    "history" must{
        "return correct value in FlatRate" in {
            val data = CsvLoader.load("log.csv")
            println(data.mkString(" "))
            data must be (new History(Map()))
        }
    }
}