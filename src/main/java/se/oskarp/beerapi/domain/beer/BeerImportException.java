package se.oskarp.beerapi.domain.beer;

public class BeerImportException extends RuntimeException {

    public BeerImportException(Throwable t) {
        super(t);
    }
}
