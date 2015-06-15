package se.oskarp.beerapi.events;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by oskar on 09/06/15.
 */
public class Event {

    private int drinkNumber;
    private Action action;
    private Map<String, Object> before = new HashMap<>();
    private Map<String, Object> after = new HashMap<>();

    public Event(int drinkNumber, Action action, Map<String, Object> before, Map<String, Object> after) {
        this.drinkNumber = drinkNumber;
        this.action = action;
        this.before = before;
        this.after = after;
    }

    public enum Action {
        Create, Update, Delete
    }

    public int getDrinkNumber() {
        return drinkNumber;
    }

    public void setDrinkNumber(int drinkNumber) {
        this.drinkNumber = drinkNumber;
    }

    public Map<String, Object> getBefore() {
        return before;
    }

    public void setBefore(Map<String, Object> before) {
        this.before = before;
    }

    public Map<String, Object> getAfter() {
        return after;
    }

    public void setAfter(Map<String, Object> after) {
        this.after = after;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(drinkNumber, event.drinkNumber) &&
                Objects.equals(before, event.before) &&
                Objects.equals(after, event.after);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drinkNumber, before, after);
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
