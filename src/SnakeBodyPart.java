import java.awt.Color;
import javax.swing.JPanel;
@SuppressWarnings("serial")
/**
 * The individual body part of the snake
 * @author Theo
 * @author Andrew L
 */
public class SnakeBodyPart extends JPanel{
    Color bodyColor;
    int x = 0;
    int y = 0;
    int pastX = 0;
    int pastY = 0;
    String part; 
    /**
     * Creates the Snake Body Part Object
     * @param c the color of the body part
     * @param x the starting X cordinate of the body part
     * @param y the starting Y cordinate of the body part
     * @param part the type of the body part
     */
    public SnakeBodyPart(Color c, int x, int y, String part) {
        this.part = part;
        this.bodyColor = c;
        this.x = x;
        this.y = y;
        this.pastX = x;
        this.pastY = y;
    }
    /** 
     * @return the color of the body part
     */
    public Color getColor() {
        return bodyColor;
    }

    /**
     * @param x sets the x cordinate
     */
    public void setX(int x) {
        this.pastX = this.x;
        this.x = x;
    }
    /**
     * @param y sets the y cordinate
     */
    public void setY(int y) {
        this.pastY = this.y;
        this.y = y;
    }

    /**
     * @return the x cordinate
     */
    @Override 
    public int getX() {
        return x;
    }

    /**
     * @return the y cordinate
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * get the past X cordinate
     * @return past X
     */
    public int getPastX() {
        return pastX;
    }

    /**
     * get the past Y cordinate
     * @return past Y
     */
    public int getPastY() {
        return pastY;
    }
  }