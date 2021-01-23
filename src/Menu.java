import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.BorderLayout;
@SuppressWarnings("serial")
/*
* Bottom section container controls for the game
*/
public class Menu extends JPanel implements MouseListener {
    JPanel newGame;
    JPanel stopGame;
    Board board;
    /**
     * Creates the Menu object
     * @param board The board object
     */
    public Menu(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(100, 100));
        setBackground(new Color(42, 147, 50));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        newGame = createButton(newGame, "Start Game");
        stopGame = createButton(stopGame, "Stop Game");
        add(Box.createHorizontalGlue());
        add(stopGame);
        add(Box.createRigidArea(new Dimension(20,10)));
        add(newGame);
        add(Box.createRigidArea(new Dimension(20,10)));
    }

    /**
     * Creates a button
     * @param button The button you want to customize
     * @param s the text inside of the button
     * @return the customized button
     */
    private JPanel createButton(JPanel button, String s) {
        button = new JPanel();
        button.setBackground(new Color(105, 185, 111, 255));
        button.setMaximumSize(new Dimension(100,40));
        button.setPreferredSize(new Dimension(100,40)); 
        button.setLayout(new BorderLayout());
        button.addMouseListener(this);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel t = new JLabel(s);
        t.setForeground(new Color(255,255,255));
        t.setHorizontalAlignment(JLabel.CENTER);
        button.add(t, BorderLayout.CENTER);
        return button;
    }

    /**
     * Updates the buttons
     */
    public void update() {
        if(!board.getGameOver()) {
            newGame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            stopGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
            stopGame.setBackground(new Color(105, 185, 111));
            stopGame.getComponent(0).setForeground(new Color(255,255,255));
            newGame.setBackground(new Color(105, 185, 111, 130));
            newGame.getComponent(0).setForeground(new Color(255,255,255, 130));
            repaint();
            revalidate();
        } else if(board.getGameOver()) {
            stopGame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            newGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
            stopGame.getComponent(0).setForeground(new Color(255,255,255, 130));
            stopGame.setBackground(new Color(105, 185, 111, 130));
            newGame.setBackground(new Color(105, 185, 111));
            newGame.getComponent(0).setForeground(new Color(255,255,255));
            repaint();
            revalidate();
        }
    }

    /**
     * Checks which button was clicked then either starts a new game or stops one
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Component b = e.getComponent();
        if(b.equals(newGame)) {
            if(board.getGameOver()) {
                board.reset();
                board.start();
            }
        } else if(b.equals(stopGame)) {
            if(!board.getGameOver()) {
                board.setGameOver();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
