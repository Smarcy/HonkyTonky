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
    private Potion potion;
    private Room currentRoom;
    private ExpTable expTable = new ExpTable();
    private List<Item> inventory;

    public Player(String name, int maxHP, Weapon weapon, Armor armor, Potion potion,
      Room currentRoom) {

        super(name, maxHP, 0, 0, 1);        // give x & y default values - coordinates not needed anymore after Room-Door revamp?!

        //@formatter:off
        this.currentRoom        = currentRoom;
        this.temporaryDefBoost  = 0;
        this.experience         = 0;
        this.weapon             = weapon;
        this.armor              = armor;
        this.potion             = potion;
        this.inventory          = new ArrayList<>();
        //@formatter:on

        inventory.add(weapon);
        inventory.add(armor);
        inventory.add(potion);
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

    public void showInventory(String option) {

        switch (option) {
            case "weapons":
                for (Item item : inventory) {
                    if (item instanceof Weapon) {

                        if(item.equals(this.weapon)) {
                            System.out.println(item + " (equipped)");
                        } else {
                            System.out.println(item);
                        }
                    }
                }
                break;
            case "armors":
                for (Item item : inventory) {
                    if (item instanceof Armor) {
                        if(item.equals(this.armor)) {
                            System.out.println(item + " (equipped)");
                        } else {
                            System.out.println(item);
                        }
                    }
                }
                break;
            case "potions":
                for (Item item : inventory) {
                    if (item instanceof Potion) {
                        System.out.println(item);
                    }
                }
                break;
        }
    }

    public void usePotion(String potionType, int amount) {
        boolean playerHasPotion = false;

        switch (potionType) {
            case "health":

                for (Item p : inventory) {
                    if (p.getName().equals("Small Health Potion")) {
                        inventory.remove(p);
                        playerHasPotion = true;
                        break;
                    }
                }

                if (playerHasPotion) {
                    switch (amount) {
                        case 10:
                            this.healPlayer(10);
                            break;
                        case 20:
                            this.healPlayer(20);
                            break;
                    }
                    System.out.println("\nYou were healed by " + ANSI_GREEN +  amount + ANSI_RESET + " points!");
                } else {
                    System.out.println("\nYou do not have any Small Health Potions!");
                }
            case "defense":
                break;
        }
    }


    private void healPlayer(int amount) {
        this.setHp(this.getHp() + amount);

        if (this.getHp() > this.getMaxHP()) {
            this.setHp(this.getMaxHP());
        }
    }

    public float getPercentalExperience() {
        return expTable.calculatePercentalExperience(experience, getLevel());
    }

}
