package by.babanin.genesis.gui;

import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import by.babanin.genesis.core.noise.NoiseAlgorithm;

public enum NoiseAlgorithmItem {
    SIMPLEX_NOISE("Simplex noise", "Simplex noise", NoiseAlgorithm.SIMPLEX_NOISE),
    ;

    private final String name;
    private final String description;
    private final NoiseAlgorithm algorithm;

    NoiseAlgorithmItem(String name, String description, NoiseAlgorithm algorithm) {
        this.name = name;
        this.description = description;
        this.algorithm = algorithm;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public NoiseAlgorithm getAlgorithm() {
        return algorithm;
    }

    public static Optional<NoiseAlgorithmItem> find(String name) {
        if(StringUtils.isBlank(name)) {
            return Optional.empty();
        }
        return Arrays.stream(NoiseAlgorithmItem.values())
                .filter(noiseAlgorithmItem -> noiseAlgorithmItem.getName().equals(name))
                .findFirst();
    }
}
