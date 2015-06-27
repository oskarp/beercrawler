package se.oskarp.beerapi.event;

import se.oskarp.beerapi.beer.Beer;

import java.util.Objects;

/**
 * Event represents a change to the state of one of the Beers in the API we track.
 * The event object is simply put a identifier (drinkNumber), what happened (action),
 * how it looked before and how it looked after.
 *
 * Created by oskar on 09/06/15.
 */
public class Event {

    private int drinkNumber;
    private Action action;
    private Beer before;
    private Beer after;


    public enum Action {
        Create, Update, Delete
    }

    /**
     *
     * @param drinkNumber Unique number from the Beer object tracked.
     * @param action Enum of what type of change it was.
     * @param before State of the beer before the change. In case of create, send in empty Beer object.
     * @param after State of the beer after the change. In case of delete, send in empty Beer object.
     */

    public Event(int drinkNumber, Action action, Beer before, Beer after) {
        this.drinkNumber = drinkNumber;
        this.action = action;
        this.before = before;
        this.after = after;
    }

    public int getDrinkNumber() {
        return drinkNumber;
    }

    /**
     * DrinkNumber is the atomic identifier of a unique drink. This is a number created by Systembolaget.
     * @param drinkNumber
     */
    public void setDrinkNumber(int drinkNumber) {
        this.drinkNumber = drinkNumber;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Beer getBefore() {
        return before;
    }

    public void setBefore(Beer before) {
        this.before = before;
    }

    public Beer getAfter() {
        return after;
    }

    public void setAfter(Beer after) {
        this.after = after;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(getDrinkNumber(), event.getDrinkNumber()) &&
                Objects.equals(getAction(), event.getAction()) &&
                Objects.equals(getBefore(), event.getBefore()) &&
                Objects.equals(getAfter(), event.getAfter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDrinkNumber(), getAction(), getBefore(), getAfter());
    }

    @Override
    public String toString() {
        return "Event{" +
                "drinkNumber=" + drinkNumber +
                ", action=" + action +
                ", before=" + before +
                ", after=" + after +
                '}';
    }
}
