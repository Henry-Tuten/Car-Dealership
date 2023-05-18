import java.util.ArrayList;

public class RaceResults implements Subject{

    ArrayList<Observer> observers = new ArrayList<>();
    // race results, what positions the 0-3 drivers got

    public void RegisterObserver(Observer o) {
        observers.add(o);
    }

    public void RemoveObserver(Observer o) {
        observers.remove(o);
    }

    public void NotifyObserver(String s, Integer i1, Integer i2) {
        for(Observer o : observers){
            o.Update(s, i1, i2);
        }
    }
}
