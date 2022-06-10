package by.babanin.genesis.gui.scene;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;

import net.rgielen.fxweaver.core.FxWeaver;

@Component
public class CustomSceneFactory extends SceneFactory {

    private final FxWeaver fxWeaver;

    public CustomSceneFactory(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @NotNull
    @Override
    public FXGLMenu newMainMenu() {
        return new CustomMenu(fxWeaver);
    }
}
