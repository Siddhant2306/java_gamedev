package src.Entity;

import src.Main.KeyHandler;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import src.Main.GamePanel;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyHandler;
    int spriteCounter = 0;   // For animation timing
    int spriteNum = 1;       // To switch between 1 and 2 for walking
    
    public Player(int x, int y, int Speed, GamePanel gp, KeyHandler keyHandler, String Direction){
        this.x = x;
        this.y = y;
        this.Speed = Speed;
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.Direction = Direction;
        
        try {
            SpriteImage(); 
        } catch (IOException e) {
        e.printStackTrace(); 
        
        }
    }

    @Override
    public void SpriteImage() throws IOException {
        super.SpriteImage(); // loads walkFrames and assigns right1, right2, etc.
    }


    public void Update(boolean up, boolean down, boolean left, boolean right) {
        
        if (up) {
            y -= Speed;
            Direction = "up";
        }
        if (down) {
            y += Speed;
            Direction = "down";
        }
        if (left) {
            x -= Speed;
            Direction = "left";
        }
        if (right) {
            x += Speed;
            Direction = "right";
        }

        // Simple animation toggle
        spriteCounter++;
        if (spriteCounter > 15) { // Change frame every 15 ticks
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }

    public void Draw(Graphics2D g2) {
        BufferedImage image;

        switch(Direction) {
            case "up":
            case "down": // If no up/down sprites, default to right
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                break;
            default:
                image = right1;
                break;
        }

        g2.drawImage(image, x, y, gp.TileSize, gp.TileSize, null);
    }
}
