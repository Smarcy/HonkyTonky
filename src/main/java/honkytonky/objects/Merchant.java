package honkytonky.objects;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_BLUE;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_CYAN;

import honkytonky.misc.ClearScreen;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Merchant extends Actor {

    private final List<Item> itemsForSell = new ArrayList<>();
    private final String smalltalk;

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
        System.out.println(ANSI_CYAN + this.getName() + ": " + ANSI_BLUE + smalltalk);
    }

    public void printItemsForSell() {
        int i = 1;  //start counting at 1 for design purposes
        for(Item item : itemsForSell) {
            System.out.println(i + ") " + item + "\n");
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
