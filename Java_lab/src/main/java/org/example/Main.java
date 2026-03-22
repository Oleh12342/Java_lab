package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Clothes> list = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Меню ---");
            System.out.println("1. Додати об'єкт");
            System.out.println("2. Вивести всі об'єкти");
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
        try {
            System.out.print("Назва: ");
            String name = sc.nextLine();

            System.out.print("Розмір: ");
            int size = Integer.parseInt(sc.nextLine());

            System.out.print("Ціна: ");
            double price = Double.parseDouble(sc.nextLine());

            System.out.print("Матеріал: ");
            String mat = sc.nextLine();

            list.add(new Clothes(name, size, price, mat));
        } catch (NumberFormatException e) {
            System.out.println("Помилка: Введіть числове значення!");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    private static void showObjects() {
        if (list.isEmpty()) System.out.println("Список порожній");
        else list.forEach(System.out::println);
    }
}