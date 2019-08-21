package honkytonky.objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RoomTest {

   Room room;

   @Mock
   Door door;

   @Mock
   Monster monster;

   @Mock
   Merchant merchant;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        room = new Room(1, "myRoom", false);
    }

    @Test
    public void testAddMonsterAndKillMonster() {
        assertNull(room.getPresentMerchant());
        assertFalse(room.hasLivingMonster());
        room.addMonster(monster);
        assertTrue(room.hasLivingMonster());
        assertEquals(room.getPresentMonster(), monster);
        room.monsterKilled();
        assertFalse(room.hasLivingMonster());
        assertNull(room.getPresentMerchant());
    }

    @Test
    public void testAddMerchant() {
        assertFalse(room.hasMerchant());
        room.addMerchant(merchant);
        assertTrue(room.hasMerchant());
        assertEquals(room.getPresentMerchant(), merchant);
    }

    @Test
    public void testAddDoor() {
        assertEquals(room.getDoors().size(), 0);
        room.addDoor(door);
        assertEquals(room.getDoors().size(), 1);
    }
}