package by.babanin.genesis.gui.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import by.babanin.genesis.gui.NoiseAlgorithmItem;
import by.babanin.genesis.gui.converter.NoiseAlgorithmItemConverter;
import by.babanin.genesis.gui.dto.TerrainSettings;
import by.babanin.genesis.gui.formatter.FloatFormatter;
import by.babanin.genesis.gui.formatter.IntegerFormatter;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.util.converter.NumberStringConverter;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/assets/views/terrain_settings.fxml")
public class TerrainSettingsController implements Initializable {

    public TextField radiusOfHexagonFiled;
    public TextField worldHeightField;
    public ComboBox<NoiseAlgorithmItem> algorithmComboBox;

    private final TerrainSettings terrainSettings;
    private final FxControllerAndView<SimplexNoiseAlgorithmFormController, TitledPane> algorithmParametersFormCtrlAndView;
    public Group algorithmContent;

    public TerrainSettingsController(TerrainSettings terrainSettings,
            FxControllerAndView<SimplexNoiseAlgorithmFormController, TitledPane> algorithmParametersFormCtrlAndView) {
        this.terrainSettings = terrainSettings;
        this.algorithmParametersFormCtrlAndView = algorithmParametersFormCtrlAndView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupRadiusOfHexagonFiled();
        setupWorldHeightField();
        setupAlgorithmComboBox();
    }

    private void setupRadiusOfHexagonFiled() {
        radiusOfHexagonFiled.setTextFormatter(new FloatFormatter());
        radiusOfHexagonFiled.textProperty().bindBidirectional(terrainSettings.radiusOfHexagonProperty(), new DecimalFormat());
    }

    private void setupWorldHeightField() {
        worldHeightField.setTextFormatter(new IntegerFormatter());
        worldHeightField.textProperty().bindBidirectional(terrainSettings.worldHeightProperty(), new NumberStringConverter());
    }

    private void setupAlgorithmComboBox() {
        algorithmComboBox.setConverter(new NoiseAlgorithmItemConverter());
        algorithmComboBox.getItems().add(null);
        algorithmComboBox.getItems().addAll(NoiseAlgorithmItem.values());
        algorithmComboBox.getSelectionModel().selectedItemProperty().addListener(this::onSelectedAlgorithm);
        Optional<NoiseAlgorithmItem> noiseAlgorithmItem = terrainSettings.getNoiseAlgorithmItem();
        if(noiseAlgorithmItem.isPresent()) {
            algorithmComboBox.getSelectionModel().select(noiseAlgorithmItem.get());
        }
        else {
            algorithmComboBox.getSelectionModel().select(0);
        }

    }

    private void onSelectedAlgorithm(ObservableValue<? extends NoiseAlgorithmItem> observable, NoiseAlgorithmItem oldValue,
            NoiseAlgorithmItem newValue) {
        terrainSettings.setNoiseAlgorithmItem(newValue);
        showAlgorithmForm(newValue);
    }

    private void showAlgorithmForm(NoiseAlgorithmItem selectedAlgorithmItem) {
        ObservableList<Node> children = algorithmContent.getChildren();
        children.clear();
        children.add(getAlgorithmForm(selectedAlgorithmItem));
    }

    private Node getAlgorithmForm(NoiseAlgorithmItem selectedAlgorithmItem) {
        if(selectedAlgorithmItem == null) {
            return new StackPane();
        }
        return algorithmParametersFormCtrlAndView.getView().get();
    }
}
