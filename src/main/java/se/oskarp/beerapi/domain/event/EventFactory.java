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
    private final List<Beer> localCache;

    public EventFactory(List<Beer> localCache) {
        this.localCache = localCache;
    }

    /**
     * Creates {@link Event} instances based on the difference
     * between the provided argument and the local beer cache.
     * @param remote The "new" list of beers i.e. the one that just came from the API.
     * @return List of Events that differs between the two sets of Beers
     */
    public List<Event> create(final List<Beer> remote) {

        List<Event> result = new ArrayList<>();
        List<Beer> addList = diffBeer(remote, localCache);
        List<Beer> deleteList = diffBeer(localCache, remote);
        // Find things that simply changed we create Event.Action.Update
        for(Beer b: addList) {
            for(Beer b2: deleteList) {
                if(b.getDrink_number() == b2.getDrink_number()) {
                    Event e = new Event(b.getDrink_number(), Event.Action.Update, b, b2);
                    result.add(e);
                    addList.remove(b);
                    deleteList.remove(b2);
                }
            }
        }

        // For new additions to the beer list we generate Event.Action.Create

        for(Beer b: addList) {
            Event e = new Event(b.getDrink_number(), Event.Action.Create, new Beer(), b);
            result.add(e);
        }

        // For deletions from the beer list we generate Event.Action.Delete

        for(Beer b: deleteList) {
            Event e = new Event(b.getDrink_number(), Event.Action.Delete, b, new Beer() );
            result.add(e);
        }

        return result;
    }

    private List<Beer> diffBeer(List<Beer> newList, List<Beer> oldList) {
        List<Beer> changeList = new ArrayList<>();
        for(Beer b: newList) {
            if(!oldList.contains(b)) {
                changeList.add(b);
            }
        }
        return changeList;
    }
}
