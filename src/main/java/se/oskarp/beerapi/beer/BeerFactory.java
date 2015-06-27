package se.oskarp.beerapi.beer;

import se.oskarp.beerapi.beer.Beer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This class construct {@link Beer} instances through parsing XML formatted
 * data from our beloved "Systembolaget".
 */
public class BeerFactory {

    private static String DATE_FORMAT = "yyyy-MM-dd";

    /**
     *
     * @param is InputStream of a Systembolaget XML file to be parsed.
     * @return A list of Beer objects
     * @throws XMLStreamException
     * @throws IOException
     * @throws ParseException
     */

    public List<Beer> create(InputStream is) throws XMLStreamException, IOException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        XMLInputFactory xmlFactory = XMLInputFactory.newFactory();
        XMLStreamReader streamReader = xmlFactory.createXMLStreamReader(is);

        List<Beer> result = new ArrayList<>();
        String content = null;
        Beer currentBeer = null;

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

        return result;
    }
}


