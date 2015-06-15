package se.oskarp.beerapi.persistence;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * This class simply rotates four repositories (in reality four json files).
 *
 *
 * Created by oskar on 12/06/15.
 */
public class BeerFactory {

    private List<BeerRepository> repos = new ArrayList<>();

    public BeerRepositoryFS getRepository(String type) {
        switch(type) {
            case "beer":
                BeerRepositoryFS repo = new BeerRepositoryFS("");
                repos.add(repo);
                return repo;
            case "event":
                break;
        }
         // TODO: This should throw a unsupported repository exception
        return null;
    }
}
