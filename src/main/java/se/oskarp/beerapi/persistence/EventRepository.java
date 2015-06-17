package se.oskarp.beerapi.persistence;

import se.oskarp.beerapi.events.Event;

import java.util.List;

/**
 * Created by oskar on 16/06/15.
 */
public interface EventRepository {
    void save(List<Event> events);
}
