package net.svil.bootcamp.electricity.Models

import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


class History(val data: Map[LocalDateTime, Long])

object CsvLoader{
  def load(path: String): Map[LocalDateTime,Long] = {
    val src = Source.fromFile(path)
    var convertedRows  = Map.empty[LocalDateTime,Long]
    val df = DateTimeFormatter.ofPattern("yyyy/MM/dd H[H]:mm:ss");
    var count = 0

    for ((line, index) <- src.getLines().zipWithIndex) {
      val row = line.split(",").map(_.trim)
      if(!row.isEmpty && index != 0 && count < 24*7 && row(0).contains(":00:"))
      {
        convertedRows += ( LocalDateTime.parse(row(0),df) -> row(2).toLong)
        count = count + 1
      }
    }
    src.close()
    convertedRows
    }
}