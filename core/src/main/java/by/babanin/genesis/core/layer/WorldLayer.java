package by.babanin.genesis.core.layer;

import by.babanin.genesis.core.exception.CoreException;
import by.babanin.genesis.core.World;
import by.babanin.genesis.core.cell.Cell;

public abstract class WorldLayer<T extends Cell> {

    private final World world;
    private boolean initialized;
    private T[][] matrix;

    public WorldLayer(World world) {
        this.world = world;
    }

    public void initialize() {
        if(!initialized) {
            this.matrix = createMatrix(world.getRowCount(), world.getColumnCount());
            initialized = true;
        }
        else {
            throw new CoreException("Layer already is initialized");
        }
    }

    protected abstract T[][] createMatrix(int rowCount, int columnIndex);

    public T getCell(int rowCount, int columnIndex) {
        return matrix[rowCount][columnIndex];
    }

    public World getWorld() {
        return world;
    }
}
