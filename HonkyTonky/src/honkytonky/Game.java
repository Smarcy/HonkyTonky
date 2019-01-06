package honkytonky;

import honkytonky.objects.Player;
import honkytonky.objects.Room;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game
{

    private final Scanner scanner = new Scanner(System.in);
    private final ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls").inheritIO();
    private Player player = null;

    public static void main(String[] args) throws IOException, InterruptedException
    {
        Game game = new Game();
        game.createRooms();
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
            pb.start().waitFor();   // clear screen

            System.out.print(
              "Welcome to HonkyTonky!\n" +
                "Please choose an option:\n\n" +
                "1) Start Game\n" +
                "2) Create New Player\n"
            );

            int option = scanner.nextInt();

            switch (option)
            {
                case 1:
                    if (player != null)
                    {
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
        pb.start().waitFor();

        System.out.println("Enter your name: ");
        player = new Player(scanner.next(), 100, 0, 0);
    }

    private void createRooms()
    {
        new Room("Entrance", 0, 0, true, true, false, false);
        new Room("Hall", 0, 1, false, false, true, true);
        new Room("Bedroom", 0, 2, false, true, false, false);
        new Room("Kitchen", 1, 0, false, false, true, true);
    }

    private void startGame()
    {
        System.out.println("Choose an option:\n\n");

        System.out.println("1) Move");
        System.out.println("2) Where am I?");

        String option = scanner.next().trim().toLowerCase();

        switch (option)
        {
            case 1:
                move();
                break;
            case 2:
                System.out.println(""
                  + "x-Coordinate: " + player.getX()
                  + "y-Coordinate: " + player.getY()
                  + "Current room: " + );
        }

    }

    private void move()
    {
            System.out.println("Where would you like to go?\n");
            System.out.println("\nType west, east, south or north\n");

            String direction = (scanner.next().trim().toLowerCase());

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
    }
}