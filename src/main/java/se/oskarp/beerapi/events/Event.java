package se.oskarp.beerapi.events;

import se.oskarp.beerapi.beer.Beer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
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


    public Event(int drinkNumber, Action action, Beer before, Beer after) {
        this.drinkNumber = drinkNumber;
        this.action = action;
        this.before = before;
        this.after = after;
    }

    public int getDrinkNumber() {
        return drinkNumber;
    }

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
