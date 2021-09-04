import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* This class controls the drawing of snake on the screen */
public class Graph extends JPanel implements ActionListener {
    /* The state of the game is set here */
    /* gameState is 1 when at the beginning, 2 when playing, and 3 when ended */
    public int gameState;


    /* This timer is set to refresh the screen */
    private Timer timer = new Timer(200, this);

    public void setTimer(int time) {
        this.timer.stop();
        this.timer = new Timer(time,this);
        this.timer.start();
    }

    private Snake snake;
    private Game game;
    private Food food;

    public Graph(Game g){
        timer.start();
        gameState=1;
        game=g;
        snake=g.getSnake();
        food=g.getFood();

        /* Add a keyListener to the game */
        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    /* The drawing part of the graph */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D) g;

        /* This is the background */
        backgroundGrid(g2d);

        if(gameState==1){
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Serif", Font.PLAIN, 35));
            g2d.drawString("Are you ready to start the game?",Game.GRID_WIDTH /2*Game.DIMENSION-220,Game.GRID_WIDTH /2*Game.DIMENSION-80);
            g2d.setFont(new Font("Serif", Font.PLAIN, 25));
            g2d.drawString("By default you have 3 lives",Game.GRID_WIDTH /2*Game.DIMENSION-150,Game.GRID_WIDTH /2*Game.DIMENSION+20);
            g2d.drawString("Or you could go to settings to reset difficulty",Game.GRID_WIDTH /2*Game.DIMENSION-220,Game.GRID_WIDTH /2*Game.DIMENSION+70);
        }
        else if(gameState==2){
            /* This is the food */
            g2d.drawImage(Food.foodImage,food.getX(), food.getY(), Game.GRID_WIDTH, Game.GRID_HEIGHT,null);

            /* This is the snake */
            for(int i=0;i<snake.getSnakeBody().size();i++){
                if(i==0){
                    g2d.setColor(Color.RED);
                    g2d.fill(snake.getSnakeBody().get(0));
                    continue;
                }
                g2d.setColor(new Color((i*20)%255,(i*15)%255,255));
                g2d.fill(snake.getSnakeBody().get(i));
            }

            /* This is the score */
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Serif", Font.BOLD, 35));
            g2d.drawString("Score: "+snake.getScore(),10,30);

            /*This is the snakeLife */
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Serif", Font.BOLD, 35));
            g2d.drawString("Life: "+snake.getSnakeLife()/2,500,30);
        }
        else if(gameState==3){
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Serif", Font.PLAIN, 35));
            g2d.drawString("Your score is "+snake.getScore(),Game.GRID_WIDTH /2*Game.DIMENSION-100,Game.GRID_WIDTH /2*Game.DIMENSION-30);
        }
    }

    /* The timer is linked to actionEvent here */
    /* Every 100 milliseconds the graph will be updated */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.updateGameState();
    }

    private void backgroundGrid(Graphics graphics){
        int h=Game.GRID_HEIGHT;
        for(int i=0;i<Game.DIMENSION;i++){
            for(int l=0;l<Game.DIMENSION;l++){
                if((i+l)%2==0){
                    graphics.setColor(Color.decode("#ffd55a"));
                }
                else {
                    graphics.setColor(Color.decode("#fbdf7e"));
                }
                graphics.fillRect(i * h, l * h, h, h);
            }
        }
    }
}
