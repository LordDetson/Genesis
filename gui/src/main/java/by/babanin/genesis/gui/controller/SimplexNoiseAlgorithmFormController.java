package by.babanin.genesis.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import by.babanin.genesis.gui.dto.TerrainSettings;
import by.babanin.genesis.gui.formatter.FloatFormatter;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/assets/views/simplex_noise_algorithm_form.fxml")
public class SimplexNoiseAlgorithmFormController implements Initializable {

    public TextField offsetAlgorithmField;

    private final TerrainSettings terrainSettings;

    public SimplexNoiseAlgorithmFormController(TerrainSettings terrainSettings) {
        this.terrainSettings = terrainSettings;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        offsetAlgorithmField.setTextFormatter(new FloatFormatter());
        offsetAlgorithmField.textProperty().bindBidirectional(terrainSettings.noiseAlgorithmOffsetProperty(), new NumberStringConverter());
    }
}
