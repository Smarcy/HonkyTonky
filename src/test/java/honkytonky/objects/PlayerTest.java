package honkytonky.objects;

import honkytonky.objects.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void testNullPlayer() {
        Assertions.assertThrows(NullPointerException.class,
          () -> new Player("John", 50, null, null, null, null));
    }
}