package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
  // screen settings
  final int originalTileSize = 16; // in px, default size
  final int scale = 3; // scales up tile (originalTileSize*scale)
  // 48 px tile size. Public as accessed from Player.java
  public final int tileSize = originalTileSize * scale;
  public final int maxScreenCol = 16;
  public final int maxScreenRow = 12;
  public final int screenWidth = tileSize * maxScreenCol; // 768 px
  public final int screenHeight = tileSize * maxScreenRow; // 576 px

  int FPS = 144; // Set FPS

  TileManager tm = new TileManager(this);
  KeyHandler keyH = new KeyHandler(); // create new key handler
  Thread gameThread;
  Player player = new Player(this, keyH); // create new player

  public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH); // add key handler to game panel
    this.setFocusable(true);
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start(); // automatically calls the below run method
  }

  @Override
  public void run() {
    // Timing variables for game loop
    double drawInterval = 1000000000/FPS;
    double delta = 0;
    double lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int drawCount = 0;

    while(gameThread != null) { // game loop

      currentTime = System.nanoTime(); // get time in nanoseconds
      delta += (currentTime - lastTime)/drawInterval;
      timer += currentTime - lastTime;
      lastTime = currentTime;

      if(delta >= 1) {
        // 1. update system information like character positions
        update();
        // 2. draw screen with updated information
        repaint(); // calls paintComponent method (predefined java method)
        delta--;
        drawCount++;
      }

      if(timer >= 1000000000) {
        System.out.println("FPS: " + drawCount);
        drawCount = 0;
        timer = 0;
      }
    }
  }

  public void update() { // 1. update system information like character positions
    player.update();

  }

  public void paintComponent(Graphics g) { // 2. draw screen with updated information
    super.paintComponent(g); // super = JPanel here

    Graphics2D g2 = (Graphics2D)g; // Graphics2D extends Graphics

    tm.draw(g2); // draw tiles first so the player is drawn on top
    player.draw(g2);

    g2.dispose(); // release system memory
  }
}
