import controllers.MainController;
import models.areaelements.Area;
import views.Board;
import views.IntroPanel;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Wanderer");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                Area area = new Area();
                Board board = new Board(area);

                IntroPanel introPanel = new IntroPanel(board);
                frame.add(introPanel);

                MainController controller = new MainController(board, area);
                frame.addKeyListener(controller);

                board.setController(controller);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setResizable(false);

                introPanel.display();

            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
