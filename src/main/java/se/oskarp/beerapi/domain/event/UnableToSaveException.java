package se.oskarp.beerapi.domain.event;

/**
 * This exception signals that the save operation failed. All
 * implementations of {@link EventRepository} must throw this
 * exception whenever the save operation fails in order for
 * failure recovery mechanisms to function properly.
 */
public class UnableToSaveException extends RuntimeException {

    public UnableToSaveException(Exception cause) {
        super(cause);
    }
}
