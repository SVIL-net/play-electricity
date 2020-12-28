import net.svil.bootcamp.electricity.Models._
import org.scalatestplus.play._
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SimpleHistoryCSVReader extends PlaySpec{
  "History" must {
    val csv = getClass.getResource("read_test.csv").getPath
    val result:History = History.fromCSV(csv)
    val result_partial:Map[LocalDateTime,Long] =
      Seq(
        "2020-10-23T21:00:33",
        "2020-10-25T13:00:33",
        "2020-10-27T02:00:33",
        "2020-10-28T09:00:33",
        "2020-10-26T11:00:33",
        "2020-10-25T08:00:33")
        .map(LocalDateTime.parse(_))
        .zip( Seq(13L,3L,2L,3L,11L,8L) ).toMap

    "CSV can be read" in {
      result_partial.to(Set).subsetOf(result.data.to(Set)) must be(true)
    }
  }
}
