package controllers;

import models.Direction;
import models.areaelements.Floor;
import models.characters.Boss;
import models.characters.Hero;
import models.areaelements.Area;
import models.characters.Monster;
import views.Board;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MainController implements KeyListener {
    
    
    private Board board;
    private Hero hero;
    private Area area;
    private int moveCounter = 0;
    private boolean gameOverLose;
    private boolean gameOverWin;

    public MainController(Board board, Area area) {
        this.hero = new Hero(1,0, 0);
        this.board = board;
        this.area = area;
        this.gameOverLose = false;
        this.gameOverWin = false;
        board.setHero(hero);
        board.setArea(area);
    }

    public boolean isGameOverLose() {
        return gameOverLose;
    }

    public boolean isGameOverWin() {
        return gameOverWin;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) {
        boolean moved = false;

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            moved = moveHero(hero.getY() - 1, hero.getX(), Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            moved = moveHero(hero.getY() + 1, hero.getX(), Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            moved = moveHero(hero.getY(), hero.getX() - 1, Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            moved = moveHero(hero.getY(), hero.getX() + 1, Direction.RIGHT);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && (isMonsterOnSameTile())) {
                initiateBattle();

        }

        if (moved) {
            moveCounter++;
            if (moveCounter % 2 == 0) {
                moveMonsters();
            }
        }
            board.repaint();
    }

    private boolean moveHero(int newY, int newX, Direction direction) {
        if (newY >= 0 && newY < 10 && newX >= 0 && newX < 10 && area.getCurrentTiles()[newY][newX] instanceof Floor) {
            hero.setY(newY);
            hero.setX(newX);
            hero.setDirection(direction);
            return true;
        }
        hero.setDirection(direction);
        return false;
    }

    private boolean isMonsterOnSameTile() {
        List<Monster> monsters = area.getMonsters();
        for (Monster monster : monsters) {
            if (hero.getX() == monster.getX() && hero.getY() == monster.getY()) {
                return true;
            }
        }
        return false;
    }

    private void moveMonsters() {
        List<Monster> monsters = area.getMonsters();
        int heroX = hero.getX();
        int heroY = hero.getY();

        for (Monster monster : monsters) {
            int monsterX = monster.getX();
            int monsterY = monster.getY();

            int distanceX = Math.abs(heroX - monsterX);
            int distanceY = Math.abs(heroY - monsterY);

            int newX = monsterX;
            int newY = monsterY;

            if (distanceX <= 3 && distanceY <= 3) {
                // Move towards the hero
                if (distanceX >= distanceY) {
                    if (heroX > monsterX) newX++;
                    else if (heroX < monsterX) newX--;
                } else {
                    if (heroY > monsterY) newY++;
                    else if (heroY < monsterY) newY--;
                }
            } else {
                // Move monster randomly
                int direction = new Random().nextInt(4);
                switch (direction) {
                    case 0:
                        newX++;
                        break; // Move right
                    case 1:
                        newX--;
                        break; // Move left
                    case 2:
                        newY++;
                        break; // Move down
                    case 3:
                        newY--;
                        break; // Move up
                    default:
                        newY ++;
                        break;
                }
            }

            // Ensure new position is within bounds and is a Floor tile
            if (newX >= 0 && newX < 10 && newY >= 0 && newY < 10 &&
                area.getCurrentTiles()[newY][newX] instanceof Floor &&
                !area.isPositionOccupied(newX, newY)) {
                monster.setX(newX);
                monster.setY(newY);
            }
        }
    }

    boolean keyBearerKilled = false;

    private void initiateBattle() {
        List<Monster> monsters = area.getMonsters();
        boolean bossExists = monsters.stream().anyMatch(Boss.class::isInstance);

        for (Iterator<Monster> iterator = monsters.iterator(); iterator.hasNext(); ) {
            Monster monster = iterator.next();
            if (hero.getX() == monster.getX() && hero.getY() == monster.getY()) {
                while (hero.getCurrentHP() > 0 && monster.getCurrentHP() > 0) {
                    heroAttack(monster);
                    if (monster.getCurrentHP() > 0) {
                        monsterAttack(monster, hero);
                    }
                }

                if (hero.getCurrentHP() > 0) {
                    hero.lvlUp();
                    if (monster.hasKey()) keyBearerKilled = true;
                    if (monster instanceof Boss) bossExists = false;

                    iterator.remove();

                } else {
                    gameOverLose = true;
                    break;
                }
                break;
            }
        }

        // Check if key bearer is killed and no boss exists
        if (keyBearerKilled && !bossExists) {
            area.increaseLevel();
            if (area.getAreaLevel() > 5) {
                gameOverWin = true;
                return;
            }
            hero.restoreHP();
            hero.setX(0);
            hero.setY(0);
            hero.setDirection(Direction.DOWN);
            keyBearerKilled = false;
        }
    }



    private void heroAttack(Monster monster) {
        int strikeValue = hero.performStrike();
        if (strikeValue > monster.getDP()) {
            monster.takeDamage(strikeValue);
        }
    }

    private void monsterAttack(Monster monster, Hero hero) {
        int strikeValue = monster.performStrike();
        if (strikeValue > hero.getDP()) {
            hero.takeDamage(strikeValue);
        }
    }
}
