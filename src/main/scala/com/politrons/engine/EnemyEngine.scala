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
                  val characterEngine:CharacterEngine,
                  val heart1Engine: HeartEngine,
                  val heart2Engine: HeartEngine,
                  val heart3Engine: HeartEngine,
                  val gameOverEngine:GameOverEngine,
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
    collisionEngine()
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
    setLocation(enemy.x, enemy.y)
  }

  /**
   * As long as enemies are alive, we check constantly if any of the enemies of the map hit the main character.
   * Function to check if the character collision with an enemy.
   * In case of collision we reduce one heart in the level, and we set
   * the character like dead.
   * In case we lose all hearts the game is over.
   */
  private def collisionEngine() = {
    Future {
      val deviation = 10
      while (enemyAlive) {
        val xComp = Math.abs(characterEngine.character.x - enemy.x)
        val yComp = Math.abs(characterEngine.character.y - enemy.y)
        if (xComp <= deviation && yComp <= deviation) {
          characterEngine.live match {
            case 3 => heart3Engine.removeHeart()
            case 2 => heart2Engine.removeHeart()
            case 1 => heart1Engine.removeHeart(); gameOverEngine.setVisible(true)
          }
          characterEngine.live -= 1
          characterEngine.characterDeadAnimation()
        }
        Thread.sleep(100)
      }
    }
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