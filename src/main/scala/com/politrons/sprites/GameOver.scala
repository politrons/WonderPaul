package com.politrons.sprites

import java.awt.Image
import javax.swing.ImageIcon

class GameOver() {

  var imageIcon: ImageIcon = new ImageIcon("src/main/resources/level/game-over.jpeg")
  var image: Image = imageIcon.getImage

  loadImage()

  private def loadImage(): Unit = {
    imageIcon = new ImageIcon(image)
  }

}