package honkytonky.objects;

public class Armor extends Item
{

    private int armorPoints;

    public Armor(int id, String name, int armorPoints)
    {
        super(id, name);
        this.armorPoints = armorPoints;
    }
}
