package se.oskarp.beerapi.infrastructure.event;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import se.oskarp.beerapi.domain.beer.Beer;
import se.oskarp.beerapi.domain.event.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oskar on 22/06/15.
 */

public class EventRepositoryRESTTest {

    @Test
    @Ignore

    // Test now creates a beer, updates it and then deletes it.
    public void save() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        EventRepositoryREST repo = new EventRepositoryREST("http://homestead.app/event");

        Beer b1 = new Beer("Åbro orginal", "Smakar lite som en säl luktar.", "Lager", 1483, 22.3, "Åbro", "Sverige", "", 6, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro", "orginalet", 1483);
        Beer b2 = new Beer("Norrlands guld", "Hopps", "Lager", 14127, 19.5, "Spendrups", "Sverige", "", 5, "flaska", "öl", true, 330, formatter.parse("2005-10-01"), "Spendrups", "", 14127);
        Beer b3 = new Beer("Åbro Orginal", "Damm", "Lager", 1483, 23.2, "Åbro", "England", "", 5.2, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro", "orginalet", 1483);
        Beer b4 = new Beer("Falcon", "Njet", "Lager", 95483, 20, "Falcon", "Sverige", "", 4.8, "flaska", "öl", true, 335, formatter.parse("1999-10-01"), "Spendrups", "", 95483);
        /*
        Event e1 = new Event(1483, Event.Action.Update, b1, b3);
        Event e2 = new Event(14127, Event.Action.Create, new Beer(), b2);
        Event e3 = new Event(95483, Event.Action.Delete, b4, new Beer());
        */


        Event en1 = new Event(1483, Event.Action.Create, new Beer(), b1);
        Event en2 = new Event(1483, Event.Action.Update, b1, b3);
        Event en3 = new Event(1483, Event.Action.Delete, b3, new Beer());



        List<Event> eventList = new ArrayList<>();
        eventList.add(en1);
        eventList.add(en2);
        eventList.add(en3);
        repo.save(eventList);
    }

    @Test
    public void persistWithHeaders() throws Exception {
        String token = null;
        assertNotNull(token);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        EventRepositoryRESTwHeaders repo = new EventRepositoryRESTwHeaders("http://homestead.app/event", token);
        Beer b1 = new Beer("Åbro orginal", "Smakar lite som en säl luktar.", "Lager", 1483, 22.3, "Åbro", "Sverige", "", 6, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro", "orginalet", 1483);
        Beer b2 = new Beer("Norrlands guld", "Hopps", "Lager", 14127, 19.5, "Spendrups", "Sverige", "", 5, "flaska", "öl", true, 330, formatter.parse("2005-10-01"), "Spendrups", "", 14127);
        Beer b3 = new Beer("Åbro Orginal", "Damm", "Lager", 1483, 23.2, "Åbro", "England", "", 5.2, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro", "orginalet", 1483);
        Beer b4 = new Beer("Falcon", "Njet", "Lager", 95483, 20, "Falcon", "Sverige", "", 4.8, "flaska", "öl", true, 335, formatter.parse("1999-10-01"), "Spendrups", "", 95483);
        /*
        Event e1 = new Event(1483, Event.Action.Update, b1, b3);
        Event e2 = new Event(14127, Event.Action.Create, new Beer(), b2);
        Event e3 = new Event(95483, Event.Action.Delete, b4, new Beer());
        */


        Event en1 = new Event(1483, Event.Action.Create, new Beer(), b1);
        Event en2 = new Event(1483, Event.Action.Update, b1, b3);
        Event en3 = new Event(1483, Event.Action.Delete, b3, new Beer());



        List<Event> eventList = new ArrayList<>();
        eventList.add(en1);
        eventList.add(en2);
        eventList.add(en3);
        repo.save(eventList);
    }
}