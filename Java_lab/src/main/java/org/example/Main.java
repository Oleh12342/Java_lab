package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Clothes> list = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Меню (Лабораторна №8) ---");
            System.out.println("1. Додати об'єкт");
            System.out.println("2. Вивести інформацію про всі об’єкти");
            System.out.println("3. Вихід");

            System.out.print("Ваш вибір: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> addObject();
                case "2" -> showObjects();
                case "3" -> {
                    System.out.println("Програма завершена.");
                    System.exit(0);
                }
                default -> System.out.println("Невірний вибір!");
            }
        }
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
                case "1" -> {
                    list.add(new Clothes(name, size, price, mat));
                }
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
}