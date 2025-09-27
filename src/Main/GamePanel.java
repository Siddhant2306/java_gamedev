package src.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{  // This is inside the JFrame component which will allow us to draw on the screen
    //Screeen Settings
    final int OriginalTileSize = 16;
    final int scale = 3;    

    final int TileSize = OriginalTileSize * scale;
    final int maxscreencol = 16;
    final int maxscreenrow = 12;
    final int ScreeenWidth = TileSize * maxscreencol; // 768 pixels 
    final int ScreenHeight = TileSize * maxscreenrow; // 576 pixels

    //FPS
    int Fps = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gametThread;

    //Default position
    int PlayerX = 100;
    int PlayerY = 100;
    int PlayerSpeed = 5;



    public GamePanel(){
        this.setPreferredSize(new Dimension(ScreeenWidth,ScreenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void StartGameThread(){
        gametThread = new Thread(this);
        gametThread.start();
    }


    @Override
    public void run() {

        double drawInterval = 1000000000/Fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCounter = 0;
        
        while(gametThread != null){    

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1){
            //Update and Draw the screen as per the Fps
                Update();


                repaint();

                delta--;
                drawCounter++;
            }

            if(timer >= 1000000000)
            {
                System.out.println("FPS:" + drawCounter);
                drawCounter = 0;
                timer = 0;
            }
             
        }
    }

    public void Update()
    {
        if(keyHandler.upPressed == true)
        {
            PlayerY -= PlayerSpeed;
        }
        else if(keyHandler.downPressed == true){
            PlayerY += PlayerSpeed;
        }
        else if(keyHandler.leftPressed == true){
            PlayerX -= PlayerSpeed;
        }
        else if(keyHandler.rightPressed == true){
            PlayerX += PlayerSpeed;
        }
    
    }

    public void paintComponent(Graphics g) // A JPanel Methon To draw Objects on screen(JFrame)
    {
        super.paintComponent(g); //It clears the background so old frames don’t overlap
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(PlayerX,PlayerY, TileSize, TileSize);
        g2.dispose();
    }
}




