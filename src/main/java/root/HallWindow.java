package root;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import server.ServerMain;

import java.sql.SQLException;

public class HallWindow extends Stage {
    private ServerMain parent;
    private MySQLAccess sql;
    private GridPane grid;
    private TextField numberInput;
    private TextField seatsInput;
    private Label hallNumberLabel;
    private Label hallSeatsLabel;
    private Button addButton;

    public HallWindow(ServerMain parent) throws SQLException, ClassNotFoundException {
        this.parent = parent;
        this.initComponents();
        this.initStyling();
        this.initEvents();
    }

    private void initStyling() {
        this.numberInput.setPromptText("Hall number");
        this.seatsInput.setPromptText("Seats");
        this.grid.setHalignment(this.numberInput, HPos.CENTER);
        this.grid.setHalignment(this.seatsInput, HPos.CENTER);
        this.grid.setHalignment(this.hallNumberLabel, HPos.CENTER);
        this.grid.setHalignment(this.hallSeatsLabel, HPos.CENTER);
        this.grid.setHalignment(this.addButton, HPos.CENTER);
        this.grid.setVgap(10);
        this.grid.setPadding(new Insets(30, 30, 30, 30));
        this.grid.add(this.numberInput, 0, 0);
        this.grid.add(this.seatsInput, 0, 1);
        this.grid.add(this.hallNumberLabel, 0, 2);
        this.grid.add(this.hallSeatsLabel, 0, 3);
        this.grid.add(this.addButton, 0, 4);
        this.setTitle("Add");
        this.setScene(new Scene(grid));
        this.show();
    }

    private void initComponents() throws SQLException, ClassNotFoundException {
        this.sql = new MySQLAccess();
        this.grid = new GridPane();
        this.numberInput = new TextField();
        this.seatsInput = new TextField();
        this.hallNumberLabel = new Label("Number: ");
        this.hallSeatsLabel = new Label("Seats: ");
        this.addButton = new Button("Add");
    }

    private void initEvents() {
        this.numberInput.textProperty().addListener((obs, oldv, newv) -> {
            this.hallNumberLabel.setText("Number: " + newv);
        });

        this.seatsInput.textProperty().addListener((obs, oldv, newv) -> {
            this.hallSeatsLabel.setText("Seats: " + newv);
        });

        this.addButton.setOnAction(actionEvent -> {
            if (this.numberInput.getText().trim().isEmpty() || this.numberInput.getText() == null || this.seatsInput.getText().trim().isEmpty() || this.seatsInput.getText() == null) {
                System.out.println("EMPTY");
            } else {
                Hall h = new Hall();
                h.setHallNumber(Integer.parseInt(this.numberInput.getText()));
                h.setSeatsNumber(Integer.parseInt(this.seatsInput.getText()));
                h.setAvailableSeats(Integer.parseInt(this.seatsInput.getText()));
                try {
                    this.sql.insertHall(h);
                    this.parent.refreshTables();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                this.close();
            }
        });

        this.setOnCloseRequest(windowEvent -> {
            try {
                this.parent.refreshTables();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
