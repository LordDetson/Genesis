package by.babanin.genesis.core.cell;

import by.babanin.genesis.core.exception.CoreException;

public class TerrainCellFactory {
    private final float deepSee;
    private final float see;
    private final float coast;
    private final float plains;
    private final float hills;
    private final float mountains;
    private final float highMountains;

    public TerrainCellFactory(float deepSee, float see, float coast, float plains, float hills, float mountains, float highMountains) {
        checkValue(deepSee, highMountains);
        checkValue(see, highMountains);
        checkValue(coast, highMountains);
        checkValue(plains, highMountains);
        checkValue(hills, highMountains);
        checkValue(mountains, highMountains);
        this.deepSee = deepSee;
        this.see = see;
        this.coast = coast;
        this.plains = plains;
        this.hills = hills;
        this.mountains = mountains;
        this.highMountains = highMountains;
    }

    private void checkValue(float value, float maxHeight) {
        if(value > maxHeight) {
            throw new CoreException("'" + value + "' can't be larger then '" + maxHeight + "' max height");
        }
    }

    public float getMaxHeight() {
        return highMountains;
    }

    public TerrainCell create(int row, int column, float height) {
        float maxHeight = getMaxHeight();
        if(height > maxHeight) {
            throw new CoreException("'" + height + "' height can't be larger then '" + maxHeight + "' max height");
        }
        BiomeType type;
        if(height <= deepSee) {
            type = BiomeType.DEEP_SEE;
        }
        else if(height <= see) {
            type = BiomeType.SEE;
        }
        else if(height <= coast) {
            type = BiomeType.COAST;
        }
        else if(height <= plains) {
            type = BiomeType.PLAINS;
        }
        else if(height <= hills) {
            type = BiomeType.HILLS;
        }
        else if(height <= mountains) {
            type = BiomeType.MOUNTAINS;
        }
        else if(height <= highMountains) {
            type = BiomeType.HIGH_MOUNTAINS;
        }
        else {
            throw new CoreException("Unknown type with '" + height + "' height");
        }
        return new TerrainCell(row, column, type, height);
    }
}
