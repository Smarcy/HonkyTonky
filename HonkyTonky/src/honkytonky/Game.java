package honkytonky;

import honkytonky.objects.Player;
import java.io.IOException;
import java.util.Scanner;

public class Game
{
    private final Scanner scanner = new Scanner(System.in);

    private Player player = null;
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
        while(player == null)
        {
            createPlayer();
        }

        pb.start().waitFor();   // clear screen

        System.out.print(
          "Welcome to HonkyTonky!\n" +
          "Please choose an option:\n\n" +
          "1) Start Game\n" +
          "2) Create New Player\n" +
          "3) Exit\n"
        );

        option = scanner.nextInt();

        switch(option)
        {
            case 1:

            case 2:
                createPlayer();
                break;
            case 3:
                System.exit(0);
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
        player = new Player(scanner.next());
    }
}