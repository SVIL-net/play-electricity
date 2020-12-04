package net.svil.bootcamp.electricity.Models

import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap


class History(val data: Map[LocalDateTime, Long]){
  // TODO
  /*
  def toHour: Map[LocalDateTime, Long] = {
    
    return Map[LocalDateTime, Long]}
  */
}

object CsvLoader{
  def load(path: String): HashMap[LocalDateTime,Long] = {
    val src = Source.fromFile(path)
    val convertedRows  = HashMap.empty[LocalDateTime,Long]
    val df = DateTimeFormatter.ofPattern("yyyy/MM/dd H[H]:mm:ss");

    for ((line, count) <- src.getLines().zipWithIndex) {
      val row = line.split(",").map(_.trim)
      if(!row.isEmpty && count != 0)
      {
        convertedRows += ( LocalDateTime.parse(row(0),df) -> row(2).toLong)
      }
    }
    src.close()
    convertedRows
    }
}