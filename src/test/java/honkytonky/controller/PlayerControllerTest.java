package honkytonky.controller;

import honkytonky.factories.ArmorFactory;
import honkytonky.factories.MapLayout;
import honkytonky.factories.PotionFactory;
import honkytonky.factories.WeaponFactory;
import honkytonky.objects.Player;
import honkytonky.objects.Room;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PlayerControllerTest {

    @InjectMocks
    PlayerController pc;

    @Mock
    Player player;

    @Mock
    WeaponFactory wf;

    @Mock
    ArmorFactory af;

    @Mock
    BattleController bc;

    @Mock
    PotionFactory pf;

    @Mock
    List<Room> rooms;

    @Mock
    MapLayout ml;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLel() {
    //    when(pc.createPlayer(af, wf, pf, bc, rooms)).thenReturn(null);
    }
}