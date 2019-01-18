package honkytonky.objects;

import static honkytonky.resources.ANSI_Color_Codes.ANSI_GREEN;

import honkytonky.resources.ExpTable;

public class Player extends Actor {

    private int currentRoomID;
    private int temporaryDefBoost;
    private int experience;
    private boolean hadLevelUp = false;
    private Weapon weapon;
    private Armor armor;
    private ExpTable expTable = new ExpTable();

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

    public int getExperience()
    {
        return experience;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void increaseExperience(int amount)
    {
        this.experience += amount;
    }

    public void checkForLevelUp(Player player)
    {
        if(expTable.hasLevelUp(player.getLevel(), player.experience))
        {
            this.setLevel(getLevel() + 1);

            System.out.println("\nYou have leveled up!\n");
            System.out.println("Your new Level is: " + ANSI_GREEN + this.getLevel());
        }
    }

    public float getPercentalExperience()
    {
        return expTable.calculatePercentalExperience(experience, getLevel());
    }
}
