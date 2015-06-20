package se.oskarp.beerapi.persistence;

import se.oskarp.beerapi.events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * The EventFactory is a structured way to initialize our EventRepository.
 * Events are meant to simply be persisted by being pushed to another service.
 * The Factory is expandable to let us utilize other methods however.
 *
 * For now we have EventRepositoryFS for File system and EventRepositoryREST for dispatching to other services.
 *
 * Created by oskar on 16/06/15.
 */
public class EventFactory {
    private EventRepository repo;

    /**
     * Returns a EventRepository of the specified type.
     *
     * @param type Either EventRepositoryFS or EventRepositoryREST. Leave blank for default.
     * @return
     */
    public EventRepository getRepository(String type) {

        switch(type) {
            case "fs":
                if (this.repo == null || this.repo.getClass().toString() != "EventRepositoryFS") {
                    this.repo = new EventRepositoryFS("");
                    return repo;
                } else {
                    return this.repo;
                }
            default: {
                if (this.repo == null || this.repo.getClass().toString() != "EventRepositoryREST") {
                    this.repo = new EventRepositoryFS("");
                    return repo;
                } else {
                    return this.repo;
                }
            }
        }
    }
}
