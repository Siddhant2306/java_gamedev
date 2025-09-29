package src.Entity;

import src.Main.KeyHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import src.Main.GamePanel;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyHandler;
    int playerScale = 2;

    public Player(int x, int y, int Speed, GamePanel gp, KeyHandler keyHandler, String Direction) {
        this.x = x;
        this.y = y;
        this.Speed = Speed;
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.Direction = Direction;

        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            rightFrames = new BufferedImage[8];
            leftFrames = new BufferedImage[8];

            for (int i = 0; i < 8; i++) {
                rightFrames[i] = ImageIO.read(
                        new File("res/walking_sprite/walk_" + (i + 1) + ".png"));
                leftFrames[i] = flipImage(rightFrames[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Flip horizontally
    private BufferedImage flipImage(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        // Use TYPE_INT_ARGB to preserve transparency
        BufferedImage flipped = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flipped.createGraphics();
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return flipped;
    }

    // Update player position and animation
    public void Update(boolean up, boolean down, boolean left, boolean right) {
        boolean moving = false;

        if (up) {
            y -= Speed;
            moving = true;
        }
        if (down) {
            y += Speed;
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

        // Animate only when moving
        if (moving) {
            frameCounter++;
            if (frameCounter >= frameDelay) {
                frameIndex = (frameIndex + 1) % rightFrames.length;
                frameCounter = 0;
            }
        } else {
            frameIndex = 0; // idle frame
        }
    }

    public void Draw(Graphics2D g2) {
        BufferedImage image = null;

        if ("right".equals(Direction)) {
            image = rightFrames[frameIndex];
        } else if ("left".equals(Direction)) {
            image = leftFrames[frameIndex];
        } else {
            image = rightFrames[0]; // idle frame
        }
        
        int newWidth = image.getWidth() * playerScale;
        int newHeight = image.getHeight() * playerScale;


        g2.drawImage(image, x, y, newWidth, newHeight, null);
    }
}
