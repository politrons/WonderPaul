package com.politrons.sprites

import java.awt.image.BufferedImage
import java.awt.{Image, RenderingHints}
import javax.swing.ImageIcon


class Enemy() {

  private var frame = 0
  private var dx = 0
  private var dy = 0
  private var x = 250
  private var y = 500
  private var width = 0
  private var height = 0
  var image: Image = null
  var imageIcon: ImageIcon = null

  val images = Map(
    "left-" + 1 -> new ImageIcon("src/main/resources/pirate-left-1.png"),
    "left-" + 2 -> new ImageIcon("src/main/resources/pirate-left-2.png"),
    "right-" + 1 -> new ImageIcon("src/main/resources/pirate-right-1.png"),
    "right-" + 2 -> new ImageIcon("src/main/resources/pirate-right-2.png"),
    "up-" + 1 -> new ImageIcon("src/main/resources/pirate-up-1.png"),
    "up-" + 2 -> new ImageIcon("src/main/resources/pirate-up-2.png"),
    "down-" + 1 -> new ImageIcon("src/main/resources/pirate-down-1.png"),
    "down-" + 2 -> new ImageIcon("src/main/resources/pirate-down-2.png")
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

  def getX: Int = x

  def getY: Int = y


  private def increaseFrame = {
    if (frame == 2) frame = 1
    else frame += 1
    frame
  }
}