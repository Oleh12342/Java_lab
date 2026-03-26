package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Драйвер програми для керування списком одягу.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Clothes> wardrobe = new ArrayList<Clothes>();

        while (true) {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Додати новий одяг");
            System.out.println("2. Показати весь одяг та лічильник");
            System.out.println("3. Демонстрація конструктора копіювання");
            System.out.println("0. Вихід");
            System.out.print("Виберіть дію: ");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                try {
                    System.out.print("Введіть назву бренду: ");
                    String brand = sc.nextLine();
                    System.out.print("Введіть країну виробника: ");
                    String country = sc.nextLine();
                    Manufacturer manufacturer = new Manufacturer(brand, country);

                    System.out.print("Введіть назву одягу: ");
                    String name = sc.nextLine();
                    System.out.print("Введіть розмір (число): ");
                    int size = Integer.parseInt(sc.nextLine());
                    System.out.print("Введіть ціну: ");
                    double price = Double.parseDouble(sc.nextLine());

                    System.out.println("Виберіть сезон (0-WINTER, 1-SPRING, 2-SUMMER, 3-AUTUMN): ");
                    int seasonIndex = Integer.parseInt(sc.nextLine());
                    Season season = Season.values()[seasonIndex];

                    Clothes item = new Clothes(name, size, price, season, manufacturer);
                    wardrobe.add(item);
                    System.out.println("Додано успішно!");

                } catch (Exception e) {
                    System.out.println("Помилка введення: " + e.getMessage());
                }

            } else if (choice.equals("2")) {
                System.out.println("\nВсього створено об'єктів (static count): " + Clothes.getNumberOfObjects());

                for (int i = 0; i < wardrobe.size(); i++) {
                    System.out.println(wardrobe.get(i).toString());
                }

            } else if (choice.equals("3")) {
                if (wardrobe.isEmpty()) {
                    System.out.println("Список порожній!");
                } else {
                    Clothes original = wardrobe.get(0);
                    Clothes copy = new Clothes(original);
                    System.out.println("Оригінал: " + original);
                    System.out.println("Копія:    " + copy);
                    System.out.println("Лічильник після копіювання: " + Clothes.getNumberOfObjects());
                }

            } else if (choice.equals("0")) {
                break;
            }
        }
    }
}