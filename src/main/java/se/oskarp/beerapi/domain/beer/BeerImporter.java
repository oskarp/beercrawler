package se.oskarp.beerapi.domain.beer;

import java.util.List;

/**
 * A contract for importing beers from somewhere
 */
public interface BeerImporter {

    /**
     * Performs the actual import
     * @return The result of the import
     */
    List<Beer> doImport();
}
