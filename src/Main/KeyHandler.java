package src.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    //Player 1
    public boolean upPressed_1,downPressed_1,leftPressed_1,rightPressed_1;

    //Player 2
    public boolean upPressed_2,downPressed_2,leftPressed_2,rightPressed_2;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // Player 1 (Arrow keys)
        if (code == KeyEvent.VK_UP) upPressed_1 = true;
        if (code == KeyEvent.VK_DOWN) downPressed_1 = true;
        if (code == KeyEvent.VK_LEFT) leftPressed_1 = true;
        if (code == KeyEvent.VK_RIGHT) rightPressed_1 = true;

        // Player 2 (WASD)
        if (code == KeyEvent.VK_W) upPressed_2 = true;
        if (code == KeyEvent.VK_S) downPressed_2 = true;
        if (code == KeyEvent.VK_A) leftPressed_2 = true;
        if (code == KeyEvent.VK_D) rightPressed_2 = true;

        }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        // Player 1 (Arrow keys)
        if (code == KeyEvent.VK_UP) upPressed_1 = false;
        if (code == KeyEvent.VK_DOWN) downPressed_1 = false;
        if (code == KeyEvent.VK_LEFT) leftPressed_1 = false;
        if (code == KeyEvent.VK_RIGHT) rightPressed_1 = false;

        // Player 2 (WASD)
        if (code == KeyEvent.VK_W) upPressed_2 = false;
        if (code == KeyEvent.VK_S) downPressed_2 = false;
        if (code == KeyEvent.VK_A) leftPressed_2 = false;
        if (code == KeyEvent.VK_D) rightPressed_2 = false;
        
    }
    
}
