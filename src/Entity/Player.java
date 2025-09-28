package src.Entity;

import src.Main.KeyHandler;
import java.awt.Graphics2D;
import java.io.IOException;
import src.Main.GamePanel;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;
    int spriteCounter = 0;   // For animation timing

    public Player(int x, int y, int Speed, GamePanel gp, KeyHandler keyHandler, String Direction) {
        this.x = x;
        this.y = y;
        this.Speed = Speed;
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.Direction = Direction;

        try {
            SpriteImage(); // loads all frames dynamically
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetFrame() {
        this.currentFrame = 0;
    }

    @Override
    public void SpriteImage() throws IOException {
        super.SpriteImage(); // load frames from sprite sheet
    }

    // Update player position and animation
    public void Update(boolean up, boolean down, boolean left, boolean right) {

        boolean moving = false;

        if (up) {
            y -= Speed;
            Direction = "up";
            moving = true;
        } 
        if (down) {
            y += Speed;
            Direction = "down";
            moving = true;
        }
        if (left) {
            x -= Speed;
            Direction = "left";
            moving = true;
        }
        if (right) {
            x += Speed;
            Direction = "right";
            moving = true;
        }

        // Animate only if a key is pressed
        if (moving) {
            spriteCounter++;
            if (spriteCounter > 15) { // change frame every 15 ticks
                nextFrame(); // advance frame in Entity
                spriteCounter = 0;
            }
        } 
    // Do nothing if no key is pressed: frame stays the same
}


    // Draw the player with rotation and scaling
    public void Draw(Graphics2D g2) {
        super.draw(g2, gp.TileSize);
    }
}
