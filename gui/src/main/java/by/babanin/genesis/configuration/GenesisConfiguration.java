package by.babanin.genesis.configuration;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import by.babanin.genesis.core.World;
import by.babanin.genesis.core.cell.TerrainCell;
import by.babanin.genesis.core.cell.TerrainCellFactory;
import by.babanin.genesis.core.layer.FlatTerrainLayer;
import by.babanin.genesis.core.layer.TerrainLayer;
import by.babanin.genesis.core.layer.WorldLayer;
import by.babanin.genesis.core.noise.NoiseAlgorithm;
import by.babanin.genesis.gui.NoiseAlgorithmItem;
import by.babanin.genesis.gui.dto.BiomeSettings;
import by.babanin.genesis.gui.dto.GeneralSettings;
import by.babanin.genesis.gui.dto.TerrainSettings;

@Component
public class GenesisConfiguration {

    @Bean
    @Scope("prototype")
    public TerrainCellFactory getTerrainCellFactory(BiomeSettings biomeSettings) {
        return new TerrainCellFactory(
                biomeSettings.getDeepSeeSetting().getLevel(),
                biomeSettings.getSeeSetting().getLevel(),
                biomeSettings.getCoastSetting().getLevel(),
                biomeSettings.getPlainsSetting().getLevel(),
                biomeSettings.getHillsSetting().getLevel(),
                biomeSettings.getMountainsSetting().getLevel(),
                biomeSettings.getHighMountainsSetting().getLevel()
        );
    }

    @Bean
    @Scope("prototype")
    public World getWorld(GeneralSettings generalSettings, TerrainSettings terrainSettings, TerrainCellFactory terrainCellFactory) {
        World world = new World(generalSettings.getRowCount(), generalSettings.getColumnCount());
        world.setAndInitializeTerrainLayer(createTerrainLayer(world, terrainSettings, terrainCellFactory));
        return world;
    }

    public WorldLayer<TerrainCell> createTerrainLayer(World world, TerrainSettings terrainSettings, TerrainCellFactory terrainCellFactory) {
        Optional<NoiseAlgorithmItem> noiseAlgorithmItem = terrainSettings.getNoiseAlgorithmItem();
        if(noiseAlgorithmItem.isPresent()) {
            NoiseAlgorithm algorithm = noiseAlgorithmItem.get().getAlgorithm();
            float offset = terrainSettings.getNoiseAlgorithmOffset();
            return new TerrainLayer(world, terrainCellFactory, algorithm, algorithm.getMinValue(), algorithm.getMaxValue(), offset, offset);
        }
        return new FlatTerrainLayer(world, terrainSettings.getWorldHeight());
    }
}
