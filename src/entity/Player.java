package entity;

import java.awt.Color;
import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics2D;

public class Player extends Entity {

  GamePanel gp;
  KeyHandler keyH;

  public Player(GamePanel gp, KeyHandler keyH) {
    this.gp = gp;
    this.keyH = keyH;
    setDefaultValues();
  }

  // Constructor
  public void setDefaultValues() {
    // Sets player's info (vars from parent class Entity)
    x = 100;
    y = 100;
    speed = 2; // could use gp.playerSpeed instead
  }

  public void update() {
    if(keyH.upPressed == true) {
      y -= speed;
    }
    if (keyH.downPressed == true) {
      y += speed;
    }
    if (keyH.leftPressed == true) {
      x -= speed;
    }
    if (keyH.rightPressed == true) {
      x += speed;
    }
  }

  public void draw(Graphics2D g2) {
    g2.setColor(Color.white); // makes rectangle white
    g2.fillRect(x, y, gp.tileSize, gp.tileSize); // sets posn and size of rectangle
  }
}
