package se.oskarp.beerapi.persistence;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import se.oskarp.beerapi.event.Event;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Saves events to file system.
 *
 * Created by oskar on 17/06/15.
 */
public class EventRepositoryFS implements EventRepository {
    private String path;
    private ObjectMapper mapper = JsonFactory.create();

    /**
     *
     * @param path Leave empty or "" for default.
     */
    public EventRepositoryFS(String path) {
        if(path.isEmpty() || path.equals("")) {
            this.path = "events.json";

        } else {
            this.path = path;

        }
    }

    /**
     * Saves List of events to disk.
     * @param events
     */

    @Override
    public void save(List<Event> events) {
        try {
            FileOutputStream fos = new FileOutputStream(this.path);
            //puts(mapper.writeValueAsString(events));
            mapper.writeValue(fos, events);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
