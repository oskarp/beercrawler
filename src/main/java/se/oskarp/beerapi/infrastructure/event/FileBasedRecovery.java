package se.oskarp.beerapi.infrastructure.event;

import com.google.common.base.Throwables;
import com.google.inject.Inject;
import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.oskarp.beerapi.domain.event.Event;
import se.oskarp.beerapi.domain.event.EventRepository;
import se.oskarp.beerapi.domain.event.UnableToSaveException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This class decorates an event repository with a file based
 * recovery mechanism upon failure.
 */
public class FileBasedRecovery implements EventRepository {

    private final Logger logger = LoggerFactory.getLogger(FileBasedRecovery.class);
    private final ObjectMapper mapper = JsonFactory.create();

    private final EventRepository repository;
    private final Path pendingEventsFilePath;

    @Inject
    public FileBasedRecovery(EventRepository repository, Path pendingEventsFilePath) {
        checkArgument(this != repository, "Cannot decorate self");
        this.repository = checkNotNull(repository);
        this.pendingEventsFilePath = checkNotNull(pendingEventsFilePath);
    }

    @Override
    public void save(List<Event> events) {
        checkNotNull(events);

        try {
            savePendingEvents(readPendingEvents(pendingEventsFilePath));
            recreatePendingFile(pendingEventsFilePath);
            repository.save(events);
        } catch (UnableToSaveException | IOException e) {
            List<Event> all = readPendingEvents(pendingEventsFilePath);
            all.addAll(events);
            mapper.writeValue(pendingEventsFilePath.toFile(), all);

            logger.error("Something went terribly wrong, appending events to pending file", e);
            throw Throwables.propagate(e);
        }
    }

    void savePendingEvents(List<Event> pending) {
        if (pending.isEmpty())
            return;

        try {
            repository.save(pending);
        } catch (Exception e) {
            logger.error("Failed to save pending events from file system - the events remain in the file.", e);
            throw Throwables.propagate(e);
        }
    }

    @SuppressWarnings("unchecked")
    List<Event> readPendingEvents(Path path) {
        createIfMissing(path);

        // boon will cry if file is empty :/
        return path.toFile().length() != 0
                ? mapper.readValue(path.toFile(), List.class, Event.class)
                : new ArrayList<>();
    }

    private void createIfMissing(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                logger.error("Failed to create the pending.json file", e);
                Throwables.propagate(e);
            }
        }
    }

    private void recreatePendingFile(Path path) throws IOException {
        Files.deleteIfExists(path);
        createIfMissing(path);
    }
}
