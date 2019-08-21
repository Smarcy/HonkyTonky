package honkytonky.objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MerchantTest {

    @Mock
    Item item;

    private Merchant merchant;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        merchant = new Merchant("Dude", 100, 100, null);
    }

    @Test
    public void testAddAndRemoveItem() {
        assertEquals(merchant.getItemsForSell().size(), 0);
        merchant.addItemToShop(item);
        assertEquals(merchant.getItemsForSell().size(), 1);
        merchant.removeItemFromShop(item);
        assertEquals(merchant.getItemsForSell().size(), 0);
        merchant.addItemToShop(item);
        merchant.addItemToShop(item);
        merchant.addItemToShop(item);
        assertEquals(merchant.getItemsForSell().size(), 3);
        merchant.removeItemFromShop(item);
        merchant.removeItemFromShop(item);
        assertEquals(merchant.getItemsForSell().size(), 1);
        merchant.removeItemFromShop(item);
        assertEquals(merchant.getItemsForSell().size(), 0);
    }
}