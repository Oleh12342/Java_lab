package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClothesTest {

    @Test
    public void testStaticCounter() {
        int initialCount = Clothes.getNumberOfObjects();
        Manufacturer m = new Manufacturer("Test", "Test");
        new Clothes("T-Shirt", 42, 100, Season.SUMMER, m);

        assertEquals(initialCount + 1, Clothes.getNumberOfObjects(),
                "Лічильник має збільшитися на 1");
    }

    @Test
    public void testCopyConstructor() {
        Manufacturer m = new Manufacturer("Nike", "USA");
        Clothes original = new Clothes("Jeans", 32, 500, Season.AUTUMN, m);
        Clothes copy = new Clothes(original);

        assertEquals(original.getName(), copy.getName());
        assertEquals(original.getPrice(), copy.getPrice());
        assertSame(original.getSeason(), copy.getSeason());
    }
}
