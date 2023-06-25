package org.example;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.convert.Convert;
import org.example.convert.FromAPI;
import org.example.convert.FromDatabase;
import org.example.helper.FileManagement;
import org.example.model.Currency;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public Button convert;
    @FXML
    public Label state;
    @FXML
    public RadioButton api;
    @FXML
    public RadioButton database;
    @FXML
    private TextField amount;

    @FXML
    private ComboBox<Currency> from;

    @FXML
    private TextField resultTextField;

    @FXML
    private TextField rate;

    @FXML
    private ComboBox<Currency> to;
    private Double[] result;

    @FXML
    void convert() {


        Thread t = new Thread(() -> {
            try {
                Platform.runLater(() -> {

                    convert.setDisable(true);
                    state.setText("Please wait for response.( =");
                });

                Convert convert;

                if (api.isSelected())
                    convert = new FromAPI();
                else
                    convert = new FromDatabase();

                result = convert.convert(from.getValue(), to.getValue(), Double.parseDouble(amount.getText()));

                Platform.runLater(() -> {
                    rate.setText(String.format("%.2f", result[Convert.RATE]));
                    resultTextField.setText(String.format("%.2f", result[Convert.RESULT]));
                });

            } catch (Exception e) {
                Platform.runLater(() -> {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setContentText(e.getMessage());
                    error.show();
                });
            } finally {
                Platform.runLater(() -> {
                    state.setText("");
                    convert.setDisable(false);
                });

            }
        });
        t.start();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Currency> list = FileManagement.readCurrency("currencyInput.txt");
        from.setItems(list);
        to.setItems(list);

        ToggleGroup group = new ToggleGroup();
        api.setToggleGroup(group);
        database.setToggleGroup(group);
    }
}
