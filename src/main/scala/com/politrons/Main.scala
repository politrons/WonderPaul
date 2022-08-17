package com.politrons

import com.politrons.engine.{CharacterEngine, EnemyEngine}

import java.awt._
import javax.swing._
import scala.collection.{Seq, immutable}


object Main extends App {
  EventQueue.invokeLater(() => {
    val ex = new Main()
    ex.setVisible(true)
  })
}

class Main extends JFrame {


  val enemy1MovePattern: Seq[String] = immutable.List(
    "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left", "left",
    "right", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right", "right"
  )

  val enemy2MovePattern: Seq[String] = immutable.List(
    "down", "down", "down", "down", "down", "down", "down", "down", "down", "down", "down", "down",
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

  initGame()

  private def initGame(): Unit = {
    this.add(new CharacterEngine)
    this.add(new EnemyEngine(250, 400, enemy1MovePattern))
    this.add(new EnemyEngine(700, 400, enemy2MovePattern))
    this.add(new EnemyEngine(546, 162, enemy3MovePattern))
    this.add(new Background, BorderLayout.CENTER)
    this.setResizable(false)
    this.pack()
    this.setVisible(true)
    setTitle("WonderPol")
    setLocationRelativeTo(null)
    setResizable(false)
    setDefaultCloseOperation(3)

  }
}