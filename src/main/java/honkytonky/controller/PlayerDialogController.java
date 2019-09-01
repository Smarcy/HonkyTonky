package honkytonky.controller;

import static honkytonky.misc.ANSI_Color_Codes.ANSI_CYAN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_GREEN;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RED;
import static honkytonky.misc.ANSI_Color_Codes.ANSI_RESET;
import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.factories.CreateWorld;
import honkytonky.misc.CharacterInfoPattern;
import honkytonky.objects.Armor;
import honkytonky.objects.Player;
import honkytonky.objects.Potion;
import honkytonky.objects.Weapon;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerDialogController {

    /**
     * Instance of CharacterInfoPattern so the Player can print all his infos formatted
     */
    private final CharacterInfoPattern charInfo = new CharacterInfoPattern();
    /**
     * Scanner for reading Player Inputs
     */
    private final Scanner scanner = new Scanner(System.in);
    /**
     * The Player Object
     */
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
    public void printUsePotionDialog () {
        clearScreen();

        System.out.println("You have got the following Potions:\n");

        Object[] tmpData = CreateWorld.getPlayerController().countAndPrintPlayerPotions(true);        // pretty hacky solution, maybe FIXME later ..
        int option = (int) tmpData[0];
        List tmpPotions = (List) tmpData[1];

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice >= 1 && choice <= option) {
                player.usePotion((Potion) tmpPotions.get(choice - 1));
                scanner.nextLine();
            } else {
                printUsePotionDialog();
            }
        } catch (NumberFormatException ignored) {
        }
    }

    /**
     * prints all items the player has in his inventory (sorted)
     */
    public void printInventoryDialog() {
        PlayerController playerController = CreateWorld.getPlayerController();
        clearScreen();
        System.out.println(ANSI_RED + "Weapons:" + ANSI_RESET);
        playerController.showInventory("weapons");
        System.out.println(ANSI_CYAN + "w) Equip another Weapon" + ANSI_RESET);

        System.out.println(ANSI_RED + "\nArmors:" + ANSI_RESET);
        playerController.showInventory("armors");
        System.out.println(ANSI_CYAN + "a) Equip another Armor" + ANSI_RESET);

        System.out.println(ANSI_RED + "\nPotions:" + ANSI_RESET);
        playerController.showInventory("potions");

        switch (scanner.nextLine()) {
            case "w":
                equipOtherWeapon();
                break;
            case "a":
                equipOtherArmor();
                break;
        }
    }

    /**
     * shows all Armors in Players inventory and lets him choose one to equip
     */
    private void equipOtherArmor() {
        clearScreen();
        int option = 1;
        List<Armor> tmpArmorList = new ArrayList<>();       // needed for counting correct later

        System.out.println("Which Armor would you like to equip?\n");

        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i) instanceof Armor) {
                tmpArmorList.add((Armor) player.getInventory().get(i));
                System.out.println(option + ") " + player.getInventory().get(i));
                option++;
            }
        }
        option = Integer.parseInt(scanner.nextLine());
        player.setArmor(tmpArmorList.get(option - 1));
    }

    /**
     * lets the Player choose from all his weapons and choose one to equip
     */
    private void equipOtherWeapon() {
        clearScreen();
        int option = 1;
        List<Weapon> tmpWeaponList = new ArrayList<>();     // needed for counting correct later

        System.out.println("Which Weapon would you like to equip?\n");

        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i) instanceof Weapon) {
                tmpWeaponList.add((Weapon) player.getInventory().get(i));
                System.out.println(option + ") " + player.getInventory().get(i));
                option++;
            }
        }
        option = Integer.parseInt(scanner.nextLine());
        player.setWeapon(tmpWeaponList.get(option - 1));
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
