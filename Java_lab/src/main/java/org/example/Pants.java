package org.example;

public class Pants extends Clothes {
    private int length;

    public Pants(String name, int size, double price, String material, int length, int quantity) {
        super(name, size, price, material, quantity);
        this.classType = "Pants";
        setLength(length);
    }

    public int getLength() { return length; }

    public void setLength(int length) {
        if (length <= 0) throw new IllegalArgumentException("Довжина має бути більше 0");
        this.length = length;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Довжина: %d см [Тип: Штани]", length);
    }
}