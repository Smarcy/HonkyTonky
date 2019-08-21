package honkytonky.misc;

public class ClearScreen {

    /**
     * Clear the console completely
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
