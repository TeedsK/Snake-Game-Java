import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
@SuppressWarnings("serial")
/**
 * The Game Board Frame
 * @author Theo
 * @author Andrew
 */
public class Board extends JFrame {
    BoardBlock[][] BoardBlocks = new BoardBlock[23][23];
    JPanel background;
    JPanel BoardContainer;
    Snake snake;
    Apple apple;
    Header header;
    boolean GameOver = true;
    Menu menu;

    /**
     * Creates the gameboard with a new JFrame
     */
    public Board() {
        this.setTitle("Snake");
        this.setPreferredSize(new Dimension(763,830));
        this.setMinimumSize(new Dimension(300,300));
        this.setSize(763,830);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.background = new JPanel();
        this.background.setLayout(new BorderLayout());
        menu = new Menu(this);
        this.background.add(menu, BorderLayout.SOUTH);
        this.add(this.background);
        this.header = new Header();
        this.background.add(header, BorderLayout.NORTH);
        this.BoardContainer = new JPanel();
        BoardContainer.setLayout(new GridLayout(23, 23));
        Color LightColor = new Color(25,137,27);
        Color DarkColor = new Color(13,104,15);
        int times = 0;
        for(int x = 0; x < 23; x++) {
            for(int y = 0; y < 23; y++) {
                BoardBlock block;
                if(times % 2 == 0)  {
                    block = new BoardBlock(LightColor);
                } else {
                    block = new BoardBlock(DarkColor);
                }
                this.BoardContainer.add(block);
                BoardBlocks[x][y] = block;
                times++;
            }
        }
        this.background.add(this.BoardContainer, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    } 

    /**
     * Creates a new thread and starts the game in that thread. 
     */
    public void start() {
        Thread t = new Thread() {
            public void run() {
                GameOver = false;
                menu.update();
                getHeader().getScore().reset();
                try {
                    Thread.sleep(500);
                } catch(Exception err1) {}
                while(!GameOver) {
                    snake.move();
                    try {
                        Thread.sleep(100);
                    } catch(Exception err1) {}
                }
            }
        }; t.start();
    }

    /**
     * makes the game stop 
     */
    public void setGameOver() {
        GameOver = true;
        menu.update();
    }

    /**
     * @return the status of the game
     */
    public boolean getGameOver() {
        return GameOver;
    }

    /**
     * get the snake object
     * @return the snake
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * 
     * @return get the header containing the score and title
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Resets the game baord
     */
    public void reset() {
        for(BoardBlock[] blocks : BoardBlocks) {
            for(BoardBlock block : blocks) {
                block.fullReset();
            }
        }
        snake = new Snake(8,5, this);
        apple = new Apple(this);
    }

    /**
     * Get the apple object
     * @return the apple
     */
    public Apple getApple() {
        return apple;
    }

    /**
     * Get all the board blocks
     * @return the board blocks
     */
    public BoardBlock[][] getBoardBlocks() {
        return BoardBlocks;
    }
}
