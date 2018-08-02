package example.magic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.Collections;

public class EnvironmentPostProcessorExample implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment,
                                       SpringApplication application) {
        MapPropertySource source =
                new MapPropertySource("mycustomsource", Collections.singletonMap("my.mega.custom.property", "true"));
        environment.getPropertySources().addLast(source);
    }

}
