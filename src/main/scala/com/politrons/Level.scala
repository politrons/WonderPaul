package com.politrons

import java.awt._
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing._


class Level() extends JLabel with ActionListener {

  val character = new Character()

  init()

  private def init(): Unit ={
    addKeyListener(new KeyListener)
    setFocusable(true)
    this.setIcon(character.imageIcon)
    this.setLocation(40, 60)
    this.setSize(this.getPreferredSize)
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
    character.move()
    setIcon(character.imageIcon)
    println(s"Position X:${character.getX} Y:${character.getY}")
    setLocation(character.getX, character.getY)
  }

  private class KeyListener extends KeyAdapter {
    override def keyReleased(e: KeyEvent): Unit = {
      character.keyReleased(e)
    }

    override def keyPressed(e: KeyEvent): Unit = {
      character.keyPressed(e)
    }
  }
}