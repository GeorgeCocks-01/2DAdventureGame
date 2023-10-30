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
    mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

    getTileImage();
    loadMap("/maps/map01.txt");
  }

  public void getTileImage() {
    try {
      tile[0] = new Tile();
      tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

      tile[1] = new Tile();
      tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

      tile[2] = new Tile();
      tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

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

      while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
        String line = br.readLine(); // read line from txt file

        while(col < gp.maxScreenCol) {
          String numbers[] = line.split(" "); // split line into array of strings

          int num = Integer.parseInt(numbers[col]);

          mapTileNum[col][row] = num;
          col++;
        }

        if(col == gp.maxScreenCol) {
          col = 0;
          row++;
        }
      }
      br.close();

    } catch(Exception e) {

    }
  }

  public void draw(Graphics2D g2) {
    int col = 0;
    int row = 0;
    int x = 0;
    int y = 0;

    // Loop to draw tiles over entire screen
    while(col < gp.maxScreenCol && row < gp.maxScreenRow) {

      int tileNum = mapTileNum[col][row];

      g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

      col++;
      x += gp.tileSize;

      // if we reach the edge (right side of screen)
      if(col == gp.maxScreenCol) {
        col = 0;
        x = 0;
        row++;
        y += gp.tileSize;
      }
    }
  }

}
