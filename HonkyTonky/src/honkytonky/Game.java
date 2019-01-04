package honkytonky;

import honkytonky.Objects.Player;
import honkytonky.console.JavaConsole;
import java.util.Scanner;

public class Game
{
    private static final Scanner scanner = new Scanner(System.in);
    private static final JavaConsole console = new JavaConsole();

    private Player player = null;

    public static void main(String[] args)
    {
        Game game = new Game();
        game.showIntro(game);
    }


    private void showIntro(Game game)
    {
        console.clear();

        System.out.print(
          "Welcome to HonkyTonky!\n" +
          "Please choose an option:\n\n" +
          "1) Create Player\n" +
          "2) Start Game\n" +
          "3) Exit\n"
        );

        int option = scanner.nextInt();

        switch(option)
        {
            case 1:
                game.createPlayer();
        }
    }

    private void createPlayer()
    {
        System.out.println("Enter your name: ");
        player = new Player(scanner.next());
    }
}