package com.politrons.sprites

import com.politrons.engine.ThunderboltEngine
import com.politrons.sprites.SpriteUtils.{changeImageIcon, scaleImage}

import java.awt.Image
import java.awt.event.KeyEvent
import javax.swing.ImageIcon

/**
 * All logic related with the movement of the sprite [Character]
 */
class Character(thunderboltEngine: ThunderboltEngine,
                var x: Integer = 540,
                var y: Integer = 78) {

  private var frame = 0
  private var dx = 0
  private var dy = 0
  var orientation = ""
  var image: Image = _
  var imageIcon: ImageIcon = _

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
  }

  def move(): Unit = {
    if (!isAreaNotAvailable) {
      x += dx
      y += dy
    }
  }

  val xMapCollision = List(
    Tuple2(320, Tuple2(360, 200)),
    Tuple2(380, Tuple2(60, 160)),
    Tuple2(376, Tuple2(446, 506)),
    Tuple2(726, Tuple2(342, 400)),
    Tuple2(458, Tuple2(310, 352)),


  )

  val yMapCollision = List(
    Tuple2(300, Tuple2(400, 460))

  )

  /**
   * Check if the main character can go through over an area in the map.
   * TODO:Work in progress
   */
  private def isAreaNotAvailable: Boolean = {
    val tmpX = x + dx
    val tmpY = y + dy
    val xCollision: Boolean = xMapCollision.exists(tuple => {
      val colX = tuple._1;
      val tupleY = tuple._2
      return Math.abs(colX - tmpX) <= 10 && (tmpY <= tupleY._1) && (tmpY >= tupleY._2)
    })
    val yCollision: Boolean = yMapCollision.exists(tuple => {
      val colY = tuple._1;
      val tupleX = tuple._2
      return Math.abs(colY - tmpY) <= 10 && (tmpX <= tupleX._1) && (tmpX >= tupleX._2)
    })
    xCollision || yCollision
  }

  private def increaseFrame(): Int = {
    if (frame == 2) frame = 1
    else frame += 1
    frame
  }

  def keyPressed(e: KeyEvent): Unit = {
    e.getKeyCode match {
      case KeyEvent.VK_LEFT =>
        dx = -2
        orientation = "left"
        imageIcon = changeImageIcon(images(s"$orientation-" + increaseFrame))
      case KeyEvent.VK_RIGHT =>
        dx = 2
        orientation = "right"
        imageIcon = changeImageIcon(images(s"$orientation-" + increaseFrame))
      case KeyEvent.VK_UP =>
        dy = -2
        orientation = "up"
        imageIcon = changeImageIcon(images(s"$orientation-" + increaseFrame))
      case KeyEvent.VK_DOWN =>
        dy = 2
        orientation = "down"
        imageIcon = changeImageIcon(images(s"$orientation-" + increaseFrame))
      case KeyEvent.VK_SPACE =>
        thunderboltEngine.directionOfThunderbolt(orientation, x, y)
      case _ => println(s"Key not implemented")
    }
  }

  def keyReleased(e: KeyEvent): Unit = {
    e.getKeyCode match {
      case KeyEvent.VK_LEFT => dx = 0
      case KeyEvent.VK_RIGHT => dx = 0
      case KeyEvent.VK_UP => dy = 0
      case KeyEvent.VK_DOWN => dy = 0
      case KeyEvent.VK_SPACE => ()
    }
  }
}