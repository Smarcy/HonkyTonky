package honkytonky.objects;

public class Armor extends Item
{

    private final int armorPoints;
    private int durability;
    private int maxDurability;

    public Armor(int id, String name, int armorPoints, int durability, int maxDurability)
    {
        super(id, name);
        this.armorPoints    = armorPoints;
        this.durability     = durability;
        this.maxDurability  = maxDurability;
    }

    public int getArmorPoints() {
        return armorPoints;
    }

    @Override
    public String toString()
    {
        return this.getName();
    }
}
