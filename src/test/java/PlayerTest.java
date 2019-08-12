import honkytonky.objects.Armor;
import honkytonky.objects.Player;
import honkytonky.objects.Weapon;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void weaponNull() {
        assertThrows(NullPointerException.class, () -> {
            Player p = new Player("Foo", 50, null, new Armor(1, "LelArmor", 10, 10, 10), null, null);
        });
    }

}
