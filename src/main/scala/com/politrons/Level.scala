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

  val heart1 = new HeartEngine(100,10)
  val heart2 = new HeartEngine(70,10)
  val heart3 = new HeartEngine(40,10)
  val enemyEngine1 = new EnemyEngine("Enemy1", 250, 400, enemy1MovePattern)
  val enemyEngine2 = new EnemyEngine("Enemy1",700, 400, enemy2MovePattern)
  val enemyEngine3 = new EnemyEngine("Enemy1",546, 162, enemy3MovePattern)
  val characterEngine = new CharacterEngine

  initGame()

  private def initGame(): Unit = {
    this.add(heart1)
    this.add(heart2)
    this.add(heart3)
    this.add(enemyEngine1)
    this.add(enemyEngine2)
    this.add(enemyEngine3)
    this.add(new Background(characterEngine), BorderLayout.CENTER)
    this.setResizable(false)
    this.pack()
    this.setVisible(true)
    setTitle("WonderPol")
    setLocationRelativeTo(null)
    setResizable(false)
    setDefaultCloseOperation(3)
    collisionEngine()
  }



  private def collisionEngine() = {
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

          characterEngine.live match {
            case 3 => heart3.setVisible(false)
            case 2 => heart2.setVisible(false)
            case 1 => heart1.setVisible(false)

          }
          characterEngine.live-=1
          characterEngine.character.dead=true

          println("###############BOOOOOOOOOOMMMMM#################")
        }
        Thread.sleep(500)
      }
    }
  }
}