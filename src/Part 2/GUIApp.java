package Part2;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Labels and dropdowns
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

        TextField symbolField = new TextField();
        symbolField.setPromptText("Symbol (e.g. ♞)");

        Button createButton = new Button("Create Horse");

        Label outputLabel = new Label();

        createButton.setOnAction(e -> {
            String name = nameField.getText();
            String breed = breedBox.getValue();
            String coat = coatBox.getValue();
            String symbolText = symbolField.getText();
            char symbol = (symbolText != null && !symbolText.isEmpty()) ? symbolText.charAt(0) : '?';

            // Very basic confidence boost logic
            double confidence = switch (breed) {
                case "Arabian" -> 0.8;
                case "Thoroughbred" -> 0.6;
                case "Quarter Horse" -> 0.5;
                case "Mustang" -> 0.4;
                default -> 0.5;
            };

            CustomHorse horse = new CustomHorse(name, breed, coat, symbol, "Standard", "Regular", confidence);
            outputLabel.setText(horse.toString());
        });

        VBox root = new VBox(10,
                new Label("Horse Customisation:"),
                nameField,
                breedBox,
                coatBox,
                saddleBox,
                horseshoeBox,
                symbolField,
                createButton,
                outputLabel
        );

        Scene scene = new Scene(root, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Horse Customiser");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
