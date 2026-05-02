package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StoreTest {

    @Test
    void shouldThrowObjectNotFoundExceptionWhenDeletingNonExistingObject() {
        Store store = new Store("Test Store");
        Clothes item = new Pants("Джинси", 34, 1200.0, "Денім", 100, 5);

        assertThrows(ObjectNotFoundException.class, () -> {
            store.delete(item);
        }, "Мало виникнути ObjectNotFoundException, бо товару немає в списку");
    }

    @Test
    void shouldThrowInvalidFieldValueExceptionForNegativePrice() {
        Clothes item = new Pants("Футболка", 42, 500.0, "Бавовна", 70, 10);

        assertThrows(InvalidFieldValueException.class, () -> {
            item.setPrice(-100.0);
        }, "Мало виникнути InvalidFieldValueException через від'ємну ціну");
    }
}
