package se.oskarp.beerapi.infrastructure.beer;

import com.google.inject.Inject;
import se.oskarp.beerapi.domain.beer.Beer;
import se.oskarp.beerapi.domain.beer.BeerImportException;
import se.oskarp.beerapi.domain.beer.BeerImporter;

import javax.inject.Named;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This class construct {@link Beer} instances through parsing XML formatted
 * data from our beloved "Systembolaget".
 */
public class BeerImporterSystemetApi implements BeerImporter {

    private static String DATE_FORMAT = "yyyy-MM-dd";

    private URL url;

    @Inject
    public BeerImporterSystemetApi(@Named("beercrawler.bolaget.api.url") String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * This method performs the actual import
     * @return The imported beer instances
     */

    public List<Beer> doImport() {
        List<Beer> result = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        XMLStreamReader streamReader = newStreamReader();

        String content = null;
        Beer currentBeer = null;

        try {
            while (streamReader.hasNext()) {

                int event = streamReader.next();

                switch (event) {

                    case XMLStreamConstants.START_ELEMENT:
                        if (streamReader.getLocalName().equals("artikel")) {
                            currentBeer = new Beer();
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        content = streamReader.getText().trim();
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        if (currentBeer == null) {
                            break;
                        }

                        switch (streamReader.getLocalName()) {
                            case "Varnummer":
                                currentBeer.setDrink_number(Integer.parseInt(content));
                                break;
                            case "Namn":
                                currentBeer.setName(content);
                                break;
                            case "Prisinklmoms":
                                currentBeer.setPrice(Double.parseDouble(content));
                                break;
                            case "Volymiml":
                                currentBeer.setVolume(Double.parseDouble(content));
                                break;
                            case "Saljstart":
                                currentBeer.setSalestart(formatter.parse(content));
                                break;
                            case "Varugrupp":
                                currentBeer.setVarugrupp(content.toLowerCase());
                                break;
                            case "Forpackning":
                                currentBeer.setPackaging(content);
                                break;
                            case "Ursprung":
                                currentBeer.setOrigin(content);
                                break;
                            case "Ursprunglandnamn":
                                currentBeer.setCountry(content);
                                break;
                            case "Producent":
                                currentBeer.setBrewery(content);
                                break;
                            case "Leverantor":
                                currentBeer.setSupplier(content);
                                break;
                            // TODO: Implement this to account for (as in remove) the % sign that the fucktard API sends.
                            case "Alkoholhalt":
                                //currentBeer.abv = Double.parseDouble(content);
                                break;
                            case "Ekologisk":
                                currentBeer.setEkologisk(Boolean.parseBoolean(content));
                                break;
                            case "RavarorBeskrivning":
                                currentBeer.setDescription(content);
                                break;
                            case "artikel":
                                if (currentBeer.getVarugrupp().equals("öl")) {
                                    result.add(currentBeer);
                                }
                                break;
                        }
                }
            }
        } catch (Exception e) {
            throw new BeerImportException(e);
        }

        return result;
    }

    private XMLStreamReader newStreamReader() {
        XMLInputFactory xmlFactory = XMLInputFactory.newFactory();

        try {
            return xmlFactory.createXMLStreamReader(url.openStream());
        } catch (Exception e) {
            throw new BeerImportException(e);
        }
    }
}


