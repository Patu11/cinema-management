package server;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import root.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ServerMain extends Application {
    private final int WIDTH = 1640;
    private final int HEIGHT = 485;

    private MySQLAccess sql;
    private GridPane root;
    private GridPane leftGrid;
    private VBox buttonList;
    private HBox deleteList;
    private TableView hallTable;
    private TableColumn<Hall, String> hallCol1;
    private TableColumn<Hall, String> hallCol2;
    private TableColumn<Hall, String> hallCol3;
    private TableView movieTable;
    private TableColumn<Movie, String> movieCol1;
    private TableColumn<Movie, String> movieCol2;
    private TableColumn<Movie, String> movieCol3;
    private TableColumn<Movie, String> movieCol4;
    private TableColumn<Movie, String> movieCol5;
    private TableView clientTable;
    private TableColumn<Client, String> clientCol1;
    private TableColumn<Client, String> clientCol2;
    private TableColumn<Client, String> clientCol3;
    private TableView reservationTable;
    private TableColumn<Reservation, String> reservationCol1;
    private TableColumn<Reservation, String> reservationCol2;
    private TableColumn<Reservation, String> reservationCol3;
    private TableColumn<Reservation, String> reservationCol4;
    private TableColumn<Reservation, String> reservationCol5;
    private TableColumn<Reservation, String> reservationCol6;
    private TableColumn<Reservation, String> reservationCol7;
    private Label hallsLabel;
    private Label moviesLabel;
    private Label clientsLabel;
    private Label reservationsLabel;
    private Label optionsLabel;
    private Button addHallButton;
    private Button addMovieButton;
    private Button refreshButton;
    private Button deleteHall;
    private Button deleteMovie;
    private Server server;
    private Thread serverThread;
    private Reservation reservation;

    public ServerMain() throws SQLException, ClassNotFoundException, IOException {
        this.initComponents();
        this.initEvents();
        this.server = new Server(this);
        this.serverThread = new Thread(this.server);
        this.serverThread.start();
    }

    public void setReservation(Reservation reservation) throws SQLException {
        this.reservation = reservation;
//        System.out.println(this.reservation);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        hallTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        movieTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        clientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        reservationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        hallCol1.setCellValueFactory(new PropertyValueFactory<>("hallNumber"));
        hallCol2.setCellValueFactory(new PropertyValueFactory<>("seatsNumber"));
        hallCol3.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));
        this.hallTable.getColumns().addAll(hallCol1, hallCol2, hallCol3);
        List<Hall> halls = sql.getAllHalls();
        this.hallTable.getItems().addAll(halls);

        this.movieCol1.setCellValueFactory(new PropertyValueFactory<>("title"));
        this.movieCol2.setCellValueFactory(new PropertyValueFactory<>("ageCategory"));
        this.movieCol3.setCellValueFactory(new PropertyValueFactory<>("length"));
        this.movieCol4.setCellValueFactory(new PropertyValueFactory<>("genre"));
        this.movieCol5.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.movieTable.getColumns().addAll(movieCol1, movieCol2, movieCol3, movieCol4, movieCol5);
        List<Movie> movies = sql.getAllMovies();
        this.movieTable.getItems().addAll(movies);


        clientCol1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        clientCol2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        clientCol3.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        this.clientTable.getColumns().addAll(clientCol1, clientCol2, clientCol3);
        List<Client> clients = sql.getAllClients();
        clientTable.getItems().addAll(clients);

        reservationCol1.setCellValueFactory(new PropertyValueFactory<>("hallId"));
        reservationCol2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        reservationCol3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        reservationCol4.setCellValueFactory(new PropertyValueFactory<>("title"));
        reservationCol5.setCellValueFactory(new PropertyValueFactory<>("seats"));
        reservationCol6.setCellValueFactory(new PropertyValueFactory<>("date"));
        reservationCol7.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.reservationTable.getColumns().addAll(reservationCol1, reservationCol2, reservationCol3, reservationCol4, reservationCol5, reservationCol6, reservationCol7);
        List<Reservation> res = sql.getAllReservations();
        List<ReservationData> reservations = res.stream()
                .map(r -> new ReservationData(r.getCinemaHall().getHallNumber(), r.getClient().getFirstName(), r.getClient().getLastName(), r.getMovie().getTitle(), r.getSeatNumber(), r.getDate(), r.getPrice()))
                .collect(Collectors.toList());
        reservationTable.getItems().addAll(reservations);


        addHallButton.setFont(new Font("Arial", 15));
        addMovieButton.setFont(new Font("Arial", 15));
        refreshButton.setFont(new Font("Arial", 15));
        deleteHall.setFont(new Font("Arial", 15));
        deleteMovie.setFont(new Font("Arial", 15));

        this.deleteList.setSpacing(10);
        this.deleteList.getChildren().addAll(deleteHall, deleteMovie);

        buttonList.getChildren().addAll(addHallButton, addMovieButton, refreshButton);
        buttonList.setAlignment(Pos.CENTER);
        buttonList.setSpacing(10);
        leftGrid.setPadding(new Insets(20, 20, 20, 20));
        leftGrid.setVgap(10);
        leftGrid.add(buttonList, 0, 0);
        leftGrid.add(this.deleteList, 0, 1);

        root.setHalignment(hallsLabel, HPos.CENTER);
        root.setHalignment(moviesLabel, HPos.CENTER);
        root.setHalignment(clientsLabel, HPos.CENTER);
        root.setHalignment(reservationsLabel, HPos.CENTER);
        root.setHalignment(optionsLabel, HPos.CENTER);

        hallsLabel.setFont(new Font("Arial", 20));
        moviesLabel.setFont(new Font("Arial", 20));
        clientsLabel.setFont(new Font("Arial", 20));
        reservationsLabel.setFont(new Font("Arial", 20));
        optionsLabel.setFont(new Font("Arial", 20));

        root.setPadding(new Insets(10, 10, 10, 10));
        root.add(optionsLabel, 0, 0);
        root.add(leftGrid, 0, 1);
        root.add(hallsLabel, 1, 0);
        root.add(moviesLabel, 2, 0);
        root.add(clientsLabel, 3, 0);
        root.add(reservationsLabel, 4, 0);
        root.add(hallTable, 1, 1);
        root.add(movieTable, 2, 1);
        root.add(clientTable, 3, 1);
        root.add(reservationTable, 4, 1);

        primaryStage.setMinWidth(this.WIDTH);
        primaryStage.setMinHeight(this.HEIGHT);
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void initEvents() {

        this.addHallButton.setOnAction(actionEvent -> {
            try {
                new HallWindow(this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        this.addMovieButton.setOnAction(actionEvent -> {
            try {
                new MovieWindow(this);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        this.refreshButton.setOnAction(actionEvent -> {
            try {
                this.refreshTables();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        this.deleteHall.setOnAction(actionEvent -> {
            Hall h = (Hall) this.hallTable.getSelectionModel().getSelectedItem();
            if (h != null) {
                try {
                    this.sql.deleteHallByNumber(h.getHallNumber());
                    this.hallTable.getItems().remove(h);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        this.deleteMovie.setOnAction(actionEvent -> {
            Movie m = (Movie) this.movieTable.getSelectionModel().getSelectedItem();
            if (m != null) {
                try {
                    this.sql.deleteMovieByTitle(m.getTitle());
                    this.movieTable.getItems().remove(m);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

    private void initComponents() throws SQLException, ClassNotFoundException, IOException {
        this.sql = new MySQLAccess();
        this.root = new GridPane();
        this.leftGrid = new GridPane();
        this.buttonList = new VBox();
        this.deleteList = new HBox();
        this.hallTable = new TableView();
        this.hallCol1 = new TableColumn<>("Hall number");
        this.hallCol2 = new TableColumn<>("Seats");
        this.hallCol3 = new TableColumn<>("Available");
        this.movieTable = new TableView();
        this.movieCol1 = new TableColumn<>("Title");
        this.movieCol2 = new TableColumn<>("Age");
        this.movieCol3 = new TableColumn<>("Length");
        this.movieCol4 = new TableColumn<>("Genre");
        this.movieCol5 = new TableColumn<>("Price");
        this.clientTable = new TableView();
        this.clientCol1 = new TableColumn<>("First name");
        this.clientCol2 = new TableColumn<>("Last name");
        this.clientCol3 = new TableColumn<>("Birthdate");
        this.reservationTable = new TableView();
        this.reservationCol1 = new TableColumn<>("Hall number");
        this.reservationCol2 = new TableColumn<>("First name");
        this.reservationCol3 = new TableColumn<>("Last name");
        this.reservationCol4 = new TableColumn<>("Title");
        this.reservationCol5 = new TableColumn<>("Seats");
        this.reservationCol6 = new TableColumn<>("Date");
        this.reservationCol7 = new TableColumn<>("Price");
        this.hallsLabel = new Label("Halls");
        this.moviesLabel = new Label("Movies");
        this.clientsLabel = new Label("Clients");
        this.optionsLabel = new Label("Options");
        this.reservationsLabel = new Label("Reservations");
        this.addHallButton = new Button("Add hall");
        this.addMovieButton = new Button("Add movie");
        this.refreshButton = new Button("Refresh table");
        this.deleteHall = new Button("Delete hall");
        this.deleteMovie = new Button("Delete movie");
        this.reservation = null;
    }

    public void refreshTables() throws SQLException {
        this.hallTable.getItems().clear();
        this.movieTable.getItems().clear();
        this.clientTable.getItems().clear();
        this.reservationTable.getItems().clear();
        List<Hall> halls = sql.getAllHalls();
        this.hallTable.getItems().addAll(halls);
        List<Movie> movies = sql.getAllMovies();
        this.movieTable.getItems().addAll(movies);
        List<Client> clients = sql.getAllClients();
        clientTable.getItems().addAll(clients);
        List<Reservation> res = sql.getAllReservations();
        List<ReservationData> reservations = res.stream()
                .map(r -> new ReservationData(r.getCinemaHall().getHallNumber(), r.getClient().getFirstName(), r.getClient().getLastName(), r.getMovie().getTitle(), r.getSeatNumber(), r.getDate(), r.getPrice()))
                .collect(Collectors.toList());
        reservationTable.getItems().addAll(reservations);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
