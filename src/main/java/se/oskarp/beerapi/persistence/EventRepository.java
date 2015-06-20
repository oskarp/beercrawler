package se.oskarp.beerapi.persistence;

import se.oskarp.beerapi.events.Event;

import java.util.List;

/**
 * EventRepositories only have to implement the save method, as its primary function is to pass data along.
 *
 * Created by oskar on 16/06/15.
 */
public interface EventRepository {
    void save(List<Event> events);
}
