import java.awt.*;
import java.util.ArrayList;

public class Snake {
    /* The body of the snake is constructed by an array of Rectangles */
    private ArrayList<Rectangle> snakeBody;
    private int snakeLife=6;
    private int score=0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSnakeLife() {
        return snakeLife;
    }

    public void setSnakeLife(int snakeLife) {
        this.snakeLife = snakeLife;
    }

    public ArrayList<Rectangle> getSnakeBody() {
        return snakeBody;
    }

    public void setSnakeBody(ArrayList<Rectangle> snakeBody) {
        this.snakeBody = snakeBody;
    }
    /* These 2 methods return the position of Snake's head */
    public int getSnakePositionX(){
        return snakeBody.get(0).x;
    }
    public int getSnakePositionY(){
        return snakeBody.get(0).y;
    }

    /* The value is from Game class, where sets the size of the window frame and the snake */
    private int w=Game.GRID_WIDTH;
    private int h=Game.GRID_HEIGHT;
    private int d=Game.DIMENSION;

    /* The direction variable controls the direction of the snake */
    /* Which is 0 at the beginning, 1 when UP, 2 when LEFT, 3 when DOWN, 4 when RIGHT */
    int direction=4;
    public void up(){
        if(direction!=3){
            direction=1;
        }
    }
    public void left(){
        if(direction!=4) {
            direction = 2;
        }
    }
    public void down(){
        if(direction!=1){
            direction=3;
        }
    }
    public void right(){
        if(direction!=2){
            direction=4;
        }
    }

    public Snake(){
        /* Initailizing the body of the snake */
        /* At the beginning the snake's body is 3-dots-long */
        snakeBody=new ArrayList<>();
        Rectangle bodypart= new Rectangle(w,h);
        bodypart.setLocation(d/2*w,d/2*h);
        snakeBody.add(bodypart);
        bodypart = new Rectangle(w, h);
        bodypart.setLocation((d/2-1) * w, d/2*h);
        snakeBody.add(bodypart);
        bodypart = new Rectangle(w, h);
        bodypart.setLocation((d/2-2) * w, d/2*h);
        snakeBody.add(bodypart);
    }

    /* The method to make the snake moving */
    /* The head of the snake is added to the previous head */
    /* The tail of the snake is deleted */
    /* Direction is 0 at the beginning, 1 when UP, 2 when LEFT, 3 when DOWN, 4 when RIGHT */
    public void moveSnake(){
        if(direction!=0){
            Rectangle head = snakeBody.get(0);
            Rectangle tempBody = new Rectangle(w, h);
            if(direction==1){
                tempBody.setLocation(head.x,head.y-w);
            }
            else if(direction==2){
                tempBody.setLocation(head.x-w,head.y);
            }
            else if(direction==3){
                tempBody.setLocation(head.x,head.y+w);
            }
            else if(direction==4){
                tempBody.setLocation(head.x+w,head.y);
            }
            snakeBody.add(0, tempBody);
            snakeBody.remove(snakeBody.size() - 1);
        }
    }

    /* The growing of the snake is similar to the moving function */
    /* Except that the last tail is not deleted */
    public void growSnake(){
        Rectangle head = snakeBody.get(0);
        Rectangle tempBody = new Rectangle(w, h);
        if(direction==1){
            tempBody.setLocation(head.x,head.y-w);
        }
        else if(direction==2){
            tempBody.setLocation(head.x-w,head.y);
        }
        else if(direction==3){
            tempBody.setLocation(head.x,head.y+w);
        }
        else if(direction==4){
            tempBody.setLocation(head.x+w,head.y);
        }
        snakeBody.add(0, tempBody);
        score++;
    }
}
