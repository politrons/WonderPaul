package com.politrons.engine

import com.politrons.sprites.Thunderbolt

import java.util.concurrent.Executors
import javax.swing._
import scala.concurrent.{ExecutionContext, Future}

class ThunderboltEngine() extends JLabel {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val thunderbolt = new Thunderbolt(250,250)

  init()

  private def init(): Unit = {
    setFocusable(true)
    setIcon(thunderbolt.imageIcon)
    setSize(this.getPreferredSize)
    setLocation(thunderbolt.x, thunderbolt.y)
  }

  def directionOfThunderbolt(characterX: Int, characterY: Int): Future[Unit] = {
    Future {
      //TODO:Refactor Hit someone or end of map
      thunderbolt.x = characterX
      thunderbolt.y = characterY
      while (true) {
        println(s"########### Thunderbolt X:${thunderbolt.x} Y:${thunderbolt.y}")

        thunderbolt.x += 10
        setThunderboltPosition()
        Thread.sleep(100)
        //        thunderbolt.y = characterY + 2
      }
    }
  }

  private def setThunderboltPosition(): Unit = {
    setIcon(thunderbolt.imageIcon)
    setLocation(thunderbolt.x, thunderbolt.y)
  }
}