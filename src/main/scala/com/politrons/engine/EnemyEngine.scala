package com.politrons.engine

import com.politrons.sprites.Enemy

import java.awt._
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing._


class EnemyEngine() extends JLabel with ActionListener {

  val enemy = new Enemy()

  init()

  private def init(): Unit = {
    setFocusable(true)
    setIcon(enemy.imageIcon)
    setSize(this.getPreferredSize)
    setFrameDelay()
  }

  private def setFrameDelay(): Unit = {
    val DELAY = 15
    val timer = new Timer(DELAY, this)
    timer.start()
  }

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    setIcon(enemy.imageIcon)
    println(s"Position X:${enemy.getX} Y:${enemy.getY}")
    setLocation(enemy.getX, enemy.getY)
  }
}