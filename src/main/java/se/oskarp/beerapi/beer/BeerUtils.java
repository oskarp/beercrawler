package se.oskarp.beerapi.beer;

import se.oskarp.beerapi.event.Event;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by oskar on 09/06/15.
 */
public class BeerUtils {

    /**
     * Compares two lists of Beer and finds Beers that differ between the two lists.
     * @param newList The original list
     * @param oldList The list that is to be compared against.
     * @return List of Beer that has changed.
     */
    public static List<Beer> compare(List<Beer> newList, List<Beer> oldList) {
        List<Beer> changeList = new ArrayList<>();
        for(Beer b: newList) {
            if(!oldList.contains(b)) {
                changeList.add(b);
            }

        }
        return changeList;
    }

    /**
     *
     * @param addList The "new" list of beers i.e. the one that just came from the API.
     * @param deleteList The "old" list of beers. USually the one fetched from the local FS.
     * @return List of Events that differs between the two sets of Beers
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */

    public static List<Event> generateEvents(List<Beer> addList, List<Beer> deleteList) throws InvocationTargetException, IllegalAccessException {

        List<Event> eventList = new ArrayList<>();

        // Find things that simply changed we generate Event.Action.Update

        for(Beer b: addList) {
            for(Beer b2: deleteList) {
                if(b.getDrink_number() == b2.getDrink_number()) {
                    Event e = new Event(b.getDrink_number(), Event.Action.Update, b, b2);
                    eventList.add(e);
                    addList.remove(b);
                    deleteList.remove(b2);
                }
            }
        }

        // For new additions to the beer list we generate Event.Action.Create

        for(Beer b: addList) {
            Event e = new Event(b.getDrink_number(), Event.Action.Create, new Beer(), b);
            eventList.add(e);
        }

        // For deletions from the beer list we generate Event.Action.Delete

        for(Beer b: deleteList) {
            Event e = new Event(b.getDrink_number(), Event.Action.Delete, b, new Beer() );
            eventList.add(e);
        }

        return eventList;
    }
}
