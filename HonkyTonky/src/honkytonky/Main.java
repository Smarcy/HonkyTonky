package honkytonky;

import honkytonky.console.*;
import honkytonky.objects.*;
import java.util.Scanner;

public class Main
{

    private static Main instance;

    private  final Scanner reader = new Scanner(System.in);
    private  final JavaConsole console = new JavaConsole();

    private static Player player = null;

    public static void main(String[] args)
    {
        instance = getInstance();
        instance.showIntro();
    }

    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }

    private void createPlayer()
    {
        console.clear();
        System.out.println("Enter your name: ");
        String name = reader.next();
        instance.player = new Player(name);
        instance.showIntro();
    }

    private void showIntro()
    {
        while(instance.player == null)
        {
            instance.createPlayer();
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