package org.example;

import java.util.UUID;
/**
 * Клас, що представляє предмет одягу.
 */
public abstract class Clothes implements Comparable<Clothes>, Identifiable {
    private static int idCounter = 1;
    private int id;
    private UUID uuid;
    private int quantity;
    protected String classType;
    private String name;
    private int size;
    private double price;
    private String material;

    /**
     * Конструктор для створення об'єкта одягу.
     * @throws IllegalArgumentException якщо параметри некоректні
     */
    public Clothes(String name, int size, double price, String material, int quantity) {
        this.id = idCounter++;
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.size = size;
        this.price = price;
        this.material = material;
        this.quantity = quantity;
        this.classType = "Clothes";
    }

    @Override
    public UUID getUuid() { return uuid; }

    @Override
    public int compareTo(Clothes other) {
        if (other == null) return 1;
        return this.name.compareToIgnoreCase(other.name);
    }

    public int getId() { return id; }
    public int getQuantity() { return quantity; }
    public String getClassType() { return classType; }
    public String getName() { return name; }
    public int getSize() { return size; }
    public double getPrice() { return price; }
    public String getMaterial() { return material; }

    public static void setCounter(int newStart) {
        idCounter = newStart;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("[ID: %d | UUID: %s...] %s (%s), Розмір: %d, Ціна: %.2f, К-сть: %d шт.",
                id, uuid.toString().substring(0, 8), name, classType, size, price, quantity);
    }
}