package org.example;

/**
 * Клас, що представляє предмет одягу.
 */
public class Clothes {
    protected String classType;
    private String name;
    private int size;
    private double price;
    private String material;

    /**
     * Конструктор для створення об'єкта одягу.
     * @throws IllegalArgumentException якщо параметри некоректні
     */
    public Clothes(String name, int size, double price, String material) {
        this.classType = "Clothes";

        setName(name);
        setSize(size);
        setPrice(price);
        setMaterial(material);
    }

    public String getClassType() { return classType; }
    public String getName() { return name; }
    public int getSize() { return size; }
    public double getPrice() { return price; }
    public String getMaterial() { return material; }

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