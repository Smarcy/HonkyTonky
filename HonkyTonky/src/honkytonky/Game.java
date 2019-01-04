package honkytonky;

import honkytonky.objects.Player;
import honkytonky.objects.Room;
import honkytonky.objects.Room.exits;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game
{
    private final Scanner scanner = new Scanner(System.in);

    private Player player = null;
    private Room room = null;
    private List<Room> rooms = null;
    private int option = 0;

    private final ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls").inheritIO();

    public static void main(String[] args) throws IOException, InterruptedException
    {
        Game game = new Game();
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

            option = scanner.nextInt();

            switch (option)
            {
                case 1:
                    if(player != null)
                    {
                        //startGame();
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
        room = new Room("Entrance", 1, 1, 0, 1);
    }
}