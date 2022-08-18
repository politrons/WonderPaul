package com.politrons.level

import scala.collection.{Seq, immutable}

object EnemyPatterns {

  val enemy1MovePattern: Seq[String] = immutable.List(
    "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left",
    "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left",
    "right", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right",
    "right", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right"
  )

  val enemy2MovePattern: Seq[String] = immutable.List(
    "down", "down", "down", "down", "down", "down", "down", "down", "down", "down", "down", "down",
    "down", "down", "down", "down", "down", "down", "down", "down", "down", "down", "down", "down",
    "up", "up", "up", "up", "up", "up", "up", "up", "up", "up", "up", "up",
    "up", "up", "up", "up", "up", "up", "up", "up", "up", "up", "up", "up"
  )

  val enemy3MovePattern: Seq[String] = immutable.List(
    "stop",
    "down", "down", "down", "down", "down", "down", "down", "down", "down", "down", "down", "down",
    "down", "down", "down", "down", "down", "down", "down", "down", "down", "down", "down", "down",
    "down", "down", "down", "down", "down", "down", "down", "down", "down", "down", "down", "down",
    "up", "up", "up", "up", "up", "up", "up", "up", "up", "up", "up", "up",
    "up", "up", "up", "up", "up", "up", "up", "up", "up", "up", "up", "up",
    "up", "up", "up", "up", "up", "up", "up", "up", "up", "up", "up", "up",
    "stop",
    "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left",
    "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left",
    "right", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right",
    "right", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right"
  )

}
