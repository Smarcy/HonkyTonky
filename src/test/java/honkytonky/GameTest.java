package honkytonky;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import honkytonky.Game;
import honkytonky.objects.Armor;
import honkytonky.objects.Monster;
import honkytonky.objects.Player;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {

    @InjectMocks
    Game game;

    @Mock
    Player player;

    @Mock
    Monster monster;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMonsterAttacks() {

        MockitoAnnotations.initMocks(this);

        when(monster.getDamage()).thenReturn(1);
        when(player.getArmor()).thenReturn(new Armor(1, "armor", 10, 10, 10));
        when(player.getTemporaryDefBoost()).thenReturn(5);
        when(player.getHp()).thenReturn(100);

        game.monsterAttacks(true);

        verify(player, atLeastOnce()).getTemporaryDefBoost();
        verify(player, times(1)).setHp(anyInt());


    }

}
