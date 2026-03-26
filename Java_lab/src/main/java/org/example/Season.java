package org.example;

/**
 * Перерахування, що представляє сезони року для одягу.
 */
public enum Season {
    WINTER("Зима"),
    SPRING("Весна"),
    SUMMER("Літо"),
    AUTUMN("Осінь");

    private final String title;

    Season(String title) {
        this.title = title;
    }

    /**
     * @return назва сезону.
     */
    public String getTitle() {
        return title;
    }
}