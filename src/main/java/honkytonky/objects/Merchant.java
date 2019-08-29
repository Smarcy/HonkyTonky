package honkytonky.objects;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_BLUE;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;

import honkytonky.misc.ClearScreen;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Merchant extends Actor {

    /**
     * The List of the Items the Merchant has to sell
     */
    private final List<Item> itemsForSell = new ArrayList<>();
    /**
     * The Merchants Dialog
     */
    private final String smalltalk;
    /**
     * Amount of Experience granted if the Player decides to attack
     */
    private final int grantedExperience;
    /**
     * Amount of Gold granted if the Player decides to attack
     */
    private final int grantedGold;
    /**
     * Scanner for taking Player Inputs
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * create a new Merchant
     *
     * @param name name of the Merchant
     * @param maxHP Merchants maximal Health Points
     * @param level Merchants level
     * @param damage Merchants damage it does
     * @param smalltalk Merchants dialog
     */
    public Merchant(String name, int maxHP, int level, int damage, int grantedExperience, int grantedGold, String smalltalk) {
        super(name, maxHP, level, damage);
        this.smalltalk = smalltalk;
        this.grantedExperience = grantedExperience;
        this.grantedGold = grantedGold;
    }

    /**
     * adds an Item to the Merchants shop to sell
     *
     * @param item the Item to add
     */
    public void addItemToShop(Item item) {
        this.itemsForSell.add(item);
    }

    /**
     * removes an Item from the Merchants shop
     *
     * @param item the Item to remove
     */
    public void removeItemFromShop(Item item) {
        this.itemsForSell.remove(item);
    }

    /**
     * print the Merchants dialog
     */
    public void printSmalltalk() {
        ClearScreen.clearScreen();
        System.out.println(ANSI_YELLOW + this.getName() + ": " + ANSI_BLUE + smalltalk + ANSI_RESET);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
