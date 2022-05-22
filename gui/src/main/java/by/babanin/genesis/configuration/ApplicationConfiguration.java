package by.babanin.genesis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.almasb.fxgl.app.FXGLApplication;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public FXGLApplication getFxglApplication() {
        return new FXGLApplication();
    }
}
