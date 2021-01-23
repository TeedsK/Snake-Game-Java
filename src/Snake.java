import java.util.ArrayList;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The Snake Head and the container for the body parts
 * @author Andrew L
 * @author Theo K
 */
public class Snake implements KeyListener {
    ArrayList<SnakeBodyPart> SnakeBody = new ArrayList<SnakeBodyPart>();
    Board board;
    String direction = "right";
    String pastDirection = "left";
    Color[] SnakeColors = { 
            new Color(53, 214, 54), // Plain Snake
            // new Color(152, 72, 255), // Purple
            // new Color(249, 72, 255), // Pink
            // new Color(255, 72, 72), // Red
            // new Color(255, 161, 72), // Orange
            // new Color(244, 255, 72), // Yellow
            // new Color(16, 115, 33) // Green
    };

    /**
     * Creates the snake object with the body parts
     * @param x the starting X cordinate
     * @param y the starting Y cordinate
     * @param board The game board
     */
    public Snake(int x, int y, Board board) {
        this.board = board;
        addBodyPart(x, y);
        addBodyPart(x, y-1);
        addBodyPart(x, y-2);
        addBodyPart(x, y-3);
        addBodyPart(x, y-4);
        board.addKeyListener(this);
    }

    /**
     * @return the snake body arraylist
     */
    public ArrayList<SnakeBodyPart> getSnakeBody() {
        return SnakeBody;
    }

    /*
     * adds a new body part to the snake
     */
    public void addBodyPart(int x, int y) {
        Color BodyColor = SnakeColors[SnakeBody.size() % SnakeColors.length];
        SnakeBody.add(new SnakeBodyPart(BodyColor, x, y, "tail"));
        board.getBoardBlocks()[x][y].setBackgroundColor(BodyColor);
    }

    /**
     * moves the snake in the direction chosen
     */
    public void move() {
        int x = 0;
        int y = 0;
        if(direction.equals("up")) {
            x--;
        } else if(direction.equals("left")) {
            y--;
        } else if(direction.equals("right")) {
            y++;
        } else if(direction.equals("down")) {
            x++;
        }
        SnakeBodyPart head = SnakeBody.get(0);
        board.getBoardBlocks()[head.getX()][head.getY()].setPastDirection(pastDirection);
        board.getBoardBlocks()[head.getX()][head.getY()].setDirection(direction);
        board.getBoardBlocks()[head.getX()][head.getY()].setHead(false);
        pastDirection = getInverse(direction);
        head.setX(head.getX() + x);
        head.setY(head.getY() + y);
        try {
            if(board.getBoardBlocks()[head.getX()][head.getY()].getApple()) {
                board.getBoardBlocks()[head.getX()][head.getY()].setApple(false);
                board.getApple().updateApple();
                SnakeBodyPart tail = SnakeBody.get(SnakeBody.size() - 1);
                addBodyPart(tail.getX(), tail.getY());
                board.getHeader().getScore().addPoint();
            }
            BoardBlock block = board.getBoardBlocks()[head.getX()][head.getY()];
            block.setDirection(direction);
            block.setHead(true);
            block.setBackgroundColor(head.getColor());
            for(int t = 1; t < SnakeBody.size(); t++) {
                SnakeBodyPart body = SnakeBody.get(t);
                int xVal = SnakeBody.get(t - 1).getPastX();
                int yVal = SnakeBody.get(t - 1).getPastY();
                if(t == SnakeBody.size() - 1) {
                    board.getBoardBlocks()[body.getX()][body.getY()].reset();
                }
                body.setX(xVal);
                body.setY(yVal);
                board.getBoardBlocks()[xVal][yVal].setBackgroundColor(body.getColor());
            }
            if(checkCollision(head.getX(), head.getY())) {
                board.setGameOver();
            }
        } catch(java.lang.ArrayIndexOutOfBoundsException e) {
            board.setGameOver();
        }
    }

    /**
     * Gets the opposite move
     * @param direction the direction 
     * @return the opposite direction
     */
    private String getInverse(String direction) {
        if(direction.equals("down")) {
            return "up";
        } else if(direction.equals("up")) {
            return "down";
        } else if(direction.equals("left")) {
            return "right";
        } else {
            return "left";
        }
    }

    /**
     * Checks if the snake has collided
     * @param headX the X position of the Head
     * @param headY the Y position of the Head
     * @return
     */
    private boolean checkCollision(int headX, int headY) {
        for(int x = 1; x < SnakeBody.size(); x++) {
            if(headX == SnakeBody.get(x).getX() && headY == SnakeBody.get(x).getY()) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && !direction.equals("down")) {
            pastDirection = direction;
            direction = "up";
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT && !direction.equals("right")) {
            pastDirection = direction;
            direction = "left";
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT && !direction.equals("left")) {
            pastDirection = direction;
            direction = "right";
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN && !direction.equals("up")) {
            pastDirection = direction;
            direction = "down";
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}