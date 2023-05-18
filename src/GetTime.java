public class GetTime extends CommandActions{

    public void Execute(){
        // the salesperson will return what time it is
        long time = s.GetTime();
        System.out.println("Time is " + time);
    }

}
