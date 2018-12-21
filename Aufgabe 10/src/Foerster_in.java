/**
 * Die Klasse implementiert eine Förster*in, die einen rautenförmigen
 * Wald auf eine quadratische Rasenfläche pflanzt. Siehe dazu die Abbildung
 * im Arbeitsblatt.
 */
class Foerster_in
{
    /** Die Figur, die gesteuert wird. */
    private final GameObject figur = new GameObject(0, 0, 0, "player");

    // Hier weitere Attribute, falls ihr welche benötigt

    /**
     * Konstruktor der Klasse Foerster_in.
     * @param breite Die gewünschte Breite des Waldes in Zellen. Sie ist
     *         immer eine ungerade, positive Zahl.
     */
    Foerster_in(final int breite)
    {
        // Hier Code des Konstruktors einfügen
    }

    /**
     * Die Methode erzeugt in jedem Schritt ein neues GameObject (Bodenstück
     * oder Baum) an der Position der Figur. Die Figur dreht sich danach in
     * die Richtung, in die sie als nächstes gehen soll.
     *
     * Benötigte Methoden der Figur: getX, getY, setRotation.
     * Außerdem new GameObject(..., "floor" bzw. "tree").
     *
     * x-Koordinaten wachsen nach unten rechts (Südost), y-Koordinaten nach
     * unten links (Südwest). Die Rotation ist 0 in Richtung +x, 1 in
     * Richtung +y, 2 in Richtung -x und 3 in Richtung -y.
     *
     * @return true, wenn die Figur einen Schritt vorwärts machen soll.
     *         false, wenn Rasen und Wald zu Ende gepflanzt wurde.
     */
    boolean bewege()
    {
        return false; // Hier durch Code der Methode ersetzen
    }

    /**
     * Die Testmethode. Bei richtiger Implementierung entsteht eine Rasenfläche
     * mit Wald und die Figur verschwindet, sobald das Grundstück komplett ist.
     * Insbesondere sollte dann auch das Programm anhalten.
     */
    public static void main(String[] args)
    {
        final Foerster_in instanz = new Foerster_in(5);
        Game.getNextKey();

        final GameObject figur = instanz.figur;
        final int[][] versatz = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (instanz.bewege()) {
            figur.setLocation(figur.getX() + versatz[figur.getRotation()][0], 
                              figur.getY() + versatz[figur.getRotation()][1]);
            Game.getNextKey();
        }
        figur.setVisible(false);
    }
}
