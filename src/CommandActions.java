public abstract class CommandActions {

    protected static boolean active = true;
    protected static FNCD f;
    protected static FNCD f1;
    protected static FNCD f2;
    protected static Salesperson s;
    public abstract void Execute();

    public boolean GetActive(){ // while GetActive() is true, continue
        return active;
    }

}
