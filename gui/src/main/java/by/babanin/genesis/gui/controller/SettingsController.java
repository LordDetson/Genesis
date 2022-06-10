package by.babanin.genesis.gui.controller;

import static com.almasb.fxgl.dsl.FXGL.getGameController;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/assets/views/settings.fxml")
public class SettingsController {

    public void generateWorld(ActionEvent actionEvent) {
        getGameController().startNewGame();
    }
}
