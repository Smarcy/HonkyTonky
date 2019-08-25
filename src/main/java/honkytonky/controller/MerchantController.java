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

    private final Scanner scanner = new Scanner(System.in);
    private Player player;
    private Merchant merchant;

    void printMerchantDialog(Player player) {
        this.player = player;
        Boolean run = true;

        while(run) {
            clearScreen();
            this.merchant = player.getCurrentRoom().getPresentMerchant();

            System.out.println("Hello, my name is " + ANSI_YELLOW + merchant + ANSI_RESET + "\n");
            System.out.println("1) Talk to " + ANSI_YELLOW + merchant + ANSI_RESET);
            System.out.println("2) Trade with " + ANSI_YELLOW + merchant + ANSI_RESET);
            System.out.println("3) Attack " + ANSI_YELLOW + merchant + ANSI_RESET);
            System.out.println("4) Leave " + ANSI_YELLOW +  merchant + ANSI_RESET + " alone");

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    merchant.printSmalltalk();
                    System.out.print("\n\nContinue..");
                    scanner.nextLine();
                    break;
                case 2:
                    int choice = printItemsForSell(merchant);
                    if(choice != -1) {
                        tradeWithMerchant(choice);
                    }
                    break;
                case 3:
                    break;
                case 4:
                    run = false;
                    break;
            }
        }
    }

    /**
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
        } else if (choice < i && choice > 0) {     // choice does match an item
            return choice;
        } else {    // choice does not match with options -> print Dialog again
            this.printItemsForSell(merchant);
        }
        return -1;
    }

    /**
     * Add Item to Player Inventory & remove Item from Merchant Inventory
     * @param choice ID of Item in Merchants inventory (+1)
     */
    private void tradeWithMerchant(int choice) {
        Item itemToBuy = merchant.getItemsForSell().get(choice - 1);

        if(player.getGold() >= itemToBuy.getValue()) {
            player.getInventory().add(itemToBuy);
            merchant.removeItemFromShop(itemToBuy);
            player.giveGold(-itemToBuy.getValue());
            System.out.println("You succesfully purchased " + ANSI_PURPLE + itemToBuy.getName() + ANSI_RESET + "!");

            if(itemToBuy instanceof Potion) {
                if(player.getPlayersPotions().containsKey(itemToBuy.getName())) {
                    player.getPlayersPotions().put(itemToBuy.getName(), player.getPlayersPotions().get(itemToBuy.getName()) + 1);
                }
            }
        } else {
            System.out.println("You don't have enough gold to purchase " + ANSI_PURPLE + itemToBuy.getName() + ANSI_RESET + "!");
        }
        scanner.nextLine();
    }
}
