import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; //used to store the snake body segments
import java.util.Random; //to generate redfood for snake at random places
import javax.swing.*;
public class SnakeGame extends JPanel implements ActionListener, KeyListener{
        
    private class Tile {
        int x;
        int y;

        Tile(int x, int y){ //constactor to create the snake head
            this.x = x;
            this.y = y;
        }
       
    }

    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    //snake
    Tile snakeHead;
    ArrayList<Tile> snakeBody; //used to store the snake body segments

    //food
    Tile food;
    Random random;

    //game logic
    Timer gameloop;
    int velocityX;
    int velocityY;
    boolean gameOver = false;



    SnakeGame(int boardWidth, int boardHeight) { //constractor
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5,5); //default starting place
        snakeBody = new ArrayList<Tile>();
        food = new Tile(10, 10) ;
        random = new Random();
        placeFood();

        velocityX=0;
        velocityY=0; 


        gameloop = new Timer(100, this); //loop in every 100ms
        gameloop.start();
    }

    //drawing
    public void paintComponent(Graphics g){
        super.paintComponent(g);   //used to draw the snake
        draw(g) ;
    }
    public void draw(Graphics g){
        //grid
        // for(int i=0;i<boardWidth/tileSize;i++) {
        //     g.drawLine(i*tileSize, 0, i*tileSize, boardHeight); //horizontal lines
        //     g.drawLine(0, i*tileSize, boardWidth, i*tileSize); //vertical lines
        // }

        //food
        g.setColor(Color.red);
        g.fillRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize);
        g.fill3DRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize, true);


        //snake head
        g.setColor(Color.green);
        // g.fillRect(snakeHead.x*tileSize, snakeHead.y*tileSize, tileSize, tileSize);
        g.fill3DRect(snakeHead.x*tileSize, snakeHead.y*tileSize, tileSize, tileSize,true);

        //snake body
        for(int i=0;i<snakeBody.size();i++) {
            Tile snakePart = snakeBody.get(i);
            // g.fillRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize);
            g.fill3DRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize,true);
        }

        //Score
        g.setFont(new Font("Arial", Font.PLAIN,16)) ;
        if(gameOver) {
            g.setColor(Color.red);
            g.drawString("GAME OVER: "+String.valueOf(snakeBody.size()), tileSize-16, tileSize);
        }
        else {
            g.drawString(("Score: "+String.valueOf(snakeBody.size())), tileSize-16, tileSize);
        }

    }
    
    //placing food in random place
    public void placeFood(){
        food.x = random.nextInt(boardWidth/tileSize); //600/25=24
        food.y = random.nextInt(boardHeight/tileSize);
    }

    public boolean collision(Tile tile1, Tile tile2) {
        return tile1.x==tile2.x && tile1.y==tile2.y;
    }



    public void move(){
        //eat food
        if(collision(snakeHead, food)) {
            snakeBody.add(new Tile(snakeHead.x, snakeHead.y));
            placeFood();
        }



        //snake body
        for(int i=snakeBody.size()-1; i>=0; i--) {
            Tile snakePart = snakeBody.get(i);

            if(i==0) {
                snakePart.x = snakeHead.x;
                snakePart.y = snakeHead.y;
            }
            else {
                Tile prevSnakePart = snakeBody.get(i-1) ;
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }


        //snake head
        snakeHead.x+=velocityX;
        snakeHead.y+=velocityY; 


        //game over conditions 
        for(int i=0; i<snakeBody.size();i++) {
            Tile snakePart = snakeBody.get(i);
            //collide with snake head
            if(collision(snakeHead, snakePart)) {
                gameOver = true;
            }
            if(snakeHead.x*tileSize<0 || snakeHead.x*tileSize>=boardWidth || snakeHead.y*tileSize<0 || snakeHead.y*tileSize>=boardHeight) {
                gameOver = true;
            }
        }

    }


    //Animation
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint(); 

        if(gameOver) {
            gameloop.stop();
        }
    }
     

    //key press
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) { //pressed up arrow key
            velocityX = 0;
            velocityY = -1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY!= -1) { //pressed down arrow key
            velocityX = 0;
            velocityY =  1;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX!= 1) { //pressed Left arrow key
            velocityX = -1;
            velocityY = 0;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX!= -1) { //pressed Right arrow key
            velocityX = 1;
            velocityY = 0;
        }
    }


    //Do not need this
    @Override
    public void keyTyped(KeyEvent e) {}

    

    @Override
    public void keyReleased(KeyEvent e) {}


 }
    
