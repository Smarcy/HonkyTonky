package honkytonky;

import honkytonky.factories.MonsterFactory;
import honkytonky.factories.RoomFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Actor;
import honkytonky.objects.Monster;
import honkytonky.objects.Player;
import honkytonky.objects.Room;
import honkytonky.objects.Weapon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game
{

    private final Scanner scanner = new Scanner(System.in);
    private final ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls").inheritIO();

    private Player player = null;
    private int monsterID;

    private final MonsterFactory monsterFactory = new MonsterFactory();
    private final RoomFactory roomFactory = new RoomFactory();
    private final WeaponFactory weaponFactory = new WeaponFactory();

    private final Room[][] roomList = roomFactory.createRooms();
    private final List<Actor> actors = monsterFactory.getMonsterList();

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws IOException, InterruptedException
    {
        Game game = new Game();
        game.showIntro();
    }

    /**
     * Shows the menu to choose an option
     */
    private void showIntro() throws InterruptedException, NumberFormatException
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

            try
            {
                int option = Integer.parseInt(scanner.nextLine());

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
                    default:
                        break;
                }
            } catch (NumberFormatException e)
            {
                showIntro();
            }
        }

    }

    /**
     * Lets the user create a Player Object
     */
    private void createPlayer()
      throws NumberFormatException, InputMismatchException, IndexOutOfBoundsException
    {
        clearScreen();

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        clearScreen();

        while (player == null)
        {
            clearScreen();

            System.out.println("Choose a weapon: ");
            System.out.println("1) One-Handed Sword");
            System.out.println("2) Two-Handed Sword");
            System.out.println("3) One-Handed Axe");
            System.out.println("4) Two-Handed Axe");

            try
            {
                int weapon = Integer.parseInt(scanner.nextLine());

                Weapon startWeapon = weaponFactory.getWeaponList().get(weapon - 1);

                switch (weapon)
                {
                    case 1:
                        player = new Player(name, 20, 0, 0, startWeapon); // One-Handed Sword
                        break;
                    case 2:
                        player = new Player(name, 20, 0, 0, startWeapon); // Two-Handed Sword
                        break;
                    case 3:
                        player = new Player(name, 20, 0, 0, startWeapon); // One-Handed Axe
                        break;
                    case 4:
                        player = new Player(name, 20, 0, 0, startWeapon); // Two-Handed Axe
                        break;
                    default:
                        clearScreen();
                        System.out.println(weapon + " is not a valid option!\n");
                        break;
                }
            } catch (NumberFormatException | InputMismatchException | IndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * main game loop
     */
    private void startGame() throws InputMismatchException, NumberFormatException
    {
        while (true)
        {
            whereAmI();

            System.out.println("Choose an option:\n");
            System.out.println("1) Move");
            System.out.println("2) Character Info");
            System.out.println("3) Exit Game");
            System.out.print("\n> ");

            try
            {
                int option = Integer.parseInt(scanner.nextLine());

                switch (option)
                {
                    case 1:
                        clearScreen();
                        move();
                        clearScreen();
                        if (roomHasMonster())
                        {
                            startBattle();
                        }
                        break;
                    case 2:
                        showCharacterInfo();
                        clearScreen();
                        break;
                    case 3:
                        System.exit(0);
                }
            } catch (InputMismatchException | NumberFormatException e)
            {
                clearScreen();
                startGame();
            }

        }
    }

    /**
     * Console output of the current Room or Place
     */
    private void whereAmI()
    {
        System.out.println(
          "You are currently in: " + ANSI_GREEN + roomList[player.getX()][player.getY()]
            + ANSI_RESET);  // set Console color to green and reset after
    }

    private void showCharacterInfo()
    {
        clearScreen();

        System.out.println(
          "Your Name Is: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 1, 30) + player
            + ANSI_RESET);
        System.out.println(
          "Your Current Weapon Is: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 2, 30)
            + player.getWeapon() + ANSI_RESET);
        System.out.println(
          "You Are Currently In: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 3, 30)
            + roomList[player.getX()][player.getY()] + ANSI_RESET);
        System.out.println(
          "Your Current HP: " + ANSI_YELLOW + String.format("%c[%d;%df", 0x1B, 4, 30)
            + player.getHp() + " / " + player.getMaxHP() + ANSI_RESET);

        try
        {
            System.in.read();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Lets the player move to a requested direction
     */
    private void move()
    {
        System.out.println("Where would you like to go?\n");
        System.out.println("\nType west, east, south or north\n");
        String direction;

        try
        {
            direction = (scanner.nextLine().trim().toLowerCase());

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
                clearScreen();
                System.out
                  .println(ANSI_RED + "There is no place connected in this direction! (" + direction
                    + ")\n" + ANSI_RESET);
                startGame();
            }
        } catch (ArrayIndexOutOfBoundsException e)
        {
            clearScreen();
            System.out.println(
              ANSI_RED + "There is no place connected in this direction!\n" + ANSI_RESET);
            startGame();
        }
    }

    /**
     * Checks if a requested move is valid by comparing input string & roomList[x][y] != null &
     * Room.has[direction]Exit
     *
     * @param direction where the player wants to move
     * @return if the requested move is valid
     */
    private boolean isValidMove(String direction)
    {
        if (direction.equals("north") && roomList[player.getX()][player.getY() + 1] != null
          && roomList[player.getX()][player.getY()].hasNorthExit())
        {
            return true;
        } else if (direction.equals("east") && roomList[player.getX() + 1][player.getY()] != null
          && roomList[player.getX()][player.getY()].hasEastExit())
        {
            return true;
        } else if (direction.equals("south") && roomList[player.getX()][player.getY() - 1] != null
          && roomList[player.getX()][player.getY()].hasSouthExit())
        {
            return true;
        } else
        {
            return direction.equals("west") && roomList[player.getX() - 1][player.getY()] != null
              && roomList[player.getX()][player.getY()].hasWestExit();
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
        for (Actor actor : actors)
        {
            if (actor.getX() == player.getX() && actor.getY() == player.getY())
            {
                monsterID = actor.getID();
                return true;
            }
        }
        return false;
    }

    /**
     * This method gets called when the player enters a room or place that contains an enemy
     */
    private void startBattle() throws InputMismatchException, NumberFormatException
    {
        clearScreen();

        boolean monsterAlive = true;
        Actor monster = monsterFactory.getMonsterList().get(monsterID);

        while (monsterAlive)
        {
            System.out.println(ANSI_RED + "You encountered a " + monster + "!" + ANSI_RESET);

            System.out.println("What do you want to do this round?\n");
            System.out.println(
              "1) Attack with your " + ANSI_YELLOW + player.getWeapon() + ANSI_RESET);
            System.out.println("2) Defensive Mode");
            System.out.println("3) Flee from " + ANSI_YELLOW + monster + ANSI_RESET);
            System.out.print("\n> ");

            try
            {
                switch (Integer.parseInt(scanner.nextLine()))
                {
                    case 1:
                        playerAttack();
                        break;
                    case 2:
                        playerDefend();
                        break;
                    case 3:
                        playerFlee();
                        break;
                    default:
                        break;
                }
            } catch (InputMismatchException | NumberFormatException e)
            {
                clearScreen();
                continue;
            }
        }
    }

    private void playerAttack()
    {

    }

    private void playerDefend()
    {

    }

    private void playerFlee()
    {

    }
}