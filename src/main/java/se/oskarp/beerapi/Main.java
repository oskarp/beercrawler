package se.oskarp.beerapi;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger("se.oskarp.beerapi.main");


        logger.debug("--- Begin execution          ---");

        Injector injector = Guice.createInjector(new Configuration());

        JustForShow justForShow = injector.getInstance(JustForShow.class);
        logger.debug("url: " + justForShow.getBolagetApiUrl());

        logger.debug("--- End execution          ---");
    }
}
