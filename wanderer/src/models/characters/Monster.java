package models.characters;

public class Monster extends Character{
    private boolean hasKey;

    public Monster(int level, int x, int y) {
        super(level, x, y);
        setMaxHP(2 * level * rollDice(6));
        setCurrentHP(getMaxHP());
        setDP((level / 2) * rollDice(6));
        setSP(level * rollDice(6));
        hasKey = false;
    }

    public boolean hasKey() {
        return hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    @Override
    public String toString() {
        return "Monster{" +
               "level=" + getLevel() +
               ", x=" + getX() +
               ", y=" + getY() +
               ", hasKey=" + hasKey +
               '}';
    }
}
