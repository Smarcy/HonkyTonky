package honkytonky.controller;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.misc.CharacterInfoPattern;
import honkytonky.objects.Item;
import honkytonky.objects.Player;
import honkytonky.objects.Potion;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PlayerDialogController {

    private final CharacterInfoPattern charInfo = new CharacterInfoPattern();
    private final Scanner scanner = new Scanner(System.in);

    public void printUsePotionDialog(Player player) {
        clearScreen();

        // --------------------------------------- REFACTORME START ---------------------------------------

        List<Item> playersPotions = new ArrayList<>();

        for(Item i : player.getInventory()) {
           if(i instanceof Potion && !(playersPotions.contains(i))) {
               playersPotions.add(i);
           }
        }

        System.out.println("You have got the following Potions:\n");
        int option = 0;

        for(Item i : playersPotions) {
            option++;
            System.out.println(option + ") " + i.getName() + " (" + player.countPotion(i) + "x) \n");
        }

        int choice = Integer.parseInt(scanner.nextLine());

        if(choice >= 0 && choice <= option) {
            player.usePotion((Potion) playersPotions.get(option - 1));
            scanner.nextLine();
        } else {
            printUsePotionDialog(player);
        }
        // --------------------------------------- REFACTORME END ---------------------------------------
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
