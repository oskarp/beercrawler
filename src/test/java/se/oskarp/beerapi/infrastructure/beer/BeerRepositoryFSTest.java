package se.oskarp.beerapi.infrastructure.beer;

import org.junit.Test;
import se.oskarp.beerapi.domain.beer.Beer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by oskar on 12/06/15.
 */
public class BeerRepositoryFSTest {

    @Test
    public void saveAndRetrieve() throws Exception {
        BeerRepositoryFS rfs = new BeerRepositoryFS("");
        List<Beer> beers = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Beer b1 = new Beer("Åbro Orginal", "Damm", "Lager", 213124, 23.2, "Åbro", "Sverige", "", 5.2, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro", "");
        Beer b2 = new Beer("Norrlands guld", "Hopps", "Lager", 14127, 19.5, "Spendrups", "Sverige", "", 5, "flaska", "öl", true, 330, formatter.parse("2005-10-01"), "Spendrups", "");
        Beer b3 = new Beer("Åbro Orginal", "Damm", "Lager", 213124, 23.2, "Åbro", "England", "", 5.2, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro", "");
        Beer b4 = new Beer("Falcon", "Njet", "Lager", 95483, 20, "Falcon", "Sverige", "", 4.8, "flaska", "öl", true, 335, formatter.parse("1999-10-01"), "Spendrups", "");

        beers.add(b1);
        beers.add(b2);
        beers.add(b3);
        beers.add(b4);
        rfs.save(beers);
        List<Beer> newBeers = rfs.fetchAll();
        assertEquals(beers, newBeers);
        rfs.cleanUp();
    }
}