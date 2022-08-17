package com.politrons.engine

import com.politrons.sprites.Enemy

import java.awt._
import java.awt.event.{ActionEvent, ActionListener, KeyEvent}
import java.util.concurrent.{Executor, Executors}
import javax.swing._
import scala.collection._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class EnemyEngine() extends JLabel {

  val enemy = new Enemy()

  val movePattern: Seq[String] = immutable.List(
    "left","left","left","left","left","left",
    "right","right","right","right","right","right"
  )

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
        movePattern.foreach(move =>{
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