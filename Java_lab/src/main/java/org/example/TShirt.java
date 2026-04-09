package org.example;

public class TShirt extends Shirts {
    private boolean hasPrint;

    public TShirt(String name, int size, double price, String material, String sleeveType, boolean hasPrint, int quantity) {
        super(name, size, price, material, sleeveType, quantity);
        this.classType = "TShirt";
        this.hasPrint = hasPrint;
    }

    @Override
    public String toString() {
        return super.toString() + ", Наявність принту: " + (hasPrint ? "Так" : "Ні") + " [Тип: Футболка]";
    }
}