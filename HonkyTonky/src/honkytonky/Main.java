package honkytonky;

import honkytonky.objects.*;
import java.util.Scanner;

public class Main
{

    private static Player player;

    public static void main(String[] args)
    {
        createPlayer();
        System.out.println(player.getName());

    }

    private static void createPlayer()
    {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = reader.next();
        player = new Player(name);
        reader.close();
    }

}