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
  val characterEngine = new CharacterEngine(thunderboltEngine)
  val gameOverEngine = new GameOverEngine()
  val heart1Engine = new HeartEngine(100, 10)
  val heart2Engine = new HeartEngine(70, 10)
  val heart3Engine = new HeartEngine(40, 10)
  val enemyEngine1 = new EnemyEngine("Enemy1", 250, 400,
    enemy1MovePattern, characterEngine, heart1Engine, heart2Engine, heart3Engine, gameOverEngine, thunderboltEngine)
  val enemyEngine2 = new EnemyEngine("Enemy1", 700, 400,
    enemy1MovePattern, characterEngine, heart1Engine, heart2Engine, heart3Engine, gameOverEngine, thunderboltEngine)
  val enemyEngine3 = new EnemyEngine("Enemy1", 546, 162,
    enemy1MovePattern, characterEngine, heart1Engine, heart2Engine, heart3Engine, gameOverEngine, thunderboltEngine)

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
    setTitle("WonderPaul")
    setLocationRelativeTo(null)
    setResizable(false)
    setDefaultCloseOperation(3)
  }

}