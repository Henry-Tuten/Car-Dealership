import java.util.ArrayList;

public class FNCDMoney implements Subject{

    ArrayList<Observer> observers = new ArrayList<>();

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
