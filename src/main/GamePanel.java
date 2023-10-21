package main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel{
  // screen settings
  final int originalTileSize = 16; // in px, default size
  final int scale = 3; // scales up tile (originalTileSize*scale)

  final int tileSize = originalTileSize * scale; // 48 px tile size

  final int maxScreenCol = 16;
  final int maxScreenRow = 12;
  final int screenWidth = tileSize * maxScreenCol; // 768 px
  final int screenHeight = tileSize * maxScreenRow; // 576 px

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);

  }
}
