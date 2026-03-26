package org.example;

/**
 * Клас, що представляє предмет одягу.
 */
public class Clothes {
    private static int numberOfObjects = 0;

    private Manufacturer manufacturer;

    private String name;
    private int size;
    private double price;
    private String material;

    /**
     * Конструктор для створення об'єкта одягу.
     * @throws IllegalArgumentException якщо параметри некоректні
     */
    public Clothes(String name, int size, double price, String material) {
        setName(name);
        setSize(size);
        setPrice(price);
        setSeason(season);
        this.manufacturer = manufacturer;
        numberOfObjects++;
    }

    /**
     * Конструктор копіювання.
     * Створює новий об'єкт на основі вже існуючого.
     * @param other об'єкт для копіювання
     */
    public Clothes(Clothes other) {
        this.name = other.name;
        this.size = other.size;
        this.price = other.price;
        this.season = other.season;
        this.manufacturer = other.manufacturer;
        numberOfObjects++;
    }

    /**
     * Статичний метод для отримання загальної кількості створених об'єктів.
     * @return кількість об'єктів
     */
    public static int getNumberOfObjects() {
        return numberOfObjects;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Назва не може бути порожньою");
        this.name = name;
    }

    public void setSize(int size) {
        if (size <= 0) throw new IllegalArgumentException("Розмір має бути більше 0");
        this.size = size;
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Ціна не може бути від'ємною");
        this.price = price;
    }

    public void setMaterial(String material) {
        if (material == null || material.trim().isEmpty())
            throw new IllegalArgumentException("Матеріал не вказано");
        this.material = material;
    }

    @Override
    public String toString() {
        return String.format("Одяг: %s, Розмір: %d, Ціна: %.2f, Матеріал: %s",
                name, size, price, material);
    }
}