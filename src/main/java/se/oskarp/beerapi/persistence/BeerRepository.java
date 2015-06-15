package se.oskarp.beerapi.persistence;

import se.oskarp.beerapi.beer.Beer;

import java.util.List;

/**
 * Created by oskar on 12/06/15.
 */
public interface BeerRepository {
    void save(List<Beer> beer);
    List<Beer> fetchAll();
    void cleanUp();

}
