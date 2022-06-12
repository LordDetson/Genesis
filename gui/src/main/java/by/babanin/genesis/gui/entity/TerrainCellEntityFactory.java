package by.babanin.genesis.gui.entity;

import org.springframework.stereotype.Component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

import by.babanin.genesis.core.cell.TerrainCell;
import by.babanin.genesis.gui.dto.BiomeSettings;
import by.babanin.genesis.gui.dto.GeneralSettings;
import javafx.scene.paint.PhongMaterial;

@Component
public class TerrainCellEntityFactory implements EntityFactory {

    private final GeneralSettings generalSettings;
    private final BiomeSettings biomeSettings;

    public TerrainCellEntityFactory(GeneralSettings generalSettings, BiomeSettings biomeSettings) {
        this.generalSettings = generalSettings;
        this.biomeSettings = biomeSettings;
    }

    @Spawns("terrainCell2D")
    public Entity terrainCell2D(SpawnData data) {
        TerrainCell cell = data.get("cell");
        TerrainCell2DView view = new TerrainCell2DView(cell, generalSettings.getRadiusOfHexagon(), data.getX(), data.getY());
        view.setFill(biomeSettings.getColor(cell.getType()));
        return FXGL.entityBuilder(data)
                .type(EntityType.TERRAIN_CELL)
                .view(view)
                .build();
    }

    @Spawns("terrainCell3D")
    public Entity terrainCell3D(SpawnData data) {
        TerrainCell cell = data.get("cell");
        TerrainCell3DView view = new TerrainCell3DView(cell, (float) generalSettings.getRadiusOfHexagon(), data.getX(), data.getZ());
        view.setMaterial(new PhongMaterial(biomeSettings.getColor(cell.getType())));
        return FXGL.entityBuilder(data)
                .type(EntityType.TERRAIN_CELL)
                .view(view)
                .build();
    }
}
