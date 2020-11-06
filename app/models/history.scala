package net.svil.bootcamp.electricity.Models

import java.time.LocalDateTime

class History(val data: Map[LocalDateTime, Long]){
  // TODO
  val totalConsumption = data.foldLeft(0L){ case (a, (k, v)) => a+v}
}

