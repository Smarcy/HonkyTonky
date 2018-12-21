import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Eine Klasse, die drei Klassenmethoden kapselt, die für Spiele benötigt werden.
 *
 * @author Thomas Röfer
 */
public abstract class Game
{
    /** Ist Sun-Audio vorhanden? (Hat weniger Probleme). */
    private static boolean hasSunAudio = sunAudioAvailable();

    /**
     * Ermittelt, ob es Sun-Audio gibt.
     * @return Gibt es Sun-Audio?
     */
    private static boolean sunAudioAvailable() 
    {
        try {
            Class.forName("sun.audio.AudioPlayer");
            return true;
        }
        catch (final ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Warten auf und Liefern der nächsten gedrückten Taste.
     * @return Ein Tastencode, wie sie in der Klasse {@link java.awt.event.KeyEvent}
     *         definiert sind.
     */
    public static int getNextKey()
    {
        return GameObject.Canvas.getInstance().getNextKey();
    }

    /**
     * Warten für eine bestimmte Zeit.
     * @param millis Die Anzahl Millisekunden, die gewartet wird. Die tatsächliche
     *         Wartezeit kann etwas länger sein.
     */
    public static void sleep(final int millis)
    {
        try {
            Thread.sleep(millis);
        }
        catch (final InterruptedException e) {
            // Ignorieren
        }
    }

    /**
     * Abspielen einer Sound-Datei.
     * @param fileName Der Name der Datei. Sie wird im Unterverzeichnis "sounds"
     *         gesucht. Wenn keine Dateiendung angegeben wurde, wird ".wav"
     *         angehängt.
     * @throws IllegalArgumentException Die Datei konnte nicht gefunden werden
     *         oder hat kein akzeptiertes Format.
     */
   // /@SuppressWarnings("sunapi")
/*    public static void playSound(final String fileName)
    {
        final int period = fileName.lastIndexOf('.');
        final int separator = fileName.lastIndexOf(File.separator);
        final String completeName = fileName + (period > separator ? "" : ".wav");
        try {
            if (hasSunAudio) {
                sun.audio.AudioPlayer.player.start(getInputStream("sounds"
                        + File.separator + completeName));
            }
            else {
                final Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(getInputStream("sounds"
                        + File.separator + completeName))));
                clip.addLineListener(e -> {if (e.getType() == LineEvent.Type.STOP) {clip.close();}});
                clip.start();
            }
        }
        catch (final IOException e) {
            throw new IllegalArgumentException("Die Sounddatei '" + completeName
                    + "' konnte nicht geladen werden.");
        }
        catch (final UnsupportedAudioFileException e) {
            throw new IllegalArgumentException("Die Sounddatei '" + completeName
                    + "' hat kein unterstütztes Format.");
        }
        catch (final LineUnavailableException e) {
            // Zu viele Sounds gleichzeitig: Diesen einfach nicht abspielen.
        }
    }*/

    /**
     * Liefern eines Datenstroms zu einem Dateinamen. Dies funktioniert sowohl
     * direkt, als auch aus einem jar-Archiv heraus.
     * @param fileName Der Name des Datenstroms, der geliefert werden soll.
     * @throws FileNotFoundException Zu dem Namen gibt es keinen Datenstrom.
     */
    static InputStream getInputStream(String fileName) throws FileNotFoundException
    {
        // Ressourcen nutzen / als Verzeichnistrenner, weshalb \ ersetzt wird.
        if (File.separatorChar == '\\') {
            fileName = fileName.replaceAll("\\\\", "/");
        }
        
        final InputStream stream = Game.class.getResourceAsStream(fileName);
        if (stream != null) {
            return stream;
        }
        else {
            throw new FileNotFoundException("Cannot find resource " + fileName);
        }
    }
}
