package com.politrons.engine

import com.politrons.sprites.Character

import java.awt.event.{ActionEvent, ActionListener, KeyAdapter, KeyEvent}
import java.util.concurrent.Executors
import javax.swing._
import scala.concurrent.{ExecutionContext, Future}


class CharacterEngine(val thunderboltEngine: ThunderboltEngine,
                      var live:Int=3) extends JLabel with ActionListener {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val character = new Character(thunderboltEngine)

  init()

  private def init(): Unit = {
    addKeyListener(new KeyListener(character))
    setFocusable(true)
    setIcon(character.imageIcon)
    setSize(this.getPreferredSize)
    setFrameDelay()
  }

  private def setFrameDelay(): Unit = {
    val DELAY = 15
    val timer = new Timer(DELAY, this)
    timer.start()
  }

  override def actionPerformed(e: ActionEvent): Unit = {
    character.move()
    setIcon(character.imageIcon)
    println(s"Character Position X:${character.x} Y:${character.y}")
    setLocation(character.x, character.y)
  }

  private class KeyListener(character: Character) extends KeyAdapter {
    override def keyReleased(e: KeyEvent): Unit = {
      character.keyReleased(e)
    }

    override def keyPressed(e: KeyEvent): Unit = {
      character.keyPressed(e)
    }
  }

  /**
   * Move character to the initial position and make an effect of reset
   */
  def characterDeadAnimation(): Unit = {
    Future{
      character.x = 540
      character.y = 78
      0 to 50 foreach { _ =>
        setIcon(null)
        Thread.sleep(10)
        setIcon(character.imageIcon)
        Thread.sleep(10)
      }
    }
  }

}