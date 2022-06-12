package by.babanin.genesis.gui.entity;

import by.babanin.genesis.core.cell.TerrainCell;
import by.babanin.genesis.gui.shape.Hexagon2D;

public class TerrainCell2DView extends Hexagon2D {

    private final TerrainCell cell;

    public TerrainCell2DView(TerrainCell cell, double radius, double x, double y) {
        super(radius, x, y);
        this.cell = cell;
    }

    public TerrainCell getCell() {
        return cell;
    }
}
