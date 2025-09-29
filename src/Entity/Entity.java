package src.Entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int Speed;

    public BufferedImage[] rightFrames; // walking right
    public BufferedImage[] leftFrames;  // walking left
    public String Direction;

    public int frameIndex = 0;       // which animation frame
    public int frameDelay = 8;       // controls speed of animation
    public int frameCounter = 0;
}

