package se.oskarp.beerapi.domain.event;

import se.oskarp.beerapi.domain.beer.Beer;

import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to create {@link Event} instances.
 *
 * Created by oskar on 09/06/15.
 */
public class EventFactory {

    /**
     * The local beer cache
     */
    private List<Beer> localCache;

    public EventFactory(List<Beer> localCache) {
        this.localCache = localCache;
    }

    /**
     * Creates {@link Event} instances based on the difference
     * between the provided argument and the local beer cache.
     * @param remote The "new" list of beers i.e. the one that just came from the API.
     * @return List of Events that differs between the two sets of Beers
     */
    public List<Event> create(List<Beer> remote) {

        List<Event> result = new ArrayList<>();

        // Find things that simply changed we create Event.Action.Update
        List<Integer> updated = new ArrayList<>();
        for (Beer b: remote) {
            for (Beer b2: localCache) {
                if (b.getDrink_number() == b2.getDrink_number()) {
                    updated.add(b.getDrink_number());
                    result.add(new Event(b.getDrink_number(), Event.Action.Update, b, b2));
                }
            }
        }

        // For new additions to the beer list we create Event.Action.Create

        for (Beer b: remote) {
            if (!updated.contains(b.getDrink_number())) {
                result.add(
                        new Event(b.getDrink_number(), Event.Action.Create, new Beer(), b));
            }
        }

        // For deletions from the beer list we create Event.Action.Delete
        for (Beer b : localCache) {
            if (!updated.contains(b.getDrink_number())) {
                result.add(
                        new Event(b.getDrink_number(), Event.Action.Delete, b, new Beer()));
            }
        }

        return result;
    }
}
