package se.oskarp.beerapi.infrastructure.beer;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import se.oskarp.beerapi.domain.beer.Beer;
import se.oskarp.beerapi.domain.beer.BeerRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.boon.Boon.puts;


/**
 * BeerRepositoryFS persists Beers to the File System.
 *
 * Created by oskar on 12/06/15.
 */
public class BeerRepositoryFS implements BeerRepository {

    private String path;
    private ObjectMapper mapper = JsonFactory.create();

    /**
     * Constructor that allows for setting the path where the repo is persisted.
     *
     * @param path If path is specified, it saves the file to there. Leave empty or "" if no preference.
     */

    public BeerRepositoryFS(String path) {
        if (path.equals("") || path.isEmpty()) {
            Date date = new Date();
            this.path = date.getTime() + ".json";
        }
        else {
            this.path = path;
        }
    }

    /**
     * Saves the list of Beers to disk using Boons .
     * @param beers The list of Beers to be persisted.
     */
    public void save(List<Beer> beers) {
        try {
            FileOutputStream fos = new FileOutputStream(this.path);
            mapper.writeValue(fos, beers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * fetchAll uses Boon to read the beers from disk and make objects of them.
     * @return
     */
    public List<Beer> fetchAll() {
        List<Beer> beers = new ArrayList<>();
        try {
            beers = mapper.readValue(new FileInputStream(this.path), List.class, Beer.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return beers;
    }

    public void cleanUp() {
        File file = new File(this.path);

        if (file.exists()) {
            file.delete();
        }
    }
}
