package se.oskarp.beerapi.systemet_api;

import se.oskarp.beerapi.beer.Beer;
import se.oskarp.beerapi.events.Event;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by oskar on 09/06/15.
 */
public class SystemetApiChangesetComparator {

    public static List<Beer> compare(List<Beer> newList, List<Beer> oldList) {
        List<Beer> changeList = new ArrayList<>();
        for(Beer b: newList) {
            if(!oldList.contains(b)) {
                changeList.add(b);
            }

        }
        return changeList;
    }

    public static List<Event> generateEvents(List<Beer> addList, List<Beer> deleteList) throws InvocationTargetException, IllegalAccessException {

        List<Event> eventList = new ArrayList<>();

        // Find things that simply changed we generate Event.Action.Update

        for(Beer b: addList) {
            for(Beer b2: deleteList) {
                if(b.getDrink_number() == b2.getDrink_number()) {
                    Event e = new Event(b.getDrink_number(), Event.Action.Update, b.toMap(), b2.toMap());
                    eventList.add(e);
                    addList.remove(b);
                    deleteList.remove(b2);
                }
            }
        }

        // For new additions to the beer list we generate Event.Action.Create

        for(Beer b: addList) {
            Event e = new Event(b.getDrink_number(), Event.Action.Create, new HashMap<String, Object>() ,b.toMap());
            eventList.add(e);
        }

        // For deletions from the beer list we generate Event.Action.Delete

        for(Beer b: deleteList) {
            Event e = new Event(b.getDrink_number(), Event.Action.Delete, b.toMap(), new HashMap<String, Object>() );
            eventList.add(e);
        }

        return eventList;
    }


}
