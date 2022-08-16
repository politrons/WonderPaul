package com.politrons

import com.politrons.engine.{CharacterEngine, EnemyEngine}

import java.awt._
import javax.swing._


object Main extends App {
  EventQueue.invokeLater(() => {
    val ex = new Main()
    ex.setVisible(true)
  })
}

class Main extends JFrame {

  initGame()

  private def initGame(): Unit = {
    this.add(new CharacterEngine)
    this.add(new EnemyEngine)
    this.add(new Background, BorderLayout.CENTER)
    this.setResizable(false)
    this.pack()
    this.setVisible(true)
    setTitle("WonderPol")
    setLocationRelativeTo(null)
    setResizable(false)
    setDefaultCloseOperation(3)

  }
}