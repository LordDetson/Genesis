package by.babanin.genesis.gui;

import static com.almasb.fxgl.dsl.FXGL.*;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.GameScene;
import com.almasb.fxgl.entity.SpawnData;

import by.babanin.genesis.GenesisLauncher;
import by.babanin.genesis.core.World;
import by.babanin.genesis.core.cell.TerrainCell;
import by.babanin.genesis.gui.controller.MainMenuController;
import by.babanin.genesis.gui.dto.GeneralSettings;
import by.babanin.genesis.gui.entity.TerrainCellEntityFactory;
import by.babanin.genesis.gui.scene.CustomSceneFactory;
import by.babanin.genesis.gui.shape.Hexagon2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class GenesisApplication extends GameApplication {

    private static final int APP_WIDTH = 1280;
    private static final int APP_HEIGHT = 720;

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void initSettings(GameSettings settings) {
        initializeSpringApplicationContext();

        settings.setTitle("Genesis");
        settings.setWidth(APP_WIDTH);
        settings.setHeight(APP_HEIGHT);
        settings.setGameMenuEnabled(true);
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(applicationContext.getBean(CustomSceneFactory.class));
    }

    private void initializeSpringApplicationContext() {
        applicationContext = new SpringApplicationBuilder()
                .sources(GenesisLauncher.class)
                .run(GenesisLauncher.getArguments());
    }

    @Override
    protected void onPreInit() {
        getGameController().gotoMainMenu();
        focusCurrentContentPane();
    }

    private void focusCurrentContentPane() {
        MainMenuController mainMenuController = applicationContext.getBean(MainMenuController.class);
        Node currentContentPane = mainMenuController.getCurrentContentPane();
        currentContentPane.requestFocus();
    }

    @Override
    protected void initGame() {
        addApplicationContextClosing();

        GameScene gameScene = getGameScene();
        gameScene.setBackgroundColor(Color.AZURE);

        World world = applicationContext.getBean(World.class);
        GeneralSettings generalSettings = applicationContext.getBean(GeneralSettings.class);
        double radiusOfHexagon = generalSettings.getRadiusOfHexagon();

        spawnTerrainCells(world, radiusOfHexagon);
    }

    private void addApplicationContextClosing() {
        getPrimaryStage().setOnCloseRequest(event -> applicationContext.close());
    }

    private void spawnTerrainCells(World world, double radiusOfHexagon) {
        getGameWorld().addEntityFactory(applicationContext.getBean(TerrainCellEntityFactory.class));
        Hexagon2D exampleHexagon = new Hexagon2D(radiusOfHexagon, 0, 0);
        double radius = exampleHexagon.getRadius();
        double bigLeg = exampleHexagon.getBigLeg();
        double minLeg = exampleHexagon.getMinLeg();
        double bigDiameter = radius * 2;
        double smallDiameter = bigLeg * 2;
        double yStep = bigDiameter - minLeg;
        double y = radius;
        for(int rowIndex = 0; rowIndex < world.getRowCount(); rowIndex++) {
            double x = bigLeg;
            if((rowIndex + 1) % 2 == 0) {
                x += bigLeg;
            }
            for(int columnIndex = 0; columnIndex < world.getColumnCount(); columnIndex++) {
                TerrainCell terrainCell = world.getTerrainCell(rowIndex, columnIndex);
                SpawnData spawnData = new SpawnData(x, y);
                spawnData.put("cell", terrainCell);
                spawn("terrainCell2D", spawnData);
                x += smallDiameter;
            }
            y += yStep;
        }
    }
}
