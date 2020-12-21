package client;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ClientMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        GridPane right = new GridPane();
        GridPane hallGrid = new GridPane();
        HBox selecting = new HBox(3);
        HBox buttons = new HBox(4);
        HBox counting = new HBox(1);
        VBox details = new VBox(3);
        HBox status = new HBox(1);

        Separator separator = new Separator(Orientation.HORIZONTAL);
        Separator separator2 = new Separator(Orientation.HORIZONTAL);
        DatePicker datePicker = new DatePicker();
        Label movieInfo = new Label("Movie: ");
        Label counter = new Label("5:00");
        Label infoLabel = new Label("Your reservation:");
        Label titleLabel = new Label("Title: Movie 1");
        Label dateLabel = new Label("Date: 10.12.2020");
        Label costLabel = new Label("Cost: 20zł");
        Label statusLabel = new Label("STATUS: Waiting for reservation");
        CheckBox checkBox = new CheckBox("Discount");
        Button reserve = new Button("Reserve");
        Button pay = new Button("Pay");
        Button cancel = new Button("Cancel");
        ComboBox<String> moviesList = new ComboBox<>();
        ObservableList<String> items = FXCollections.observableArrayList("Movie 1", "Movie 2", "Movie 3", "Movie 4");

        moviesList.setItems(items);

        counter.setFont(new Font("Arial", 50));

        datePicker.setValue(LocalDate.now());
        moviesList.getSelectionModel().selectFirst();

        infoLabel.setStyle("-fx-font-weight: bold;");

        selecting.setPadding(new Insets(10));
        selecting.getChildren().addAll(datePicker, movieInfo, moviesList);
        selecting.setAlignment(Pos.CENTER);

        buttons.setPadding(new Insets(10));
        buttons.getChildren().addAll(reserve, cancel, pay, checkBox);
        buttons.setAlignment(Pos.CENTER);

        counting.setPadding(new Insets(10));
        counting.getChildren().add(counter);
        counting.setAlignment(Pos.CENTER);

        details.setPadding(new Insets(10));
        details.getChildren().addAll(infoLabel, titleLabel, dateLabel, costLabel);
        details.setAlignment(Pos.CENTER);

        status.setPadding(new Insets(10));
        status.getChildren().add(statusLabel);
        status.setAlignment(Pos.CENTER);

        right.setHgap(5);
        right.add(selecting, 0, 0);
        right.add(details, 0, 1);
        right.add(buttons, 0, 2);
        right.add(separator, 0, 3);
        right.add(counting, 0, 4);
        right.add(separator2, 0, 5);
        right.add(status, 0, 6);


        int width = 700;
        int height = 300;
        int rows = 10;
        int columns = 10;
        int size = 30;

        Group group = new Group();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Rectangle rect = new Rectangle(j * size, i * size, size, size);

                rect.setStyle("-fx-fill: red; -fx-stroke: black; -fx-stroke-width: 1;");
                group.getChildren().add(rect);
            }
        }

        StackPane stp = new StackPane();
        Rectangle temp = new Rectangle(0, 0, 300, 50);
        Text screen = new Text("Screen");
        screen.setFont(new Font("Arial", 20));
        screen.setFill(Color.WHITE);
        stp.getChildren().addAll(temp, screen);

        hallGrid.add(group, 0, 0);
        hallGrid.add(stp, 0, 1);
        hallGrid.setAlignment(Pos.CENTER);

        gridPane.setMinSize(width, height);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.add(hallGrid, 0, 0);
//        gridPane.add(group, 0, 0);
        gridPane.add(right, 1, 0);

        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
