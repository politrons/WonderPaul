package com.politrons.sprites

import com.politrons.sprites.SpriteUtils.{changeImageIcon, scaleImage}

import java.awt.Image
import javax.swing.ImageIcon


class Thunderbolt(var x:Integer=0, var y:Integer=0) {

  var imageIcon: ImageIcon = new ImageIcon("src/main/resources/level/thunderbolt.png")
  var image: Image = imageIcon.getImage

  loadImage()

  private def loadImage(): Unit = {
    image = scaleImage(image, 40, 40)
    imageIcon = new ImageIcon(image)
  }

}