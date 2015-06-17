package se.oskarp.beerapi.persistence;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import se.oskarp.beerapi.events.Event;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.boon.Boon.puts;

/**
 * Created by oskar on 17/06/15.
 */
public class EventRepositoryFS implements EventRepository {
    private String path;
    private ObjectMapper mapper = JsonFactory.create();

    public EventRepositoryFS(String path) {
        if(path.isEmpty() || path.equals("")) {
            this.path = "events.json";

        } else {
            this.path = path;

        }
    }
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
