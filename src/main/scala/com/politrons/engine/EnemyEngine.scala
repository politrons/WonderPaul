package com.politrons.engine

import com.politrons.sprites.Enemy

import java.awt._
import javax.swing._
import scala.collection._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class EnemyEngine(var xPos: Integer,
                  var yPos: Integer,
                  val movePattern: Seq[String]
                 ) extends JLabel {

  val enemy = new Enemy(xPos, yPos)

  init()

  private def init(): Unit = {
    setFocusable(true)
    setIcon(enemy.imageIcon)
    setSize(this.getPreferredSize)
    artificialIntelligenceAction()
  }

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
  }

  def artificialIntelligenceAction(): Future[Unit] = {
    Future {
      while (true) {
        movePattern.foreach(move => {
          enemy.artificialIntelligenceKeyPressed(move)
          setEnemyPosition()
          Thread.sleep(500)
        })
      }
    }
  }

  private def setEnemyPosition(): Unit = {
    enemy.move()
    setIcon(enemy.imageIcon)
    println(s"Enemy Position X:${enemy.x} Y:${enemy.y}")
    setLocation(enemy.x, enemy.y)
  }
}