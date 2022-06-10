package by.babanin.genesis.gui.dto;

import java.util.Optional;

import org.springframework.stereotype.Component;

import by.babanin.genesis.gui.NoiseAlgorithmItem;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

@Component
public class TerrainSettings {

    private static final float DEFAULT_RADIUS_OF_HEXAGON = 1;
    private static final NoiseAlgorithmItem DEFAULT_NOISE_ALGORITHM = NoiseAlgorithmItem.SIMPLEX_NOISE;
    private static final float DEFAULT_OFFSET = 0.021f;
    public static final int DEFAULT_WORLD_HEIGHT = 70;

    private final SimpleFloatProperty radiusOfHexagon;
    private final SimpleObjectProperty<Optional<NoiseAlgorithmItem>> noiseAlgorithmItem;
    private final SimpleFloatProperty noiseAlgorithmOffset;
    private final SimpleIntegerProperty worldHeight;

    public TerrainSettings() {
        this.radiusOfHexagon = new SimpleFloatProperty(DEFAULT_RADIUS_OF_HEXAGON);
        this.noiseAlgorithmItem = new SimpleObjectProperty<>(Optional.of(DEFAULT_NOISE_ALGORITHM));
        this.noiseAlgorithmOffset = new SimpleFloatProperty(DEFAULT_OFFSET);
        this.worldHeight = new SimpleIntegerProperty(DEFAULT_WORLD_HEIGHT);
    }

    public float getRadiusOfHexagon() {
        return radiusOfHexagon.get();
    }

    public SimpleFloatProperty radiusOfHexagonProperty() {
        return radiusOfHexagon;
    }

    public void setRadiusOfHexagon(float radiusOfHexagon) {
        this.radiusOfHexagon.set(radiusOfHexagon);
    }

    public Optional<NoiseAlgorithmItem> getNoiseAlgorithmItem() {
        return noiseAlgorithmItem.get();
    }

    public SimpleObjectProperty<Optional<NoiseAlgorithmItem>> noiseAlgorithmItemProperty() {
        return noiseAlgorithmItem;
    }

    public void setNoiseAlgorithmItem(NoiseAlgorithmItem noiseAlgorithmItem) {
        this.noiseAlgorithmItem.set(Optional.ofNullable(noiseAlgorithmItem));
    }

    public float getNoiseAlgorithmOffset() {
        return noiseAlgorithmOffset.get();
    }

    public SimpleFloatProperty noiseAlgorithmOffsetProperty() {
        return noiseAlgorithmOffset;
    }

    public void setNoiseAlgorithmOffset(float noiseAlgorithmOffset) {
        this.noiseAlgorithmOffset.set(noiseAlgorithmOffset);
    }

    public int getWorldHeight() {
        return worldHeight.get();
    }

    public SimpleIntegerProperty worldHeightProperty() {
        return worldHeight;
    }

    public void setWorldHeight(int worldHeight) {
        this.worldHeight.set(worldHeight);
    }
}
