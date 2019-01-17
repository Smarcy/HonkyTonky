package honkytonky.objects;

public class Player extends Actor {

    private int currentRoomID;
    private int temporaryDefBoost;
    private int experience;
    private boolean hadLevelUp = false;
    private Weapon weapon;
    private Armor armor;

    public Player(String name, int maxHP, int x, int y, Weapon weapon, Armor armor) {

        super(name, maxHP, x, y, 1);

        //@formatter:off
        this.currentRoomID      = 0;
        this.temporaryDefBoost  = 0;
        this.experience         = 0;
        this.weapon             = weapon;
        this.armor              = armor;
        //@formatter:on

    }

    public int getCurrentRoomID() {
        return currentRoomID;
    }

    public void setCurrentRoomID(int currentRoomID) {
        this.currentRoomID = currentRoomID;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public boolean rewardExperience(int amount)
    {
        int tempLevel = getLevel();
        this.experience += amount;

        if(didLevelUp(tempLevel))
        {
            return true;
        }

        return false;
    }

    public boolean didLevelUp(int tempLevel)
    {
        if(getLevel() > tempLevel)
        {
            return true;
        }

        return false;
    }
}
