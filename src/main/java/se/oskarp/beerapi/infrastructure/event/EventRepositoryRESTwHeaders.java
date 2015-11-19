package se.oskarp.beerapi.infrastructure.event;

import com.google.inject.Inject;
import org.boon.HTTP;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import se.oskarp.beerapi.domain.event.Event;
import se.oskarp.beerapi.domain.event.EventRepository;

import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oskar_000 on 2015-11-19.
 */
public class EventRepositoryRESTwHeaders implements EventRepository{

    private final String url;
    private final String token;
    private final ObjectMapper mapper = JsonFactory.create();


    @Inject
    public EventRepositoryRESTwHeaders(@Named("beercrawler.event.repository.url") String url, @Named("beercrawler.event.repository.token") String token) {
        this.url = url;
        this.token = token;
    }

    @Override
    public void save(List<Event> events) {
        Map headers = new HashMap();
        headers.put("X-Authorization", this.token);
        String result = HTTP.postWithContentType(this.url, headers,"application/json", this.mapper.writeValueAsString(events));
        //String result = HTTP.postJSON(this.url, this.mapper.writeValueAsString(events));
        System.out.println(result);
    }
}
