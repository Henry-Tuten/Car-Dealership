import java.util.ArrayList;

public class CommandBroker {

    ArrayList<CommandActions> actions = new ArrayList<>();

    CommandBroker(FNCD f1, FNCD f2){
        CommandActions.f1 = f1;
        CommandActions.f2 = f2; // set these default
        CommandActions.f = f1;
        CommandActions.s = (Salesperson) f1.staff.get(FNCD.StaffType.SALESPERSON).get(0); // just gets the first salesperson in f1 by default
    }

    public void AddAction(CommandActions a){
        actions.add(a);
    }

    public void ExecuteActions(){
        for(CommandActions a : actions){
            a.Execute();
        }
        actions.clear(); // remove all after they have been executed
    }

}
