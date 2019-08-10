package honkytonky.objects;

import java.util.ArrayList;
import java.util.List;

public class Merchant extends Actor {

    private List<Item> itemsForSell = new ArrayList<>();
    private String smalltalk;

    public Merchant(String name, int maxHP, int level, String smalltalk) {
        super(name, maxHP, level);
        this.smalltalk = smalltalk;
    }

    public void addItemToShop(Item item) {
        this.itemsForSell.add(item);
    }

    public void removeItemFromShop(int id) {
        this.itemsForSell.remove(itemsForSell.get(id));
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
}
