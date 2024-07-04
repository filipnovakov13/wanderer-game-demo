package models.characters;

import java.util.Random;

public class Character {

    private int maxHP;
    private int currentHP;
    private int DP;
    private int SP;
    private int level;
    private int x;
    private int y;

    protected Random random = new Random();

    public Character(int level, int x, int y) {
        this.level = level;
        this.x = x;
        this.y = y;
    }

    public int performStrike() {
        return 2 * rollDice(6) + this.SP;
    }

    public void takeDamage(int damage) {
        this.currentHP -= damage - this.DP;
    }

    public int rollDice(int sides) { return random.nextInt(sides) + 1; }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getDP() {
        return DP;
    }

    public void setDP(int DP) {
        this.DP = DP;
    }

    public int getSP() {
        return SP;
    }

    public void setSP(int SP) {
        this.SP = SP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
}