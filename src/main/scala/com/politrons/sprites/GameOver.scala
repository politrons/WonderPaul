package com.politrons.sprites

import java.awt.Image
import javax.swing.ImageIcon

class GameOver() {

  private var width = 0
  private var height = 0
  var imageIcon: ImageIcon = new ImageIcon("src/main/resources/level/game-over.jpeg")
  var image: Image = imageIcon.getImage

  loadImage()

  private def loadImage(): Unit = {
    imageIcon = new ImageIcon(image)
    width = image.getWidth(null)
    height = image.getHeight(null)
  }

}