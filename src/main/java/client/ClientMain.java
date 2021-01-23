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
import root.Hall;
import root.Movie;
import root.MySQLAccess;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientMain extends Application {

    MySQLAccess sql;

    GridPane gridPane;
    GridPane right;
    HBox selecting;
    HBox clientInput;
    HBox buttons;
    HBox counting;
    VBox details;
    HBox status;
    Separator separator;
    Separator separator2;
    DatePicker datePicker;
    DatePicker birthDate;
    TextField nameInput;
    TextField surnameInput;
    ComboBox<Integer> seats;
    ComboBox<Integer> halls;
    Label movieInfo;
    Label counter;
    Label infoLabel;
    Label titleLabel;
    Label dateLabel;
    Label costLabel;
    Label seatsLabel;
    Label hallLabel;
    Label statusLabel;
    CheckBox checkBox;
    Button reserve;
    Button pay;
    Button cancel;
    ComboBox<String> moviesList;
    List<String> movieList;
    List<Integer> hallList;
    ObservableList<Integer> h;
    ObservableList<String> items;

    public ClientMain() throws SQLException, ClassNotFoundException {
        this.initComponents();
        this.initEvents();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        MySQLAccess sql = new MySQLAccess();

//        GridPane gridPane = new GridPane();
//        GridPane right = new GridPane();
//        HBox selecting = new HBox(5);
//        HBox clientInput = new HBox(3);
//        HBox buttons = new HBox(4);
//        HBox counting = new HBox(1);
//        VBox details = new VBox(3);
//        HBox status = new HBox(1);

//        Separator separator = new Separator(Orientation.HORIZONTAL);
//        Separator separator2 = new Separator(Orientation.HORIZONTAL);
//        DatePicker datePicker = new DatePicker();
//        DatePicker birthDate = new DatePicker();
//        TextField nameInput = new TextField();
//        TextField surnameInput = new TextField();
//        ComboBox<Integer> seats = new ComboBox<>();
//        ComboBox<Integer> halls = new ComboBox<>();


//        Label movieInfo = new Label("Movie: ");
//        Label counter = new Label("5:00");
//        Label infoLabel = new Label("Your reservation:");
//        Label titleLabel = new Label("Title:");
//        Label dateLabel = new Label("Date:");
//        Label costLabel = new Label("Cost:");
//        Label seatsLabel = new Label("Seats:");
//        Label hallLabel = new Label("Hall:");
//        Label statusLabel = new Label("STATUS: Waiting for reservation");
//        CheckBox checkBox = new CheckBox("Discount");
//        Button reserve = new Button("Reserve");
//        Button pay = new Button("Pay");
//        Button cancel = new Button("Cancel");
//        ComboBox<String> moviesList = new ComboBox<>();
//        List<String> movieList = sql.getAllMovies().stream().map(Movie::getTitle).collect(Collectors.toList());
//        List<Integer> hallList = sql.getAllHalls().stream().map(Hall::getHallNumber).collect(Collectors.toList());

//        ObservableList<Integer> h = FXCollections.observableArrayList(hallList);
//        ObservableList<String> items = FXCollections.observableArrayList(movieList);

//        nameInput.textProperty().addListener((obs, oldText, newText) -> {
//            titleLabel.setText("Title: " + newText);
//        });
//
//        surnameInput.textProperty().addListener((obs, oldText, newText) -> {
//            costLabel.setText("Title: " + newText);
//        });

//        datePicker.valueProperty().addListener((ov, oldv, newv) -> {
//            dateLabel.setText("Date: " + newv);
//        });


        moviesList.setItems(items);

        seats.getItems().addAll(1, 2, 3, 4);
        seats.setPromptText("Seats");

//        seats.setOnAction(e -> {
//            seatsLabel.setText("Seats: " + seats.getSelectionModel().getSelectedItem());
//        });

        halls.setItems(h);
        halls.setPromptText("Hall");

//        halls.setOnAction(e -> {
//            hallLabel.setText("Hall: " + halls.getSelectionModel().getSelectedItem());
//        });

        counter.setFont(new Font("Arial", 50));

        datePicker.setValue(LocalDate.now());
        birthDate.setValue(LocalDate.now());

        nameInput.setPromptText("First Name");
        surnameInput.setPromptText("Last Name");

        moviesList.getSelectionModel().selectFirst();

//        moviesList.setOnAction(e -> {
//            titleLabel.setText("Title: " + moviesList.getSelectionModel().getSelectedItem());
//            try {
//                Movie m = sql.getMovieByTitle(moviesList.getSelectionModel().getSelectedItem());
//                Integer s = seats.getSelectionModel().getSelectedItem();
//                if (s == null) {
//
//                } else {
//                    costLabel.setText("Price: " + m.getPrice() * s);
//                }
//
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        });

        infoLabel.setStyle("-fx-font-weight: bold;");

        clientInput.setPadding(new Insets(10));
        clientInput.getChildren().addAll(birthDate, nameInput, surnameInput);
        clientInput.setAlignment(Pos.CENTER);

        selecting.setPadding(new Insets(10));
        selecting.getChildren().addAll(datePicker, movieInfo, moviesList, seats, halls);
        selecting.setAlignment(Pos.CENTER);

        buttons.setPadding(new Insets(10));
        buttons.getChildren().addAll(reserve, cancel, pay, checkBox);
        buttons.setAlignment(Pos.CENTER);

        counting.setPadding(new Insets(10));
        counting.getChildren().add(counter);
        counting.setAlignment(Pos.CENTER);

        details.setPadding(new Insets(10));
        details.getChildren().addAll(infoLabel, titleLabel, dateLabel, costLabel, seatsLabel, hallLabel);
        details.setAlignment(Pos.CENTER);

        status.setPadding(new Insets(10));
        status.getChildren().add(statusLabel);
        status.setAlignment(Pos.CENTER);

        right.setHgap(5);
        right.add(clientInput, 0, 0);
        right.add(selecting, 0, 1);
        right.add(details, 0, 2);
        right.add(buttons, 0, 3);
        right.add(separator, 0, 4);
        right.add(counting, 0, 5);
        right.add(separator2, 0, 6);
        right.add(status, 0, 7);

        primaryStage.setMinWidth(540);
        primaryStage.setMinHeight(455);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.add(right, 0, 0);

        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }

    public void initEvents() {
        this.datePicker.valueProperty().addListener((ov, oldv, newv) -> {
            this.dateLabel.setText("Date: " + newv);
        });

        this.seats.setOnAction(e -> {
            this.seatsLabel.setText("Seats: " + this.seats.getSelectionModel().getSelectedItem());
        });

        this.halls.setOnAction(e -> {
            this.hallLabel.setText("Hall: " + this.halls.getSelectionModel().getSelectedItem());
        });

        this.moviesList.setOnAction(e -> {
            this.titleLabel.setText("Title: " + this.moviesList.getSelectionModel().getSelectedItem());
            try {
                Movie m = this.sql.getMovieByTitle(this.moviesList.getSelectionModel().getSelectedItem());
                Integer s = this.seats.getSelectionModel().getSelectedItem();
                if (s == null) {

                } else {
                    this.costLabel.setText("Price: " + m.getPrice() * s);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public void initComponents() throws SQLException, ClassNotFoundException {
        this.sql = new MySQLAccess();
        this.gridPane = new GridPane();
        this.right = new GridPane();
        this.selecting = new HBox(5);
        this.clientInput = new HBox(3);
        this.buttons = new HBox(4);
        this.counting = new HBox(1);
        this.details = new VBox(3);
        this.status = new HBox(1);
        this.separator = new Separator(Orientation.HORIZONTAL);
        this.separator2 = new Separator(Orientation.HORIZONTAL);
        this.datePicker = new DatePicker();
        this.birthDate = new DatePicker();
        this.nameInput = new TextField();
        this.surnameInput = new TextField();
        this.seats = new ComboBox<>();
        this.halls = new ComboBox<>();
        this.movieInfo = new Label("Movie: ");
        this.counter = new Label("5:00");
        this.infoLabel = new Label("Your reservation:");
        this.titleLabel = new Label("Title:");
        this.dateLabel = new Label("Date:");
        this.costLabel = new Label("Cost:");
        this.seatsLabel = new Label("Seats:");
        this.hallLabel = new Label("Hall:");
        this.statusLabel = new Label("STATUS: Waiting for reservation");
        this.checkBox = new CheckBox("Discount");
        this.reserve = new Button("Reserve");
        this.pay = new Button("Pay");
        this.cancel = new Button("Cancel");
        this.moviesList = new ComboBox<>();
        this.movieList = sql.getAllMovies().stream().map(Movie::getTitle).collect(Collectors.toList());
        this.hallList = sql.getAllHalls().stream().map(Hall::getHallNumber).collect(Collectors.toList());
        this.h = FXCollections.observableArrayList(hallList);
        this.items = FXCollections.observableArrayList(movieList);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
