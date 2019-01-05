package honkytonky;

import honkytonky.objects.Player;
import honkytonky.objects.Room;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game
{
    private final Scanner scanner   = new Scanner(System.in);
    private Player player           = null;
    private final ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls").inheritIO();

    private enum exits
    {
        north,
        east,
        south,
        west
    }

    public static void main(String[] args) throws IOException, InterruptedException
    {
        Game game = new Game();
        game.createRooms();
        game.showIntro();
    }


    /**
     * Shows the menu to choose an option
     *
     * @throws IOException
     * @throws InterruptedException
     */
    private void showIntro() throws IOException, InterruptedException
    {
        while(true)
        {
            pb.start().waitFor();   // clear screen

            System.out.print(
              "Welcome to HonkyTonky!\n" +
                "Please choose an option:\n\n" +
                "1) Start Game\n" +
                "2) Create New Player\n" +
                "3) Create Room (DEV)\n"
            );

            int option = scanner.nextInt();

            switch (option)
            {
                case 1:
                    if(player != null)
                    {
                        startGame();
                    } else {
                        System.out.println("Please create a new player first!");
                        TimeUnit.SECONDS.sleep(3);
                    }
                    break;
                case 2:
                    createPlayer();
                    break;
                case 3:
                    createRooms();
                    break;
            }
        }
    }

    /**
     * Lets the user create a Player Object
     * @throws IOException
     * @throws InterruptedException
     */
    private void createPlayer() throws IOException, InterruptedException
    {
        pb.start().waitFor();

        System.out.println("Enter your name: ");
        player = new Player(scanner.next(), 100);
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
        System.out.println("Where would you like to go?");
        System.out.println("\nType west, east, south or north");

        String option = (scanner.next().trim().toLowerCase());

        switch(option)
        {
            case "north":
                move(exits.north);
                break;
            case "east":
                //move(east);
                break;
            case "south":
                //move(south);
                break;
            case "west":
                //move(west);
                break;
        }
    }

    private void move(exits direction)
    {

    }
}