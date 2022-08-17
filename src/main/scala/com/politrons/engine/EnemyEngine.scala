package com.politrons.engine

import com.politrons.sprites.Enemy

import java.awt._
import java.awt.event.{ActionEvent, ActionListener, KeyEvent}
import java.util.concurrent.{Executor, Executors}
import javax.swing._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class EnemyEngine() extends JLabel {

  val enemy = new Enemy()

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
        enemy.artificialIntelligenceKeyPressed("left")
        setEnemyPosition
        Thread.sleep(500)
        enemy.artificialIntelligenceKeyPressed("left")
        setEnemyPosition
        Thread.sleep(500)
        enemy.artificialIntelligenceKeyPressed("left")
        setEnemyPosition
        Thread.sleep(500)
        enemy.artificialIntelligenceKeyPressed("up")
        setEnemyPosition
        Thread.sleep(500)
        enemy.artificialIntelligenceKeyPressed("down")
        setEnemyPosition
        Thread.sleep(500)
        enemy.artificialIntelligenceKeyPressed("right")
        setEnemyPosition
        Thread.sleep(500)
        enemy.artificialIntelligenceKeyPressed("right")
        setEnemyPosition
        Thread.sleep(500)
        enemy.artificialIntelligenceKeyPressed("right")
        setEnemyPosition
        Thread.sleep(500)
      }
    }
  }

  private def setEnemyPosition = {
    setIcon(enemy.imageIcon)
    println(s"Position X:${enemy.getX} Y:${enemy.getY}")
    setLocation(enemy.getX, enemy.getY)
  }
}