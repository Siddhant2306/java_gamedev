package src.Entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Entity {
    int x, y;
    int Speed;

    public BufferedImage[] frames; // all frames
    public String Direction = "right"; // "right" or "left"
    protected int currentFrame = 0;

    // Coordinates and size of each frame (from GIMP)
    private final int[][] frameData = {
        // {startX, startY, width, height}
        {0, 40, 80, 88},   // frame 0
        {80, 40, 80, 88},  // frame 1
        {0, 128, 80, 88},  // frame 2
        {80, 128, 80, 88}, //frame 3
        {0, 216, 80, 88}, //frame 4
    };

    public void SpriteImage() throws IOException {
        BufferedImage spriteSheet = ImageIO.read(new File("res/Player1/Walk.png"));
        frames = new BufferedImage[frameData.length];

        for (int i = 0; i < frameData.length; i++) {
            int startX = frameData[i][0];
            int startY = frameData[i][1];
            int width  = frameData[i][2];
            int height = frameData[i][3];

            // safety check
            if (startX + width > spriteSheet.getWidth() || startY + height > spriteSheet.getHeight()) {
                System.out.println("Skipping frame " + i + " (out of bounds)");
                continue;
            }

            frames[i] = spriteSheet.getSubimage(startX, startY, width, height);
        }
    }

    // Draw method with rotation for left/right
    public void draw(Graphics2D g2, int TileSize) {
        if (frames.length == 0 || frames[currentFrame] == null) return;

        BufferedImage image = frames[currentFrame];

        int spriteWidth = image.getWidth();
        int spriteHeight = image.getHeight();

        int drawHeight = TileSize * spriteHeight / spriteWidth;
        int yOffset = TileSize - drawHeight;

        AffineTransform at = new AffineTransform();
        at.translate(x, y + yOffset);

        if (Direction.equals("left")) {
            at.translate(TileSize / 2.0, drawHeight / 2.0);
            at.rotate(Math.toRadians(180));
            at.translate(-TileSize / 2.0, -drawHeight / 2.0);
        }

        g2.drawImage(image, at, null);
    }

    // Advance animation frame
    public void nextFrame() {
        currentFrame++;
        if (currentFrame >= frames.length) currentFrame = 0;
    }

    // Reset frame to first
    public void resetFrame() {
        currentFrame = 0;
    }
}
