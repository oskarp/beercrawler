package se.oskarp.beerapi.application;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(BeerProxy.class);
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
        logger.info("Remote api call in progress, this might take a while..");

        List<Beer> remote = beerImporter.doImport();
        logger.info(String.format("Remote api call resulted in %s beers", remote.size()));

        List<Beer> local = localCache.fetchAll();
        logger.info(String.format("Local cache lookup resulted in %s beers", local.size()));

        if(!remote.equals(local)) {
            localCache.save(remote);
            logger.info("The fetched list differed from cache, overwriting cache.");
        } else
        {
            logger.info("No changes detected, doing nothing");
        }

        eventRepository.save(new EventFactory(local).create(remote));
        logger.info("Events have been relayed.");
    }
}
