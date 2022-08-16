package com.politrons

import java.awt.Image
import java.awt.event.KeyEvent
import java.util
import javax.swing.ImageIcon


class Character() {

  private var frame = 0
  private var dx = 0
  private var dy = 0
  private var x = 40
  private var y = 60
  private var w = 0
  private var h = 0
  var image: Image = null
  var imageIcon: ImageIcon = null

  val images = Map(
    "left-" + 1->new ImageIcon("src/main/resources/pirate-left-1.png"),
    "left-" + 2->new ImageIcon("src/main/resources/pirate-left-2.png"),
    "right-" + 1 ->new ImageIcon("src/main/resources/pirate-right-1.png"),
    "right-" + 2 -> new ImageIcon("src/main/resources/pirate-right-2.png"),
    "up-" + 1 -> new ImageIcon("src/main/resources/pirate-up-1.png"),
    "up-" + 2 -> new ImageIcon("src/main/resources/pirate-up-2.png"),
    "down-" + 1 ->new ImageIcon("src/main/resources/pirate-down-1.png"),
    "down-" + 2 ->  new ImageIcon("src/main/resources/pirate-down-2.png")
  )

  loadImage()

  private def loadImage(): Unit = {
    imageIcon = images("left-1")
    image = imageIcon.getImage
    w = image.getWidth(null)
    h = image.getHeight(null)
  }

  private def extractImage(frame: String): Unit = {
    imageIcon = images(frame)
  }

  def move(): Unit = {
    x += dx
    y += dy
  }

  def getX: Int = x

  def getY: Int = y

  def keyPressed(e: KeyEvent): Unit = {
    val key = e.getKeyCode
    if (key == KeyEvent.VK_LEFT) {
      dx = -2
      extractImage("left-" + increaseFrame)
    }
    if (key == KeyEvent.VK_RIGHT) {
      dx = 2
      extractImage("right-" + increaseFrame)
    }
    if (key == KeyEvent.VK_UP) {
      dy = -2
      extractImage("up-" + increaseFrame)
    }
    if (key == KeyEvent.VK_DOWN) {
      dy = 2
      extractImage("down-" + increaseFrame)
    }
  }

  private def increaseFrame = {
    if (frame == 2) frame = 1
    else frame += 1
    frame
  }

  def keyReleased(e: KeyEvent): Unit = {
    val key = e.getKeyCode
    if (key == KeyEvent.VK_LEFT) dx = 0
    if (key == KeyEvent.VK_RIGHT) dx = 0
    if (key == KeyEvent.VK_UP) dy = 0
    if (key == KeyEvent.VK_DOWN) dy = 0
  }
}