package entity;

import java.awt.image.BufferedImage;

/* Parent/Super class for all players, monsters and NPCs */
public class Entity {

  public int worldX, worldY;
  public int speed;

  public BufferedImage forward, forward1, forward2, backward, backward1,
    backward2, left, left1, right, right1;

  public String direction;
  public boolean moving;

  public int spriteCounter = 0;
  public int spriteNum = 1;

}
