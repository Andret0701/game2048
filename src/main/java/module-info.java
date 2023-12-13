module game2048 {
    requires javafx.controls;
    requires javafx.fxml;

    opens game2048 to javafx.fxml;
    exports game2048;
}
