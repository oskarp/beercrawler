package se.oskarp.beerapi.application;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

public class Main {

    private static String PROPERTIES_FILE_NAME = "application.properties";


    public static void main(String[] args) throws ParseException, XMLStreamException, IOException {
        Logger logger = LoggerFactory.getLogger("se.oskarp.beerapi.application.Main");
        logger.info("BeerProxy™ execution start");

        Injector injector = Guice.createInjector(
                new Configuration(createProperties()));

        injector.getInstance(BeerProxy.class).execute();

        logger.info("BeerProxy™ execution end");
    }

    private static Properties createProperties() {
        Properties result = new Properties();
        Logger logger = LoggerFactory.getLogger("se.oskarp.beerapi.application.Main");
        try {
            result.load(new FileReader(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            logger.error(e.getMessage());
            System.exit(0);
        }

        return result;
    }
}
