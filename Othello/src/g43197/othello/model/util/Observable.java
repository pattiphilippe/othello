package g43197.othello.model.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Philippe
 */
public class Observable {

    private boolean changed = false;
    private final List<Observer> obs;
    //TODO use java's classes, it now doesn't change a thing that it is synchronized or not

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
            for (Observer o : obs) {
                o.update(this, arg);
            }
        }
        clearChanged();
    }

    public void deleteObservers() {
        obs.clear();
    }

    protected void setChanged() {
        changed = true;
    }

    protected void clearChanged() {
        changed = false;
    }

    public boolean hasChanged() {
        return changed;
    }

    public int countObservers() {
        return obs.size();
    }
}
