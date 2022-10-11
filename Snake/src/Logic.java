import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Logic extends JPanel implements KeyListener {

    public static final int SIZE = 10;
    public static final Point SNAKESTART = new Point(0, 40);
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int WALLUP = 0;
    public static final int WALLDOWN = HEIGHT - SIZE;
    public static final int WALLLEFT = 0;
    public static final int WALLRIGHT = WIDTH - SIZE;


    // Variables


    private Snake snake;
    private Apple apple;
    private boolean inGame = true;
    private char invalidMove = (char) 0;
    private char direction = (char) 0;
    private int score;
    Random number = new Random();


    // Constructor
    public Logic() {
        init();
    }


    // Responsible for initializing important Objects/parameters.
    public void init() {
        Border border = BorderFactory.createLineBorder(Color.gray);
        setBorder(border);
        setBackground(Color.black);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        snake = new Snake(SIZE);
        apple = new Apple(SIZE);
        createNewApple();
        score = 0;

        snake.getBody().add(SNAKESTART);
        snake.setHead(snake.getBody().get(0));
        snake.setTail(snake.getBody().get(snake.getBody().size() - 1));


    }

    // Method checks if Apple is still on the Grid
    public void checkAppleStatus() {
        if (appleEaten())
            createNewApple();
    }


    // Method creates a new Apple at a random spot where there are no Snake body-parts.
    public void createNewApple() {
        int x = number.nextInt((WIDTH - SIZE) / SIZE) * SIZE;
        int y = number.nextInt((HEIGHT - SIZE) / SIZE) * SIZE;
        Point point = new Point(x, y);

        if (!snake.getBody().contains(point)) {
            apple.appleSetX(x);
            apple.appleSetY(y);
            score++;
        } else {
            createNewApple();
        }

    }


    // Method moves the Snake depending on the flags set by the Key listener
    public void moveSnake() {
        switch (direction) {
            case ('U') -> {
                if (snake.getHead().y == WALLUP) {
                    snake.getBody().add(0, new Point(snake.getHead().x, WALLDOWN - SIZE));
                    snake.getBody().remove(snake.getBody().get(snake.getBody().size() - 1));
                    break;
                }
                snake.getBody().add(0, new Point(snake.getHead().x, snake.getHead().y - SIZE));
                snake.getBody().remove(snake.getBody().get(snake.getBody().size() - 1));
            }
            case ('D') -> {
                if (snake.getHead().y == WALLDOWN) {
                    snake.getBody().add(0, new Point(snake.getHead().x, WALLUP));
                    snake.getBody().remove(snake.getBody().get(snake.getBody().size() - 1));
                    break;
                }
                snake.getBody().add(0, new Point(snake.getHead().x, snake.getHead().y + SIZE));
                snake.getBody().remove(snake.getBody().get(snake.getBody().size() - 1));
            }
            case ('L') -> {
                if (snake.getHead().x == WALLLEFT) {
                    snake.getBody().add(0, new Point(WALLRIGHT - SIZE, snake.getHead().y));
                    snake.getBody().remove(snake.getBody().get(snake.getBody().size() - 1));
                    break;
                }
                snake.getBody().add(0, new Point((snake.getHead().x - SIZE), snake.getHead().y));
                snake.getBody().remove(snake.getBody().get(snake.getBody().size() - 1));
            }
            case ('R') -> {
                if (snake.getHead().x == WALLRIGHT) {
                    snake.getBody().add(0, new Point(WALLLEFT, snake.getHead().y));
                    snake.getBody().remove(snake.getBody().get(snake.getBody().size() - 1));
                    break;
                }
                snake.getBody().add(0, new Point(snake.getHead().x + SIZE, snake.getHead().y));
                snake.getBody().remove(snake.getBody().get(snake.getBody().size() - 1));
            }
        }

        snake.setHead(snake.getBody().get(0));
        snake.setTail(snake.getBody().get(snake.getBody().size() - 1));
        repaint();
    }

    // Method checks for self-collision
    public boolean bitHimself() {
        for (int i = 1; i < snake.getBody().size() - 1; i++) {
            if (snake.getHead().equals(snake.getBody().get(i))) {
                setInGame(false);
                return true;
            }
        }
        return false;
    }

    // Method checks if Apple has been eaten and creates a new Point for the Snake if true, dynamically adjusts the Point
    // with help of the moveSnake() method.
    public boolean appleEaten() {
        if (snake.getHead().x == apple.getX() && snake.getHead().getY() == apple.getY()) {
            snake.getBody().add(new Point(800, 800));
            return true;
        }
        return false;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Point tmp : snake.getBody()) {
            g.setColor(Color.red);
            g.fillRect(tmp.x, tmp.y, snake.getSnakeSize(), snake.getSnakeSize());
        }

        g.setColor(Color.green);
        g.fillRect(apple.getX(), apple.getY(), apple.getAppleSize(), apple.getAppleSize());
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    //Listener checks which arrow has been pressed and sets flags accordingly
    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_UP -> {
                if (invalidMove != 'U') {
                    setInvalidMove('D');
                    setDirection('U');
                }

            }
            case KeyEvent.VK_DOWN -> {
                if (invalidMove != 'D') {
                    setInvalidMove('U');
                    setDirection('D');
                }
            }
            case KeyEvent.VK_LEFT -> {
                if (invalidMove != 'L') {
                    setInvalidMove('R');
                    setDirection('L');
                }
            }
            case KeyEvent.VK_RIGHT -> {
                if (invalidMove != 'R') {
                    setInvalidMove('L');
                    setDirection('R');
                }
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

    //GETTER


    public boolean isInGame() {
        return inGame;
    }

    public int getScore() {
        return score;
    }


    //SETTER

    public void setDirection(char dir) {
        direction = dir;
    }

    public void setInvalidMove(char c) {
        invalidMove = c;
    }

    public void setInGame(boolean status) {
        inGame = status;
    }

}



