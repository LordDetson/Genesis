package by.babanin.genesis.gui.dto;

import org.springframework.stereotype.Component;

import by.babanin.genesis.core.cell.BiomeType;
import javafx.scene.paint.Color;

@Component
public class BiomeSettings {
    private static final int DEEP_SEE_LEVEL = 12;
    private static final int DEEP_SEE_ENERGY_COST = 4;
    private static final Color DEEP_SEE_COLOR = Color.ROYALBLUE;
    private static final int SEE_LEVEL = 15;
    private static final int SEE_ENERGY_COST = 2;
    private static final Color SEE_COLOR = Color.LIGHTSKYBLUE;
    private static final int COAST_LEVEL = 18;
    private static final int COAST_ENERGY_COST = 1;
    private static final Color COAST_COLOR = Color.MOCCASIN;
    private static final int PLAINS_LEVEL = 40;
    private static final int PLAINS_ENERGY_COST = 1;
    private static final Color PLAINS_COLOR = Color.YELLOWGREEN;
    private static final int HILLS_LEVEL = 50;
    private static final int HILLS_ENERGY_COST = 2;
    private static final Color HILLS_COLOR = Color.YELLOWGREEN;
    private static final int MOUNTAINS_LEVEL = 65;
    private static final int MOUNTAINS_ENERGY_COST = 4;
    private static final Color MOUNTAINS_COLOR = Color.GRAY;
    private static final int HIGH_MOUNTAINS_LEVEL = TerrainSettings.DEFAULT_WORLD_HEIGHT;
    private static final int HIGH_MOUNTAINS_ENERGY_COST = 10;
    private static final Color HIGH_MOUNTAINS_COLOR = Color.HONEYDEW;

    private final BiomeSetting deepSeeSetting;
    private final BiomeSetting seeSetting;
    private final BiomeSetting coastSetting;
    private final BiomeSetting plainsSetting;
    private final BiomeSetting hillsSetting;
    private final BiomeSetting mountainsSetting;
    private final BiomeSetting highMountainsSetting;

    public BiomeSettings() {
        this.deepSeeSetting = new BiomeSetting(BiomeType.DEEP_SEE);
        this.seeSetting = new BiomeSetting(BiomeType.SEE);
        this.coastSetting = new BiomeSetting(BiomeType.COAST);
        this.plainsSetting = new BiomeSetting(BiomeType.PLAINS);
        this.hillsSetting = new BiomeSetting(BiomeType.HILLS);
        this.mountainsSetting = new BiomeSetting(BiomeType.MOUNTAINS);
        this.highMountainsSetting = new BiomeSetting(BiomeType.HIGH_MOUNTAINS);
        initializeDefaultSettings();
    }

    private void initializeDefaultSettings() {
        deepSeeSetting.setLevel(DEEP_SEE_LEVEL);
        deepSeeSetting.setEnergyCost(DEEP_SEE_ENERGY_COST);
        deepSeeSetting.setColor(DEEP_SEE_COLOR);

        seeSetting.setLevel(SEE_LEVEL);
        seeSetting.setEnergyCost(SEE_ENERGY_COST);
        seeSetting.setColor(SEE_COLOR);

        coastSetting.setLevel(COAST_LEVEL);
        coastSetting.setEnergyCost(COAST_ENERGY_COST);
        coastSetting.setColor(COAST_COLOR);

        plainsSetting.setLevel(PLAINS_LEVEL);
        plainsSetting.setEnergyCost(PLAINS_ENERGY_COST);
        plainsSetting.setColor(PLAINS_COLOR);

        hillsSetting.setLevel(HILLS_LEVEL);
        hillsSetting.setEnergyCost(HILLS_ENERGY_COST);
        hillsSetting.setColor(HILLS_COLOR);

        mountainsSetting.setLevel(MOUNTAINS_LEVEL);
        mountainsSetting.setEnergyCost(MOUNTAINS_ENERGY_COST);
        mountainsSetting.setColor(MOUNTAINS_COLOR);

        highMountainsSetting.setLevel(HIGH_MOUNTAINS_LEVEL);
        highMountainsSetting.setEnergyCost(HIGH_MOUNTAINS_ENERGY_COST);
        highMountainsSetting.setColor(HIGH_MOUNTAINS_COLOR);
    }

    public BiomeSetting getDeepSeeSetting() {
        return deepSeeSetting;
    }

    public BiomeSetting getSeeSetting() {
        return seeSetting;
    }

    public BiomeSetting getCoastSetting() {
        return coastSetting;
    }

    public BiomeSetting getPlainsSetting() {
        return plainsSetting;
    }

    public BiomeSetting getHillsSetting() {
        return hillsSetting;
    }

    public BiomeSetting getMountainsSetting() {
        return mountainsSetting;
    }

    public BiomeSetting getHighMountainsSetting() {
        return highMountainsSetting;
    }

    public Color getColor(BiomeType biomeType) {
        return getSetting(biomeType).getColor();
    }

    public BiomeSetting getSetting(BiomeType biomeType) {
        switch(biomeType) {
        case DEEP_SEE:
            return deepSeeSetting;
        case SEE:
            return seeSetting;
        case COAST:
            return coastSetting;
        case PLAINS:
            return plainsSetting;
        case HILLS:
            return hillsSetting;
        case MOUNTAINS:
            return mountainsSetting;
        case HIGH_MOUNTAINS:
            return highMountainsSetting;
        default:
            assert false : biomeType + " biome type isn't support";
            return null;
        }
    }
}
