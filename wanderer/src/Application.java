import controllers.MainController;
import models.areaelements.Area;
import views.Board;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Wanderer game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Area area = new Area();
        Board board = new Board(area);
        frame.add(board);

        MainController controller = new MainController(board, area);
        frame.addKeyListener(controller);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
