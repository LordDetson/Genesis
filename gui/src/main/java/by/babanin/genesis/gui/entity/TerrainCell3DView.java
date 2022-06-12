package by.babanin.genesis.gui.entity;

import by.babanin.genesis.core.cell.TerrainCell;
import by.babanin.genesis.gui.shape.HexagonPillar;

public class TerrainCell3DView extends HexagonPillar {

    private final TerrainCell cell;

    public TerrainCell3DView(TerrainCell cell, float radius, double x, double z) {
        super(radius, 0, x, z);
        this.cell = cell;
    }

    public TerrainCell getCell() {
        return cell;
    }
}
