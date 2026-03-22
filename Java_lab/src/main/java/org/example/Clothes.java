package org.example;

public class Clothes {
    private String type;
    private int size;

    public Clothes(String type, int size) {
        this.type = type;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Предмет одягу: " + type + ", Розмір: " + size;
    }
}