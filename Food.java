import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Food {

    /* Data members */
    /* Where the food locate */
    private int x;
    private int y;
    private static final String[] FOOD_IMAGE=new String[]{"burger.png","cake.png","chocolate.png","coke.png","cookie.png",
            "egg.png","nacho.png","pizza.png","sausage.png"};
    protected static Image foodImage=null;

    /* Constructor */
    public Food(Snake snake){
        this.random_range(snake);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    /* This method put food into the screen */
    public void random_range(Snake snake){
        Boolean onSnake=true;
        while(onSnake){
            onSnake=false;
            x=(int)(Math.random()* Game.DIMENSION)*Game.GRID_WIDTH;
            y=(int)(Math.random()* Game.DIMENSION)*Game.GRID_HEIGHT;
            for (Rectangle rectangle: snake.getSnakeBody()){
                if(rectangle.x==x&& rectangle.y==y){
                    onSnake=true;
                }
            }
            try{
                foodImage = ImageIO.read(new File(FOOD_IMAGE[(int) (Math.random() * 9)]));
            }catch (IOException e){e.printStackTrace();}
        }

    }
}
