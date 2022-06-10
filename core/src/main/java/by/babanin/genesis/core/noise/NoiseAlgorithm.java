package by.babanin.genesis.core.noise;

import java.util.function.BiFunction;

public enum NoiseAlgorithm implements BiFunction<Float, Float, Float> {
    SIMPLEX_NOISE((xin, yin) -> (float) SimplexNoise.noise2D(xin, yin), -1, 1),
    ;

    private final BiFunction<Float, Float, Float> noiseFunction;
    private final float minValue;
    private final float maxValue;

    NoiseAlgorithm(BiFunction<Float, Float, Float> noiseFunction, float minValue, float maxValue) {
        this.noiseFunction = noiseFunction;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public Float apply(Float xin, Float yin) {
        return noiseFunction.apply(xin, yin);
    }

    public float getMinValue() {
        return minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }
}
