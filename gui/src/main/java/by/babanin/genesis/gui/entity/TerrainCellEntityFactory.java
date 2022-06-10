package by.babanin.genesis.gui.entity;

import org.springframework.stereotype.Component;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

import by.babanin.genesis.core.cell.TerrainCell;
import by.babanin.genesis.gui.dto.BiomeSettings;
import by.babanin.genesis.gui.dto.TerrainSettings;
import javafx.scene.paint.PhongMaterial;

@Component
public class TerrainCellEntityFactory implements EntityFactory {

    private final TerrainSettings terrainSettings;
    private final BiomeSettings biomeSettings;

    public TerrainCellEntityFactory(TerrainSettings terrainSettings, BiomeSettings biomeSettings) {
        this.terrainSettings = terrainSettings;
        this.biomeSettings = biomeSettings;
    }

    @Spawns("terrainCell")
    public Entity terrainCell(SpawnData data) {
        TerrainCell cell = data.get("cell");
        TerrainCellView view = new TerrainCellView(cell, terrainSettings.getRadiusOfHexagon(), data.getX(), data.getZ());
        view.setMaterial(new PhongMaterial(biomeSettings.getColor(cell.getType())));
        return FXGL.entityBuilder(data)
                .type(EntityType.TERRAIN_CELL)
                .view(view)
                .build();
    }
}
