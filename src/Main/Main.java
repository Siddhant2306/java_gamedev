package src.Main;

import javax.swing.JFrame;

public class Main{
    public static void main(String[] arg){
        JFrame window = new JFrame(); //Top Level swing component , the main window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("class_Project");
        

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.StartGameThread();
    }
}