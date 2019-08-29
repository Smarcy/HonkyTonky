package honkytonky.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import honkytonky.objects.Item;
import honkytonky.objects.Merchant;
import honkytonky.objects.Player;
import honkytonky.objects.Potion;
import honkytonky.objects.Weapon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MerchantControllerTest {

    @InjectMocks
    MerchantController mc;

    @Mock
    Merchant merchant;

    @Mock
    Player player;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void tradeWithMerchantSuccessfulPurchaseWeaponAndPotion() {

        List<Item> merchantItems = new ArrayList<>();
        Weapon dummyWeapon = mock(Weapon.class);
        merchantItems.add(dummyWeapon);

        when(merchant.getItemsForSell()).thenReturn(merchantItems);
        when(player.getInventory()).thenReturn(new ArrayList<>());

        mc.tradeWithMerchant(1, false);

        verify(player, times(1)).getGold();
        verify(player, times(1)).giveGold(anyInt());
        verify(player, times(1)).getInventory();
        verify(player, times(1)).giveGold(anyInt());
        verify(player, never()).getPlayersPotions();
        verify(merchant, times(1)).removeItemFromShop(any());
        verify(merchant, times(1)).getItemsForSell();
        verify(dummyWeapon, times(1)).getName();
        verify(dummyWeapon, times(2)).getValue();
        assertEquals(1, player.getInventory().size());
        assertTrue(player.getInventory().contains(dummyWeapon));

        Potion dummyPotion = mock(Potion.class);
        Map<Potion, Integer> playerPotions = new HashMap<>();
        playerPotions.put(dummyPotion, 1);
        when(player.getPlayersPotions()).thenReturn((playerPotions));

        merchantItems.add(dummyPotion);
        mc.tradeWithMerchant(2, false);

        verify(player, times(2)).getGold();
        verify(player, times(2)).giveGold(anyInt());
        verify(player, times(4)).getInventory();
        verify(player, times(2)).giveGold(anyInt());
        verify(player, times(3)).getPlayersPotions();
        verify(merchant, times(2)).removeItemFromShop(any());
        verify(merchant, times(2)).getItemsForSell();
        verify(dummyPotion, times(1)).getName();
        verify(dummyPotion, times(2)).getValue();
        assertEquals(2, player.getInventory().size());
        assertEquals(1, player.getPlayersPotions().size());
        assertTrue(player.getPlayersPotions().get(dummyPotion) == 2);
        assertTrue(player.getInventory().contains(dummyPotion));
    }

    @Test
    public void tradeWithMerchantNotEnoughGold() {
        List<Item> merchantItems = new ArrayList<>();
        Weapon dummyWeapon = mock(Weapon.class);
        merchantItems.add(dummyWeapon);

        when(merchant.getItemsForSell()).thenReturn(merchantItems);
        when(player.getInventory()).thenReturn(new ArrayList<>());
        when(player.getGold()).thenReturn(0);
        when(dummyWeapon.getValue()).thenReturn(100);

        mc.tradeWithMerchant(1, false);

        verify(player, times(1)).getGold();
        verify(player, never()).giveGold(anyInt());
        verify(player, never()).getInventory();
        verify(player, never()).giveGold(anyInt());
        verify(player, never()).getPlayersPotions();
        verify(merchant, never()).removeItemFromShop(any());
        verify(merchant, times(1)).getItemsForSell();
        verify(dummyWeapon, times(1)).getName();
        verify(dummyWeapon, times(1)).getValue();
        assertEquals(0, player.getInventory().size());
    }
}