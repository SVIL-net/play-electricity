import net.svil.bootcamp.electricity.Models._
import org.scalatestplus.play._

class CsvLoaderTest extends PlaySpec{
    val data = CsvLoader.load("log.csv")
    println(data.mkString(" "))
}