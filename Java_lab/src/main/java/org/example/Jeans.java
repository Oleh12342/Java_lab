package org.example;

public class Jeans extends Pants {
    private String fit;

    public Jeans(String name, int size, double price, String material, int length, String fit, int quantity) {
        super(name, size, price, material, length, quantity);
        this.classType = "Jeans";
        this.fit = fit;
    }

    public String getFit() { return fit; }

    @Override
    public String toString() {
        return super.toString() + ", Фасон: " + fit + " [Тип: Джинси]";
    }
}