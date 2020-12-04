import net.svil.bootcamp.electricity.Models._

import org.scalatestplus.play._
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SimpleHistoryCSVReader extends PlaySpec{
  "History" must {
    val csv = getClass.getResource("HomeEnergyLogs600.csv").getPath
    val result =
      Seq(
          "2020/12/04 11:00:33",
          "2020/12/04 10:00:34",
          "2020/12/04 9:00:33",
          "2020/12/04 8:00:33",
          "2020/12/04 7:00:45",
          "2020/12/04 6:00:33",
          "2020/12/04 4:00:35",
          "2020/12/04 4:00:28",
          "2020/12/04 4:00:25",
          "2020/12/04 3:00:33",
          "2020/12/04 2:00:33")
        .map(LocalDateTime.parse(_, DateTimeFormatter.ofPattern("yyyy/MM/dd[ H:mm:ss]")))
        .zip( Seq(3,4,6,7,8,15,0,0,9,10) ).toMap

    "CSV can be read" in {
      println(History.fromCSV(csv).data)
      History.fromCSV(csv).data must be (result)
    }
  }
}
