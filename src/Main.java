import java.io.IOException;
import java.util.Scanner;


public class Main {

    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static Scanner in = new Scanner(System.in);
    private static int input;

    public static void main(String... args)
    {
        showIntro();
    }

    private static void showIntro()
    {
        System.out.flush();
        System.out.println(">>> Willkommen im Adressbuch!\n" +
                ">>> Bitte wähle deine Option:\n" +
                ">>>\n" +
                ">>> " + ANSI_BLUE + "1)" + ANSI_RESET + "Neuen Eintrag anlegen\n" +
                ">>> " + ANSI_BLUE + "2)" + ANSI_RESET + "Einträge einsehen\n" +
                ">>> " + ANSI_BLUE + "3)" + ANSI_RESET + "Exit\n");

       input = in.nextInt();
    }
}
