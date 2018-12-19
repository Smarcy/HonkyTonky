import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

class Main {

    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static Scanner in = new Scanner(System.in);
    private static List<Entry> entries = new ArrayList<Entry>();

    private static Entry entry;

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
        entry = new Entry(name, street);
        entries.add(entry);
    }

    private static void listEntries()
    {
        for(Entry entry : entries)
        {


            System.out.println("Name: " + entries.get(0).getName() + "\nStraße: " + entries.get(0).getStreet()+"\n");
        }
    }
}
