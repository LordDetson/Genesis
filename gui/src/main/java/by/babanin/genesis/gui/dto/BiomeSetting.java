package by.babanin.genesis.gui.dto;

import by.babanin.genesis.core.cell.BiomeType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;

public class BiomeSetting {

    private final BiomeType biomeType;
    private final SimpleIntegerProperty level;
    private final SimpleIntegerProperty energyCost;
    private final SimpleObjectProperty<Color> color;

    public BiomeSetting(BiomeType biomeType) {
        this.biomeType = biomeType;
        this.level = new SimpleIntegerProperty();
        this.energyCost = new SimpleIntegerProperty();
        this.color = new SimpleObjectProperty<>();
    }

    public BiomeType getBiomeType() {
        return biomeType;
    }

    public int getLevel() {
        return level.get();
    }

    public SimpleIntegerProperty levelProperty() {
        return level;
    }

    public void setLevel(int level) {
        this.level.set(level);
    }

    public int getEnergyCost() {
        return energyCost.get();
    }

    public SimpleIntegerProperty energyCostProperty() {
        return energyCost;
    }

    public void setEnergyCost(int energyCost) {
        this.energyCost.set(energyCost);
    }

    public Color getColor() {
        return color.get();
    }

    public SimpleObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }
}
