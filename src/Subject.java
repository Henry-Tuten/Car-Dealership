public interface Subject {

    public abstract void RegisterObserver(Observer o);
    public abstract void RemoveObserver(Observer o);
    public abstract void NotifyObserver(String s, Integer i1, Integer i2);

}
