package se.oskarp.beerapi.systemet_api;

import se.oskarp.beerapi.beer.Beer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by oskar on 09/06/15.
 */
public class SystemetApiClientLocal {
    //private String origin = "http://www.systembolaget.se/api/assortment/products/xml";
    private String origin = "/Users/opeadmin/Desktop/xml2.xml";

    public ArrayList<Beer> parse() throws XMLStreamException, IOException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Beer> drinkList = new ArrayList<>();
        Beer currentBeer = null;
        String content = null;
        XMLInputFactory xmlFactory = XMLInputFactory.newFactory();
        XMLStreamReader streamReader = xmlFactory.createXMLStreamReader(new FileInputStream(this.origin));
        while(streamReader.hasNext()) {
            int event = streamReader.next();

            switch (event) {

                case XMLStreamConstants.START_ELEMENT:
                    if(streamReader.getLocalName().equals("artikel")) {
                        currentBeer = new Beer();
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    content = streamReader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    if(currentBeer != null) {

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
                                currentBeer.setVolym(Double.parseDouble(content));
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
                                    drinkList.add(currentBeer);
                                }
                                break;
                        }
                    }
            }

        }
        return drinkList;

    }

}
