package se.oskarp.beerapi;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration extends AbstractModule {

    @Override
    protected void configure() {
        bindProperties();
    }

    /**
     * Binds all properties in the application properties file
     */
    private void bindProperties() {
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        Names.bindProperties(binder(), properties);
    }
}
