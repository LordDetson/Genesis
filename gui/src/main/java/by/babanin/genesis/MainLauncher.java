package by.babanin.genesis;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import by.babanin.genesis.gui.MainApplication;
import javafx.application.Application;

@SpringBootApplication
public class MainLauncher {

    public static void main(String[] args) {
        Application.launch(MainApplication.class, args);
    }
}
