package entity;

import java.awt.Color;
import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity {

  GamePanel gp;
  KeyHandler keyH;

  public Player(GamePanel gp, KeyHandler keyH) {
    this.gp = gp;
    this.keyH = keyH;
    setDefaultValues();
    getPlayerImage();
  }

  // Constructor
  public void setDefaultValues() {
    // Sets player's info (vars from parent class Entity)
    x = 100;
    y = 100;
    speed = 2; // could use gp.playerSpeed instead
    direction = "forward";
  }

  public void getPlayerImage() {
    System.out.println("Loading player images");
    try {
      forward = ImageIO.read(getClass().getResourceAsStream("/player/player_standing.png"));
      forward1 = ImageIO.read(getClass().getResourceAsStream("/player/player_walking_forward_1.png"));
      forward2 = ImageIO.read(getClass().getResourceAsStream("/player/player_walking_forward_2.png"));
      backward = ImageIO.read(getClass().getResourceAsStream("/player/player_standing_back.png"));
      backward1 = ImageIO.read(getClass().getResourceAsStream("/player/player_walking_away_1.png"));
      backward2 = ImageIO.read(getClass().getResourceAsStream("/player/player_walking_away_2.png"));
      left = ImageIO.read(getClass().getResourceAsStream("/player/player_standing_left.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_walking_left.png"));
      right = ImageIO.read(getClass().getResourceAsStream("/player/player_standing_right.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_walking_right.png"));
      System.out.println("Player images loaded");
    } catch(IOException e) {
      System.out.println("Player images not loaded");
      e.printStackTrace();
    }
  }

  public void update() {
    if(keyH.upPressed == true) {
      direction = "backward";
      y -= speed;
    }
    if (keyH.downPressed == true) {
      direction = "forward";
      y += speed;
    }
    if (keyH.leftPressed == true) {
      direction = "left";
      x -= speed;
    }
    if (keyH.rightPressed == true) {
      direction = "right";
      x += speed;
    }

    spriteCounter++;
    if(spriteCounter > 24) { // Instead of every 10 frames at 60FPS
      if(spriteNum == 1) {
        spriteNum = 2;
      } else if(spriteNum == 2) {
        spriteNum = 3;
      } else if(spriteNum == 3) {
        spriteNum = 4;
      } else if(spriteNum == 4) {
        spriteNum = 1;
      }
      spriteCounter = 0;
    }
  }

  public void draw(Graphics2D g2) {
    // g2.setColor(Color.white); // makes rectangle white
    // g2.fillRect(x, y, gp.tileSize, gp.tileSize); // sets posn and size of rectangle

    BufferedImage image = null;

    switch(direction) {
      case "forward":
        if(spriteNum == 1) {
          image = forward;
        } else if(spriteNum == 2) {
          image = forward1;
        } else if(spriteNum == 3) {
          image = forward;
        } else if(spriteNum == 4) {
          image = forward2;
        }
        break;
      case "backward":
        if(spriteNum == 1) {
          image = backward;
        } else if(spriteNum == 2) {
          image = backward1;
        } else if(spriteNum == 3) {
          image = backward;
        } else if(spriteNum == 4) {
          image = backward2;
        }
        break;
      case "left":
        if(spriteNum == 1) {
          image = left;
        } else if(spriteNum == 2) {
          image = left1;
        } else if(spriteNum == 3) {
          image = left;
        } else if(spriteNum == 4) {
          image = left1;
        }
        break;
      case "right":
        if(spriteNum == 1) {
          image = right;
        } else if(spriteNum == 2) {
          image = right1;
        } else if(spriteNum == 3) {
          image = right;
        } else if(spriteNum == 4) {
          image = right1;
        }
        break;
      default:
        if(image == forward1 || image == forward2) {
          image = forward;
        } else if(image == backward1 || image == backward2) {
          image = backward;
        } else if(image == left || image == left1) {
          image = left;
        } else if(image == right || image == right1) {
          image = right;
        }
    }

    g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null); // image observer = null

  }
}
