package com.politrons

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
    this.add(new Level)
    this.add(new Background, BorderLayout.CENTER)
    this.setResizable(false)
    this.pack()
    this.setVisible(true)
    setTitle("WonderPol")
    setSize(975, 650)
    setLocationRelativeTo(null)
    setResizable(false)
  }
}