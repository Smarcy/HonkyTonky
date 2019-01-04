package honkytonky;

import honkytonky.objects.Player;
import java.io.IOException;
import java.util.Scanner;

public class Game
{
    private final Scanner scanner = new Scanner(System.in);
    //private final JavaConsole console = new JavaConsole();

    private Player player = null;
    private int option = 0;

    public static void main(String[] args) throws IOException, InterruptedException
    {
        Game game = new Game();
        game.showIntro();
    }


    private void showIntro() throws IOException, InterruptedException
    {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        System.out.print(
          "Welcome to HonkyTonky!\n" +
          "Please choose an option:\n\n" +
          "1) Create Player\n" +
          "2) Start Game\n" +
          "3) Exit\n"
        );

        //Integer option = Integer.parseInt(scanner.nextLine());
        //int option = Integer.parseInt(scanner.nextLine());
        this.option = this.scanner.nextInt();

        System.out.println(option);

        switch(option)
        {
            case 1:
                this.createPlayer();
        }
    }

    private void createPlayer() throws IOException, InterruptedException
    {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        System.out.println("Enter your name: ");
        player = new Player(scanner.next());
    }
}