package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість елементів одягу: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        Clothes[] clothesArray = new Clothes[n];

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