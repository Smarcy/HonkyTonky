package honkytonky.controller;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RED;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.factories.PotionFactory;
import honkytonky.misc.CharacterInfoPattern;
import honkytonky.objects.Player;
import honkytonky.objects.Potion;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PlayerDialogController {

    private final CharacterInfoPattern charInfo = new CharacterInfoPattern();
    private final Scanner scanner = new Scanner(System.in);
    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * print all Potions the player has in inventory/potionlist to choose from
     */
    public void printUsePotionDialog() {
        clearScreen();

        Map<Potion, Integer> playersPotions = player.getPlayersPotions();
        List<Potion> tmpPotions = new ArrayList<>();    // needed to temp save actual potions, not names
        int option = 0;

        System.out.println("You have got the following Potions:\n");

        for (Potion potion : playersPotions.keySet()) {     // found no elegant solution without temp saving iterated keys to get entry X
            if (playersPotions.get(potion) > 0) {
                option++;
                System.out
                  .println(
                    option + ") " + potion.getName() + " (" + playersPotions.get(potion) + "x) \n");
                tmpPotions.add(new PotionFactory().getPotionByName(potion.getName()));
            }
        }
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice >= 0 && choice <= option) {
            player.usePotion(tmpPotions.get(option - 1));
            scanner.nextLine();
        } else {
            printUsePotionDialog();
        }
    }

    /**
     * prints all items the player has in his inventory (sorted)
     */
    public void printInventoryDialog() {
        clearScreen();

        if (player.getInventory().size() < 30) {
            System.out.println(ANSI_RED + "Weapons:" + ANSI_RESET);
            player.showInventory("weapons");
            System.out.println(ANSI_RED + "\nArmors:" + ANSI_RESET);
            player.showInventory("armors");
            System.out.println(ANSI_RED + "\nPotions:" + ANSI_RESET);
            player.showInventory("potions");
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
                        player.showInventory("weapons");
                        break;
                    case 2:
                        player.showInventory("armors");
                        break;
                    case 3:
                        player.showInventory("potions");
                        break;
                    default:
                        printInventoryDialog();
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e) {
                printInventoryDialog();
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
