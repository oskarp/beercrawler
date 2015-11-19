package se.oskarp.beerapi.infrastructure.event;

import com.google.inject.Inject;
import org.boon.HTTP;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import se.oskarp.beerapi.domain.event.Event;
import se.oskarp.beerapi.domain.event.EventRepository;

import javax.inject.Named;
import java.util.List;

/**
 * Created by oskar on 20/06/15.
 */
public class EventRepositoryREST implements EventRepository {

    private final String url;
    private final ObjectMapper mapper = JsonFactory.create();


    @Inject
    public EventRepositoryREST(@Named("beercrawler.event.repository.url") String url) {
        this.url = url;
    }

    @Override
    public void save(List<Event> events) {
        String result = HTTP.postJSON(this.url, this.mapper.writeValueAsString(events));
        System.out.println(result);
    }
}
