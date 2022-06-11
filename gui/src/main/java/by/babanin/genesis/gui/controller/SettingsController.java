package by.babanin.genesis.gui.controller;

import static com.almasb.fxgl.dsl.FXGL.getGameController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/assets/views/settings.fxml")
public class SettingsController implements Initializable {

    public BorderPane settingsPane;

    public void generateWorld(ActionEvent actionEvent) {
        getGameController().startNewGame();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settingsPane.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                generateWorld(null);
            }
        });
    }
}
