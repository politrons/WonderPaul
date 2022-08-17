package com.politrons.sprites

import java.awt.event.KeyEvent
import java.awt.image.BufferedImage
import java.awt.{Image, RenderingHints}
import javax.swing.ImageIcon


class Enemy(var x:Integer, var y:Integer) {

  private var frame = 0
  private var dx = 0
  private var dy = 0
  private var width = 0
  private var height = 0
  var image: Image = null
  var imageIcon: ImageIcon = null

  val images = Map(
    "left-" + 1 -> new ImageIcon("src/main/resources/enemy/enemy-left-1.png"),
    "left-" + 2 -> new ImageIcon("src/main/resources/enemy/enemy-left-2.png"),
    "right-" + 1 -> new ImageIcon("src/main/resources/enemy/enemy-right-1.png"),
    "right-" + 2 -> new ImageIcon("src/main/resources/enemy/enemy-right-2.png"),
    "up-" + 1 -> new ImageIcon("src/main/resources/enemy/enemy-up-1.png"),
    "up-" + 2 -> new ImageIcon("src/main/resources/enemy/enemy-up-2.png"),
    "down-" + 1 -> new ImageIcon("src/main/resources/enemy/enemy-down-1.png"),
    "down-" + 2 -> new ImageIcon("src/main/resources/enemy/enemy-down-2.png")
  )

  loadImage()

  private def loadImage(): Unit = {
    imageIcon = images("left-1")
    image = imageIcon.getImage
    image = scaleImage(image, 40, 40)
    imageIcon = new ImageIcon(image)
    width = image.getWidth(null)
    height = image.getHeight(null)
  }

  private def scaleImage(srcImg: Image, w: Int, h: Int): Image = {
    val resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB)
    val g2 = resizedImg.createGraphics
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
    g2.drawImage(srcImg, 0, 0, w, h, null)
    g2.dispose()
    resizedImg
  }

  private def extractImage(frame: String): Unit = {
    imageIcon = new ImageIcon(scaleImage(images(frame).getImage, 40, 40))
  }

  def move(): Unit = {
    x += dx
    y += dy
  }

  def artificialIntelligenceKeyPressed(e: String): Unit = {
    e match {
      case "left" =>
        dx = -5
        extractImage("left-" + increaseFrame)
      case "right" =>
        dx = 5
        extractImage("right-" + increaseFrame)
      case "up" =>
        dy = -5
        extractImage("up-" + increaseFrame)
      case "down" =>
        dy = 5
        extractImage("down-" + increaseFrame)
    }
  }

  private def increaseFrame = {
    if (frame == 2) frame = 1
    else frame += 1
    frame
  }
}