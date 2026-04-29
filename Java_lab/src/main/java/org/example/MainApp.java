package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.UUID;

public class MainApp extends Application {
    private Store myStore = new Store("GUI Store");
    private ListView<String> listView = new ListView<>();
    private TextArea resultArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Clothes Manager - UUID System");

        TextField nameField = new TextField();
        nameField.setPromptText("Назва");
        TextField priceField = new TextField();
        priceField.setPromptText("Ціна");

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Штани", "Сорочка", "Джинси", "Футболка");
        typeBox.setValue("Штани");
        typeBox.setMaxWidth(Double.MAX_VALUE);

        Button addButton = new Button("Додати товар");
        addButton.setMaxWidth(Double.MAX_VALUE);

        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                String selectedType = typeBox.getValue();

                Clothes item = switch (selectedType) {
                    case "Штани" -> new Pants(name, 42, price, "Cotton", 100, 10);
                    case "Сорочка" -> new Shirts(name, 40, price, "Silk", "Long", 5);
                    case "Джинси" -> new Jeans(name, 32, price, "Denim", 34, "Straight", 8);
                    case "Футболка" -> new TShirt(name, 44, price, "Cotton", "Short", true, 12);
                    default -> null;
                };

                if (item != null) {
                    myStore.getInventory().add(item);
                    updateList();
                    nameField.clear();
                    priceField.clear();
                }
            } catch (Exception ex) {
                resultArea.setText("Помилка: Перевірте назву та ціну!");
            }
        });

        TextField searchField = new TextField();
        searchField.setPromptText("Вставте UUID сюди");
        Button searchButton = new Button("Знайти за UUID");
        searchButton.setMaxWidth(Double.MAX_VALUE);

        searchButton.setOnAction(e -> {
            try {
                UUID searchId = UUID.fromString(searchField.getText().trim());
                Clothes found = null;

                for (Clothes c : myStore.getInventory()) {
                    if (c.getUuid().equals(searchId)) {
                        found = c;
                        break;
                    }
                }

                if (found != null) {
                    resultArea.setText("Знайдено повну інформацію:\n" + found.toString());
                } else {
                    resultArea.setText("Об'єкт не знайдено.");
                }
            } catch (IllegalArgumentException ex) {
                resultArea.setText("Помилка: Невірний формат UUID!");
            }
        });

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(
                new Label("Тип товару:"), typeBox,
                new Label("Дані:"), nameField, priceField, addButton,
                new Label("Список товарів (Назва | UUID):"), listView,
                new Label("Пошук:"), searchField, searchButton, resultArea
        );

        Scene scene = new Scene(root, 550, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateList() {
        listView.getItems().clear();
        for (Clothes c : myStore.getInventory()) {
            listView.getItems().add(c.getName() + " | " + c.getUuid().toString());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
