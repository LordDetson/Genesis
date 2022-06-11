package by.babanin.genesis.gui;

import static com.almasb.fxgl.dsl.FXGL.*;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Camera3D;
import com.almasb.fxgl.app.scene.GameScene;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.components.TransformComponent;

import by.babanin.genesis.GenesisLauncher;
import by.babanin.genesis.core.World;
import by.babanin.genesis.core.cell.TerrainCell;
import by.babanin.genesis.gui.controller.MainMenuController;
import by.babanin.genesis.gui.dto.TerrainSettings;
import by.babanin.genesis.gui.entity.TerrainCellEntityFactory;
import by.babanin.genesis.gui.scene.CustomSceneFactory;
import by.babanin.genesis.gui.shape.FlatHexagon;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class GenesisApplication extends GameApplication {

    private static final int APP_WIDTH = 1280;
    private static final int APP_HEIGHT = 720;
    private static final int CAMERA_Y_OFFSET = 30;
    private static final int CAMERA_Z_OFFSET = 40;

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void initSettings(GameSettings settings) {
        initializeSpringApplicationContext();

        settings.setTitle("Genesis");
        settings.setWidth(APP_WIDTH);
        settings.setHeight(APP_HEIGHT);
        settings.set3D(true);
        settings.setGameMenuEnabled(true);
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(applicationContext.getBean(CustomSceneFactory.class));
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
        TerrainSettings terrainSettings = applicationContext.getBean(TerrainSettings.class);
        float radiusOfHexagon = terrainSettings.getRadiusOfHexagon();

        spawnTerrainCells(world, radiusOfHexagon);

        setupCamera3D(getWorldCenter(world, radiusOfHexagon), terrainSettings.getWorldHeight());
    }

    private void spawnTerrainCells(World world, float radiusOfHexagon) {
        getGameWorld().addEntityFactory(applicationContext.getBean(TerrainCellEntityFactory.class));
        FlatHexagon exampleHexagon = new FlatHexagon(radiusOfHexagon);
        double bigDiameter = exampleHexagon.getRadius() * 2;
        double smallDiameter = exampleHexagon.getBigLeg() * 2;
        double z = 0;
        for(int rowIndex = 0; rowIndex < world.getRowCount(); rowIndex++) {
            double x = 0;
            if((rowIndex + 1) % 2 == 0) {
                x += exampleHexagon.getBigLeg();
            }
            for(int columnIndex = 0; columnIndex < world.getColumnCount(); columnIndex++) {
                TerrainCell terrainCell = world.getTerrainCell(rowIndex, columnIndex);
                SpawnData spawnData = new SpawnData(x, 0, z);
                spawnData.put("cell", terrainCell);
                spawn("terrainCell", spawnData);
                x += smallDiameter;
            }
            z += bigDiameter - exampleHexagon.getMinLeg();
        }
    }

    private void initializeSpringApplicationContext() {
        applicationContext = new SpringApplicationBuilder()
                .sources(GenesisLauncher.class)
                .run(GenesisLauncher.getArguments());
    }

    private void addApplicationContextClosing() {
        getPrimaryStage().setOnCloseRequest(event -> applicationContext.close());
    }

    private void setupCamera3D(Point3D lookAt, int worldHeight) {
        GameScene gameScene = getGameScene();
        gameScene.setFPSCamera(true);

        Camera3D camera3D = gameScene.getCamera3D();
        camera3D.setOverRotationXAllowed(true);

        TransformComponent transform = camera3D.getTransform();
        transform.setX(lookAt.getX());
        transform.setY(-(worldHeight + CAMERA_Y_OFFSET));
        transform.setZ(-CAMERA_Z_OFFSET);
        transform.lookAt(lookAt);

        double tpfMoveSpeed = camera3D.getMoveSpeed() * 0.017;
        onKey(KeyCode.W, camera3D::moveForward);
        onKey(KeyCode.S, camera3D::moveBack);
        onKey(KeyCode.A, camera3D::moveLeft);
        onKey(KeyCode.D, camera3D::moveRight);
        onKey(KeyCode.UP, () -> {
            Point3D v = camera3D.getTransform().getDirection3D().multiply(tpfMoveSpeed);
            camera3D.getTransform().translate3D(0, -v.getY(), 0);
        });
        onKey(KeyCode.DOWN, () -> {
            Point3D v = camera3D.getTransform().getDirection3D().multiply(tpfMoveSpeed);
            camera3D.getTransform().translate3D(0, v.getY(), 0);
        });
    }

    public Point3D getWorldCenter(World world, float radiusOfHexagon) {
        int middleRow = world.getRowCount() / 2;
        int middleColumn = world.getColumnCount() / 2;
        FlatHexagon exampleHexagon = new FlatHexagon(radiusOfHexagon);
        double x = 0;
        double z = 0;
        if((middleRow + 1) % 2 == 0) {
            x += exampleHexagon.getBigLeg();
        }
        x += (exampleHexagon.getBigLeg() * 2) * middleRow;
        z += (exampleHexagon.getRadius() * 2 - exampleHexagon.getMinLeg()) * middleColumn;
        return new Point3D(x, 0, z);
    }
}
