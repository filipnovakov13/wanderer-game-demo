package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IntroPanel extends JPanel {
        private Board board;
        private boolean startGame;

        public IntroPanel(Board board) {
            this.board = board;
            this.startGame = false;

            setPreferredSize(new Dimension(720, 760));
            setBackground(Color.BLACK);

            // Listen for a key press to start the game
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    startGame = true;
                    removeKeyListener(this); // Remove listener after key is pressed
                    displayBoard();
                }
            });
            setFocusable(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Welcome to Wanderer", 150, 150);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Controls:", 200, 250);
            g.drawString("Move Up: W or UP arrow", 200, 300);
            g.drawString("Move Down: S or DOWN arrow", 200, 350);
            g.drawString("Move Left: A or LEFT arrow", 200, 400);
            g.drawString("Move Right: D or RIGHT arrow", 200, 450);
            g.drawString("Attack: Press SPACE", 200, 500);
            g.drawString("Press any key to start", 200, 600);
        }

        public void display() {
            requestFocusInWindow();
        }

        private void displayBoard() {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.remove(this);
            frame.add(board);
            frame.revalidate();
            frame.repaint();
            board.setVisible(true); // Make the board visible
            board.requestFocusInWindow();
        }
}
