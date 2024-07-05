package views;

import controllers.MainController;
import models.areaelements.Area;
import models.Direction;
import models.characters.Boss;
import models.characters.Hero;
import models.areaelements.Floor;
import models.areaelements.Tile;
import models.characters.Monster;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Board extends JComponent {

    private Hero hero;
    private Area area;
    private final int tileSize;

    private MainController controller;

    public Board(Area area) {
        this.area = area;
        tileSize = 72;
        setPreferredSize(new Dimension(tileSize * 10, tileSize * 10 + 40));
        setVisible(false);
        setFocusable(true);
    }
    public void setController(MainController controller) {
        this.controller = controller;
        addKeyListener(controller);
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        if (controller.isGameOverWin()) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, getWidth(), getHeight());
            PositionedImage gameOverImage = new PositionedImage("wanderer/img/you-win.png", 40, 180);
            gameOverImage.draw(graphics);
        } else if (controller.isGameOverLose()) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, getWidth(), getHeight());
            PositionedImage gameOverImage = new PositionedImage("wanderer/img/game-over.png", 40, 180);
            gameOverImage.draw(graphics);
        } else {
            drawTiles(graphics);
            drawHero(graphics);
            drawStatistics(graphics);
            drawMonsters(graphics);
        }
    }
    private void drawTiles(Graphics graphics) {
        Tile[][] tiles = area.getCurrentTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                String image = tiles[j][i] instanceof Floor ? "wanderer/img/floor.png" : "wanderer/img/wall.png";

                new PositionedImage(image, i * tileSize, j * tileSize).draw(graphics);
            }
        }
    }

    private void drawHero(Graphics graphics) {
        String heroImagePath = "";
        if (hero.getDirection() == Direction.UP) {
            heroImagePath = "wanderer/img/hero-up.png";
        } else if (hero.getDirection() == Direction.DOWN) {
            heroImagePath = "wanderer/img/hero-down.png";
        } else if (hero.getDirection() == Direction.LEFT) {
            heroImagePath = "wanderer/img/hero-left.png";
        } else if (hero.getDirection() == Direction.RIGHT) {
            heroImagePath = "wanderer/img/hero-right.png";
        }

        PositionedImage heroImage = new PositionedImage(heroImagePath, hero.getX() * tileSize, hero.getY() * tileSize);
        heroImage.draw(graphics);
    }

    private void drawMonsters(Graphics graphics) {
        for (int i = 0; i < area.getMonsters().size(); i++) {
            Integer[] position = {area.getMonsters().get(i).getX(), area.getMonsters().get(i).getY()};
                if (position != null) {
                    String imagePath = area.getMonsters().get(i) instanceof Boss ? "wanderer/img/boss.png" : "wanderer/img/skeleton.png";
                    PositionedImage skeletonImage = new PositionedImage(
                            imagePath,
                            position[0] * tileSize,
                            position[1] * tileSize);
                    skeletonImage.draw(graphics);
                }
            }
        }

    private void drawStatistics(Graphics graphics) {
        boolean occupiedSquare = false;
        List<Monster> monsters = area.getMonsters();
        Monster occupyingMonster = null;
        String monsterName = null;

        for (Monster monster : monsters) {
            if (hero.getX() == monster.getX() && hero.getY() == monster.getY()) {
                occupiedSquare = true;
                occupyingMonster = monster;
                monsterName = monster instanceof Boss ? "Boss" : "Skeleton";
                break;
            }
        }

        if (!occupiedSquare) {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 720, 720, 40);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("Arial", Font.BOLD, 16));
            graphics.drawString("Hero (Level " + hero.getLevel() + ") HP: " + hero.getCurrentHP() + "/" +
                                hero.getMaxHP() + " | DP: " + hero.getDP() + " | SP: " + hero.getSP(), 20, 745);
        } else {
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 720, 360, 40);
            if (occupyingMonster.hasKey()) {
                graphics.setColor(Color.ORANGE);
            } else {
                graphics.setColor(Color.BLACK);
            }
            graphics.fillRect(360, 720, 360, 40);
            graphics.setFont(new Font("Arial", Font.BOLD, 16));
            graphics.drawString("Hero (Level " + hero.getLevel() + ") HP: " + hero.getCurrentHP() + "/" +
                                hero.getMaxHP() + " | DP: " + hero.getDP() + " | SP: " + hero.getSP(), 20, 745);
            graphics.setColor(Color.WHITE);
            graphics.drawString(   monsterName + " (Level " + occupyingMonster.getLevel() + ") HP: " + occupyingMonster.getCurrentHP() + "/" +
                                occupyingMonster.getMaxHP() + " | DP: " + occupyingMonster.getDP() + " | SP: " + occupyingMonster.getSP(), 380, 745);
        }
    }
}