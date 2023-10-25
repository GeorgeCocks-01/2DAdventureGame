package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
  // screen settings
  final int originalTileSize = 16; // in px, default size
  final int scale = 3; // scales up tile (originalTileSize*scale)

  final int tileSize = originalTileSize * scale; // 48 px tile size

  final int maxScreenCol = 16;
  final int maxScreenRow = 12;
  final int screenWidth = tileSize * maxScreenCol; // 768 px
  final int screenHeight = tileSize * maxScreenRow; // 576 px

  double FPS = 144; // Set FPS

  KeyHandler keyH = new KeyHandler(); // create new key handler

  Thread gameThread;

  // Set player's default position
  int playerX = 100;
  int playerY = 100;
  double scaleFPS = 60.0/FPS; // scales movement speed to adjust for FPS
  int playerSpeed = (int) (4*scaleFPS + 0.5); // Player's movement speed

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
    if(keyH.upPressed == true) {
      playerY -= playerSpeed;
    }
    if (keyH.downPressed == true) {
      playerY += playerSpeed;
    }
    if (keyH.leftPressed == true) {
      playerX -= playerSpeed;
    }
    if (keyH.rightPressed == true) {
      playerX += playerSpeed;
    }

  }

  public void paintComponent(Graphics g) { // 2. draw screen with updated information
    super.paintComponent(g); // super = JPanel here

    Graphics2D g2 = (Graphics2D)g; // Graphics2D extends Graphics

    g2.setColor(Color.white); // makes rectangle white
    g2.fillRect(playerX, playerY, tileSize, tileSize); // sets posn and size of rectangle
    g2.dispose(); // release system memory
  }
}
