package honkytonky.controller;

import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.factories.ArmorFactory;
import honkytonky.factories.MapLayout;
import honkytonky.factories.PotionFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Door;
import honkytonky.objects.Player;
import honkytonky.objects.Room;
import honkytonky.objects.Weapon;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PlayerController {

    private Player player;

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Lets the user create a Player Object
     */
    public Player createPlayer( ArmorFactory armorFactory,
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

    /**
     * Lets the player move to a requested direction
     */
    public void move(Scanner scanner, MapLayout mapLayout) {
        clearScreen();

        player.getCurrentRoom().listDoorOptions();

        try {
            int option = Integer.parseInt(scanner.nextLine());

            Door targetDoor = player.getCurrentRoom().getDoors().get(option - 1);
            Room targetRoom = mapLayout.getRoomByName(targetDoor.getTargetRoom().getName());

            player.setCurrentRoom(targetRoom);
        } catch (IndexOutOfBoundsException e) {
            move(scanner, mapLayout);
        }
        clearScreen();
    }
}
