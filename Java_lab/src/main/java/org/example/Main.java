package org.example;

import com.google.gson.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    private static final Store myStore = new Store("TechStyle Shop");
    private static final Scanner sc = new Scanner(System.in);
    private static final String FILE_NAME = "input.json";

    public static void main(String[] args) {
        loadFromJson();

        while (true) {
            System.out.println("\n--- Меню (Лабораторна №11) ---");
            System.out.println("1. Пошук об’єкта");
            System.out.println("2. Додати об'єкт");
            System.out.println("3. Вивести інформацію про всі об’єкти");
            System.out.println("4. Вихід (зі збереженням)");

            System.out.print("Ваш вибір: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> searchMenu();
                case "2" -> addObject();
                case "3" -> showObjects();
                case "4" -> {
                    saveToJson();
                    System.out.println("Програма завершена. Дані збережено.");
                    System.exit(0);
                }
                default -> System.out.println("Невірний вибір!");
            }
        }
    }

    private static void searchMenu() {
        while (true) {
            System.out.println("\n--- Підменю пошуку ---");
            System.out.println("1. Пошук за назвою");
            System.out.println("2. Пошук за діапазоном ціни");
            System.out.println("3. Пошук за розміром");
            System.out.println("0. Повернутися до головного меню");
            System.out.print("Ваш вибір: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Назва: ");
                    myStore.findByName(sc.nextLine());
                }
                case "2" -> {
                    try {
                        System.out.print("Мін. ціна: ");
                        double min = Double.parseDouble(sc.nextLine());
                        System.out.print("Макс. ціна: ");
                        double max = Double.parseDouble(sc.nextLine());
                        myStore.findByPriceRange(min, max);
                    } catch (NumberFormatException e) {
                        System.out.println("Помилка числа!");
                    }
                }
                case "3" -> {
                    try {
                        System.out.print("Розмір: ");
                        myStore.findBySize(Integer.parseInt(sc.nextLine()));
                    } catch (NumberFormatException e) {
                        System.out.println("Помилка числа!");
                    }
                }
                case "0" -> { return; }
            }
        }
    }

    private static void addObject() {
        System.out.println("\n1. Одяг | 2. Штани | 3. Сорочка | 4. Джинси | 5. Футболка | 0. Назад");
        System.out.print("Вибір: ");
        String type = sc.nextLine();
        if (type.equals("0")) return;

        try {
            System.out.print("Назва: "); String name = sc.nextLine();
            System.out.print("Розмір: "); int size = Integer.parseInt(sc.nextLine());
            System.out.print("Ціна: "); double price = Double.parseDouble(sc.nextLine());
            System.out.print("Матеріал: "); String mat = sc.nextLine();
            System.out.print("Кількість: "); int qty = Integer.parseInt(sc.nextLine());

            Clothes item = null;
            switch (type) {
                case "1" -> item = new Clothes(name, size, price, mat, qty);
                case "2" -> {
                    System.out.print("Довжина: ");
                    item = new Pants(name, size, price, mat, Integer.parseInt(sc.nextLine()), qty);
                }
                case "3" -> {
                    System.out.print("Рукав: ");
                    item = new Shirts(name, size, price, mat, sc.nextLine(), qty);
                }
                case "4" -> {
                    System.out.print("Довжина: "); int l = Integer.parseInt(sc.nextLine());
                    System.out.print("Фасон: ");
                    item = new Jeans(name, size, price, mat, l, sc.nextLine(), qty);
                }
                case "5" -> {
                    System.out.print("Рукав: "); String s = sc.nextLine();
                    System.out.print("Принт (true/false): ");
                    item = new TShirt(name, size, price, mat, s, Boolean.parseBoolean(sc.nextLine()), qty);
                }
            }
            if (item != null) myStore.addNewClothes(item, qty);

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private static void showObjects() {
        if (myStore.getInventory().isEmpty()) {
            System.out.println("\nМагазин порожній.");
        } else {
            System.out.println("\n--- Склад магазину ---");
            for (Clothes c : myStore.getInventory()) System.out.println(c);
        }
    }

    private static void saveToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(myStore.getInventory(), writer);
        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
        }
    }

    private static void loadFromJson() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(file)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();
                String type = obj.get("classType").getAsString();
                Clothes c = switch (type) {
                    case "Clothes" -> gson.fromJson(obj, Clothes.class);
                    case "Pants"   -> gson.fromJson(obj, Pants.class);
                    case "Shirts"  -> gson.fromJson(obj, Shirts.class);
                    case "Jeans"   -> gson.fromJson(obj, Jeans.class);
                    case "TShirt"  -> gson.fromJson(obj, TShirt.class);
                    default -> null;
                };
                if (c != null) myStore.getInventory().add(c);
            }
        } catch (Exception e) {
            System.out.println("Помилка завантаження: " + e.getMessage());
        }
    }
}