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

    private final List<Item> itemsForSell = new ArrayList<>();
    private final String smalltalk;
    Scanner scanner = new Scanner(System.in);

    public Merchant(String name, int maxHP, int level, String smalltalk) {
        super(name, maxHP, level);
        this.smalltalk = smalltalk;
    }

    public void addItemToShop(Item item) {
        this.itemsForSell.add(item);
    }

    public void removeItemFromShop(Item item) {
        this.itemsForSell.remove(item);
    }

    public void printSmalltalk() {
        ClearScreen.clearScreen();
        System.out.println(ANSI_YELLOW + this.getName() + ": " + ANSI_BLUE + smalltalk + ANSI_RESET);
    }

    /**
     * @return ID of item player wants to buy
     */
    public int printItemsForSell() {
        ClearScreen.clearScreen();

        System.out
          .println(ANSI_YELLOW + this.getName() + ANSI_RESET + " is selling the following items:\n");

        int i = 1;  //start counting at 1 for design purposes
        for (Item item : itemsForSell) {
            System.out.println(i + ") " + item + " (" + item.getValue() + " Gold)");
            i++;
        }

        System.out.println(i + ") Don't trade \n");
        final int choice = Integer.parseInt(scanner.nextLine());

        // Can't use variable in switch-Statement, therefore a specialcase for choosing item
        if (choice == i) {   // choose "Dont trade"
            return -1;
        } else if (choice < i && choice > 0) {     // choice does match an item
            return choice;
        } else {    // choice does not match with options -> print Dialog again
            this.printItemsForSell();
        }
        return -1;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
