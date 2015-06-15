package se.oskarp.beerapi.beer;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oskar on 11/06/15.
 */
public class BeerTest extends TestCase {

    public void testToMap() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Beer beer = new Beer("Åbro Orginal", "Damm", "Lager", 213124, 23.2, "Åbro", "Sverige", "", 5.2, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro");
        Map<String, Object> firstMap = beer.toMap();

        Map<String, Object> secondMap = new HashMap<>();
        secondMap.put("Name", "Åbro Orginal");
        secondMap.put("Description", "Damm");
        secondMap.put("Style", "Lager");
        secondMap.put("Drink_number", 213124);
        secondMap.put("Price", 23.2);
        secondMap.put("Brewery", "Åbro");
        secondMap.put("Country", "Sverige");
        secondMap.put("Origin", "");
        secondMap.put("Abv", 5.2);
        secondMap.put("Packaging", "flaska");
        secondMap.put("Varugrupp", "öl");
        secondMap.put("Ekologisk", true);
        secondMap.put("Volym", 50.0);
        secondMap.put("Salestart", formatter.parse("2005-10-01"));
        secondMap.put("Supplier", "Åbro");

        assertEquals(firstMap, secondMap);

    }

}