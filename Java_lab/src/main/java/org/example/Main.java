package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Поліморфна колекція: ArrayList типу Clothes може зберігати і Pants, і Shirts
    private static final ArrayList<Clothes> list = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Меню (Лабораторна №7) ---");
            System.out.println("1. Додати об'єкт");
            System.out.println("2. Вивести всі об'єкти (Поліморфізм)");
            System.out.println("3. Вихід");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> addObject();
                case "2" -> showObjects();
                case "3" -> System.exit(0);
                default -> System.out.println("Невірний вибір!");
            }
        }
    }

    private static void addObject() {
        System.out.println("\nОберіть тип:");
        System.out.println("1. Звичайний одяг");
        System.out.println("2. Штани");
        System.out.println("3. Сорочка");
        String type = sc.nextLine();

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
                    System.out.print("Довжина (см): ");
                    int len = Integer.parseInt(sc.nextLine());
                    list.add(new Pants(name, size, price, mat, len));
                }
                case "3" -> {
                    System.out.print("Тип рукава (Короткий/Довгий): ");
                    String sleeve = sc.nextLine();
                    list.add(new Shirts(name, size, price, mat, sleeve));
                }
                default -> System.out.println("Невірний тип!");
            }
            System.out.println("Об'єкт успішно додано.");

        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введіть числове значення!");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    private static void showObjects() {
        if (list.isEmpty()) {
            System.out.println("Список порожній");
        } else {
            System.out.println("\n--- Список об'єктів ---");
            list.forEach(System.out::println);
        }
    }
}