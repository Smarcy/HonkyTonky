package honkytonky.controller;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_PURPLE;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.misc.ClearScreen;
import honkytonky.objects.Item;
import honkytonky.objects.Merchant;
import honkytonky.objects.Player;
import honkytonky.objects.Potion;
import java.util.Scanner;

public class MerchantController {

    /**
     * Scanner for reading Player Inputs
     */
    private final Scanner scanner = new Scanner(System.in);
    /**
     * The Player Object
     */
    private Player player;
    /**
     * The Merchant the Player encountered
     */
    private Merchant merchant;

    /**
     * If the player encounters a merchant, print this dialog
     *
     * @param player the player object
     * @param battleController instance of BattleController (needed if the player chooses to fight the merchant)
     */
    void printMerchantDialog(Player player, BattleController battleController) {
        this.player = player;
        boolean run = true;

        while (run) {
            clearScreen();
            this.merchant = player.getCurrentRoom().getPresentMerchant();

            System.out.println("Hello, my name is " + ANSI_YELLOW + merchant + ANSI_RESET + "\n");
            System.out.println("1) Talk to " + ANSI_YELLOW + merchant + ANSI_RESET);
            System.out.println("2) Trade with " + ANSI_YELLOW + merchant + ANSI_RESET);
            System.out.println("3) Attack " + ANSI_YELLOW + merchant + ANSI_RESET);
            System.out.println("4) Leave " + ANSI_YELLOW + merchant + ANSI_RESET + " alone");

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    merchant.printSmalltalk();
                    System.out.print("\n\nContinue..");
                    scanner.nextLine();
                    break;
                case 2:
                    int choice = printItemsForSell(merchant);
                    if (choice != -1) {
                        tradeWithMerchant(choice);
                        scanner.nextLine();
                    }
                    break;
                case 3:
                    battleController.startBattle(merchant);
                    run = false;
                    break;
                case 4:
                    run = false;
                    break;
            }
        }
    }

    /**
     * List all items a merchant has to sell
     *
     * @return ID of item player wants to buy
     */
    private int printItemsForSell(Merchant merchant) {
        ClearScreen.clearScreen();

        System.out
          .println(ANSI_YELLOW + merchant.getName() + ANSI_RESET + " is selling the following items:\n");

        int i = 1;  //start counting at 1 for design purposes
        for (Item item : merchant.getItemsForSell()) {
            System.out.println(i + ") " + item + " (" + item.getValue() + " Gold)");
            i++;
        }

        System.out.println(i + ") Don't trade \n");
        final int choice = Integer.parseInt(scanner.nextLine());

        // Can't use variable in switch-Statement, therefore a specialcase for choosing item
        if (choice == i) {   // choose "Dont trade"
            return -1;
        } else if (choice < i && choice > 0) {     // choice does match an option
            return choice;
        } else {    // choice does not match any option -> print Dialog again
            this.printItemsForSell(merchant);
        }
        return -1;
    }

    /**
     * Add Item to Player Inventory and Players Potionlist & remove Item from Merchant Inventory
     *
     * @param choice ID of Item in Merchants inventory (+1)
     */
    void tradeWithMerchant(int choice) {
        Item itemToBuy = merchant.getItemsForSell().get(choice - 1);

        if (player.getGold() >= itemToBuy.getValue()) {     // does Player have enough gold?
            player.getInventory().add(itemToBuy);           // add Item to inventory
            merchant.removeItemFromShop(itemToBuy);         // remove from Merchants shop
            player.giveGold(-itemToBuy.getValue());         // "giveGold" with a negative amount = reduce players gold
            System.out.println("You succesfully purchased " + ANSI_PURPLE + itemToBuy.getName() + ANSI_RESET + "!");

            if (itemToBuy instanceof Potion) {   // If player is buying a potion, additionally add it to players potionmap
                if (player.getPlayersPotions().containsKey(itemToBuy)) {
                    player.getPlayersPotions().put((Potion) itemToBuy, player.getPlayersPotions().get(itemToBuy) + 1);
                }
            }
        } else {
            System.out.println("You don't have enough gold to purchase " + ANSI_PURPLE + itemToBuy.getName() + ANSI_RESET + "!");
        }
    }
}
