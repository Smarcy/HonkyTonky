package honkytonky.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import honkytonky.objects.Armor;
import honkytonky.objects.Monster;
import honkytonky.objects.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BattleControllerTest {

    @InjectMocks
    BattleController battleController;

    @Mock
    Player player;

    @Mock
    Monster monster;

    @Mock
    Armor armor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMonsterAttacks() {
        when(monster.getDamage()).thenReturn(1);
        when(player.getArmor()).thenReturn(armor);
        when(player.getTemporaryDefBoost()).thenReturn(5);
        when(player.getHp()).thenReturn(100);

        battleController.setPlayer(player);
        battleController.monsterAttacks(true);

        verify(player, times(1)).getTemporaryDefBoost();
        verify(player, times(1)).setHp(anyInt());
        verify(player, times(1)).resetTemporaryDefBoost();
    }
}
