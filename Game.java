import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class Game implements KeyListener, ActionListener {
    /* The data members */
    private Snake snake;
    private Food food;
    private Graph graph;

    /* The setting of the window frame */
    private JFrame frame;
    public static final int GRID_WIDTH = 30;
    public static final int GRID_HEIGHT = 30;
    public static final int DIMENSION= 20;
    JMenuItem resetItem, qaItem, quitItem;


    /* This is the constructor of Game, which sets the window frame */
    public Game(){
        frame= new JFrame("Snake Game by Yuting");
        snake=new Snake();
        food = new Food(snake);
        graph = new Graph(this);


        // Add a menu bar - note this is added to the JFrame, not the JPanel
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Add a file menu with some menu items
        JMenu fileMenu = new JMenu("Settings");
        menuBar.add(fileMenu);

        qaItem = new JMenuItem("Q&A");
        qaItem.addActionListener(this);
        fileMenu.add(qaItem);

        resetItem = new JMenuItem("Reset");
        resetItem.addActionListener(this);
        fileMenu.add(resetItem);

        quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(this);
        fileMenu.add(quitItem);

        frame.add(graph);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(GRID_WIDTH * DIMENSION+15, GRID_HEIGHT * DIMENSION+62);
        frame.setVisible(true);
        frame.setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        /* Respond to each of the action events generated by the menu items */
        JComponent source = (JComponent)event.getSource();
        JLabel label1 = new JLabel();
        JTextField numLife= new JTextField(20);
        JLabel label2= new JLabel();
        JTextField numTime= new JTextField(20);
        JButton confirm = new JButton("Set");
        confirm.setSize(100,50);
        if (source == resetItem) {
            JDialog dialog = new JDialog(frame, "Reset difficulty");
            dialog.setModal(true);
            dialog.setSize(300, 200);
            dialog.setLocation(200, 350);
            dialog.setLayout(new FlowLayout(FlowLayout.CENTER,20,5));
            label1.setText("Number of lives for the snake:    ");
            label2.setText("Speed: 1 for fast,2 for middle,3 for slow:");
            dialog.add(label1);
            dialog.add(numLife);
            dialog.add(label2);
            dialog.add(numTime);
            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        snake.setSnakeLife((Integer.parseInt(numLife.getText())) * 2);
                        graph.setTimer((Integer.parseInt(numTime.getText())) * 200);
                    }
                    catch (Exception exception){}
                    dialog.dispose();
                }
            });
            dialog.add(confirm);
            dialog.setVisible(true);
        }
        else if (source == qaItem) {
            JOptionPane.showMessageDialog(frame,"Your snake will have 3 lives in default\nAfter 3 lives the snake will die and your score will show","Q & A",1);
        }
        else if (source == quitItem) {
            System.exit(0);
        }
    }

    /* This is the start of the game */
    public void init(){
        graph.gameState=2;
    }

    /* When the game changes state to running */
    public void updateGameState(){
        if(graph.gameState==2){
            if(collisionFood()){
                snake.growSnake();
                food.random_range(snake);
            }
            else if((collisionWall()||collisionSelf())&& snake.getSnakeLife()!=0){
                snake.setSnakeLife(snake.getSnakeLife()-1);
                switch (snake.direction){
                    case 1:
                        snake.direction=2;
                        break;
                    case 2:
                        snake.direction=3;
                        break;
                    case 3:
                        snake.direction=4;
                        break;
                    case 4:
                        snake.direction=1;
                        break;
                }
                snake.moveSnake();
            }
            else if((collisionWall()||collisionSelf())&& snake.getSnakeLife()==0){
                graph.gameState=3;
            }
            else {
                snake.moveSnake();
            }
        }
    }


    /* The getters and setters of private data members */
    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }


    /* This the only method that needs to be override.*/
    /* The Key reaction part */
    @Override
    public void keyPressed(KeyEvent e) {
        int key= e.getKeyCode();

        /* The snake could be controlled by WASD or Arrow keys */
        if(graph.gameState==2) {
            if ((key == KeyEvent.VK_W) || (key == KeyEvent.VK_UP)) {
                snake.up();
            } else if ((key == KeyEvent.VK_A) || (key == KeyEvent.VK_LEFT)) {
                snake.left();
            } else if ((key == KeyEvent.VK_S) || (key == KeyEvent.VK_DOWN)) {
                snake.down();
            } else if ((key == KeyEvent.VK_D) || (key == KeyEvent.VK_RIGHT)) {
                snake.right();
            }
        }

        /* At the begining or ended phrase, press any key to restart the game */
        else if(graph.gameState==1){
            this.init();
        }
        else if(graph.gameState==3){
//            Game game=new Game();
           this.init();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

    /* This method checks if the snake touch the wall */
    /* Which ends the game */
    private boolean collisionWall(){
        if(snake.getSnakePositionX()<=0||snake.getSnakePositionX()>= GRID_WIDTH *DIMENSION||
        snake.getSnakePositionY()<=0||snake.getSnakePositionY()>= GRID_HEIGHT *DIMENSION){
            return true;
        }
        return false;
    }

    /* This method checks if the snake eat the food */
    /* Which makes the snake longer */
    private boolean collisionFood(){
        if(snake.getSnakePositionX()==food.getX()
        && snake.getSnakePositionY()==food.getY()){
            return true;
        }
        return false;
    }

    /* This method checks if the snake touches its body */
    /* Which makes the snake die */
    private boolean collisionSelf(){
        for(int i=1;i<snake.getSnakeBody().size();i++){
            if(snake.getSnakePositionX()==snake.getSnakeBody().get(i).x
            &&snake.getSnakePositionY()==snake.getSnakeBody().get(i).y){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Game newGame=new Game();
        BGM.playMusic();
    }


}
