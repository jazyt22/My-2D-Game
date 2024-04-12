package main;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen Setting
    final int OriginalTileSize = 16;//16x16 Tile// Must scale due to res being so high tile would be hard to see
    final int Scale = 3;
    final int TileSize = OriginalTileSize * Scale;// makes 48x48 Tile
    final int MaxScreenCol = 16;
    final int MaxScreenRow = 12;
    final int ScreenWidth = TileSize * MaxScreenCol;//768 Pixels
    final int ScreenHeight = TileSize * MaxScreenRow;//576 pixels

    //FPS
    int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; // player speed means player moves 4 PIXELS

    public GamePanel(){
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void StartGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                //1 UPDATE:  Update info as such as character positions
                update();
                //2 DRAW: Draw the screen with update info
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
//                System.out.println("Current Time:" + currentTime);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update(){
        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        } else if(keyH.downPressed == true){
            playerY += playerSpeed;
        } else if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        } else if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }
    }

    //creates pink rectangle
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.pink);

        g2.fillRect(playerX,playerY, TileSize, TileSize);
        g2.dispose();

    }

}
