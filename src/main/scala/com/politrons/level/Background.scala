package com.politrons.level

import com.politrons.engine.CharacterEngine

import javax.swing._

/**
 * We set the background, and we set the JLabel of the [CharacterEngine]
 */
class Background(characterEngine: CharacterEngine) extends JLabel {
  val imageIcon = new ImageIcon("src/main/resources/background.png")
  this.setIcon(imageIcon)
  this.add(characterEngine)
}