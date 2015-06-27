package se.oskarp.beerapi.persistence;

import org.boon.HTTP;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import se.oskarp.beerapi.event.Event;
import se.oskarp.beerapi.event.EventRepository;

import java.util.List;

/**
 * Created by oskar on 20/06/15.
 */
public class EventRepositoryREST implements EventRepository {

    final String url = "http://homestead.app/event";
    final private ObjectMapper mapper = JsonFactory.create();


    @Override
    public void save(List<Event> events) {
        String result = HTTP.postJSON(this.url, this.mapper.writeValueAsString(events));
        System.out.println(result);
    }
}
