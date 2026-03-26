/** package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClothesTest {

    @Test
    void shouldThrowExceptionWhenInvalidSize() {
        Clothes clothes = new Clothes("T-shirt", 42, 500.0, "Cotton");
        assertThrows(IllegalArgumentException.class, () -> clothes.setSize(-1));
    }

    @Test
    void shouldThrowExceptionWhenConstructorDataInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                new Clothes("", 0, -10.0, null));
    }
} */