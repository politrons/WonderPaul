package com.politrons.engine

import com.politrons.sprites.{GameOver}

import javax.swing._

class GameOverEngine() extends JLabel {

  val gameOver = new GameOver()

  init()

  private def init(): Unit = {
    setVisible(false)
    setFocusable(true)
    setIcon(gameOver.imageIcon)
    setSize(this.getPreferredSize)
    setLocation(100,100)
  }
}