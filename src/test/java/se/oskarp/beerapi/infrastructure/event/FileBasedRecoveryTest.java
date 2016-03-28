package se.oskarp.beerapi.infrastructure.event;

import com.google.common.collect.Lists;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import se.oskarp.beerapi.domain.beer.Beer;
import se.oskarp.beerapi.domain.event.Event;
import se.oskarp.beerapi.domain.event.UnableToSaveException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FileBasedRecoveryTest {

    @Mock
    private EventRepositoryREST repository;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {
        doNothing().when(repository).save(anyListOf(Event.class));
    }

    @Test
    public void saveNoPendingEvents() throws Exception {
        FileBasedRecovery instance = new FileBasedRecovery(repository, folder.newFile("pending.json").toPath());

        List<Event> toSave = sampleEvents();
        instance.save(toSave);

        verify(repository).save(toSave);
    }

    @Test
    public void saveThrowAnException() throws Exception {
        File file = folder.newFile("pending.json");

        FileBasedRecovery instance = new FileBasedRecovery(repository, file.toPath());

        List<Event> toSave = sampleEvents();
        doThrow(UnableToSaveException.class).when(repository).save(toSave);

        try {
            instance.save(toSave);
            fail("We should not have survived the above statement");
        } catch (Exception e) {
            // this is okay - ignore eventual log statements
        }

        List<Event> wasSaved = JsonFactory.create().readValue(file, List.class, Event.class);

        assertEquals(wasSaved.size(), 3);
        assertEquals(toSave, wasSaved);
    }

    private static List<Event> sampleEvents() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Beer b1 = new Beer("Åbro Orginal", "Damm", "Lager", 213124, 23.2, "Åbro", "Sverige", "", 5.2, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro", "", 213124);
        Beer b2 = new Beer("Norrlands guld", "Hopps", "Lager", 14127, 19.5, "Spendrups", "Sverige", "", 5, "flaska", "öl", true, 330, formatter.parse("2005-10-01"), "Spendrups", "", 14127);
        Beer b3 = new Beer("Åbro Orginal", "Damm", "Lager", 213124, 23.2, "Åbro", "England", "", 5.2, "flaska", "öl", true, 50, formatter.parse("2005-10-01"), "Åbro", "", 213124);
        Beer b4 = new Beer("Falcon", "Njet", "Lager", 95483, 20, "Falcon", "Sverige", "", 4.8, "flaska", "öl", true, 335, formatter.parse("1999-10-01"), "Spendrups", "", 95483);

        return Lists.newArrayList(new Event(213124, Event.Action.Update, b1, b3),
                new Event(14127, Event.Action.Create, new Beer(), b2),
                new Event(95483, Event.Action.Delete, b4, new Beer()));
    }
}