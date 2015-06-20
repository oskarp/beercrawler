package se.oskarp.beerapi.persistence;

import se.oskarp.beerapi.beer.Beer;

import java.util.List;

/**
 * The BeerRepository interface defines what methods a BeerRepository needs to implement.
 *
 * Created by oskar on 12/06/15.
 */
public interface BeerRepository {
    /**
     * Saves the List of Beers.
     * @param beer
     */
    void save(List<Beer> beer);

    /**
     * Returns the entire list of Beer that has been saved.
     * @return List
     */
    List<Beer> fetchAll();

    /**
     * Removes the entire list of Beers from persistancy. We have no need to save the beers many times as
     * we store the changes separately.
     */

    void cleanUp();

}
