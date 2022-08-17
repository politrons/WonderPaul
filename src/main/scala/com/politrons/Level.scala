package com.politrons

import com.politrons.engine.{CharacterEngine, EnemyEngine, HeartEngine}

import java.awt._
import javax.swing._
import scala.collection.{Seq, immutable}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


object Level extends App {
  EventQueue.invokeLater(() => {
    val ex = new Level()
    ex.setVisible(true)
  })
}

class Level extends JFrame {


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
    val heart1 = new HeartEngine(100,10)
    this.add(heart1)
    val heart2 = new HeartEngine(70,10)
    this.add(heart2)
    val heart3 = new HeartEngine(40,10)
    this.add(heart3)
    val enemyEngine1 = new EnemyEngine("Enemy1", 250, 400, enemy1MovePattern)
    this.add(enemyEngine1)
    val enemyEngine2 = new EnemyEngine("Enemy1",700, 400, enemy2MovePattern)
    this.add(enemyEngine2)
    val enemyEngine3 = new EnemyEngine("Enemy1",546, 162, enemy3MovePattern)
    this.add(enemyEngine3)
    val characterEngine = new CharacterEngine
    this.add(new Background(characterEngine), BorderLayout.CENTER)
    this.setResizable(false)
    this.pack()
    this.setVisible(true)
    setTitle("WonderPol")
    setLocationRelativeTo(null)
    setResizable(false)
    setDefaultCloseOperation(3)
    collisionEngine(enemyEngine1, enemyEngine2, enemyEngine3, characterEngine)
  }

  private def collisionEngine(enemyEngine1: EnemyEngine,
                              enemyEngine2: EnemyEngine,
                              enemyEngine3: EnemyEngine,
                              characterEngine: CharacterEngine) = {
    Future {
      val deviation = 10
      while (true) {
        val charX = characterEngine.character.x
        val charY = characterEngine.character.y

        val enemy1 = enemyEngine1.enemy
        val enemy2 = enemyEngine2.enemy
        val enemy3 = enemyEngine3.enemy

        val xComp = Math.abs(charX - enemy1.x)
        val yComp = Math.abs(charY - enemy1.y)
        println(s"xComp:$xComp")
        println(s"yComp:$yComp")

        if (xComp <= deviation && yComp <= deviation) {
          println("###############BOOOOOOOOOOMMMMM#################")
        }
        Thread.sleep(500)
      }
    }
  }
}