package com.politrons

import javax.swing._


class Background() extends JLabel {
  val imageIcon = new ImageIcon("src/main/resources/background.png")
  this.setIcon(imageIcon)
  this.add(new Level)
}