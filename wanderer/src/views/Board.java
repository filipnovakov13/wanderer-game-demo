package views;

import models.areaelements.Area;
import models.Direction;
import models.areaelements.Wall;
import models.characters.Hero;
import models.areaelements.Floor;
import models.areaelements.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board extends JComponent {


    private Map<String, Integer[]> skeletonPositions;
    private Hero hero;
    private Area area;
    private final int tileSize;

    public Board(Area area) {
        this.area = area;
        tileSize = 72;
        this.skeletonPositions = new HashMap<>();
        setPreferredSize(new Dimension(tileSize * 10, tileSize * 10 + 40));
        setVisible(true);
        generateSkeletonPositions();
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setSkeletonPositions(Map<String, Integer[]> skeletonPositions) {
        this.skeletonPositions = skeletonPositions;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        drawTiles(graphics);
        drawHero(graphics);
        drawStatistics(graphics);
        drawSkeletons(graphics);
    }
    private void drawTiles(Graphics graphics) {
        Tile[][] tiles = area.getTiles(1);
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

    private void drawSkeletons(Graphics graphics) {
        for (int i = 0; i < skeletonPositions.size(); i++) {
            Integer[] position = skeletonPositions.get("skeleton" + i);
                if (position != null) {
                    PositionedImage skeletonImage = new PositionedImage(
                            "wanderer/img/skeleton.png",
                            position[0] * tileSize,
                            position[1] * tileSize);
                    skeletonImage.draw(graphics);
                }
            }
        }

    public void generateSkeletonPositions() {
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int skeleX = random.nextInt(10);
            int skeleY = random.nextInt(10);
            if (area.getTiles(1)[skeleY][skeleX] instanceof Floor) {
                skeletonPositions.put("skeleton" + i, new Integer[]{skeleX, skeleY});
            } else {
                i--; // retry if the position is not a floor
            }
        }
    }

        //if (skeleX == hero.getX() && skeleY == hero.getY() && skeleX + 1 < 10)  skeleX++;



    private void drawStatistics(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 720, 720, 40);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial", Font.BOLD, 16));
        graphics.drawString("Hero (Level " + hero.getLevel() + ") HP: " + hero.getCurrentHP() + "/" +
          hero.getMaxHP() + " | DP: " + hero.getDP() + " | SP: " + hero.getSP(), 20, 745);
    }
}