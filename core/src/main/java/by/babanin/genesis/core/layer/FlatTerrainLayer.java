package by.babanin.genesis.core.layer;

import by.babanin.genesis.core.World;
import by.babanin.genesis.core.cell.TerrainCell;
import by.babanin.genesis.core.cell.BiomeType;

public class FlatTerrainLayer extends WorldLayer<TerrainCell> {

    private final float height;

    public FlatTerrainLayer(World world, float height) {
        super(world);
        this.height = height;
    }

    @Override
    public TerrainCell[][] createMatrix(int rowCount, int columnCount) {
        TerrainCell[][] matrix = new TerrainCell[rowCount][columnCount];
        for(int row = 0; row < rowCount; row++) {
            for(int column = 0; column < columnCount; column++) {
                matrix[row][column] = new TerrainCell(row, column, BiomeType.PLAINS, height);
            }
        }
        return matrix;
    }
}
