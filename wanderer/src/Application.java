import controllers.MainController;
import models.areaelements.Area;
import views.Board;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Wanderer");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                Area area = new Area();
                Board board = new Board(area);
                frame.add(board);

                MainController controller = new MainController(board, area);
                frame.addKeyListener(controller);

                board.setController(controller);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setResizable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
