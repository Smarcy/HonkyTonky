package honkytonky;

import honkytonky.Objects.Player;
import honkytonky.console.JavaConsole;
import java.util.Scanner;

public class Game
{
    private static final Scanner scanner = new Scanner(System.in);
    private final JavaConsole console = new JavaConsole();

    private Player player = null;

    public static void main(String[] args)
    {
        Game game = new Game();
        game.showIntro();
    }


    private void showIntro()
    {
        console.clear();

        System.out.print(
          "Welcome to HonkyTonky!\n" +
          "Please choose an option:\n\n" +
          "1) Create Player\n" +
          "2) Start Game\n" +
          "3) Exit\n"
        );

        //Integer option = Integer.parseInt(scanner.nextLine());
        //int option = Integer.parseInt(scanner.nextLine());
        int option = scanner.nextInt();

        System.out.println(option);

        switch(option)
        {
            case 1:
                this.createPlayer();
        }
    }

    private void createPlayer()
    {
        System.out.println("Enter your name: ");
        player = new Player(scanner.next());
    }
}