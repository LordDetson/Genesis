package by.babanin.genesis.gui.converter;

import by.babanin.genesis.gui.NoiseAlgorithmItem;
import javafx.util.StringConverter;

public class NoiseAlgorithmItemConverter extends StringConverter<NoiseAlgorithmItem> {

    @Override
    public String toString(NoiseAlgorithmItem object) {
        return object != null ? object.getName() : "None";
    }

    @Override
    public NoiseAlgorithmItem fromString(String string) {
        return NoiseAlgorithmItem.find(string).orElse(null);
    }
}
