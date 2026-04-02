package org.example;

public class Shirts extends Clothes {
    private String sleeveType;

    public Shirts(String name, int size, double price, String material, String sleeveType) {
        super(name, size, price, material);
        setSleeveType(sleeveType);
    }

    public void setSleeveType(String sleeveType) {
        if (sleeveType == null || sleeveType.trim().isEmpty())
            throw new IllegalArgumentException("Тип рукава не вказано");
        this.sleeveType = sleeveType;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Рукав: %s [Тип: Сорочка]", sleeveType);
    }
}