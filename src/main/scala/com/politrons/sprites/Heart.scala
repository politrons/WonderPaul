package com.politrons.sprites

import com.politrons.sprites.SpriteUtils.scaleImage

import java.awt.Image
import javax.swing.ImageIcon

class Heart(var x:Integer, var y:Integer) {

  var imageIcon: ImageIcon = new ImageIcon("src/main/resources/level/heart.png")
  var image: Image = imageIcon.getImage

  loadImage()

  private def loadImage(): Unit = {
    image = scaleImage(image, 40, 40)
    imageIcon = new ImageIcon(image)
  }

}