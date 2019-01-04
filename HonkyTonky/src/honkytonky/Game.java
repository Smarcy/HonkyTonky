package honkytonky;

import honkytonky.console.JavaConsole;

public class Game
{

    private static final JavaConsole console = new JavaConsole();

    public static void main(String[] args)
    {
        Game game = new Game();
        game.showIntro();
    }


    private void showIntro()
    {
        System.out.println("Hello, World!");
    }
}