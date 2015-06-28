package se.oskarp.beerapi.application;

import com.google.inject.Guice;
import com.google.inject.Injector;

import javax.xml.stream.XMLStreamException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

public class Main {

    private static String PROPERTIES_FILE_NAME = "application.properties";

    public static void main(String[] args) throws ParseException, XMLStreamException, IOException {
        System.out.println("--- Begin execution          ---");

        Injector injector = Guice.createInjector(
                new Configuration(createProperties()));

        injector.getInstance(BeerProxy.class).execute();

        System.out.println("--- End execution            ---");
    }

    private static Properties createProperties() {
        Properties result = new Properties();

        try {
            result.load(new FileReader(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        return result;
    }
}
