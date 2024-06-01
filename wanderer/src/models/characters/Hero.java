package models.characters;

import models.Direction;
import models.areaelements.Area;
import views.Board;

public class Hero extends Character{

    private int x;
    private int y;

    private Direction direction;

    public Hero(int level, int x, int y) {
        super(level);
        this.x = x;
        this.y = y;
        direction = Direction.DOWN;
        setMaxHP(20 + 3 * rollDice(6));
        setCurrentHP(getMaxHP());
        setDP(2 * rollDice(6));
        setSP(5 + rollDice(6));
    }

    public void lvlUp() {
        setMaxHP(getMaxHP() + rollDice(6));
        setDP(getDP() + rollDice(6));
        setSP(getSP() + rollDice(6));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void moveUp() {
        this.direction = Direction.UP;
            y--;
    }

    public void moveDown() {
        this.direction = Direction.DOWN;
            y++;
    }

    public void moveLeft() {
        this.direction = Direction.LEFT;
            x--;
    }

    public void moveRight() {
        this.direction = Direction.RIGHT;
            x++;
    }
}
