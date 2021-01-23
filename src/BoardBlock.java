import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
@SuppressWarnings("serial")
/**
 * Each individual block of the board
 * @author Theo
 * @author Andrew
 */
public class BoardBlock extends JPanel {
    Color baseColor;
    Color bodyColor = null;
    boolean hasApple = false;
    boolean isHead = false;
    String direction = "right";
    String pastDirection = "left";
    public BoardBlock(Color c) {
        baseColor = c;
        this.setBackground(c);
        this.setOpaque(false);
    }

    /**
     * Resets the tile for the snake body part
     */
    public void reset() {
        bodyColor = null;
        repaint();
        revalidate();
    }

    /**
     * Fully resets the tile, including the apple
     */
    public void fullReset() {
        hasApple = false;
        reset();
    }

    /**
     * Sets the color of the background then updates
     * @param c the desired color
     */
    public void setBackgroundColor(Color c) {
        bodyColor = c;
        repaint();
        revalidate();
    }

    /**
     * Sets if this tile contains an apple
     * @param b boolean if apple is on this tile
     */
    public void setApple(boolean b) {
        this.hasApple = b;
        repaint();
        revalidate();
    }

    /**
     * get if this tile contains an apple
     * @return boolean if apple is on the tile
     */
    public boolean getApple() {
        return hasApple;
    }

    /**
     * Switches if this tile contains the head
     * @param b boolean if this tile has the head of the snake
     */
    public void setHead(boolean b) {
        this.isHead = b;
        repaint();
        revalidate();
    }

    /**
     * Updates the current direction for this tile
     * @param d the direction
     */
    public void setDirection(String d) {
        this.direction = d;
        repaint();
        revalidate();
    }

    /**
     * Updates the past direction for this tile
     * @param d the past direction
     */
    public void setPastDirection(String d) {
        this.pastDirection = d;
        repaint();
        revalidate();
    }

    /**
     * Get the current direction
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(baseColor);
        g2.fillRect(0, 0, getWidth(), getHeight());
        if(hasApple) {
            g2.setColor(new Color(240, 63, 63));
            g2.fillRoundRect(5,5,getWidth() - 10, getHeight() - 10, 10, 10);
            g2.setColor(new Color(63, 235, 76));
            g2.fillRoundRect(14,6,5,5,10,10);
        }
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        
        if(bodyColor != null) {
            g2.setColor(bodyColor);
            if(isHead) {
                int headX = getWidth() - 5;
                int headY = getHeight() - 5;
                g2.fillOval((getWidth() / 2) - (headX / 2), height - (headY / 2), headX, headY);
                if(direction.equals("right")) {
                    g2.fillRect(0, height - (height / 2), width, height);
                } else if(direction.equals("left")) {
                    g2.fillRect(width - (width / 2), height - (height / 2), getWidth(), height);
                } else if(direction.equals("down")) {
                    g2.fillRect(width - (width / 2), 0, width, height);
                } else if(direction.equals("up")) {
                    g2.fillRect(width - (width / 2), height - (height / 2), width, getHeight());
                }
                drawEyes(g2);
            } else {
                int SmallBoxWidth = getWidth() / 4;
                int SmallBoxHeight = getHeight() / 4;
                int bigBoxWidth = width + SmallBoxWidth;
                int bigBoxHeight = height + SmallBoxHeight;
                int WidthPosition = (getWidth() - width) / 2;
                if((pastDirection.equals("right") && direction.equals("down")) || (pastDirection.equals("up") && direction.equals("left"))) {
                    g2.fillRect(0, (getHeight() / 2) - (height / 2), bigBoxWidth, bigBoxHeight);
                    g2.setColor(baseColor);
                    g2.fillRect(0, getHeight() - (SmallBoxHeight), SmallBoxWidth, SmallBoxHeight);
    
                } else if((pastDirection.equals("right") && direction.equals("up")) || (pastDirection.equals("down") && direction.equals("left"))) {
                    g2.fillRect(0, 0, bigBoxWidth, bigBoxHeight);
                    g2.setColor(baseColor);
                    g2.fillRect(0, 0, SmallBoxWidth, SmallBoxHeight);
    
                } else if((pastDirection.equals("left") && direction.equals("down")) || (pastDirection.equals("up") && direction.equals("right"))) {
                    g2.fillRect(WidthPosition, (getHeight() / 2) - (height / 2), bigBoxWidth, bigBoxHeight);
                    g2.setColor(baseColor);
                    g2.fillRect((getWidth() / 2) + SmallBoxWidth, getHeight() - (SmallBoxHeight), SmallBoxWidth, SmallBoxHeight);
                    
                } else if((pastDirection.equals("left") && direction.equals("up")) || (pastDirection.equals("down") && direction.equals("right"))) {
                    g2.fillRect(WidthPosition, 0, bigBoxWidth, bigBoxHeight);
                    g2.setColor(baseColor);
                    g2.fillRect((getWidth() / 2)  + SmallBoxWidth, 0, SmallBoxWidth, SmallBoxHeight);
    
                } else {
                    if(pastDirection.equals("right") || pastDirection.equals("left")) {
                        g2.setColor(bodyColor);
                        g2.fillRect(0, (getHeight() / 2) - (height / 2), getWidth(), height);
                    } else if(pastDirection.equals("down") || pastDirection.equals("up")) {
                        g2.setColor(bodyColor);
                        g2.fillRect((getWidth() / 2) - (width / 2), 0, width, getHeight());
                    }
                }
            }
        }
    }

    /**
     * Draws the white of the eyes in the snake head
     * @param g2 the graphics2d object
     */
    private void drawEyes(Graphics2D g2) {
        g2.setColor(new Color(255,255,255, 150));
        int eyeWidth = 11;
        int eyeHeight = 11;
        if(direction.equals("right")) {
            g2.fillOval(getWidth() - 7 - eyeWidth, 2, eyeWidth, eyeHeight);
            g2.fillOval(getWidth() - 7 - eyeWidth, 16, eyeWidth, eyeHeight);
        } else if(direction.equals("left")) {
            g2.fillOval(7, 2, eyeWidth, eyeHeight);
            g2.fillOval(7, 16, eyeWidth, eyeHeight);
        } else if(direction.equals("down")) {
            g2.fillOval(2, getHeight() - 7 - eyeHeight, eyeWidth, eyeHeight);
            g2.fillOval(19, getHeight() - 7 - eyeHeight, eyeWidth, eyeHeight);
        } else if(direction.equals("up")) {
            g2.fillOval(2,  7, eyeWidth, eyeHeight);
            g2.fillOval(19, 7, eyeWidth, eyeHeight);
        }
        drawPupil(g2);
    }

    /**
     * Draws the pupils of the eyes in the snake head
     * @param g2 the graphics2d object
     */
    private void drawPupil(Graphics2D g2) {
        g2.setColor(new Color(0,0,0, 180));
        int pupilWidth = 5;
        int pupilHeight = 5;
        if(direction.equals("right")) {
            g2.fillOval(getWidth() - 7 - pupilWidth, 4, pupilWidth, pupilHeight);
            g2.fillOval(getWidth() - 7 - pupilWidth, 18, pupilWidth, pupilHeight);
        } else if(direction.equals("left")) {
            g2.fillOval(7, 4, pupilWidth, pupilHeight);
            g2.fillOval(7, 18, pupilWidth, pupilHeight);
        } else if(direction.equals("down")) {
            g2.fillOval(4, getHeight() - 7 - pupilHeight, pupilWidth, pupilHeight);
            g2.fillOval(21, getHeight() - 7 - pupilHeight, pupilWidth, pupilHeight);
        } else if(direction.equals("up")) {
            g2.fillOval(4,  7, pupilWidth, pupilHeight);
            g2.fillOval(21, 7, pupilWidth, pupilHeight);
        }
    }
}
