package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

  GamePanel gp;
  Tile[] tile;
  int mapTileNum[][]; // holds the map data

  public TileManager(GamePanel gp) {
    this.gp = gp;

    tile = new Tile[10]; // 10 different types of tile
    mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

    getTileImage();
    loadMap("/maps/world01.txt");
  }

  public void getTileImage() {
    try {
      tile[0] = new Tile();
      tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

      tile[1] = new Tile();
      tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

      tile[2] = new Tile();
      tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

      tile[3] = new Tile();
      tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

      tile[4] = new Tile();
      tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));

      tile[5] = new Tile();
      tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  // Loads map from text file into the array mapTileNum
  public void loadMap(String filePath) {
    try {
      InputStream is = getClass().getResourceAsStream(filePath);
      BufferedReader br = new BufferedReader(new InputStreamReader(is)); // format to read txt file

      int col = 0;
      int row = 0;

      while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
        String line = br.readLine(); // read line from txt file

        while(col < gp.maxWorldCol) {
          String numbers[] = line.split(" "); // split line into array of strings

          int num = Integer.parseInt(numbers[col]);

          mapTileNum[col][row] = num;
          col++;
        }

        if(col == gp.maxWorldCol) {
          col = 0;
          row++;
        }
      }
      br.close();

    } catch(Exception e) {

    }
  }

  public void draw(Graphics2D g2) {
    int worldCol = 0;
    int worldRow = 0;

    // Loop to draw tiles over entire screen
    while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

      int tileNum = mapTileNum[worldCol][worldRow];

      int worldX = worldCol * gp.tileSize;
      int worldY = worldRow * gp.tileSize;
      int screenX = worldX - gp.player.worldX + gp.player.screenX;
      int screenY = worldY - gp.player.worldY + gp.player.screenY;

      if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
        worldY - gp.tileSize < gp.player.worldY + gp.player.worldY) {
          g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

      worldCol++;

      // if we reach the edge (right side of screen)
      if(worldCol == gp.maxWorldCol) {
        worldCol = 0;
        worldRow++;
      }
    }
  }

}
