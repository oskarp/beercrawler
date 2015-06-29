package se.oskarp.beerapi.application;

import com.google.inject.Inject;
import se.oskarp.beerapi.domain.beer.Beer;
import se.oskarp.beerapi.domain.beer.BeerFactory;
import se.oskarp.beerapi.domain.beer.BeerRepository;
import se.oskarp.beerapi.domain.event.EventFactory;
import se.oskarp.beerapi.domain.event.EventRepository;

import java.util.List;

public class BeerProxy {

    private final BeerRepository beerRepository;
    private final BeerFactory beerFactory;
    private final EventRepository eventRepository;

    @Inject
    BeerProxy(BeerRepository beerRepository,
              BeerFactory beerFactory,
              EventRepository eventRepository) {

        this.beerRepository = beerRepository;
        this.beerFactory = beerFactory;
        this.eventRepository = eventRepository;
    }

    public void execute() {
        System.out.println("Remote api call in progress, this might take a while..");

        List<Beer> remote = beerFactory.create();
        System.out.println(String.format("Remote api call resulted in %s beers", remote.size()));

        List<Beer> local = beerRepository.fetchAll();
        System.out.println(String.format("Local cache lookup resulted in %s beers", local.size()));

        eventRepository.save(new EventFactory(local).create(remote));
        System.out.println("Events have been saved, hooray!");
    }
}
