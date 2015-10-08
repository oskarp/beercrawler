package se.oskarp.beerapi.domain.event;

import org.junit.Test;
import se.oskarp.beerapi.domain.beer.Beer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by oskar on 11/06/15.
 */
public class EventFactoryTest {

    /*
     This test should result in the following event being generated. Norrlands guld being added, Åbro changed and Falcon deleted
     */
    @Test
    public void generateEvents() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Beer b1 = new Beer("Åbro Orginal", "Damm", "Lager", 213124, 23.2, "Åbro", "Sverige", "", 5.2, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro", "");
        Beer b2 = new Beer("Norrlands guld", "Hopps", "Lager", 14127, 19.5, "Spendrups", "Sverige", "", 5, "flaska", "öl", true, 330, formatter.parse("2005-10-01"), "Spendrups", "");
        Beer b3 = new Beer("Åbro Orginal", "Damm", "Lager", 213124, 23.2, "Åbro", "England", "", 5.2, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro", "");
        Beer b4 = new Beer("Falcon", "Njet", "Lager", 95483, 20, "Falcon", "Sverige", "", 4.8, "flaska", "öl", true, 335, formatter.parse("1999-10-01"), "Spendrups", "");

        List<Beer> l1 = new ArrayList<Beer>();
        List<Beer> l2 = new ArrayList<Beer>();

        l1.add(b1);
        l1.add(b2);

        l2.add(b3);
        l2.add(b4);

        List<Event> eventList = new EventFactory(l2).create(l1);

        Event e1 = new Event(213124, Event.Action.Update, b1, b3);
        Event e2 = new Event(14127, Event.Action.Create, new Beer(), b2);
        Event e3 = new Event(95483, Event.Action.Delete, b4, new Beer());

        List<Event> matchEventList = new ArrayList<>();
        matchEventList.add(e1);
        matchEventList.add(e2);
        matchEventList.add(e3);

        assertEquals(eventList, matchEventList);
    }

    /*
    This test assures checks that if we send in an identical list as the one in the cache, we get no events.
     */
    @Test
    public void nothingChanged() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Beer b1 = new Beer("Åbro Orginal", "Damm", "Lager", 213124, 23.2, "Åbro", "Sverige", "", 5.2, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro", "");
        Beer b2 = new Beer("Norrlands guld", "Hopps", "Lager", 14127, 19.5, "Spendrups", "Sverige", "", 5, "flaska", "öl", true, 330, formatter.parse("2005-10-01"), "Spendrups", "");
        Beer b3 = new Beer("Falcon", "Njet", "Lager", 95483, 20, "Falcon", "Sverige", "", 4.8, "flaska", "öl", true, 335, formatter.parse("1999-10-01"), "Spendrups", "");

        List<Beer> l1 = new ArrayList<Beer>();
        List<Beer> l2 = new ArrayList<Beer>();

        l1.add(b1);
        l1.add(b2);
        l1.add(b3);
        l2.add(b1);
        l2.add(b2);
        l2.add(b3);

        List<Event> eventList = new EventFactory(l2).create(l1);
        System.out.println(eventList);
        assertTrue(eventList.isEmpty());
    }
}