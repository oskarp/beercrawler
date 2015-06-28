package se.oskarp.beerapi.domain.beer;

import com.google.inject.Inject;

import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class BeerUrlSource implements BeerSource {

    private URL url;

    @Inject
    BeerUrlSource(@Named("beercrawler.bolaget.api.url") String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public InputStream getInputStream() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new BeerFactoryException(e);
        }
    }
}
