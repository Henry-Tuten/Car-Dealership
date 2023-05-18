public class GetName extends CommandActions{

    public void Execute(){
        // have the salesperson return their name
        String name = s.GetName();
        System.out.println("Salesperson's name is " + name);
    }

}
