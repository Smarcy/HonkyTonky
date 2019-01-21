package honkytonky.objects;

import static honkytonky.resources.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.resources.ANSI_Color_Codes.ANSI_RESET;

import honkytonky.resources.ExpTable;

public class Player extends Actor {

    private int currentRoomID;
    private int temporaryDefBoost;
    private int experience;
    private boolean hadLevelUp = false;
    private Weapon weapon;
    private Armor armor;
    private Room currentRoom;
    private ExpTable expTable = new ExpTable();

    public Player(String name, int maxHP, int x, int y, Weapon weapon, Armor armor,
      Room currentRoom) {

        super(name, maxHP, x, y, 1);

        //@formatter:off
        this.currentRoomID      = 0;
        this.currentRoom        = currentRoom;
        this.temporaryDefBoost  = 0;
        this.experience         = 0;
        this.weapon             = weapon;
        this.armor              = armor;
        //@formatter:on

    }

    public int getCurrentRoomID() {
        return currentRoomID;
    }

    public void giveTemporaryDefBoost() {
        float defBoost = ((float) this.armor.getArmorPoints() / 2);

        if (defBoost > 10) {
            this.temporaryDefBoost = 2;
        } else if (defBoost < 1) {
            this.temporaryDefBoost = 1;
        } else {
            this.temporaryDefBoost = (int) defBoost;
        }
    }

    public void resetTemporaryDefBoost() {
        this.temporaryDefBoost = 0;
    }

    public float getTemporaryDefBoost() {
        return this.temporaryDefBoost;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public int getExperience() {
        return experience;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void increaseExperience(int amount) {
        this.experience += amount;
    }

    public void checkForLevelUp() {
        if (expTable.hasLevelUp(this.getLevel(), this.experience)) {
            this.setLevel(getLevel() + 1);

            System.out.println("\nYou have leveled up!\n");
            System.out.println("Your new Level is: " + ANSI_GREEN + this.getLevel() + ANSI_RESET);
        }
    }

    public float getPercentalExperience() {
        return expTable.calculatePercentalExperience(experience, getLevel());
    }
}
