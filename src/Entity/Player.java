package src.Entity;

import src.Main.KeyHandler;

import java.awt.Color;
import java.awt.Graphics2D;

import src.Main.GamePanel; 


public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyHandler;
    Color color;

    public Player(int x, int y,int Speed ,GamePanel gp , KeyHandler keyHandler, Color color)
    {
        this.x = x;
        this.y = y;
        this.Speed = Speed;
        this.gp = gp;
        this.keyHandler = keyHandler;
        this.color = color;
    }

    public void Update(boolean up, boolean down, boolean left, boolean right)
    {
        if (up) y -= Speed;
        if (down) y += Speed;
        if (left) x -= Speed;
        if (right) x += Speed;
    }

    public void Draw(Graphics2D g2)
    {
        g2.setColor(color);
        g2.fillRect(x , y , gp.TileSize, gp.TileSize);
    }
}
