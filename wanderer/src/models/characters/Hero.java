package models.characters;

import models.Direction;
import models.areaelements.Area;
import views.Board;

public class Hero {

    private int x;
    private int y;

    private Direction direction;

    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
        direction = Direction.DOWN;
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
