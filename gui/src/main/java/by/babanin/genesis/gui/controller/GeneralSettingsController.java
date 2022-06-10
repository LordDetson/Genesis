package by.babanin.genesis.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import by.babanin.genesis.gui.dto.GeneralSettings;
import by.babanin.genesis.gui.formatter.IntegerFormatter;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/assets/views/general_settings.fxml")
public class GeneralSettingsController implements Initializable {

    public TextField rowCountField;
    public TextField columnCountField;

    private final GeneralSettings generalSettings;

    public GeneralSettingsController(GeneralSettings generalSettings) {
        this.generalSettings = generalSettings;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rowCountField.setTextFormatter(new IntegerFormatter());
        columnCountField.setTextFormatter(new IntegerFormatter());

        rowCountField.textProperty().bindBidirectional(generalSettings.rowCountProperty(), new NumberStringConverter());
        columnCountField.textProperty().bindBidirectional(generalSettings.columnCountProperty(), new NumberStringConverter());
    }
}
