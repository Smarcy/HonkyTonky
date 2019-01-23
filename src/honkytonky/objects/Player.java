package honkytonky.objects;

import static honkytonky.resources.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.resources.ANSI_Color_Codes.ANSI_RESET;

import honkytonky.objects.Weapon.WeaponType;
import honkytonky.resources.ExpTable;
import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {

    private int temporaryDefBoost;
    private int experience;
    private Weapon weapon;
    private Armor armor;
    private Room currentRoom;
    private ExpTable expTable = new ExpTable();
    private List<Item> inventory;

    public Player(String name, int maxHP, int x, int y, Weapon weapon, Armor armor,
      Room currentRoom) {

        super(name, maxHP, x, y, 1);

        //@formatter:off
        this.currentRoom        = currentRoom;
        this.temporaryDefBoost  = 0;
        this.experience         = 0;
        this.weapon             = weapon;
        this.armor              = armor;
        this.inventory          = new ArrayList<>();
        //@formatter:on

        inventory.add(weapon);
        inventory.add(armor);
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

    public int getTemporaryDefBoost() {
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

    public void showInventory(String option)
    {

        switch(option)
        {
            case "weapons":
                for(Item item : inventory) {
                    if(item instanceof Weapon) {
                        System.out.println(item);
                    }
                }
                break;
            case "armors":
                for(Item item : inventory) {
                    if(item instanceof Armor) {
                        System.out.println(item);
                    }
                }
                break;
            case "potions":
                System.out.println("Potions");
                break;
        }
    }

    public float getPercentalExperience() {
        return expTable.calculatePercentalExperience(experience, getLevel());
    }

}
