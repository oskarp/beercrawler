package se.oskarp.beerapi.domain.beer;

import java.io.InputStream;

public interface BeerSource {
    InputStream getInputStream();
}
