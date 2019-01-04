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
    }

    private static void showIntro()
    {
        if(player == null)
        {
            createPlayer();
        }

        while (true)
        {
            console.clear();

            System.out.print(
              "Welcome to HonkyTonky!\n" +
                "Please choose an option:\n\n" +
                "1) Start Game\n" +
                "2) Exit\n"
            );

            int option = reader.nextInt();

            switch (option)
            {
                case 1:

                    break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }
}