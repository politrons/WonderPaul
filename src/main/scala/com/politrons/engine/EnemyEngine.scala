package com.politrons.engine

import com.politrons.sprites.Enemy

import java.util.concurrent.Executors
import javax.swing._
import scala.collection._
import scala.concurrent.{ExecutionContext, Future}


class EnemyEngine(var name:String,
                  var xPos: Integer,
                  var yPos: Integer,
                  val movePattern: Seq[String],
                  val thunderboltEngine:ThunderboltEngine
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
      val deviation = 10
      var enemyAlive=true
      while (enemyAlive) {
        movePattern.foreach(move => {
          enemy.applyEnemnyMovement(move)
          //check collision
          val charX =  thunderboltEngine.thunderbolt.x
          val charY =  thunderboltEngine.thunderbolt.y
          val xComp = Math.abs(charX - enemy.x)
          val yComp = Math.abs(charY - enemy.y)
          if (xComp <= deviation && yComp <= deviation) {
            enemy.x=0
            enemy.y=0
            enemyAlive=false
          }
          setEnemyPosition()
          Thread.sleep(100)
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