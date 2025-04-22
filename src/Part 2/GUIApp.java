package Part2;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIApp extends Application {
    private final List<CustomHorse> horseList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        Label previewLabel = new Label("🐎");
        previewLabel.setStyle("-fx-font-size: 48px; -fx-text-fill: grey;");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter horse name");

        ComboBox<String> breedBox = new ComboBox<>();
        breedBox.getItems().addAll("Thoroughbred", "Arabian", "Quarter Horse", "Mustang");
        breedBox.setPromptText("Choose breed");

        ComboBox<String> coatBox = new ComboBox<>();
        coatBox.getItems().addAll("Black", "Brown", "White", "Grey", "Spotted");
        coatBox.setPromptText("Choose coat colour");

        ComboBox<String> saddleBox = new ComboBox<>();
        saddleBox.getItems().addAll("Standard", "Racing", "Decorative");
        saddleBox.setPromptText("Choose saddle type");

        ComboBox<String> horseshoeBox = new ComboBox<>();
        horseshoeBox.getItems().addAll("Regular", "Lightweight", "Spiked");
        horseshoeBox.setPromptText("Choose horseshoe type");

        ComboBox<String> betBox = new ComboBox<>();
        betBox.setPromptText("Place your bet (select horse name)");

        TextField symbolField = new TextField();
        symbolField.setPromptText("Symbol (e.g. ♞)");

        Button createButton = new Button("Create Horse");

        Label outputLabel = new Label();

        createButton.setOnAction(e ->
         {
            String saddle = saddleBox.getValue();
            String horseshoes = horseshoeBox.getValue();

            String name = nameField.getText();
            String breed = breedBox.getValue();
            String coat = coatBox.getValue();
            String symbolText = symbolField.getText();
            char symbol = (symbolText != null && !symbolText.isEmpty()) ? symbolText.charAt(0) : '?';


            double confidence = switch (breed) {

                case "Arabian" -> 0.8;
                case "Thoroughbred" -> 0.6;
                case "Quarter Horse" -> 0.5;
                case "Mustang" -> 0.4;
                default -> 0.5;
            };

            CustomHorse horse = new CustomHorse(name, breed, coat, symbol, saddle, horseshoes, confidence);
            horseList.add(horse);
            betBox.getItems().add(name);
            // Confidence logic based on saddle
            if ("Racing".equals(saddle)) {confidence += 0.1;};
            if ("Spiked".equals(horseshoes)) {confidence += 0.05;};
            if ("Decorative".equals(saddle)) {confidence -= 0.05;};
            outputLabel.setText(horse.toString());
        });
        Button createRaceButton = new Button("Start Race!");
        createRaceButton.setOnAction(e -> {
            if (horseList.isEmpty() || betBox.getValue() == null) {
                outputLabel.setText("Please create at least one horse and place your bet.");
                return;
            }

            // Random winner
            Random rand = new Random();
            CustomHorse winner = horseList.get(rand.nextInt(horseList.size()));

            // Summary
            String result = "🏁 The race is over!\nWinner: " + winner.getName();
            if (winner.getName().equals(betBox.getValue())) {
                result += "\n🎉 You WIN your bet!";
            } else {
                result += "\n😢 You lost the bet. Try again!";
            }

            outputLabel.setText(result);
        });

        VBox root = new VBox(10,
                new Label("Horse Customisation:"),
                previewLabel,
                nameField,
                breedBox,
                coatBox,
                saddleBox,
                horseshoeBox,
                symbolField,
                createButton,
                new Label("Race Options:"),
                betBox,
                outputLabel
        );

        Scene scene = new Scene(root, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Horse Customiser");
        primaryStage.show();
        symbolField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.isEmpty()) {
                previewLabel.setText(String.valueOf(newVal.charAt(0)));
            } else {
                previewLabel.setText("🐎"); // fallback
            }
        });
        nameField.textProperty().addListener((obs, oldVal, newVal) -> {
            previewLabel.setTooltip(new Tooltip(newVal));
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
