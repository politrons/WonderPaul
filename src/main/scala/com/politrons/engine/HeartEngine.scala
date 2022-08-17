package com.politrons.engine

import com.politrons.sprites.Heart
import javax.swing._


class HeartEngine(var xPos: Integer,
                  var yPos: Integer,
                  var heartDisable:Boolean=false) extends JLabel {

  val heart = new Heart(xPos, yPos)

  init()

  private def init(): Unit = {
    setFocusable(true)
    setIcon(heart.imageIcon)
    setSize(this.getPreferredSize)
    setLocation(heart.x, heart.y)
  }
}