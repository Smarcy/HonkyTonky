package honkytonky.controller;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.objects.Item;
import honkytonky.objects.Merchant;
import honkytonky.objects.Player;
import java.util.Scanner;

public class MerchantDialogController {

    private final Scanner scanner = new Scanner(System.in);
    Player player;
    Merchant merchant;

    void printMerchantDialog(Player player) {
        this.player = player;
        Boolean run = true;

        while(run) {
            clearScreen();
            this.merchant = player.getCurrentRoom().getPresentMerchant();

            System.out.println("Hello, my name is " + ANSI_YELLOW + merchant + ANSI_RESET + "\n");
            System.out.println("1) Talk to " + merchant);
            System.out.println("2) Trade with " + merchant);
            System.out.println("3) Attack " + merchant);
            System.out.println("4) Leave " + merchant + " alone");

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    merchant.printSmalltalk();
                    System.out.print("\n\nContinue..");
                    scanner.nextLine();
                    break;
                case 2:
                    int choice = merchant.printItemsForSell();
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

    private void tradeWithMerchant(int choice) {
        Item itemToBuy = merchant.getItemsForSell().get(choice - 1);
        player.getInventory().add(itemToBuy);
        merchant.removeItemFromShop(itemToBuy);
    }
}
