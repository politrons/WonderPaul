package com.politrons.sprites

import com.politrons.sprites.SpriteUtils.{changeImageIcon, scaleImage}

import java.awt.Image
import javax.swing.ImageIcon


class Thunderbolt(var x: Integer = 0, var y: Integer = 0) {

  var imageIcon: ImageIcon = _
  var image: Image = _

  val images = Map(
    "left" -> new ImageIcon("src/main/resources/thunderbolt/thunderbolt-left.png"),
    "right" -> new ImageIcon("src/main/resources/thunderbolt/thunderbolt-right.png"),
    "up" -> new ImageIcon("src/main/resources/thunderbolt/thunderbolt-up.png"),
    "down" -> new ImageIcon("src/main/resources/thunderbolt/thunderbolt-down.png"),
  )

  loadImage()

  private def loadImage(): Unit = {
    image = scaleImage(image, 40, 40)
    imageIcon = new ImageIcon(image)
  }

}