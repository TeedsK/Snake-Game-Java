import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
// @SuppressWarnings("serial")
/**
 * Creates the header of the jframe board
 */
public class Header extends JPanel {
    Score scoreboard;

    /**
     * Creates the header object
     */
    public Header() {
        JLabel title = new JLabel("Snake");
        title.setForeground(new Color(255,255,255));
        this.setPreferredSize(new Dimension(100,30));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(Box.createRigidArea(new Dimension(10,10)));
        JLabel score = new JLabel("Score: 0");
        score.setForeground(new Color(255,255,255));
        this.scoreboard = new Score(score);
        setBackground(new Color(42, 147, 50));
        this.add(score);
        this.add(Box.createHorizontalGlue());
        this.add(title);
        this.add(Box.createHorizontalGlue());
    }

    /**
     * Returns the score object
     * @return score object
     */
    public Score getScore() {
        return scoreboard;
    }
}
