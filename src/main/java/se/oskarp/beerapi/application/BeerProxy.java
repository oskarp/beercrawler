package se.oskarp.beerapi.application;

import com.google.inject.Inject;
import se.oskarp.beerapi.domain.beer.Beer;
import se.oskarp.beerapi.domain.beer.BeerImporter;
import se.oskarp.beerapi.domain.beer.BeerRepository;
import se.oskarp.beerapi.domain.event.EventFactory;
import se.oskarp.beerapi.domain.event.EventRepository;

import java.util.List;

/**
 * Encapsulates the entire algorithm of the
 * proxy functionality.
 */
public class BeerProxy {

    private final BeerRepository localCache;
    private final BeerImporter beerImporter;
    private final EventRepository eventRepository;

    @Inject
    BeerProxy(BeerRepository localCache,
              BeerImporter beerImporter,
              EventRepository eventRepository) {

        this.localCache = localCache;
        this.beerImporter = beerImporter;
        this.eventRepository = eventRepository;
    }

    public void execute() {
        System.out.println("Remote api call in progress, this might take a while..");

        List<Beer> remote = beerImporter.doImport();
        System.out.println(String.format("Remote api call resulted in %s beers", remote.size()));

        List<Beer> local = localCache.fetchAll();
        System.out.println(String.format("Local cache lookup resulted in %s beers", local.size()));

        eventRepository.save(new EventFactory(local).create(remote));
        System.out.println("Events have been saved, hooray!");
    }
}
