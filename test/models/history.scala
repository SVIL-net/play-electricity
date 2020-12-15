import net.svil.bootcamp.electricity.Models._
import org.scalatestplus.play._
import java.time.LocalDateTime

class CsvLoaderTest extends PlaySpec{
    "history" must{
        "return correct type of CsvLoader" in {
            val data = CsvLoader.load("log.csv")
            println(data.mkString(" "))
            println(data.size)
            data mustBe a[Map[LocalDateTime,Long]]
        }
    }
}