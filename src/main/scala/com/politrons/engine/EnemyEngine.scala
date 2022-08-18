package com.politrons.engine

import com.politrons.sprites.Enemy

import java.util.concurrent.Executors
import javax.swing._
import scala.collection._
import scala.concurrent.{ExecutionContext, Future}


class EnemyEngine(var name:String,
                  var xPos: Integer,
                  var yPos: Integer,
                  val movePattern: Seq[String]
                 ) extends JLabel {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val enemy = new Enemy(xPos, yPos)

  init()

  private def init(): Unit = {
    setFocusable(true)
    setIcon(enemy.imageIcon)
    setSize(this.getPreferredSize)
    artificialIntelligenceAction()
  }

  def artificialIntelligenceAction(): Future[Unit] = {
    Future {
      while (true) {
        movePattern.foreach(move => {
          enemy.artificialIntelligenceKeyPressed(move)
          setEnemyPosition()
          Thread.sleep(300)
        })
      }
    }
  }

  private def setEnemyPosition(): Unit = {
    enemy.move()
    setIcon(enemy.imageIcon)
    println(s"$name Position X:${enemy.x} Y:${enemy.y}")
    setLocation(enemy.x, enemy.y)
  }
}