package root;

import javafx.scene.control.Alert;

public class AlertWindow extends Alert {

    public AlertWindow(AlertType alertType, String title, String header, String content) {
        super(alertType);
        this.setTitle(title);
        this.setHeaderText(header);
        this.setContentText(content);
        this.showAndWait().ifPresent(rs -> {
        });
    }
}
