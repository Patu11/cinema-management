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

public class MovieWindow extends Stage {
    private ServerMain parent;
    private MySQLAccess sql;
    private GridPane grid;
    private TextField titleInput;
    private TextField ageInput;
    private TextField lengthInput;
    private TextField genreInput;
    private TextField priceInput;
    private Label titleLabel;
    private Label ageLabel;
    private Label lengthLabel;
    private Label genreLabel;
    private Label priceLabel;
    private Button addButton;

    public MovieWindow(ServerMain parent) throws SQLException, ClassNotFoundException {
        this.parent = parent;
        this.initComponents();
        this.initStyling();
        this.initEvents();
    }

    private void initStyling() {
        this.titleInput.setPromptText("Title");
        this.ageInput.setPromptText("Age category");
        this.lengthInput.setPromptText("Length");
        this.genreInput.setPromptText("Genre");
        this.priceInput.setPromptText("Price");
        this.grid.setHalignment(this.titleInput, HPos.CENTER);
        this.grid.setHalignment(this.ageInput, HPos.CENTER);
        this.grid.setHalignment(this.lengthInput, HPos.CENTER);
        this.grid.setHalignment(this.genreInput, HPos.CENTER);
        this.grid.setHalignment(this.priceInput, HPos.CENTER);

        this.grid.setHalignment(this.titleLabel, HPos.CENTER);
        this.grid.setHalignment(this.ageLabel, HPos.CENTER);
        this.grid.setHalignment(this.lengthLabel, HPos.CENTER);
        this.grid.setHalignment(this.genreLabel, HPos.CENTER);
        this.grid.setHalignment(this.priceLabel, HPos.CENTER);
        this.grid.setHalignment(this.addButton, HPos.CENTER);
        this.grid.setVgap(10);
        this.grid.setPadding(new Insets(30, 30, 30, 30));
        this.grid.add(this.titleInput, 0, 0);
        this.grid.add(this.ageInput, 0, 1);
        this.grid.add(this.lengthInput, 0, 2);
        this.grid.add(this.genreInput, 0, 3);
        this.grid.add(this.priceInput, 0, 4);
        this.grid.add(this.titleLabel, 0, 5);
        this.grid.add(this.ageLabel, 0, 6);
        this.grid.add(this.lengthLabel, 0, 7);
        this.grid.add(this.genreLabel, 0, 8);
        this.grid.add(this.priceLabel, 0, 9);
        this.grid.add(this.addButton, 0, 10);
        this.setTitle("Add");
        this.setScene(new Scene(grid));
        this.show();
    }

    private void initComponents() throws SQLException, ClassNotFoundException {
        this.sql = new MySQLAccess();
        this.grid = new GridPane();
        this.titleInput = new TextField();
        this.ageInput = new TextField();
        this.lengthInput = new TextField();
        this.genreInput = new TextField();
        this.priceInput = new TextField();
        this.titleLabel = new Label("Title: ");
        this.ageLabel = new Label("Age: ");
        this.lengthLabel = new Label("Length: ");
        this.genreLabel = new Label("Genre: ");
        this.priceLabel = new Label("Price: ");
        this.addButton = new Button("Add");
    }

    private void initEvents() {
        this.titleInput.textProperty().addListener((obs, oldv, newv) -> {
            this.titleLabel.setText("Title: " + newv);
        });

        this.ageInput.textProperty().addListener((obs, oldv, newv) -> {
            this.ageLabel.setText("Age: " + newv);
        });

        this.lengthInput.textProperty().addListener((obs, oldv, newv) -> {
            this.lengthLabel.setText("Length: " + newv);
        });

        this.genreInput.textProperty().addListener((obs, oldv, newv) -> {
            this.genreLabel.setText("Genre: " + newv);
        });

        this.priceInput.textProperty().addListener((obs, oldv, newv) -> {
            this.priceLabel.setText("Price: " + newv);
        });

        this.addButton.setOnAction(actionEvent -> {
            if (this.titleInput.getText().trim().isEmpty() || this.titleInput.getText() == null ||
                    this.ageInput.getText().trim().isEmpty() || this.ageInput.getText() == null ||
                    this.lengthInput.getText().trim().isEmpty() || this.lengthInput.getText() == null ||
                    this.genreInput.getText().trim().isEmpty() || this.genreInput.getText() == null ||
                    this.priceInput.getText().trim().isEmpty() || this.priceInput.getText() == null) {
                System.out.println("EMPTY");
            } else {
                Movie m = new Movie();
                m.setTitle(this.titleInput.getText());
                m.setAgeCategory(Integer.parseInt(this.ageInput.getText()));
                m.setLength(Integer.parseInt(this.lengthInput.getText()));
                m.setGenre(this.genreInput.getText());
                m.setPrice(Double.parseDouble(this.priceInput.getText()));
                try {
                    this.sql.insertMovie(m);
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
