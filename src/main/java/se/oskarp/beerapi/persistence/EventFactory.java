package se.oskarp.beerapi.persistence;

import se.oskarp.beerapi.events.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oskar on 16/06/15.
 */
public class EventFactory {
    private EventRepository repo;

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
