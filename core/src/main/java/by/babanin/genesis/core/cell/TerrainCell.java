package by.babanin.genesis.core.cell;

public class TerrainCell extends Cell {

    private final BiomeType type;
    private final float height;

    public TerrainCell(int row, int column, BiomeType type, float height) {
        super(row, column);
        this.type = type;
        this.height = height;
    }

    public BiomeType getType() {
        return type;
    }

    public float getHeight() {
        return height;
    }
}
