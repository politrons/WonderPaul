package com.politrons.sprites

import com.politrons.sprites.SpriteUtils.{changeImageIcon, scaleImage}

import java.awt.Image
import java.awt.event.KeyEvent
import javax.swing.ImageIcon

class Character(var dead:Boolean=false) {

  private var frame = 0
  private var dx = 0
  private var dy = 0
  var x = 250
  var y = 250
  private var width = 0
  private var height = 0
  var image: Image = null
  var imageIcon: ImageIcon = null

  val images = Map(
    "left-" + 1 -> new ImageIcon("src/main/resources/character/pirate-left-1.png"),
    "left-" + 2 -> new ImageIcon("src/main/resources/character/pirate-left-2.png"),
    "right-" + 1 -> new ImageIcon("src/main/resources/character/pirate-right-1.png"),
    "right-" + 2 -> new ImageIcon("src/main/resources/character/pirate-right-2.png"),
    "up-" + 1 -> new ImageIcon("src/main/resources/character/pirate-up-1.png"),
    "up-" + 2 -> new ImageIcon("src/main/resources/character/pirate-up-2.png"),
    "down-" + 1 -> new ImageIcon("src/main/resources/character/pirate-down-1.png"),
    "down-" + 2 -> new ImageIcon("src/main/resources/character/pirate-down-2.png")
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

  def move(): Unit = {
    if(dead){
      x=250
      y=250
      dead=false
    }else{
      x += dx
      y += dy
    }
  }

  def keyPressed(e: KeyEvent): Unit = {
    e.getKeyCode match {
      case KeyEvent.VK_LEFT =>
        dx = -2
        imageIcon = changeImageIcon(images("left-" + increaseFrame))
      case KeyEvent.VK_RIGHT =>
        dx = 2
        imageIcon = changeImageIcon(images("right-" + increaseFrame))
      case KeyEvent.VK_UP =>
        dy = -2
        imageIcon = changeImageIcon(images("up-" + increaseFrame))
      case KeyEvent.VK_DOWN =>
        dy = 2
        imageIcon = changeImageIcon(images("down-" + increaseFrame))
      case _ => println(s"Key not implemented")
    }
  }

  private def increaseFrame = {
    if (frame == 2) frame = 1
    else frame += 1
    frame
  }

  def keyReleased(e: KeyEvent): Unit = {
    e.getKeyCode match {
      case KeyEvent.VK_LEFT => dx = 0
      case KeyEvent.VK_RIGHT => dx = 0
      case KeyEvent.VK_UP => dy = 0
      case KeyEvent.VK_DOWN => dy = 0
    }
  }
}