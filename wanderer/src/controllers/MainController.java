package controllers;

import models.Direction;
import models.areaelements.Floor;
import models.characters.Hero;
import models.areaelements.Area;
import views.Board;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainController implements KeyListener {

    private Board board;
    private Hero hero;

    private Area area;

    public MainController(Board board) {
        area = new Area();
        this.hero = new Hero(0, 0);
        this.board = board;
        board.setHero(hero);
        board.setArea(area);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
           if (hero.getY() - 1 >= 0 && (area.getTiles(1)[hero.getY() - 1][hero.getX()]) instanceof Floor) {
               hero.moveUp();
           } else {
               hero.setDirection(Direction.UP);
           }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            if (hero.getY() + 1 <= 9 && (area.getTiles(1)[hero.getY() + 1][hero.getX()]) instanceof Floor) {
                hero.moveDown();
            } else {
                hero.setDirection(Direction.DOWN);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            if (hero.getX() - 1 >= 0 && (area.getTiles(1)[hero.getY()][hero.getX() - 1]) instanceof Floor) {
                hero.moveLeft();
            } else {
                hero.setDirection(Direction.LEFT);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            if (hero.getX() + 1 <= 9 && (area.getTiles(1)[hero.getY()][hero.getX() + 1]) instanceof Floor) {
                hero.moveRight();
            } else {
                hero.setDirection(Direction.RIGHT);
            }
        }

        board.repaint();
    }
}
