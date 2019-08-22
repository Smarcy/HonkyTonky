package honkytonky.controller;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.objects.Merchant;
import honkytonky.objects.Player;
import java.util.Scanner;

public class MerchantDialogController {

    private final Scanner scanner = new Scanner(System.in);

    public void printMerchantDialog(Player player) {
        clearScreen();
        Merchant merchant = player.getCurrentRoom().getPresentMerchant();

        System.out.println("Hello, my name is " + ANSI_YELLOW + merchant + ANSI_RESET + "\n");
        System.out.println("1) Talk to " + merchant);
        System.out.println("2) Trade with " + merchant);
        System.out.println("3) Attack " + merchant);

        switch(Integer.parseInt(scanner.nextLine())) {
            case 1:
                merchant.printSmalltalk();
                break;
            case 2:
                merchant.printItemsForSell();
                break;
            case 3:
                break;
        }
        System.out.print("\n\nContinue..");
        scanner.nextLine();
    }



}
