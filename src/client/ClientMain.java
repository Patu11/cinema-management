package client;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ClientMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        GridPane right = new GridPane();
        HBox selecting = new HBox(3);
        HBox buttons = new HBox(4);

        DatePicker datePicker = new DatePicker();
        Label movieInfo = new Label("Select movie: ");
        CheckBox checkBox = new CheckBox("Discount");
        Button reserve = new Button("Reserve");
        Button pay = new Button("Pay");
        Button cancel = new Button("Cancel");
        ComboBox<String> moviesList = new ComboBox<>();
        ObservableList<String> items = FXCollections.observableArrayList("Movie 1", "Movie 2", "Movie 3", "Movie 4");
        moviesList.setItems(items);

        selecting.setPadding(new Insets(10));
        selecting.getChildren().addAll(movieInfo, moviesList, datePicker);

        buttons.setPadding(new Insets(10));
        buttons.getChildren().addAll(reserve, cancel, pay, checkBox);
        buttons.setAlignment(Pos.CENTER);

        right.setHgap(5);


        right.add(selecting, 0, 0);
        right.add(buttons, 0, 1);


        int width = 700;
        int height = 300;
        int rows = 10;
        int columns = 10;
        int size = 30;

        Group group = new Group();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Rectangle rect = new Rectangle(j * size, i * size, size, size);

                rect.setStyle("-fx-fill: red; -fx-stroke: black; -fx-stroke-width: 5;");
                group.getChildren().add(rect);
            }
        }


        gridPane.setMinSize(width, height);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.add(group, 0, 0);
        gridPane.add(right, 1, 0);

        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
