public class Driver extends Staff{

    private static int id = 0; // Identity: when constructor is called, GenerateName() creates a unique name since id is incremented and shared across all instances of Driver
    private boolean injured = false;
    private int racesWon;

    Driver(){
        salary = 300;
        name = GenerateName();
        racesWon = 0;
    }

    private String GenerateName(){
        String n =  "Driver" + "_" + id;
        id++;
        return n;
    }

    public boolean GetInjured(){
        return injured;
    }

    public void SetInjured(boolean i){
        injured = i;
    }

    public int GetRacesWon() { return racesWon; }

    public void IncrementRacesWon() {
        racesWon++;
    }

}
