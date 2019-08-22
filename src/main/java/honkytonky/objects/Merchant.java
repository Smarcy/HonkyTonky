package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(smalltalk);
    }

    public void printItemsForSell() {
        int i = 1;  //start counting at 1 for design purposes
        for(Item item : itemsForSell) {
            System.out.println(i + ") " + item + "\n");
        }
    }

    public List<Item> getItemsForSell() {
        return itemsForSell;
    }

    public String getSmalltalk() {
        return smalltalk;
    }
}
