package models.characters;

public class Boss extends Monster{
    public Boss(int level, int x, int y) {
        super(level, x, y);
        setMaxHP(2 * level * rollDice(6) + rollDice(6));
        setCurrentHP(getMaxHP());
        setDP((level / 2) * rollDice(6) + rollDice(6) / 2);
        setSP(level * rollDice(6) + level);
    }
}
