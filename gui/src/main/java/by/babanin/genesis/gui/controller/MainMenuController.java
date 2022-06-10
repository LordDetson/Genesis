package by.babanin.genesis.gui.controller;

import static com.almasb.fxgl.dsl.FXGL.getDialogService;
import static com.almasb.fxgl.dsl.FXGL.getGameController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/assets/views/main_menu.fxml")
public class MainMenuController implements Initializable {

    private final FxControllerAndView<SettingsController, BorderPane> settingsCtrlAndView;
    public BorderPane mainPane;
    public ToggleGroup menuToggleGroup;
    public ToggleButton showSettingsButton;
    public ToggleButton showOptionsButton;

    public MainMenuController(FxControllerAndView<SettingsController, BorderPane> settingsCtrlAndView) {
        this.settingsCtrlAndView = settingsCtrlAndView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settingsCtrlAndView.getView().ifPresent(centerPane -> mainPane.setCenter(centerPane));
    }

    public void showPane(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        Node centerPane = null;
        if(source == showSettingsButton) {
            centerPane = settingsCtrlAndView.getView().get();
        }
        mainPane.setCenter(centerPane);
    }

    public void quit(ActionEvent actionEvent) {
        getDialogService().showConfirmationBox("Are you sure you want to quit?", confirm -> {
            if(confirm) {
                getGameController().exit();
            }
        });
    }
}
