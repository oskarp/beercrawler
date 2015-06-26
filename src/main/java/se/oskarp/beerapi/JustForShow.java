package se.oskarp.beerapi;

import com.google.inject.Inject;

import javax.inject.Named;

/**
 * Simple demonstration of how DI works
 * with Guice..
 */
public class JustForShow {

    @Inject
    @Named("beercrawler.bolaget.api.url")
    private String bolagetApiUrl;

    public String getBolagetApiUrl() {
        return bolagetApiUrl;
    }
}
