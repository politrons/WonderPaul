package com.politrons.engine

import com.politrons.sprites.Character

import java.awt.event.{ActionEvent, ActionListener, KeyAdapter, KeyEvent}
import javax.swing._


class CharacterEngine(var live:Int=3) extends JLabel with ActionListener {

  val character = new Character()

  init()

  private def init(): Unit = {
    addKeyListener(new KeyListener(character))
    setFocusable(true)
    setIcon(character.imageIcon)
    setSize(this.getPreferredSize)
    setFrameDelay()
  }

  private def setFrameDelay(): Unit = {
    val DELAY = 15
    val timer = new Timer(DELAY, this)
    timer.start()
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    character.move()
    setIcon(character.imageIcon)
    println(s"Character Position X:${character.x} Y:${character.y}")
    setLocation(character.x, character.y)
  }

  private class KeyListener(character: Character) extends KeyAdapter {
    override def keyReleased(e: KeyEvent): Unit = {
      character.keyReleased(e)
    }

    override def keyPressed(e: KeyEvent): Unit = {
      character.keyPressed(e)
    }
  }

}