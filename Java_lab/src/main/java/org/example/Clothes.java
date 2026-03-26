package org.example;

/**
 * Клас, що представляє предмет одягу.
 */
public class Clothes {
    private static int numberOfObjects = 0;

    private String name;
    private int size;
    private double price;
    private Season season;
    private Manufacturer manufacturer;

    public Clothes(String name, int size, double price, Season season, Manufacturer manufacturer) {
        setName(name);
        setSize(size);
        setPrice(price);
        setSeason(season);
        this.manufacturer = manufacturer;
        numberOfObjects++;
    }

    public Clothes(Clothes other) {
        this.name = other.name;
        this.size = other.size;
        this.price = other.price;
        this.season = other.season;
        this.manufacturer = other.manufacturer;
        numberOfObjects++;
    }

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

    public void setSeason(Season season) {
        if (season == null) throw new IllegalArgumentException("Сезон має бути вказаний");
        this.season = season;
    }

    public String getName() { return name; }
    public int getSize() { return size; }
    public double getPrice() { return price; }
    public Season getSeason() { return season; }
    public Manufacturer getManufacturer() { return manufacturer; }

    @Override
    public String toString() {
        return String.format("Одяг: %s, Розмір: %d, Ціна: %.2f, Сезон: %s, Виробник: %s",
                name, size, price, season.getTitle(), manufacturer.toString());
    }
}