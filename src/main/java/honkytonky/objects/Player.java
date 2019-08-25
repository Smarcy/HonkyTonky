package honkytonky.objects;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;

import honkytonky.misc.ExpTable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class Player extends Actor {

    private int temporaryDefBoost;
    private int experience;
    private int gold;
    private Weapon weapon;
    private Armor armor;
    private Room currentRoom;
    private final ExpTable expTable = new ExpTable();
    private final List<Item> inventory;
    private final Map<Potion, Integer> playersPotions;

    public Player(@NonNull String name, int maxHP, @NonNull Weapon weapon, @NonNull Armor armor, @NonNull Room currentRoom) {

        super(name, maxHP, 1);

        //@formatter:off
        this.currentRoom        = currentRoom;
        this.temporaryDefBoost  = 0;
        this.experience         = 0;
        this.gold               = 0;
        this.weapon             = weapon;
        this.armor              = armor;
        this.inventory          = new ArrayList<>();
        this.playersPotions     = new HashMap<>();
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

    public void giveGold(int amount) {
        this.gold += amount;
    }

    public void showInventory(String option) {

        switch (option) {
            case "weapons":
                for (Item item : inventory) {
                    if (item instanceof Weapon) {
                        if (item.equals(this.weapon)) {
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
                        if (item.equals(this.armor)) {
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

    public void usePotion(Potion potion) {
        healPlayer(potion.getAmount());
        inventory.remove(potion);
        playersPotions.put(potion, playersPotions.get(potion) - 1);
        System.out.println("You were healed by " + ANSI_GREEN + potion.getAmount() + ANSI_RESET + " health points!");
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

    public int getPotionCount(Item i) {
       return playersPotions.get(i.getName());
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
