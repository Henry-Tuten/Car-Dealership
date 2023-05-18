import java.sql.Array;
import java.util.ArrayList;
import java.util.Hashtable;

public class FNCD {

    public enum StaffType {
        MECHANIC,
        SALESPERSON,
        INTERN,
        DRIVER
    }

    public enum VehicleType {
        MONSTER_TRUCK,
        MOTORCYCLE,
        PERFORMANCE_CAR,
        PICKUP,
        CAR,
        ELECTRIC_CAR,
        HYBRID,
        SEMI_TRUCK,
        LIMO
    }

    String name = "";
    String days[] = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    int currentDay = 1;
    int budget = 500000; // just a single budget to work with
    public int dailySales = 0;

    public JobOutcomes jo = new JobOutcomes(); // Observer pattern. These are the concrete subjects with the information for each of these things
    public StaffMoney sm = new StaffMoney();
    public RaceResults rr = new RaceResults();
    public FNCDMoney fm = new FNCDMoney();

    ArrayList<Staff> departedStaff = new ArrayList<Staff>(); // all staff that have quit
    ArrayList<Vehicle> soldVehicles = new ArrayList<Vehicle>(); // all vehicles that have been sold

    // Stores all the current staff, and the current vehicles based on enums of staff type and vehicle type, and lists of each type as values
    Hashtable<StaffType, ArrayList<Staff>> staff = new Hashtable<>();
    Hashtable<VehicleType, ArrayList<Vehicle>> vehicles = new Hashtable<>();

    FNCD(String n){
        name = n;
        // add these to a hashtable
        ArrayList<Staff> salespeople = new ArrayList<>(); // 3 staff of each kind
        ArrayList<Staff> mechanics = new ArrayList<>();
        ArrayList<Staff> interns = new ArrayList<>();
        ArrayList<Staff> drivers = new ArrayList<>();
        ArrayList<Vehicle> performanceCars = new ArrayList<>(); // 4 vehicles of each kind
        ArrayList<Vehicle> cars = new ArrayList<>();
        ArrayList<Vehicle> pickups = new ArrayList<>();
        ArrayList<Vehicle> motorcycles = new ArrayList<>();
        ArrayList<Vehicle> electricCars = new ArrayList<>();
        ArrayList<Vehicle> monsterTrucks = new ArrayList<>();
        ArrayList<Vehicle> semiTrucks = new ArrayList<>();
        ArrayList<Vehicle> limos = new ArrayList<>();
        ArrayList<Vehicle> hybrids = new ArrayList<>();
        staff.put(StaffType.SALESPERSON, salespeople);
        staff.put(StaffType.MECHANIC, mechanics);
        staff.put(StaffType.INTERN, interns);
        staff.put(StaffType.DRIVER, drivers);
        vehicles.put(VehicleType.PERFORMANCE_CAR, performanceCars);
        vehicles.put(VehicleType.CAR, cars);
        vehicles.put(VehicleType.PICKUP, pickups);
        vehicles.put(VehicleType.MOTORCYCLE, motorcycles);
        vehicles.put(VehicleType.ELECTRIC_CAR, electricCars);
        vehicles.put(VehicleType.MONSTER_TRUCK, monsterTrucks);
        vehicles.put(VehicleType.SEMI_TRUCK, semiTrucks);
        vehicles.put(VehicleType.LIMO, limos);
        vehicles.put(VehicleType.HYBRID, hybrids);

    }

    public void AddObs(Observer o){
        jo.RegisterObserver(o);
        sm.RegisterObserver(o);
        rr.RegisterObserver(o);
        fm.RegisterObserver(o);
    }

    public void RemoveObs(Observer o){
        jo.RemoveObserver(o);
        sm.RemoveObserver(o);
        rr.RemoveObserver(o);
        fm.RemoveObserver(o); // remove so that doesn't keep notifying observers that aren't being used anymore
    }

    public void Open(){
        String day = days[currentDay % 7]; // string for day of the week
        System.out.println("");
        // System.out.println("*** " + name + " Day " + currentDay + " *** (" + day + ")");
        fm.NotifyObserver("*** " + name + " Day " + currentDay + " *** (" + day + ")", 0, 0);
        dailySales = 0;
        Actions.Opening(this);
    }

    public void Wash(){
        Actions.Washing(this);
    }

    public void Repair(){
        Actions.Repairing(this);
    }

    public void Sell(){
        Actions.Selling(this);
    }

    public void End(){
        Actions.Ending(this);
        currentDay++;
    }

    public void Race(){
        Actions.Racing(this);
    }

    public void ModifyBudget(int amount){ // for modifying the budget from outside, but also checking to make sure we have more than $0
        budget += amount;
        if(budget < 0){
            // System.out.println("Budget dropped below 0, adding $250000");
            fm.NotifyObserver("Budget dropped below 0, adding $250000", 0, -250000);
            budget += 250000;
        }
    }
}
