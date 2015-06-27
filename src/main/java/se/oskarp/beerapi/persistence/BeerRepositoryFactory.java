package se.oskarp.beerapi.persistence;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * The BeerRepositoryFactory is a structured way to initialize our BeerRepository.
 *
 *
 * Created by oskar on 12/06/15.
 */
public class BeerRepositoryFactory {

    private final List<BeerRepository> repos = new ArrayList<>();

    /**
     * For now we only offer FileSystem storage of the beer objects. We should keep a backlog of Beer serializations however
     * in case of something breaking. The extent of this backlog is to be discussed.
     *
     * @return BeerRepository Returns a repository to store the Beer objects.
     */
    public BeerRepository getRepository() {
        BeerRepositoryFS repo = new BeerRepositoryFS("");
        repos.add(repo);
        return repo;

    }
}
