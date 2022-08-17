package com.politrons.sprites

import java.awt.image.BufferedImage
import java.awt.{Image, RenderingHints}
import javax.swing.ImageIcon


class Heart(var x:Integer, var y:Integer) {

  private var width = 0
  private var height = 0
  var image: Image = new ImageIcon("src/main/resources/live/heart.png").getImage
  var imageIcon: ImageIcon = new ImageIcon("src/main/resources/live/heart.png")

  loadImage()

  private def loadImage(): Unit = {
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

}