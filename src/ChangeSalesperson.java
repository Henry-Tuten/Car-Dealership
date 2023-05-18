public class ChangeSalesperson extends CommandActions{

    public void Execute(){
        s = s.SwitchSalesperson(f, s); // returns a new salesperson, set to that
        System.out.println("Swapped to salesperson " + s.name);
    }

}
