package se.oskarp.beerapi.persistence;

import junit.framework.TestCase;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import se.oskarp.beerapi.beer.Beer;
import se.oskarp.beerapi.events.Event;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.boon.Boon.puts;

/**
 *
 * This test grabs the .json file directly from the disk as EventRepository will not have
 * a fetch method.
 *
 * Created by oskar on 17/06/15.
 */
public class EventRepositoryFSTest extends TestCase {

    public void testSave() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


        EventRepositoryFS repo = new EventRepositoryFS("test.json");

        Beer b1 = new Beer("Åbro Orginal", "Damm", "Lager", 213124, 23.2, "Åbro", "Sverige", "", 5.2, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro");
        Beer b2 = new Beer("Norrlands guld", "Hopps", "Lager", 14127, 19.5, "Spendrups", "Sverige", "", 5, "flaska", "öl", true, 330, formatter.parse("2005-10-01"), "Spendrups");
        Beer b3 = new Beer("Åbro Orginal", "Damm", "Lager", 213124, 23.2, "Åbro", "England", "", 5.2, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro");
        Beer b4 = new Beer("Falcon", "Njet", "Lager", 95483, 20, "Falcon", "Sverige", "", 4.8, "flaska", "öl", true, 335, formatter.parse("1999-10-01"), "Spendrups");

        Event e1 = new Event(213124, Event.Action.Update, b1.toMap(), b3.toMap());
        Event e2 = new Event(14127, Event.Action.Create, new HashMap<String, Object>(), b2.toMap());
        Event e3 = new Event(95483, Event.Action.Update, b4.toMap(), new HashMap<String, Object>());

        List<Event> eventList = new ArrayList<>();
        eventList.add(e1);
        eventList.add(e2);
        eventList.add(e3);

        repo.save(eventList);

        ObjectMapper mapper =  JsonFactory.create();
        List<Event> events = new ArrayList<>();

        try {
            events = mapper.readValue(new FileInputStream("test.json"), List.class, Event.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //puts("events", events);

        System.out.println(eventList.get(0).toString());
        System.out.println(events.get(0).toString());

        //assertEquals(eventList, events);
    }
}