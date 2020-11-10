package server;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServerMain extends Application {
    private final int WIDTH = 580;
    private final int HEIGHT = 300;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        GridPane leftGrid = new GridPane();
        HBox inputHall = new HBox();
        HBox inputMovie = new HBox();
        VBox info = new VBox();

        TextField hallNumber = new TextField();
        TextField movieTitle = new TextField();
        Label hallInfo = new Label("Hall number: ");
        Label movieInfo = new Label("Movie title: ");
        Button addHallButton = new Button("Add hall");
        Button addMovieButton = new Button("Add movie");
        ListView<String> hallList = new ListView<>();
        hallList.getItems().addAll("Hall 1", "Hall 2", "Hall 3", "Hall 4");

        inputHall.setSpacing(10);
        inputHall.setAlignment(Pos.CENTER);
        inputHall.getChildren().addAll(hallInfo, hallNumber, addHallButton);

        inputMovie.setSpacing(10);
        inputMovie.setAlignment(Pos.CENTER);
        inputMovie.getChildren().addAll(movieInfo, movieTitle, addMovieButton);

        info.setAlignment(Pos.CENTER);
        info.getChildren().addAll(new Label("Info:"), new Label("Hall 1"), new Label("All seats: 50"), new Label("Available seats: 25"));

        leftGrid.setVgap(10);
        leftGrid.add(inputHall, 0, 0);
        leftGrid.add(inputMovie, 0, 1);
        leftGrid.add(info, 0, 2);


        root.setPadding(new Insets(10, 10, 10, 10));
        root.add(leftGrid, 0, 0);
        root.add(hallList, 1, 0);


        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
