package honkytonky.controller;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_YELLOW;
import static honkytonky.misc.ClearScreen.clearScreen;


import honkytonky.misc.CharacterInfoPattern;
import honkytonky.objects.Merchant;
import honkytonky.objects.Player;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DialogController {

    private CharacterInfoPattern charInfo = new CharacterInfoPattern();
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
        scanner.nextLine();
    }

    public void printUsePotionDialog(Player player) {
        clearScreen();
        System.out.println("What kind of Potion would you like to use?\n");
        System.out.println("1) Health Potion");
        System.out.println("2) Defense Potion");

        try {
            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    clearScreen();
                    System.out.println("Small or Big Potion?\n");
                    System.out.println("1) Small Health Potion");
                    System.out.println("2) Big Health Potion");
                    switch (Integer.parseInt(scanner.nextLine())) {
                        case 1:
                            player.usePotion("Small Health Potion");
                            break;
                        case 2:
                            player.usePotion("Big Health Potion");
                            break;
                    }
                    break;
                case 2:
                    clearScreen();
                    player.usePotion("defense");
                    break;
            }
            scanner.nextLine();
        } catch (InputMismatchException | NumberFormatException e) {
            printUsePotionDialog(player);
        }
    }

    public void printInventoryDialog(Player player) {
        clearScreen();

        System.out.println("What kind of items would you like to see?\n");
        System.out.println("1) Weapons");
        System.out.println("2) Armors");
        System.out.println("3) Potions");

        try {
            int option = Integer.parseInt(scanner.nextLine());

            clearScreen();

            switch (option) {
                case 1:
                    player.showInventory("weapons");
                    break;
                case 2:
                    player.showInventory("armors");
                    break;
                case 3:
                    player.showInventory("potions");
                    break;
                default:
                    printInventoryDialog(player);
                    break;
            }
        } catch (InputMismatchException | NumberFormatException e) {
            printInventoryDialog(player);
        }
        scanner.nextLine();
    }

    /**
     * Console output of the current Room or Place
     */
    public void printCurrentLocation(Player player) {
        System.out.println(
          "You are currently in: " + ANSI_GREEN + player.getCurrentRoom()
            + ANSI_RESET);
    }

    public void printCharacterInfo(Player player) {
        clearScreen();
        charInfo.printCharacterInfo(player);
        scanner.nextLine();
        clearScreen();
    }
}
