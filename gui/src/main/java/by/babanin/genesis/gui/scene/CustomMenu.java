package by.babanin.genesis.gui.scene;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;

import by.babanin.genesis.gui.controller.MainMenuController;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;

public class CustomMenu extends FXGLMenu {

    public CustomMenu(FxWeaver fxWeaver) {
        super(MenuType.MAIN_MENU);
        FxControllerAndView<MainMenuController, StackPane> mainMenuCtrlAndView = fxWeaver.load(MainMenuController.class);
        mainMenuCtrlAndView.getView().ifPresent(node -> {
            node.setPrefWidth(getAppWidth());
            node.setPrefHeight(getAppHeight());
            addChild(node);
        });
    }
}
