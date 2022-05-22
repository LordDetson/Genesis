package by.babanin.genesis.gui;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.almasb.fxgl.app.FXGLApplication;
import com.almasb.fxgl.app.GameApplication;

import by.babanin.genesis.gui.event.StageReadyEvent;
import javafx.stage.Stage;

@Component
public class GenesisLauncher implements ApplicationListener<StageReadyEvent> {

    private final GenesisApplication genesisApplication;
    private final FXGLApplication fxglApplication;

    public GenesisLauncher(GenesisApplication genesisApplication, FXGLApplication fxglApplication) {
        this.genesisApplication = genesisApplication;
        this.fxglApplication = fxglApplication;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        // initialize static fields of FXGLApplication: app, settings, engine, mainWindow
        // but mainWindow is EmbeddedPaneWindow
        // EmbeddedPaneWindow work incorrect and we should ignore FXGLPane
        GameApplication.embeddedLaunch(genesisApplication);
        // initialize new engine and mainWindow
        // mainWindow is PrimaryStageWindow
        Stage stage = event.getStage();
        fxglApplication.start(stage);
    }
}
