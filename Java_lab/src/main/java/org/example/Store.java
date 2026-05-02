package org.example;

import java.util.ArrayList;

public class Store {
    private String name;
    private ArrayList<Clothes> inventory;

    public Store(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
    }

    public ArrayList<Clothes> getInventory() {
        return inventory;
    }

    public void addNewClothes(Clothes newCl, int quantity) {
        for (Clothes item : inventory) {
            if (item.getName().equalsIgnoreCase(newCl.getName()) &&
                    item.getSize() == newCl.getSize() &&
                    item.getMaterial().equalsIgnoreCase(newCl.getMaterial())) {

                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("Товар '" + item.getName() + "' вже є. Кількість оновлено: " + item.getQuantity());
                return;
            }
        }

        newCl.setQuantity(quantity);
        inventory.add(newCl);
        System.out.println("Новий товар '" + newCl.getName() + "' додано до магазину.");
    }

    public void findByName(String searchName) {
        boolean found = false;
        System.out.println("\nРезультати пошуку за назвою \"" + searchName + "\":");
        for (Clothes item : inventory) {
            if (item.getName().toLowerCase().contains(searchName.toLowerCase())) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("Нічого не знайдено.");
    }

    public void findByPriceRange(double min, double max) {
        boolean found = false;
        System.out.println("\nРезультати в діапазоні ціни " + min + " - " + max + ":");
        for (Clothes item : inventory) {
            if (item.getPrice() >= min && item.getPrice() <= max) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("Нічого не знайдено.");
    }

    public void findBySize(int searchSize) {
        boolean found = false;
        System.out.println("\nРезультати пошуку за розміром " + searchSize + ":");
        for (Clothes item : inventory) {
            if (item.getSize() == searchSize) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("Нічого не знайдено.");
    }

    public boolean update(Clothes existingObject, Clothes newObject) {
        if (existingObject == null || newObject == null)
            throw new ObjectNotFoundException("Об'єкт не вказано");

        int index = inventory.indexOf(existingObject);
        if (index == -1) {
            throw new ObjectNotFoundException("Товар '" + existingObject.getName() + "' не знайдено для оновлення");
        }
        inventory.set(index, newObject);
        return true;
    }

    public boolean delete(Clothes existingObject) {
        if (existingObject == null) throw new ObjectNotFoundException("Об'єкт порожній");
        if (!inventory.contains(existingObject)) {
            throw new ObjectNotFoundException("Товар не знайдено на складі");
        }
        return inventory.remove(existingObject);
    }

    @Override
    public String toString() {
        return "Магазин: " + name + ", Кількість позицій: " + inventory.size();
    }
}