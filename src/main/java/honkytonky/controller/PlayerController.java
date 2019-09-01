package honkytonky.controller;

import static honkytonky.misc.ClearScreen.clearScreen;

import honkytonky.factories.ArmorFactory;
import honkytonky.factories.CreateWorld;
import honkytonky.factories.PotionFactory;
import honkytonky.factories.RoomFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Armor;
import honkytonky.objects.Door;
import honkytonky.objects.Item;
import honkytonky.objects.Player;
import honkytonky.objects.Potion;
import honkytonky.objects.Room;
import honkytonky.objects.Weapon;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PlayerController {

    /**
     * Scanner for reading Player Inputs
     */
    private final Scanner scanner = new Scanner(System.in);
    /**
     * The Player Object
     */
    private Player player;

    /**
     * Lets the user create a Player Object
     */
    public Player createPlayer() {
        ArmorFactory armorFactory = CreateWorld.getArmorFactory();
        WeaponFactory weaponFactory = CreateWorld.getWeaponFactory();
        BattleController battleController = CreateWorld.getBattleController();
        List<Room> rooms = CreateWorld.getRoomFactory().getRooms();
        clearScreen();

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        clearScreen();

        // Necessary if "Create A New Player" gets called a second time -> prevent NPE
        player = null;

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

                if (weapon <= 4) {
                    player = new Player(name, 20, startWeapon,
                      armorFactory.getArmorByName("Leather Armor"), rooms.get(0));
                    battleController.setPlayer(player);
                    return player;
                }
            } catch (NumberFormatException | InputMismatchException | IndexOutOfBoundsException e) {
                e.printStackTrace();
                clearScreen();
            }
        }
        throw new IllegalArgumentException("Something went wrong while creating Player..");
    }

    /**
     * Lets the player move to a requested direction
     */
    public void move(Scanner scanner) {    // scanner necessary because of Mockito Tests
        RoomFactory roomFactory = CreateWorld.getRoomFactory();
        clearScreen();

        player.getCurrentRoom().listDoorOptions();

        try {
            int option = Integer.parseInt(scanner.nextLine());

            Door targetDoor = player.getCurrentRoom().getDoors().get(option - 1);
            Room targetRoom = roomFactory.getRoomByName(targetDoor.getTargetRoom().getName());

            player.setCurrentRoom(targetRoom);
        } catch (IndexOutOfBoundsException e) {
            move(scanner);
        }
        clearScreen();
    }

    /**
     * prints items in inventory of a specific type in a formatted way
     *
     * @param option what type print
     */
    void showInventory(String option) {
        switch (option) {
            case "weapons":
                for (Item item : player.getInventory()) {
                    if (item instanceof Weapon) {
                        if (item.equals(player.getWeapon())) {
                            System.out.println(item + " (equipped)");
                        } else {
                            System.out.println(item);
                        }
                    }
                }
                break;
            case "armors":
                for (Item item : player.getInventory()) {
                    if (item instanceof Armor) {
                        if (item.equals(player.getArmor())) {
                            System.out.println(item + " (equipped)");
                        } else {
                            System.out.println(item);
                        }
                    }
                }
                break;
            case "potions":
                countAndPrintPlayerPotions(false);
                break;
        }
    }

    int listSellableItems() {
        clearScreen();
        int option = 1;
        System.out.println("Which Item would you like to sell? \n");
        for (Item i : player.getInventory()) {
            if ((!i.equals(player.getWeapon())) && (!i.equals(player.getArmor()))) {    // Don't display equipped Items
                System.out.println(option + ") " + i.getName() + " (" + i.getValue() + " Gold)");
            }
        }
        System.out.println((option + 1) + ") Sell None");

        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == option + 1) {                        // chose to Sell None
            return -1;
        } else if (choice > 0 && choice <= option) {    // choice matches an Item
            return choice;
        } else {                                        // no match -> show again
            listSellableItems();
        }
        return choice;
    }

    /**
     * Count how many Potions player has of each
     *
     * @param showOptionNumbers true if "choosing numbers" should be displayed. (use potion = true, inventory = false)
     * @return Array of {option for certain potion && all unique potions of player
     */
    Object[] countAndPrintPlayerPotions(boolean showOptionNumbers) {
        Map<Potion, Integer> playersPotions = player.getPlayersPotions();
        List<Potion> tmpPotions = new ArrayList<>();    // needed to temp save actual potions, not names
        int option = 0;

        for (Potion potion : playersPotions.keySet()) {     // found no elegant solution without temp saving iterated keys to get entry X
            if (playersPotions.get(potion) > 0) {
                option++;

                if (showOptionNumbers) {
                    System.out.println(option + ") " + potion.getName() + " (" + playersPotions.get(potion) + "x)"); // display option numbers
                } else {
                    System.out.println(potion.getName() + " (" + playersPotions.get(potion) + "x)");                 // without option numbers
                }
                tmpPotions.add(new PotionFactory().getPotionByName(potion.getName()));
            }
        }
        return new Object[]{option, tmpPotions};
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
