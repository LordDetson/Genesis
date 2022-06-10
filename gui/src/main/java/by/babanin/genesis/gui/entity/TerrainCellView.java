package by.babanin.genesis.gui.entity;

import by.babanin.genesis.core.cell.TerrainCell;
import by.babanin.genesis.gui.shape.HexagonPillar;

public class TerrainCellView extends HexagonPillar {

    private final TerrainCell cell;

    public TerrainCellView(TerrainCell cell, float radius, double x, double z) {
        super(x, z, radius, cell.getHeight());
        this.cell = cell;
    }

    public TerrainCell getCell() {
        return cell;
    }
}
