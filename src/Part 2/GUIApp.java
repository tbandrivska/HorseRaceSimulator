package Part2;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUIApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Welcome to the GUI Horse Race Simulator!");

        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Horse Race Simulator - GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
