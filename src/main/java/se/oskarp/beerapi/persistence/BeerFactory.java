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

    public BeerRepository getRepository() {
        BeerRepositoryFS repo = new BeerRepositoryFS("");
        repos.add(repo);
        return repo;

    }
}
