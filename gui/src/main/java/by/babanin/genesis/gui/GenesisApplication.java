package by.babanin.genesis.gui;

import static com.almasb.fxgl.dsl.FXGL.getPrimaryStage;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

import by.babanin.genesis.GenesisLauncher;

public class GenesisApplication extends GameApplication {

    private static final int APP_WIDTH = 1280;
    private static final int APP_HEIGHT = 720;

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void initSettings(GameSettings settings) {
        settings.setTitle("Genesis");
        settings.setWidth(APP_WIDTH);
        settings.setHeight(APP_HEIGHT);
        settings.set3D(true);
    }

    @Override
    protected void initGame() {
        initializeSpringApplicationContext();
    }

    private void initializeSpringApplicationContext() {
        applicationContext = new SpringApplicationBuilder()
                .sources(GenesisLauncher.class)
                .run(GenesisLauncher.getArguments());
        getPrimaryStage().setOnCloseRequest(event -> applicationContext.close());
    }
}
