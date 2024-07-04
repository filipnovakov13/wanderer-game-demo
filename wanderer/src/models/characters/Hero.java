package models.characters;

import models.Direction;

public class Hero extends Character{

    private Direction direction;

    public Hero(int level, int x, int y) {
        super(level, x, y);
        direction = Direction.DOWN;
        setMaxHP(20 + 3 * rollDice(6));
        setCurrentHP(getMaxHP());
        setDP(2 * rollDice(6));
        setSP(5 + rollDice(6));
    }

    public void lvlUp() {
        setLevel(getLevel() + 1);
        setMaxHP(getMaxHP() + rollDice(6));
        setDP(getDP() + rollDice(6));
        setSP(getSP() + rollDice(6));
    }

    public void restoreHP() {
        double chance = Math.random();
        if (chance < 0.5) {
            setCurrentHP(Math.min(getCurrentHP() + (getMaxHP() / 10), getMaxHP()));
        } else if (chance > 0.5 && chance < 0.9) {
            setCurrentHP((int) Math.min(getCurrentHP() + (getMaxHP() * 0.3), getMaxHP()));
        } else {
            setCurrentHP(getMaxHP());
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void moveUp() {
        this.direction = Direction.UP;
            setY(getY() - 1);
    }

    public void moveDown() {
        this.direction = Direction.DOWN;
            setY(getY() + 1);
    }

    public void moveLeft() {
        this.direction = Direction.LEFT;
            setX(getX() - 1);
    }

    public void moveRight() {
        this.direction = Direction.RIGHT;
            setX(getX() + 1);
    }
}
