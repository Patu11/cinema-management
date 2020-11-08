package server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ServerMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
