package honkytonky.objects;

public class Monster extends Actor
{
    public enum MonsterType
    {
        ZOMBIE,
        BUTTERFLY
    }

    private int damage;
    private MonsterType monsterType;

    public Monster(String name, int maxHP, int x, int y, int damage, MonsterType monsterType)
    {
        super(name, maxHP, x, y);
        this.damage         = damage;
        this.monsterType    = monsterType;
    }

    public int getDamage()
    {
        return damage;
    }
}
