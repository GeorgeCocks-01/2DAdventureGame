package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; // interface for reciving keystrokes

public class KeyHandler implements KeyListener{

  public boolean upPressed, downPressed, leftPressed, rightPressed;

  @Override
  public void keyTyped(KeyEvent e) { // NOT NEEDED
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub

    int code = e.getKeyCode(); // gets code of pressed key

    switch(code) { // checks if key is wsad and changes boolean
      case KeyEvent.VK_W:
        upPressed = true;
        break;
      case KeyEvent.VK_S:
        downPressed = true;
        break;
      case KeyEvent.VK_A:
        leftPressed = true;
        break;
      case KeyEvent.VK_D:
        rightPressed = true;
        break;
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

    int code = e.getKeyCode(); // gets code of released key

    switch(code) { // checks if key is wsad and changes boolean
      case KeyEvent.VK_W:
        upPressed = false;
        break;
      case KeyEvent.VK_S:
        downPressed = false;
        break;
      case KeyEvent.VK_A:
        leftPressed = false;
        break;
      case KeyEvent.VK_D:
        rightPressed = false;
        break;
    }
  }

}
