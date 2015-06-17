package se.oskarp.beerapi.persistence;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import se.oskarp.beerapi.beer.Beer;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.boon.Boon.puts;


/**
 * Created by oskar on 12/06/15.
 */
public class BeerRepositoryFS implements BeerRepository {

    private String path;
    private ObjectMapper mapper = JsonFactory.create();

    public BeerRepositoryFS(String path) {
        if (path.equals("") || path.isEmpty()) {
            Date date = new Date();
            this.path = date.getTime() + ".json";
        }
        else {
            this.path = path;
        }
    }

    public void save(List<Beer> beers) {
        try {
            FileOutputStream fos = new FileOutputStream(this.path);
            //puts(mapper.writeValueAsString(beers));
            mapper.writeValue(fos, beers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     // TODO: This shouldn't instantiate an empty arraylist and then discarding it. For now the option is to return null, which no.. just no.
    public List<Beer> fetchAll() {
        ObjectMapper mapper =  JsonFactory.create();
        List<Beer> beers = new ArrayList<>();

        try {
            beers = mapper.readValue(new FileInputStream(this.path), List.class, Beer.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        puts("beers", beers);

        return beers;
    }

    public void cleanUp() {
        File file = new File(this.path);

        if (file.exists()) {
            file.delete();
        }
    }
}
