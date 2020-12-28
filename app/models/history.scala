package net.svil.bootcamp.electricity.Models
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import scala.util.{Try, Success, Failure}
import scala.io.Source

class History(val data: Map[LocalDateTime, Long])

object History {
  def parseDateTime(s:String): Try[LocalDateTime] = Try{
    LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy/MM/dd H[H]:mm:ss"))
  }
  def fromCSV(path: String): History = {
    val lines: LazyList[String] = Source.fromFile(path).getLines().to(LazyList)
    val output: LazyList[(LocalDateTime, Long)] = lines.foldLeft[LazyList[(LocalDateTime, Long)]](LazyList.empty)(
      (acc:LazyList[(LocalDateTime, Long)], line) => {
        val sp:Array[String] = line.split(",").map(_.trim)
        if(sp.isEmpty) {
          acc
        } else if(sp(0).isEmpty){
          acc
        } else if(sp(0).contains(":00:")){
          parseDateTime(sp(0)) match{
            case Success(t) => (t, sp(1).toLong) #:: acc
            case Failure(e) => acc
          }
        } else {
          acc
        }
      }
    )
    val zipped = output.zip(output.drop(1)).map( e => (e._2._1, e._2._2-e._1._2))
    new History(zipped.toMap)
  }
}
