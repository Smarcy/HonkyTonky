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

    @Override
    public String toString() {
        return this.getName();
    }
}
