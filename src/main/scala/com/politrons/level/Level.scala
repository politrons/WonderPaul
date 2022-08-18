package com.politrons.level

import com.politrons.engine._
import com.politrons.level.EnemyPatterns.{enemy1MovePattern, enemy2MovePattern, enemy3MovePattern}
import com.politrons.sprites.Enemy

import java.awt._
import java.util.concurrent.Executors
import javax.swing._
import scala.concurrent.{ExecutionContext, Future}

/**
 * Main JFrame class that contains all JLabel of the game known as [engines]
 */
class Level extends JFrame {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val thunderboltEngine = new ThunderboltEngine()
  val gameOverEngine = new GameOverEngine()
  val heart1Engine = new HeartEngine(100, 10)
  val heart2Engine = new HeartEngine(70, 10)
  val heart3Engine = new HeartEngine(40, 10)
  val enemyEngine1 = new EnemyEngine("Enemy1", 250, 400, enemy1MovePattern,thunderboltEngine)
  val enemyEngine2 = new EnemyEngine("Enemy1", 700, 400, enemy2MovePattern,thunderboltEngine)
  val enemyEngine3 = new EnemyEngine("Enemy1", 546, 162, enemy3MovePattern,thunderboltEngine)
  val characterEngine = new CharacterEngine(thunderboltEngine)

  initGame()

  private def initGame(): Unit = {
    this.add(gameOverEngine)
    this.add(heart1Engine)
    this.add(heart2Engine)
    this.add(heart3Engine)
    this.add(enemyEngine1)
    this.add(enemyEngine2)
    this.add(enemyEngine3)
    this.add(thunderboltEngine)
    this.add(new Background(characterEngine), BorderLayout.CENTER)
    this.setResizable(false)
    this.pack()
    this.setVisible(true)
    setTitle("WonderPol")
    setLocationRelativeTo(null)
    setResizable(false)
    setDefaultCloseOperation(3)
    collisionEngine()
  }

  /**
   * As long as enemies are alive, we check constantly if any of the enemies of the map hit the main character
   */
  private def collisionEngine() = {
    Future {
      val deviation = 10
      while (enemyEngine1.enemyAlive && enemyEngine2.enemyAlive && enemyEngine3.enemyAlive) {
        checkCharacterEnemyCollision(deviation, enemyEngine1.enemy)
        checkCharacterEnemyCollision(deviation, enemyEngine2.enemy)
        checkCharacterEnemyCollision(deviation, enemyEngine3.enemy)
        Thread.sleep(100)
      }
    }
  }

  /**
   * Function to check if the character collision with an enemy.
   * In case of collision we reduce one heart in the level, and we set
   * the character like dead.
   * In case we lose all hearts the game is over.
   */
  private def checkCharacterEnemyCollision(deviation: Int, enemy1: Enemy): Unit = {
    val charX = characterEngine.character.x
    val charY = characterEngine.character.y
    val xComp = Math.abs(charX - enemy1.x)
    val yComp = Math.abs(charY - enemy1.y)
    if (xComp <= deviation && yComp <= deviation) {
      characterEngine.live match {
        case 3 => heart3Engine.removeHeart()
        case 2 => heart2Engine.removeHeart()
        case 1 => heart1Engine.removeHeart(); gameOverEngine.setVisible(true)
      }
      characterEngine.live -= 1
      characterEngine.characterDeadAnimation()
    }
  }

}