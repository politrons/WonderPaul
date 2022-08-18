package com.politrons.engine

import com.politrons.sprites.Enemy

import java.util.concurrent.Executors
import javax.swing._
import scala.collection._
import scala.concurrent.{ExecutionContext, Future}

class EnemyEngine(var name: String,
                  var xPos: Integer,
                  var yPos: Integer,
                  val movePattern: Seq[String],
                  val thunderboltEngine: ThunderboltEngine,
                  var enemyAlive: Boolean = true
                 ) extends JLabel {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val enemy = new Enemy(xPos, yPos)

  init()

  private def init(): Unit = {
    setFocusable(true)
    setIcon(enemy.imageIcon)
    setSize(this.getPreferredSize)
    movePatternEnemyAction()
  }

  def movePatternEnemyAction(): Future[Unit] = {
    Future {
      while (enemyAlive) {
        movePattern
          .foreach(move => {
            if (enemyAlive) {
              enemy.applyEnemyMovement(move)
              setEnemyPosition()
              checkThunderboltCollision()
              Thread.sleep(100)
            }
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

  private def checkThunderboltCollision(): Unit = {
    val deviation = 10
    val charX = thunderboltEngine.thunderbolt.x
    val charY = thunderboltEngine.thunderbolt.y
    val xComp = Math.abs(charX - enemy.x)
    val yComp = Math.abs(charY - enemy.y)
    if (xComp <= deviation && yComp <= deviation) {
      enemyDeadAnimation()
      setLocation(0, 0)
      enemyAlive = false
    }
  }

  private def enemyDeadAnimation(): Unit = {
      enemy.x = 540
      enemy.y = 78
      0 to 50 foreach { _ =>
        setIcon(null)
        Thread.sleep(10)
        setIcon(enemy.imageIcon)
        Thread.sleep(10)
        setIcon(null)
        Thread.sleep(10)
      }
  }


}