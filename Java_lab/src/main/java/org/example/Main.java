package org.example;

import com.google.gson.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class Main {
    private static final Store myStore = new Store("TechStyle Shop");
    private static final Scanner sc = new Scanner(System.in);
    private static final String FILE_NAME = "input.json";

    public static void main(String[] args) {
        loadFromJson();

        while (true) {
            System.out.println("\n--- Меню (Лабораторна №14) ---");
            System.out.println("1. Пошук об’єкта");
            System.out.println("2. Додати об'єкт");
            System.out.println("3. Вивести інформацію про всі об’єкти");
            System.out.println("4. Меню для вибору критерію сортування");
            System.out.println("5. Вихід (зі збереженням)");

            System.out.print("Ваш вибір: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> searchMenu();
                case "2" -> addObject();
                case "3" -> showObjects();
                case "4" -> showSortedObjects();
                case "5" -> {
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
        System.out.println("\n1. Штани | 2. Сорочка | 3. Джинси | 4. Футболка | 0. Назад");
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
                case "1" -> {
                    System.out.print("Довжина: ");
                    item = new Pants(name, size, price, mat, Integer.parseInt(sc.nextLine()), qty);
                }
                case "2" -> {
                    System.out.print("Рукав: ");
                    item = new Shirts(name, size, price, mat, sc.nextLine(), qty);
                }
                case "3" -> {
                    System.out.print("Довжина: "); int l = Integer.parseInt(sc.nextLine());
                    System.out.print("Фасон: ");
                    item = new Jeans(name, size, price, mat, l, sc.nextLine(), qty);
                }
                case "4" -> {
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

    private static void deleteObject() {
        List<Clothes> inventory = myStore.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("\nМагазин порожній. Нічого видаляти.");
            return;
        }

        System.out.println("\n--- Список товарів на складі ---");
        for (Clothes item : inventory) {
            System.out.println(item);
        }

        System.out.print("\nВведіть ID товару, який хочете видалити/зменшити (або 0 для скасування): ");

        try {
            int targetId = Integer.parseInt(sc.nextLine());
            if (targetId == 0) return;

            Clothes foundItem = null;
            for (Clothes item : inventory) {
                if (item.getId() == targetId) {
                    foundItem = item;
                    break;
                }
            }

            if (foundItem == null) {
                System.out.println("Товар з ID " + targetId + " не знайдено.");
                return;
            }

            System.out.println("Обрано: " + foundItem.getName() + " (в наявності: " + foundItem.getQuantity() + ")");
            System.out.print("Яку кількість бажаєте списати? ");

            int qtyToRemove = Integer.parseInt(sc.nextLine());

            if (qtyToRemove <= 0) {
                System.out.println("Кількість має бути більшою за 0.");
                return;
            }

            int currentQty = foundItem.getQuantity();

            if (qtyToRemove >= currentQty) {
                inventory.remove(foundItem);
                System.out.println("Товар '" + foundItem.getName() + "' повністю видалено зі складу.");
            } else {
                foundItem.setQuantity(currentQty - qtyToRemove);
                System.out.println("Кількість оновлено. Залишилося: " + foundItem.getQuantity());
            }

        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть коректне число.");
        }
    }

    private static void showSortedObjects() {
        List<Clothes> inventory = myStore.getInventory();

        if (inventory.isEmpty()) {
            System.out.println("\nМагазин порожній. Нічого сортувати.");
            return;
        }

        while (true) {
            System.out.println("\n--- Оберіть критерій сортування ---");
            System.out.println("1. За назвою (А-Я)");
            System.out.println("2. За ціною (від найменшої)");
            System.out.println("3. За кількістю (від найбільшої)");
            System.out.println("0. Повернутися в головне меню");
            System.out.print("Ваш вибір: ");

            String choice = sc.nextLine();
            if (choice.equals("0")) return;

            List<Clothes> sortedList = new ArrayList<>(inventory);
            Comparator<Clothes> comparator = null;

            switch (choice) {
                case "1" -> {
                    comparator = new Comparator<Clothes>() {
                        @Override
                        public int compare(Clothes c1, Clothes c2) {
                            return c1.getName().compareToIgnoreCase(c2.getName());
                        }
                    };
                }
                case "2" -> {
                    comparator = new Comparator<Clothes>() {
                        @Override
                        public int compare(Clothes c1, Clothes c2) {
                            return Double.compare(c1.getPrice(), c2.getPrice());
                        }
                    };
                }
                case "3" -> {
                    comparator = new Comparator<Clothes>() {
                        @Override
                        public int compare(Clothes c1, Clothes c2) {
                            return Integer.compare(c2.getQuantity(), c1.getQuantity());
                        }
                    };
                }
                default -> {
                    System.out.println("Невірний вибір!");
                    continue;
                }
            }

            if (comparator != null) {
                Collections.sort(sortedList, comparator);
                System.out.println("\n--- Результат сортування ---");
                for (Clothes c : sortedList) {
                    System.out.println(c);
                }
            }
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
            List<Clothes> inventory = myStore.getInventory();

            int maxId = 0;

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();
                String type = obj.get("classType").getAsString();
                Clothes c = switch (type) {
                    case "Pants"   -> gson.fromJson(obj, Pants.class);
                    case "Shirts"  -> gson.fromJson(obj, Shirts.class);
                    case "Jeans"   -> gson.fromJson(obj, Jeans.class);
                    case "TShirt"  -> gson.fromJson(obj, TShirt.class);
                    default -> null;
                };
                if (c != null) {
                    inventory.add(c);

                    if (c.getId() > maxId) {
                        maxId = c.getId();
                    }
                }
            }

            Clothes.setCounter(maxId + 1);

        } catch (Exception e) {
            System.out.println("Помилка завантаження: " + e.getMessage());
        }
    }
}