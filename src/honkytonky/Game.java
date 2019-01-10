package honkytonky;

import honkytonky.factories.MonsterFactory;
import honkytonky.factories.RoomFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Monster;
import honkytonky.objects.Player;
import honkytonky.objects.Room;
import honkytonky.objects.Weapon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game
{

    private final Scanner scanner = new Scanner(System.in);
    private final ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls").inheritIO();

    private Player player = null;

    private final MonsterFactory monsterFactory = new MonsterFactory();
    private final RoomFactory roomFactory = new RoomFactory();
    private final WeaponFactory weaponFactory = new WeaponFactory();

    private final Room[][] roomList = roomFactory.createRooms();

    public static void main(String[] args) throws IOException, InterruptedException
    {
        Game game = new Game();
        game.showIntro();
    }

    /**
     * Shows the menu to choose an option
     */
    private void showIntro() throws IOException, InterruptedException
    {
        introLoop:
        while (true)
        {
            clearScreen();

            System.out.println("Welcome to HonkyTonky!");
            System.out.println("Please choose an option:\n");
            System.out.println("1) Start Game");
            System.out.println("2) Create New Player\n");
            System.out.print("\n> ");

            int option = scanner.nextInt();

            switch (option)
            {
                case 1:
                    if (player != null)
                    {
                        clearScreen();
                        startGame();
                        break introLoop;
                    } else
                    {
                        System.out.println("Please create a new player first!");
                        TimeUnit.SECONDS.sleep(3);
                    }
                    break;
                case 2:
                    createPlayer();
                    break;
            }
        }
    }

    /**
     * Lets the user create a Player Object
     */
    private void createPlayer() throws IOException, InterruptedException
    {
        clearScreen();

        System.out.println("Enter your name: ");
        String name = scanner.next();

        clearScreen();

        while (player == null)
        {
            System.out.println("Choose a weapon: ");
            System.out.println("1) One-Handed Sword");
            System.out.println("2) Two-Handed Sword");
            System.out.println("3) One-Handed Axe");
            System.out.println("4) Two-Handed Axe");

            int weapon = scanner.nextInt();

            Weapon startWeapon = weaponFactory.getWeaponList().get(weapon - 1);

            switch (weapon)
            {
                case 1:
                    player = new Player(name, 100, 0, 0, startWeapon); // One-Handed Sword
                    break;
                case 2:
                    player = new Player(name, 100, 0, 0, startWeapon); // Two-Handed Sword
                    break;
                case 3:
                    player = new Player(name, 100, 0, 0, startWeapon); // One-Handed Axe
                    break;
                case 4:
                    player = new Player(name, 100, 0, 0, startWeapon); // Two-Handed Axe
                    break;
                default:
                    clearScreen();
                    System.out.println(weapon + " is not a valid option!\n");
                    break;
            }
        }
    }

    /**
     * main game loop
     */
    private void startGame()
    {
        while (true)
        {
            System.out.println("Choose an option:\n");
            System.out.println("1) Move");
            System.out.println("2) Where am I?");
            System.out.println("3) Exit Game");
            System.out.print("\n> ");

            int option = scanner.nextInt();

            switch (option)
            {
                case 1:
                    clearScreen();
                    move();
                    if (roomHasMonster())
                    {
                        startBattle();
                    }
                    break;
                case 2:
                    clearScreen();
                    System.out.println(
                      "You are currently in: \u001B[32m" + roomList[player.getX()][player.getY()]
                        + "\u001B[0m.\n");  // set Console color to green and reset after
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

    /**
     * Lets the player move to a requested direction
     */
    private void move()
    {
        System.out.println("Where would you like to go?\n");
        System.out.println("\nType west, east, south or north\n");
        String direction = new String();

        try
        {
            direction = (scanner.next().trim().toLowerCase());

            if (isValidMove(direction))
            {
                switch (direction)
                {
                    case "north":
                        player.setLocation(player.getX(), player.getY() + 1);
                        break;
                    case "east":
                        player.setLocation(player.getX() + 1, player.getY());
                        break;
                    case "south":
                        player.setLocation(player.getX(), player.getY() - 1);
                        break;
                    case "west":
                        player.setLocation(player.getX() - 1, player.getY());
                        break;
                    default:
                        System.out.println(direction + " is not a valid command!");
                }
            } else
            {
                System.out
                  .println("There is no place connected in this direction! (" + direction + ")");
            }
        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("There is no place connected in this direction!");
        }
    }

    /**
     * Checks if a requested move is valid by comparing input string & roomList[x][y] != null & Room.has[direction]Exit
     *
     * @param direction where the player wants to move
     * @return if the requested move is valid
     */
    private boolean isValidMove(String direction)
    {
        if (direction.equals("north") && roomList[player.getX()][player.getY() + 1] != null && roomList[player.getX()][player.getY()].hasNorthExit())
        {
            return true;
        } else if (direction.equals("east") && roomList[player.getX() + 1][player.getY()] != null && roomList[player.getX()][player.getY()].hasEastExit())
        {
            return true;
        } else if (direction.equals("south") && roomList[player.getX()][player.getY() - 1] != null && roomList[player.getX()][player.getY()].hasSouthExit())
        {
            return true;
        } else
        {
            return direction.equals("west") && roomList[player.getX() - 1][player.getY()] != null && roomList[player.getX()][player.getY()].hasWestExit();
        }
    }

    /**
     * Clear the console completely
     */
    private void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Checks after every move if the current room contains a monster
     *
     * @return true if monster in room, false if no monster in room
     */
    private boolean roomHasMonster()
    {
        for (Monster monster : monsterFactory.getMonsterList())
        {
            if (monster.getX() == player.getX() && monster.getY() == player.getY())
            {
                return true;
            } else
            {
                return false;
            }
        }

        return false;
    }

    private void startBattle()
    {

    }
}