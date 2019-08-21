package honkytonky.controller;

import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.factories.ArmorFactory;
import honkytonky.factories.PotionFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Player;
import honkytonky.objects.Room;
import honkytonky.objects.Weapon;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PlayerController {

    Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Lets the user create a Player Object
     */
    public Player createPlayer(Scanner scanner, ArmorFactory armorFactory,
      WeaponFactory weaponFactory, PotionFactory potionFactory, BattleController battleController,
      List<Room> rooms) {
        clearScreen();

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        clearScreen();

        while (player == null) {
            clearScreen();

            System.out.println("Choose a weapon: ");
            System.out.println("1) One-Handed Sword");
            System.out.println("2) Two-Handed Sword");
            System.out.println("3) One-Handed Axe");
            System.out.println("4) Two-Handed Axe");

            try {
                int weapon = Integer.parseInt(scanner.nextLine());

                Weapon startWeapon = weaponFactory.getWeaponList().get(weapon - 1);

                switch (weapon) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        player = new Player(name, 20, startWeapon,
                          armorFactory.findArmorByName("Leather Armor"),
                          potionFactory.startPotion(), rooms
                          .get(0));
                        battleController.setPlayer(player);
                        return player;
                    default:
                        clearScreen();
                        System.out.println(weapon + " is not a valid option!\n");
                        break;
                }
            } catch (NumberFormatException | InputMismatchException | IndexOutOfBoundsException e) {
                e.printStackTrace();
                clearScreen();
            }
        }
        return null;
    }
}
