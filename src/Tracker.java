public class Tracker implements Observer{

    private static Tracker instance = new Tracker(); // eager singleton instantiation, since there is only one of these the whole time that we know we will need
    private int totalStaffEarned;
    private int totalFNCDEarned;

    private Tracker(){ // singleton pattern keeps the constructor private, so we only create it once and can access this class with GetInstance()
        totalStaffEarned = 0;
        totalFNCDEarned = 0;
    }

    public static Tracker GetInstance(){
        return instance;
    }

    public void Update(String s, Integer i1, Integer i2){ // feed in values, and the "type", either staff or FNCD that it contributes / modifies
        // i1 is for staff, i2 is for FNCD
        totalStaffEarned += i1;
        totalFNCDEarned += i2;
    }

    public void Display(int day){
        System.out.println("Tracker Day " + day);
        System.out.println("Total money earned by all the staff: " + totalStaffEarned);
        System.out.println("Total money earned by the FNCD: " + totalFNCDEarned);
    }

}
