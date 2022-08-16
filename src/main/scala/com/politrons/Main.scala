package com.politrons

import java.awt._
import java.io.IOException
import javax.swing._


object Main {
  def main(args: Array[String]): Unit = {
    EventQueue.invokeLater(() => {
        val ex = new Main()
        ex.setVisible(true)
    })
  }
}

class Main extends JFrame {
  initGame()

  @throws[IOException]
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