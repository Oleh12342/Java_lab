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

        for (int i = 0; i < n; i++) {
            System.out.println("Введіть назву речі #" + (i + 1) + ":");
            String name = scanner.nextLine();
            System.out.println("Введіть розмір:");
            int size = scanner.nextInt();
            scanner.nextLine();

            clothesArray[i] = new Clothes(name, size);
        }

        System.out.println("\nВаш сформований список:");
        for (Clothes c : clothesArray) {
            System.out.println(c);
        }
    }
}