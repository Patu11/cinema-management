package client;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import root.*;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ClientMain extends Application {
    private final int WIDTH = 540;
    private final int HEIGHT = 455;

    private MySQLAccess sql;
    private GridPane gridPane;
    private GridPane right;
    private HBox selecting;
    private HBox clientInput;
    private HBox buttons;
    private HBox counting;
    private VBox details;
    private HBox status;
    private Separator separator;
    private Separator separator2;
    private DatePicker datePicker;
    private DatePicker birthDate;
    private TextField nameInput;
    private TextField surnameInput;
    private ComboBox<Integer> seats;
    private ComboBox<Integer> halls;
    private Label movieInfo;
    private Label counterLabel;
    private Label infoLabel;
    private Label titleLabel;
    private Label dateLabel;
    private Label costLabel;
    private Label seatsLabel;
    private Label hallLabel;
    private Label statusLabel;
    private Button reserve;
    private Button pay;
    private ComboBox<String> moviesList;
    private List<String> movieList;
    private List<Integer> hallList;
    private ObservableList<Integer> h;
    private ObservableList<String> items;
    private int counter;
    private boolean counterStarted;
    private Client client;
    private Reservation reservation;
    private ReservationSendingThread connection;

    public ClientMain() throws SQLException, ClassNotFoundException, IOException {
        this.initComponents();
        this.initEvents();
    }

    @Override
    public void start(Stage primaryStage) {


        moviesList.setItems(items);

        seats.getItems().addAll(1, 2, 3, 4);
        seats.setPromptText("Seats");

        halls.setItems(h);
        halls.setPromptText("Hall");

        counterLabel.setFont(new Font("Arial", 50));

        datePicker.setValue(LocalDate.now());
        birthDate.setValue(LocalDate.now());

        nameInput.setPromptText("First Name");
        surnameInput.setPromptText("Last Name");

        moviesList.getSelectionModel().selectFirst();

        infoLabel.setStyle("-fx-font-weight: bold;");

        clientInput.setPadding(new Insets(10));
        clientInput.getChildren().addAll(birthDate, nameInput, surnameInput);
        clientInput.setAlignment(Pos.CENTER);

        selecting.setPadding(new Insets(10));
        selecting.getChildren().addAll(datePicker, movieInfo, moviesList, seats, halls);
        selecting.setAlignment(Pos.CENTER);

        buttons.setPadding(new Insets(10));
        buttons.getChildren().addAll(reserve, pay);
        buttons.setAlignment(Pos.CENTER);

        counting.setPadding(new Insets(10));
        counting.getChildren().add(counterLabel);
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

        primaryStage.setMinWidth(this.WIDTH);
        primaryStage.setMinHeight(this.HEIGHT);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.add(right, 0, 0);

        primaryStage.setTitle("Client");
        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }

    public void initEvents() {

        this.reserve.setOnAction(actionEvent -> {
            this.counterStarted = true;
            this.statusLabel.setText("STATUS: Waiting for payment");

            if (this.nameInput.getText() == null || this.surnameInput.getText() == null || this.seats.getValue() == null) {
                new AlertWindow(Alert.AlertType.ERROR, "Error", "No data provided", "Fill all input fields");
            } else {


                Hall temp = null;
                Movie mtemp = null;
                this.reservation.setClient(this.client);
                try {
                    temp = sql.getHallByNumber(this.halls.getSelectionModel().getSelectedItem());
                    mtemp = sql.getMovieByTitle(this.moviesList.getSelectionModel().getSelectedItem());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (NullPointerException nullError) {
                    new AlertWindow(Alert.AlertType.ERROR, "Error", "SQL error", "Cannot connect to database");
                }

                this.client.setFirstName(this.nameInput.getText());
                this.client.setLastName(this.surnameInput.getText());
                this.client.setBirthDate(Date.valueOf(this.birthDate.getValue()));


                this.reservation.setCinemaHall(temp);
                this.reservation.setMovie(mtemp);
                this.reservation.setDate(Date.valueOf(this.datePicker.getValue()));
                this.reservation.setSeatNumber(this.seats.getValue());
                this.reservation.setPrice(mtemp.getPrice() * this.seats.getValue());

                this.connection.start();
                this.connection.send(this.reservation);

                new AlertWindow(Alert.AlertType.CONFIRMATION, "Confirm", "Succesfull", "Reservation saved, now You can pay");
            }
        });

        this.pay.setOnAction(actionEvent -> {
            this.counterStarted = false;
            this.counter = 60 * 2;
            this.statusLabel.setText("STATUS: Reservation made");
            new AlertWindow(Alert.AlertType.CONFIRMATION, "Confirm", "Succesfull", "Reservation made");
        });

        this.datePicker.valueProperty().addListener((ov, oldv, newv) -> {
            this.dateLabel.setText("Date: " + newv);
        });

        this.halls.setOnAction(e -> {
            this.hallLabel.setText("Hall: " + this.halls.getSelectionModel().getSelectedItem());
        });

        this.seats.setOnAction(e -> {
            this.seatsLabel.setText("Seats: " + this.seats.getSelectionModel().getSelectedItem());
            try {
                Movie m = this.sql.getMovieByTitle(this.moviesList.getSelectionModel().getSelectedItem());
                Integer s = this.seats.getSelectionModel().getSelectedItem();
                if (s == null) {

                } else {
                    DecimalFormat df = new DecimalFormat("###.##");
                    this.costLabel.setText("Price: " + df.format(m.getPrice() * s));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        this.moviesList.setOnAction(e -> {
            this.titleLabel.setText("Title: " + this.moviesList.getSelectionModel().getSelectedItem());
            try {
                Movie m = this.sql.getMovieByTitle(this.moviesList.getSelectionModel().getSelectedItem());
                Integer s = this.seats.getSelectionModel().getSelectedItem();
                if (s == null) {

                } else {
                    DecimalFormat df = new DecimalFormat("###.##");
                    this.costLabel.setText("Price: " + df.format(m.getPrice() * s));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.0), e -> {
                    if (this.counterStarted) {
                        this.counterLabel.setText("Time to pay: " + this.counter + "s");
                        this.counter--;
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void initComponents() throws SQLException, ClassNotFoundException, IOException {
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
        this.counterLabel = new Label("Time to pay: 120s");
        this.infoLabel = new Label("Your reservation:");
        this.titleLabel = new Label("Title:");
        this.dateLabel = new Label("Date:");
        this.costLabel = new Label("Cost:");
        this.seatsLabel = new Label("Seats:");
        this.hallLabel = new Label("Hall:");
        this.statusLabel = new Label("STATUS: Waiting for reservation");
        this.reserve = new Button("Reserve");
        this.pay = new Button("Pay");
        this.moviesList = new ComboBox<>();
        this.movieList = sql.getAllMovies().stream().map(Movie::getTitle).collect(Collectors.toList());
        this.hallList = sql.getAllHalls().stream().map(Hall::getHallNumber).collect(Collectors.toList());
        this.h = FXCollections.observableArrayList(hallList);
        this.items = FXCollections.observableArrayList(movieList);
        this.counter = 60 * 2;
        this.counterStarted = false;
        this.client = new Client();
        this.reservation = new Reservation();
        try {
            this.connection = new ReservationSendingThread();
        } catch (ConnectException e) {
            new AlertWindow(Alert.AlertType.ERROR, "Error", "Connection error", "Server is turned off");
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
