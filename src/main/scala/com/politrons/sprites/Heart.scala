package com.politrons.sprites

import com.politrons.sprites.SpriteUtils.scaleImage

import java.awt.Image
import javax.swing.ImageIcon

class Heart(var x:Integer, var y:Integer) {

  val image: Image = scaleImage(new ImageIcon("src/main/resources/level/heart.png").getImage, 40, 40)
  val imageIcon: ImageIcon = new ImageIcon(image)

}