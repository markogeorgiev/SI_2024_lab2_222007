import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class SILab2Test {

    @Test
    public void testCheckCartWithValidPayment() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "1234567890", 100, 0.1f));
        items.add(new Item("Item2", "0987654321", 200, 0.05f));
        items.add(new Item("Item3", "1357924680", 150, 0));

        assertTrue(SILab2.checkCart(items, 400));
    }

    @Test
    public void testCheckCartWithInvalidPayment() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Item1", "1234567890", 100, 0.1f));
        items.add(new Item("Item2", "0987654321", 200, 0.05f));
        items.add(new Item("Item3", "1357924680", 150, 0));

        assertFalse(SILab2.checkCart(items, 250));
    }
}