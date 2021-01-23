import javax.swing.JLabel;

/**
 * Keeps score of the game
 * @author Theo
 * @author Andrew
 */
public class Score {
    int score = 0;
    JLabel label;
    /**
     * Creates the score object
     * @param j the jlabel to write the score on
     */
    public Score(JLabel j) {
        this.label = j;
        this.score = 0;
    }
    /**
     * Adds a point to the score
     */
    public void addPoint() {
        this.score++;
        if(label != null) {
            label.setText("Score: " +score);
        }
    }
    /**
     * resets the score to 0
     */
    public void reset() {
        this.score = 0;
        if(label != null) {
            label.setText("Score: 0");
        }
    }

    /**
     * removes a point from the score
     */
    public void removePoint() {
        this.score--;
        if(label != null) {
            label.setText("Score: " +score);
        }
    }

    /**
     * @return the score value
     */
    public int getScore() {
        return score;
    }
}
