package se.oskarp.beerapi.application;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import se.oskarp.beerapi.domain.beer.BeerImporter;
import se.oskarp.beerapi.domain.beer.BeerRepository;
import se.oskarp.beerapi.domain.event.EventRepository;
import se.oskarp.beerapi.infrastructure.beer.BeerImporterSystemetApi;
import se.oskarp.beerapi.infrastructure.beer.BeerRepositoryFS;
import se.oskarp.beerapi.infrastructure.event.EventRepositoryREST;
import se.oskarp.beerapi.infrastructure.event.EventRepositoryRESTwHeaders;

import java.util.Properties;

public class Configuration extends AbstractModule {

    private Properties properties;

    public Configuration(Properties properties) {
        this.properties = properties;
    }

    @Override
    protected void configure() {
        bind(BeerRepository.class).toInstance(new BeerRepositoryFS("local.json"));
        bind(EventRepository.class).to(EventRepositoryRESTwHeaders.class);
        bind(BeerImporter.class).to(BeerImporterSystemetApi.class);

        if (properties != null) {
            Names.bindProperties(binder(), properties);
        }
    }
}
