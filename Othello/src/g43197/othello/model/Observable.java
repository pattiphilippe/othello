package g43197.othello.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Philippe
 */
public class Observable {

    private boolean changed = false;
    private List<Observer> obs;

    /**
     * Construct an Observable with zero Observers.
     */
    public Observable() {
        obs = new ArrayList<>();
    }

    public void addObserver(Observer o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!obs.contains(o)) {
            obs.add(o);
        }
    }

    public void deleteObserver(Observer o) {
        obs.remove(o);
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(Object arg) {
        if (changed) {

        }
        for (Observer o : obs) {
            o.update(this, arg);
        }
    }

    public synchronized void deleteObservers() {
        obs.clear();
    }

    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }

    public synchronized boolean hasChanged() {
        return changed;
    }

    public synchronized int countObservers() {
        return obs.size();
    }
}
