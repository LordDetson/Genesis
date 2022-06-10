package by.babanin.genesis.core;

import by.babanin.genesis.core.cell.Cell;
import by.babanin.genesis.core.cell.TerrainCell;
import by.babanin.genesis.core.exception.CoreException;
import by.babanin.genesis.core.layer.TerrainLayer;
import by.babanin.genesis.core.layer.WorldLayer;

public class World implements ReportableComponent {

    private final int rowCount;
    private final int columnCount;
    private WorldLayer<TerrainCell> terrainLayer;

    public World(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setAndInitializeTerrainLayer(WorldLayer<TerrainCell> terrainLayer) {
        if(terrainLayer.getWorld() != this) {
            throw new CoreException("The terrain layer is based on another world.");
        }
        terrainLayer.initialize();
        this.terrainLayer = terrainLayer;
    }

    public TerrainCell getTerrainCell(int rowIndex, int columnCount) {
        return getCell(terrainLayer, rowIndex, columnCount);
    }

    private <T extends Cell> T getCell(WorldLayer<T> layer, int rowIndex, int columnIndex) {
        if(layer == null) {
            return null;
        }
        return layer.getCell(rowIndex, columnIndex);
    }
}
