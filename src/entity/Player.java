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

  public final int screenX;
  public final int screenY;

  public Player(GamePanel gp, KeyHandler keyH) {
    this.gp = gp;
    this.keyH = keyH;

    // start player at centre of screen
    screenX = gp.screenWidth/2 - (gp.tileSize/2);
    screenY = gp.screenHeight/2 - (gp.tileSize/2);

    setDefaultValues();
    getPlayerImage();
  }

  // Constructor
  public void setDefaultValues() {
    // Sets player's info (vars from parent class Entity)
    worldX = gp.tileSize * 23;
    worldY = gp.tileSize * 21;
    speed = 2; // could use gp.playerSpeed instead
    direction = "forward";
    moving = false;
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
    if(keyH.upPressed == true || keyH.downPressed == true ||
    keyH.leftPressed == true || keyH.rightPressed == true) {

      moving = true;

      if(keyH.upPressed == true) {
          direction = "backward";
          worldY -= speed;
      }
      if (keyH.downPressed == true) {
        direction = "forward";
        worldY += speed;
      }
      if (keyH.leftPressed == true) {
        direction = "left";
        worldX -= speed;
      }
      if (keyH.rightPressed == true) {
        direction = "right";
        worldX += speed;
      }

      spriteCounter++;
      if(spriteCounter > 29) { // Instead of every 12 frames at 60FPS
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

    } else {
      moving = false;
      spriteCounter = 0;
    }

  }

  public void draw(Graphics2D g2) {

    BufferedImage image = null;

    if(moving == true) {
      switch(direction) {
        case "forward":
          if(spriteNum == 1) {
            image = forward1;
          } else if(spriteNum == 2) {
            image = forward;
          } else if(spriteNum == 3) {
            image = forward2;
          } else if(spriteNum == 4) {
            image = forward;
          }
          break;
        case "backward":
          if(spriteNum == 1) {
            image = backward1;
          } else if(spriteNum == 2) {
            image = backward;
          } else if(spriteNum == 3) {
            image = backward2;
          } else if(spriteNum == 4) {
            image = backward;
          }
          break;
        case "left":
          if(spriteNum == 1 || spriteNum == 3) {
            image = left1;
          } else if(spriteNum == 2 || spriteNum == 4) {
            image = left;
          }
          break;
        case "right":
          if(spriteNum == 1 || spriteNum == 3) {
            image = right1;
          } else if(spriteNum == 2 || spriteNum == 4) {
            image = right;
          }
          break;
      }
    } else if (moving == false){
      switch(direction) {
        case "forward":
          image = forward;
          break;
        case "backward":
          image = backward;
          break;
        case "left":
          image = left;
          break;
        case "right":
          image = right;
          break;
      }
    }

    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null); // image observer = null

  }
}
