package honkytonky.objects;

public class Player extends Actor {

    private int currentRoomID;
    private int temporaryDefBoost;
    private Weapon weapon;
    private Armor armor;

    public Player(String name, int maxHP, int x, int y, Weapon weapon, Armor armor) {
        super(name, maxHP, x, y);

        //@formatter:off
        this.currentRoomID      = 0;
        this.temporaryDefBoost  = 0;
        this.weapon             = weapon;
        this.armor              = armor;
        //formatter:

    }

    public int getCurrentRoomID()
    {
        return currentRoomID;
    }

    public void setCurrentRoomID(int currentRoomID)
    {
        this.currentRoomID = currentRoomID;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public Armor getArmor()
    {
        return armor;
    }
}
