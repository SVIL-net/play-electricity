import net.svil.bootcamp.electricity.Models._
import org.scalatestplus.play._
import java.time.LocalDateTime

class CsvLoaderTest extends PlaySpec{
    "history" must{
        "return correct type of CsvLoader" in {
            val csv = getClass.getResource("read_test.csv").getPath
            val data = CsvLoader.load(csv)
            data mustBe a[Map[LocalDateTime,Long]]
        }
    }
}