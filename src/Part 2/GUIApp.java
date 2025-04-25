package Part2;

import java.awt.*;
import java.util.*;
import java.util.List;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUIApp extends Application {
    private Label statsLabel = new Label("💸 Betting Stats will appear here...");
    private final List<Part2.CustomHorse> horseList = new ArrayList<>();
    private double wallet = 100.0;
    private final int MAX_HORSES = 6;
    private VBox raceTrack = new VBox(10);
    private ScrollPane raceTrackScrollPane = new ScrollPane();
    private final List<Label> horseLabels = new ArrayList<>();



    @Override
    public void start(Stage primaryStage) {
        Label previewLabel = new Label("🐎");
        raceTrack.getChildren().add(new Label("🏁 Race Track:"));


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
        //Textfields and buttons and labels

        TextField symbolField = new TextField();
        symbolField.setPromptText("Symbol (e.g. ♞)");
        TextField betAmountField = new TextField();
        betAmountField.setPromptText("Enter bet amount (£)");

        Button createButton = new Button("Create Horse");

        Label outputLabel = new Label();
        Label walletLabel = new Label("Wallet: £" + wallet);

        //creation of buttons
        createButton.setOnAction(e ->
        {
            String saddle = saddleBox.getValue();
            String horseshoes = horseshoeBox.getValue();

            String name = nameField.getText();
            String breed = breedBox.getValue();
            String coat = coatBox.getValue();
            String symbolText = symbolField.getText();
            char symbol = (symbolText != null && !symbolText.isEmpty()) ? symbolText.charAt(0) : '?';
            if (horseLabels.size() >= MAX_HORSES) {
                outputLabel.setText("⚠️ You can only have 6 horses per race.");
                return;
            }


            double confidence = switch (breed) {

                case "Arabian" -> 0.8;
                case "Thoroughbred" -> 0.6;
                case "Quarter Horse" -> 0.5;
                case "Mustang" -> 0.4;
                default -> 0.5;
            };

            Part2.CustomHorse horse = new Part2.CustomHorse(name, breed, coat, symbol, saddle, horseshoes, confidence);

            Label horseLabel = new Label(symbol + " " + name); // or get from CustomHorse
            horseLabel.setStyle("-fx-font-size: 28px;");
            horseLabels.add(horseLabel);
            raceTrack.getChildren().add(horseLabel);


            horseList.add(horse);
            betBox.getItems().add(name);
            // Confidence logic based on saddle
            if ("Racing".equals(saddle)) {
                confidence += 0.1;
            }
            ;
            if ("Spiked".equals(horseshoes)) {
                confidence += 0.05;
            }
            ;
            if ("Decorative".equals(saddle)) {
                confidence -= 0.05;
            }
            ;
            outputLabel.setText(horse.toString());
        });


        Button createRaceButton = new Button("Start Race!");
        createRaceButton.setOnAction(e -> {
            String selectedHorse = betBox.getValue();
            String betAmountText = betAmountField.getText();

            if (horseList.isEmpty() || selectedHorse == null || betAmountText.isEmpty()) {
                outputLabel.setText("Please create at least one horse, place your bet, and enter an amount.");
                return;
            }

            double bet;
            try {
                bet = Double.parseDouble(betAmountText);
            } catch (NumberFormatException ex) {
                outputLabel.setText("Please enter a valid number for your bet.");
                return;
            }

            if (bet > wallet) {
                outputLabel.setText("You don't have enough funds! Wallet: £" + wallet);
                return;
            }

            // Random winner
            Random rand = new Random();
            Part2.CustomHorse winner = horseList.get(rand.nextInt(horseList.size()));

            // Check result
            String result = "🏁 The race is over!\nWinner: " + winner.getName();
            if (winner.getName().equals(selectedHorse)) {
                wallet += bet; // win = double your money (simple logic)
                result += "\n🎉 You WIN £" + bet + "!";
            } else {
                wallet -= bet;
                result += "\n😢 You lost £" + bet + ".";
            }

            Map<Part2.CustomHorse, Double> progressMap = new HashMap<>();

            for (Part2.CustomHorse h : horseList) {
                double speedFactor = h.getSpeed(); // e.g., 1.0 = average, 1.2 = fast
                double staminaFactor = h.getStamina();
                double confidenceFactor = h.getConfidence();

                // Formula for performance
                double progress = speedFactor * 0.5 + staminaFactor * 0.3 + confidenceFactor * 0.2;

                progressMap.put(h, progress);
            }
            Part2.CustomHorse winnerHorse = Collections.max(progressMap.entrySet(), Map.Entry.comparingByValue()).getKey();



            walletLabel.setText("Wallet: £" + String.format("%.2f", wallet));

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
                raceTrack,
                statsLabel,
                new Label("Race Options:"),
                betBox,
                betAmountField,
                walletLabel,
                raceTrackScrollPane,
                createRaceButton,
                outputLabel
        );
        // Setting the scene
        ScrollPane fullScrollPane = new ScrollPane(root);
        fullScrollPane.setFitToWidth(true);
        Scene scene = new Scene(fullScrollPane, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Horse Customiser");
        primaryStage.show();

        //Window settings
        raceTrack = new VBox(10);
        raceTrackScrollPane.setContent(raceTrack);
        raceTrackScrollPane.setFitToWidth(true);
        raceTrackScrollPane.setPrefHeight(200);
        raceTrackScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        //Preview label

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
    public void startRaceGUI(Stage stage) {
        start(stage);
    }

    private void runRace(List<Label> horseLabels, int distance, Runnable onFinish) {
        Map<Label, Double> progressScores = new HashMap<>();

        // Score horses based on stats
        for (int i = 0; i < horseLabels.size(); i++) {
            Part2.CustomHorse horse = horseList.get(i);
            Label horseLabel = horseLabels.get(i);

            double base = (horse.getSpeed() * 0.5) + (horse.getStamina() * 0.3) + (horse.getConfidence() * 0.2);
            double noise = 0.95 + (Math.random() * 0.1);
            double progressScore = base * noise;

            progressScores.put(horseLabel, progressScore);
        }

        // Rank horses by performance
        List<Map.Entry<Label, Double>> ranked = new ArrayList<>(progressScores.entrySet());
        ranked.sort((a, b) -> Double.compare(b.getValue(), a.getValue())); // Descending

        double maxDuration = 5.0; // seconds

        // Animate each horse individually
        for (int i = 0; i < ranked.size(); i++) {
            Label horseLabel = ranked.get(i).getKey();
            double score = ranked.get(i).getValue();

            double durationSeconds = maxDuration * (ranked.get(ranked.size() - 1).getValue() / score);

            TranslateTransition move = new TranslateTransition(Duration.seconds(durationSeconds), horseLabel);
            move.setByX(distance);

            // When the fastest horse finishes, run onFinish
            if (i == 0) {
                move.setOnFinished(e -> onFinish.run());
            }

            move.play();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
