import java.util.Random;

/**
 * The apple class for the snake to eat
 * @author Theo 
 * @author Andrew
 */
public class Apple {
    Board board;
    int xPos = 0;
    int yPos = 0;
    /**
     * Creates the apple object
     * @param board The Game board
     */
    public Apple(Board board) {
        this.board = board;
        updateApple();
    }
    /**
     * Verifies if the apple was eaten from the head of the snake
     * @return if the apple has been eaten
     */
    public boolean checkApple() {
        SnakeBodyPart head = board.getSnake().getSnakeBody().get(0);
        if(head.getX() == xPos && head.getY() == yPos) {
            System.out.println("APPLE HAS BEEN EATED");
            return true;
        }
        return false;
    }
    
    /**
     * Changes the location of the apple and checks if the snake body part is already there
     */
    public void updateApple() {
        this.xPos = new Random().nextInt(23);
        this.yPos = new Random().nextInt(23);
        while(true) {
            boolean works = true;
            for(SnakeBodyPart p : board.getSnake().getSnakeBody()) {
                if(xPos == p.getX() && yPos == p.getY()) {
                    works = false;
                    this.xPos = new Random().nextInt(23);
                    this.yPos = new Random().nextInt(23);
                    break;
                }
            }
            if(works) {
                board.getBoardBlocks()[xPos][yPos].setApple(true);
                return;
            }
        }
    }
}
