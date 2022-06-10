package by.babanin.genesis.core.layer;

import java.util.function.BiFunction;

import by.babanin.genesis.core.exception.CoreException;
import by.babanin.genesis.core.World;
import by.babanin.genesis.core.cell.TerrainCell;
import by.babanin.genesis.core.cell.TerrainCellFactory;

public class TerrainLayer extends WorldLayer<TerrainCell> {

    private final TerrainCellFactory factory;
    private final BiFunction<Float, Float, Float> noiseFunction;
    private final float minNoiseFunctionValue;
    private final float maxNoiseFunctionValue;
    private final float xOffset;
    private final float yOffset;

    public TerrainLayer(World world, TerrainCellFactory factory, BiFunction<Float, Float, Float> noiseFunction,
            float minNoiseFunctionValue, float maxNoiseFunctionValue,
            float xOffset, float yOffset) {
        super(world);
        this.factory = factory;
        this.noiseFunction = noiseFunction;
        this.minNoiseFunctionValue = minNoiseFunctionValue;
        this.maxNoiseFunctionValue = maxNoiseFunctionValue;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    @Override
    public TerrainCell[][] createMatrix(int rowCount, int columnCount) {
        TerrainCell[][] matrix = new TerrainCell[rowCount][columnCount];
        float y = 0;
        for(int row = 0; row < rowCount; row++) {
            float x = 0;
            for(int column = 0; column < columnCount; column++) {
                float value = noiseFunction.apply(x, y);
                float height = map(value, minNoiseFunctionValue, maxNoiseFunctionValue, 0, factory.getMaxHeight());
                matrix[row][column] = factory.create(row, column, height);
                x += xOffset;
            }
            y += yOffset;
        }
        return matrix;
    }

    public static float map(float value,
            float start1, float stop1,
            float start2, float stop2) {
        float outgoing =
                start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
        String badness = null;
        if (outgoing != outgoing) {
            badness = "NaN (not a number)";

        } else if (outgoing == Float.NEGATIVE_INFINITY ||
                outgoing == Float.POSITIVE_INFINITY) {
            badness = "infinity";
        }
        if (badness != null) {
            final String message =
                    String.format("map(%s, %s, %s, %s, %s) called, which returns %s",
                            value, start1, stop1,
                            start2, stop2, badness);
            throw new CoreException(message);
        }
        return outgoing;
    }
}
