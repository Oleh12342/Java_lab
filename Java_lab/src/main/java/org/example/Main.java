package org.example;

import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Clothes> list = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);
    private static final String FILE_NAME = "input.json";

    public static void main(String[] args) {
        loadFromJson();

        while (true) {
            System.out.println("\n--- Меню (Лабораторна №10) ---");
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
            System.out.println("1. Пошук за назвою (частковий збіг)");
            System.out.println("2. Пошук за діапазоном ціни");
            System.out.println("3. Пошук за розміром");
            System.out.println("0. Повернутися до головного меню");
            System.out.print("Ваш вибір: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Введіть назву для пошуку: ");
                    String name = sc.nextLine();
                    findByName(name);
                }
                case "2" -> {
                    try {
                        System.out.print("Мінімальна ціна: ");
                        double min = Double.parseDouble(sc.nextLine());
                        System.out.print("Максимальна ціна: ");
                        double max = Double.parseDouble(sc.nextLine());
                        findByPriceRange(min, max);
                    } catch (NumberFormatException e) {
                        System.out.println("Помилка: введіть коректні числові значення для ціни!");
                    }
                }
                case "3" -> {
                    try {
                        System.out.print("Введіть розмір: ");
                        int size = Integer.parseInt(sc.nextLine());
                        findBySize(size);
                    } catch (NumberFormatException e) {
                        System.out.println("Помилка: розмір має бути цілим числом!");
                    }
                }
                case "0" -> { return; }
                default -> System.out.println("Невірний вибір у підменю!");
            }
        }
    }

    // Пошук за назвою
    private static void findByName(String name) {
        boolean found = false;
        System.out.println("\nРезультати пошуку:");
        for (Clothes item : list) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("Об'єктів із назвою \"" + name + "\" не знайдено.");
    }

    // Пошук за ціною
    private static void findByPriceRange(double min, double max) {
        boolean found = false;
        System.out.println("\nРезультати пошуку в діапазоні [" + min + " - " + max + "]:");
        for (Clothes item : list) {
            if (item.getPrice() >= min && item.getPrice() <= max) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("У вказаному ціновому діапазоні нічого не знайдено.");
    }

    // Пошук за розміром
    private static void findBySize(int size) {
        boolean found = false;
        System.out.println("\nРезультати пошуку для розміру " + size + ":");
        for (Clothes item : list) {
            if (item.getSize() == size) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("Одягу розміру " + size + " немає в списку.");
    }

    private static void addObject() {
        System.out.println("\nОберіть тип об'єкта для створення:");
        System.out.println("1. Звичайний одяг");
        System.out.println("2. Штани");
        System.out.println("3. Сорочка");
        System.out.println("4. Джинси");
        System.out.println("5. Футболка");
        System.out.println("0. Повернутися до головного меню");

        System.out.print("Вибір: ");
        String type = sc.nextLine();

        if (type.equals("0")) {
            System.out.println("Повернення до головного меню...");
            return;
        }

        try {
            System.out.print("Назва: ");
            String name = sc.nextLine();

            System.out.print("Розмір: ");
            int size = Integer.parseInt(sc.nextLine());

            System.out.print("Ціна: ");
            double price = Double.parseDouble(sc.nextLine());

            System.out.print("Матеріал: ");
            String mat = sc.nextLine();

            switch (type) {
                case "1" -> list.add(new Clothes(name, size, price, mat));
                case "2" -> {
                    System.out.print("Довжина штанів (см): ");
                    int len = Integer.parseInt(sc.nextLine());
                    list.add(new Pants(name, size, price, mat, len));
                }
                case "3" -> {
                    System.out.print("Тип рукава: ");
                    String sleeve = sc.nextLine();
                    list.add(new Shirts(name, size, price, mat, sleeve));
                }
                case "4" -> {
                    System.out.print("Довжина штанів (см): ");
                    int len = Integer.parseInt(sc.nextLine());
                    System.out.print("Фасон: ");
                    String fit = sc.nextLine();
                    list.add(new Jeans(name, size, price, mat, len, fit));
                }
                case "5" -> {
                    System.out.print("Тип рукава: ");
                    String sleeve = sc.nextLine();
                    System.out.print("Чи є принт (true/false): ");
                    boolean print = Boolean.parseBoolean(sc.nextLine());
                    list.add(new TShirt(name, size, price, mat, sleeve, print));
                }
                default -> {
                    System.out.println("Невірний тип об'єкта.");
                    return;
                }
            }
            System.out.println("Об'єкт успішно додано до колекції.");

        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введіть коректне числове значення!");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Виникла непередбачувана помилка: " + e.getMessage());
        }
    }

    private static void showObjects() {
        if (list.isEmpty()) {
            System.out.println("\nКолекція порожня.");
        } else {
            System.out.println("\n--- Список усіх об'єктів в ієрархії ---");
            list.forEach(System.out::println);
            System.out.println("---------------------------------------");
        }
    }

    private static void saveToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
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

                switch (type) {
                    case "Clothes" -> list.add(gson.fromJson(obj, Clothes.class));
                    case "Pants"   -> list.add(gson.fromJson(obj, Pants.class));
                    case "Shirts"  -> list.add(gson.fromJson(obj, Shirts.class));
                    case "Jeans"   -> list.add(gson.fromJson(obj, Jeans.class));
                    case "TShirt"  -> list.add(gson.fromJson(obj, TShirt.class));
                }
            }
        } catch (Exception e) {
            System.out.println("Помилка завантаження даних: " + e.getMessage());
        }
    }
}