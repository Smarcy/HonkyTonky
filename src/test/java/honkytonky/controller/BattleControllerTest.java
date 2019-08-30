package honkytonky.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import honkytonky.misc.ExpTable;
import honkytonky.objects.Actor;
import honkytonky.objects.Armor;
import honkytonky.objects.Monster;
import honkytonky.objects.Player;
import honkytonky.objects.Potion;
import honkytonky.objects.Room;
import honkytonky.objects.Weapon;
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

    @Mock
    Weapon weapon;

    @Mock
    Potion potion;

    @Mock
    Room room;

    @Mock
    Actor actor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ExpTable.createLevels();
    }

    @Test
    public void testMonsterAttacks() {
        when(monster.getDamage()).thenReturn(1);
        when(player.getArmor()).thenReturn(armor);
        when(player.getTemporaryDefBoost()).thenReturn(5);
        when(player.getHp()).thenReturn(100);

        battleController.setPlayer(player);
        battleController.enemyAttacks(true);

        verify(player, times(1)).getTemporaryDefBoost();
        verify(player, times(1)).setHp(anyInt());
        verify(player, times(1)).resetTemporaryDefBoost();
    }

    @Test
    public void testRewardPlayerWithoutLevelUp() {

        when(player.getExperience()).thenCallRealMethod();
        when(player.getGold()).thenCallRealMethod();

        assertEquals(0, player.getExperience());
        assertEquals(0, player.getGold());
        assertEquals(0, player.getLevel());

        when(monster.getGrantedExperience()).thenReturn(100);
        when(monster.getGoldDropped()).thenReturn(50);

        battleController.rewardPlayerForMonsterKill();

        verify(player, times(1)).increaseExperience(anyInt());
        verify(player, times(1)).giveGold(anyInt());
        verify(player, times(1)).checkForLevelUp();
        verify(player, times(1)).getLevel();
    }

    @Test
    public void testRewardPlayerWithLevelUp() {

        Player tmpPlayer = new Player("Dude", 50, weapon, armor, room);

        assertEquals(0, tmpPlayer.getExperience());
        assertEquals(0, tmpPlayer.getGold());
        assertEquals(1, tmpPlayer.getLevel());

        when(monster.getGrantedExperience()).thenReturn(100);
        when(monster.getGoldDropped()).thenReturn(50);

        battleController.setPlayer(tmpPlayer);
        battleController.rewardPlayerForMonsterKill();

        assertEquals(100, tmpPlayer.getExperience());
        assertEquals(50, tmpPlayer.getGold());
        assertEquals(2, tmpPlayer.getLevel());
    }
}
