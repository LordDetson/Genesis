package by.babanin.genesis.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import by.babanin.genesis.gui.dto.BiomeSetting;
import by.babanin.genesis.gui.dto.BiomeSettings;
import by.babanin.genesis.gui.dto.TerrainSettings;
import by.babanin.genesis.gui.formatter.IntegerFormatter;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/assets/views/biome_settings.fxml")
public class BiomeSettingsController implements Initializable {

    public TextField deepSeeLevel;
    public TextField deepSeeEnergyCost;
    public ColorPicker deepSeeColor;
    public TextField seeLevel;
    public TextField seeEnergyCost;
    public ColorPicker seeColor;
    public TextField coastLevel;
    public TextField coastEnergyCost;
    public ColorPicker coastColor;
    public TextField plainsLevel;
    public TextField plainsEnergyCost;
    public ColorPicker plainsColor;
    public TextField hillsLevel;
    public TextField hillsEnergyCost;
    public ColorPicker hillsColor;
    public TextField mountainsLevel;
    public TextField mountainsEnergyCost;
    public ColorPicker mountainsColor;
    public TextField highMountainsLevel;
    public TextField highMountainsEnergyCost;
    public ColorPicker highMountainsColor;

    private final TerrainSettings terrainSettings;
    private final BiomeSettings biomeSettings;

    public BiomeSettingsController(TerrainSettings terrainSettings, BiomeSettings biomeSettings) {
        this.terrainSettings = terrainSettings;
        this.biomeSettings = biomeSettings;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupBiomeFields(biomeSettings.getDeepSeeSetting(), deepSeeLevel, deepSeeEnergyCost, deepSeeColor);
        setupBiomeFields(biomeSettings.getSeeSetting(), seeLevel, seeEnergyCost, seeColor);
        setupBiomeFields(biomeSettings.getCoastSetting(), coastLevel, coastEnergyCost, coastColor);
        setupBiomeFields(biomeSettings.getPlainsSetting(), plainsLevel, plainsEnergyCost, plainsColor);
        setupBiomeFields(biomeSettings.getHillsSetting(), hillsLevel, hillsEnergyCost, hillsColor);
        setupBiomeFields(biomeSettings.getMountainsSetting(), mountainsLevel, mountainsEnergyCost, mountainsColor);
        setupBiomeFields(biomeSettings.getHighMountainsSetting(), highMountainsLevel, highMountainsEnergyCost, highMountainsColor);
        highMountainsLevel.textProperty().bind(terrainSettings.worldHeightProperty().asString());
    }

    private void setupBiomeFields(BiomeSetting biomeSetting, TextField levelField, TextField energyCostField, ColorPicker colorPicker) {
        levelField.setTextFormatter(new IntegerFormatter());
        energyCostField.setTextFormatter(new IntegerFormatter());
        levelField.textProperty().bindBidirectional(biomeSetting.levelProperty(), new NumberStringConverter());
        energyCostField.textProperty().bindBidirectional(biomeSetting.energyCostProperty(), new NumberStringConverter());
        colorPicker.valueProperty().bindBidirectional(biomeSetting.colorProperty());
    }
}
