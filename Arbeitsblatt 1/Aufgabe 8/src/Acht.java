// Importieren der VK_*-Tastenkonstanten
import static java.awt.event.KeyEvent.*;

/**
 * Die Klasse implementiert eine Figur, sich immer in Form einer Acht
 * bewegt.
 */
class Acht
{
    /** Die Figur, die gesteuert wird. */
    private final GameObject figur = new GameObject(0, 1, 0, "player");

    // Hier weitere Attribute, falls ihr welche benötigt
    
    /**
     * Konstruktor der Klasse Acht.
     * @param breite Die Breite (Anzahl der Gitterlinien) der Acht und
     *         damit die Seitenlänge der beiden Quadrate, aus denen die
     *         Acht gebildet wird.
     */
    Acht(final int breite)
    {
        // Hier Code des Konstruktors einfügen
    }

    /**
     * Die Figur dreht sich jeweils in die Richtung, in die sie als
     * nächstes gehen soll. Dabei soll sie die Form einer Acht abschreiten.
     *
     * Benötigte Methoden der Figur: setRotation.
     *
     * x-Koordinaten wachsen nach unten rechts (Südost), y-Koordinaten nach
     * unten links (Südwest). Die Rotation ist 0 in Richtung +x, 1 in
     * Richtung +y, 2 in Richtung -x und 3 in Richtung -y.
     */
    void bewege()
    {
        // Hier Code der Methode einfügen
    }

    /**
     * Die Testmethode. Bei richtiger Implementierung läuft die Figur in
     * Form einer Acht auf dem Gitter herum. Für jeden Schritt muss eine
     * Taste gedrückt werden. Der Druck auf Escape beendet das Programm.
     */
    public static void main(String[] args)
    {
        new GameObject(0, 1, 0, "floor-l");
        new GameObject(1, 1, 0, "floor-i");
        new GameObject(2, 1, 1, "floor-t");
        new GameObject(3, 1, 0, "floor-i");
        new GameObject(4, 1, 1, "floor-l");
        new GameObject(0, 2, 1, "floor-i");
        new GameObject(1, 2, 0, "floor");
        new GameObject(2, 2, 1, "floor-i");
        new GameObject(3, 2, 0, "floor");
        new GameObject(4, 2, 1, "floor-i");
        new GameObject(0, 3, 3, "floor-l");
        new GameObject(1, 3, 0, "floor-i");
        new GameObject(2, 3, 3, "floor-t");
        new GameObject(3, 3, 0, "floor-i");
        new GameObject(4, 3, 2, "floor-l");

        final Acht instanz = new Acht(2);

        final GameObject figur = instanz.figur;
        final int[][] versatz = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (Game.getNextKey() != VK_ESCAPE) {
            instanz.bewege();
            figur.setLocation(figur.getX() + versatz[figur.getRotation()][0], 
                              figur.getY() + versatz[figur.getRotation()][1]);
        }
        System.exit(0);
    }
}
