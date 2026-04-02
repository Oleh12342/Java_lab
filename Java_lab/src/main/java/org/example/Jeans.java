package org.example;

public class Jeans extends Pants {
    private String fit;

    public Jeans(String name, int size, double price, String material, int length, String fit) {
        super(name, size, price, material, length);
        this.fit = fit;
    }

    @Override
    public String toString() {
        return super.toString() + ", Фасон: " + fit + " [Тип: Джинси]";
    }
}