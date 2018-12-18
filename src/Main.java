import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static Scanner in = new Scanner(System.in);
    private static List<String> entries = new ArrayList<String>();

    public static void main(String... args)
    {
        while(true)
        {
            int option = showIntro();

            switch (option)
            {
                case 1:
                    addNewEntry();
                    break;
                case 2:
                    listEntries();
                    break;
                case 3:
                    System.exit(1);
                    break;
                default:
                    break;
            }
        }
    }

    private static int showIntro()
    {
        System.out.flush();
        System.out.println(">>> Willkommen im Adressbuch!\n" +
                ">>> Bitte wähle deine Option:\n" +
                ">>>\n" +
                ">>> " + ANSI_BLUE + "1)" + ANSI_RESET + "Neuen Eintrag anlegen\n" +
                ">>> " + ANSI_BLUE + "2)" + ANSI_RESET + "Einträge einsehen\n" +
                ">>> " + ANSI_BLUE + "3)" + ANSI_RESET + "Exit\n");

      return in.nextInt();
    }

    private static void addNewEntry()
    {
        String name, street;

        System.out.flush();
        System.out.println("Name: ");
        name = in.next();
        System.out.println("Straße: ");
        street = in.next();
        entries.add(name + "," + street);
        showIntro();
    }

    private static void listEntries()
    {
        //String[] formattedEntries = new String[2];
        int i = 0;

        for(String entry : entries)
        {
            String[] formattedEntries = entry.split(",");

            List<String> items = Arrays.asList(formattedEntries);

            System.out.println("Name: " + items.get(i) + "\nStraße: " + items.get(i+1));

            i += 2;
        }


    }
}
