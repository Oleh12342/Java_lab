package org.example;

/**
 * Клас, що представляє виробника одягу.
 */
public class Manufacturer {
    private String brandName;
    private String country;

    /**
     * Конструктор для створення об'єкта виробника.
     * @param brandName назва бренду
     * @param country країна походження
     * @throws IllegalArgumentException якщо дані некоректні
     */
    public Manufacturer(String brandName, String country) {
        setBrandName(brandName);
        setCountry(country);
    }

    public void setBrandName(String brandName) {
        if (brandName == null || brandName.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва бренду не може бути порожньою");
        }
        this.brandName = brandName;
    }

    public void setCountry(String country) {
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Країна не може бути порожньою");
        }
        this.country = country;
    }

    public String getBrandName() { return brandName; }
    public String getCountry() { return country; }

    @Override
    public String toString() {
        return String.format("%s (%s)", brandName, country);
    }
}