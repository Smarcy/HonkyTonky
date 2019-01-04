package honkytonky;

import honkytonky.console.*;
import honkytonky.objects.*;
import java.util.Scanner;

public class Main
{

    private static final Scanner reader = new Scanner(System.in);
    private static JavaConsole console = new JavaConsole();

    private static Player player = null;

    public static void main(String[] args)
    {
        showIntro();
    }

    private static void createPlayer()
    {
        console.clear();
        System.out.println("Enter your name: ");
        String name = reader.next();
        player = new Player(name);
        showIntro();
    }

    private static void showIntro()
    {
        Scanner reader = new Scanner(System.in);

        while (true)
        {
            console.clear();

            System.out.print(
              "Welcome to HonkyTonky!\n" +
                "Please choose an option:\n\n" +
                "1) Create Player\n" +
                "2) Exit\n"
            );

            int option = reader.nextInt();

            switch (option)
            {
                case 1:
                    createPlayer();
                    break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }
}