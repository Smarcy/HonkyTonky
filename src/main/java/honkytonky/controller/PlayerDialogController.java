package honkytonky.controller;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RED;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.misc.CharacterInfoPattern;
import honkytonky.objects.Player;
import honkytonky.objects.Potion;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PlayerDialogController {

    private final CharacterInfoPattern charInfo = new CharacterInfoPattern();
    private final Scanner scanner = new Scanner(System.in);
    private Player player;

    /**
     * set the attribute Player to corrrespond to the real object to save a bunch of parameters
     *
     * @param player the player object
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * print all Potions the player has in inventory/potionlist to choose from
     */
    public void printUsePotionDialog(PlayerController playerController) {
        clearScreen();

        System.out.println("You have got the following Potions:\n");

        Object[] tmpData = playerController.countAndPrintPlayerPotions(true);        // pretty hacky solution, maybe FIXME later ..
        int option = (int) tmpData[0];
        List tmpPotions = (List) tmpData[1];

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice >= 1 && choice <= option) {
                player.usePotion((Potion) tmpPotions.get(choice - 1));
                scanner.nextLine();
            } else {
                printUsePotionDialog(playerController);
            }
        } catch (NumberFormatException ignored) {
        }
    }

    /**
     * prints all items the player has in his inventory (sorted)
     */
    public void printInventoryDialog(PlayerController playerController) {
        clearScreen();

        if (player.getInventory().size() < 30) {
            System.out.println(ANSI_RED + "Weapons:" + ANSI_RESET);
            playerController.showInventory("weapons");
            System.out.println(ANSI_RED + "\nArmors:" + ANSI_RESET);
            playerController.showInventory("armors");
            System.out.println(ANSI_RED + "\nPotions:" + ANSI_RESET);
            playerController.showInventory("potions");
        } else {
            System.out.println("What kind of items would you like to see?\n");
            System.out.println("1) Weapons");
            System.out.println("2) Armors");
            System.out.println("3) Potions");

            try {
                int option = Integer.parseInt(scanner.nextLine());
                clearScreen();

                switch (option) {
                    case 1:
                        playerController.showInventory("weapons");
                        break;
                    case 2:
                        playerController.showInventory("armors");
                        break;
                    case 3:
                        playerController.showInventory("potions");
                        break;
                    default:
                        printInventoryDialog(playerController);
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                printInventoryDialog(playerController);
            }
        }
        scanner.nextLine();
    }

    /**
     * Console output of the current Room or Place
     */
    public void printCurrentLocation() {
        System.out.println(
          "You are currently in: " + ANSI_GREEN + player.getCurrentRoom()
            + ANSI_RESET);
    }

    /**
     * prints all relevant character informations (formatted)
     */
    public void printCharacterInfo() {
        clearScreen();
        charInfo.printCharacterInfo(player);
        scanner.nextLine();
        clearScreen();
    }
}
