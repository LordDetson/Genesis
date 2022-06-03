package by.babanin.genesis;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.almasb.fxgl.app.GameApplication;

import by.babanin.genesis.gui.GenesisApplication;

@SpringBootApplication
public class GenesisLauncher {

    private static String[] arguments;

    public static void main(String[] args) {
        GenesisLauncher.arguments = args;
        GameApplication.launch(GenesisApplication.class, args);
    }

    public static String[] getArguments() {
        return arguments;
    }
}
