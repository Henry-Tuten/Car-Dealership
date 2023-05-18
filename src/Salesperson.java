import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

// inheritance from Staff
public class Salesperson extends Staff{

    // structure overall of these is similar to mechanic and intern, with each having a unique way of setting salary and name
    private static int id = 0;

    Salesperson(){
        salary = 250;
        name = GenerateName();
    }

    // polymorphism with constructors, because in some cases we are hiring a new salesperson, and in some cases we are converting an intern to a salesperson
    Salesperson(String n, int days, int earn){ // for an intern that is converting to a salesperson
        salary = 250;
        name = n;
        daysWorked = days;
        earned = earn;
    }

    private String GenerateName(){
        String n =  "Salesperson" + "_" + id;
        id++;
        return n;
    }
    public String GetName(){
        return name;
    }

    public long GetTime(){
        return System.currentTimeMillis();
    }

    public Salesperson SwitchSalesperson(FNCD f, Salesperson s){
        ArrayList<Staff> salespeople = f.staff.get(FNCD.StaffType.SALESPERSON);
        int num = ThreadLocalRandom.current().nextInt(0, salespeople.size());
        return (Salesperson) salespeople.get(num); // just gets a random one (might not technically switch)
    }

    public void GetInventory(FNCD f){
        // show the whole inventory (just the names and the type though, not all the details, which will be in below function)
        f.sm.NotifyObserver("Current inventory to buy from is:", 0, 0);
        for(FNCD.VehicleType t : FNCD.VehicleType.values()){
            ArrayList<Vehicle> vehicles = f.vehicles.get(t);
            for(Vehicle v : vehicles){
                f.sm.NotifyObserver("Vehicle - " + v.GetName(), 0, 0);
            }
        }
    }

    public boolean GetInventoryDetails(FNCD f, String s){
        // get specific details of a certain inventory item, based on the name of the item
        for(FNCD.VehicleType t : FNCD.VehicleType.values()){
            ArrayList<Vehicle> vehicles = f.vehicles.get(t);
            for(Vehicle v : vehicles){
                // System.out.println("name of the vehicle is " + v.GetName() + " and name we are looking for is " + s);
                if(s.equals(v.GetName())){ // really inefficient since we search through everything, but finds it based on the name
                    f.sm.NotifyObserver("Vehicle - " + v.GetName() + ", " + v.GetCondition() + ", " + v.GetState() + ", Saleprice - $" + v.GetSalePrice(), 0, 0);
                    return true; // found the vehicle by name
                }
            }
        }
        return false;
    }

    public boolean BuyInventoryItem(FNCD f, String s){
        // buy item based on the name, in addition to extentions...
        for(FNCD.VehicleType t : FNCD.VehicleType.values()){
            ArrayList<Vehicle> vehicles = f.vehicles.get(t);
            for(Vehicle v : vehicles){
                if(s.equals(v.GetName())){ // really inefficient since we search through everything, but finds it based on the name, then purchases
                    f.sm.NotifyObserver("User bought vehicle - " + v.GetName() + ", " + v.GetCondition() + ", " + v.GetState() + ", Saleprice - $" + v.GetSalePrice(), 0, 0);
                    // then ask for all the information about add ons, just yes or no (default no)

                    Scanner scan = new Scanner(System.in);
                    String response = "";
                    // extended warranty, undercoating, road rescue coverage, and satellite radio

                    System.out.println("Would you like to add extended warranty?");
                    response = scan.nextLine();
                    if(response.equals("yes") || response.equals("y") || response.equals("Yes")){
                        v = new ExtendedWarranty(v);
                        f.jo.NotifyObserver("Extended warranty added", 0, 0);
                    }
                    System.out.println("Would you like to add undercoating?");
                    response = scan.nextLine();
                    if(response.equals("yes") || response.equals("y") || response.equals("Yes")){
                        v = new Undercoating(v);
                        f.jo.NotifyObserver("Undercoating added", 0, 0);
                    }
                    System.out.println("Would you like to add road rescue coverage?");
                    response = scan.nextLine();
                    if(response.equals("yes") || response.equals("y") || response.equals("Yes")){
                        v = new RoadRescueCoverage(v);
                        f.jo.NotifyObserver("Road rescue coverage added", 0, 0);
                    }
                    System.out.println("Would you like to add satellite radio?");
                    response = scan.nextLine();
                    if(response.equals("yes") || response.equals("y") || response.equals("Yes")){
                        v = new SatelliteRadio(v);
                        f.jo.NotifyObserver("Satellite radio added", 0, 0);
                    }

                    f.jo.NotifyObserver("Full description of everything bought is " + v.GetDescription() + " with a total cost of $"  + v.Cost(), 0, (int)v.Cost());
                    f.jo.NotifyObserver("Salesperson has gained bonus for selling: $" + v.GetSaleBonus(), v.GetSaleBonus(), 0);

                    f.ModifyBudget((int)v.Cost());
                    this.GainMoney(v.GetSaleBonus());
                    v.sold = true;
                    f.soldVehicles.add(v);
                    f.dailySales += v.GetSalePrice(); // update budget, daily sales, sale bonus
                    f.vehicles.get(t).remove(v);

                    return true; // they have bought the one vehicle, don't need to loop through the rest
                }
            }
        }
        return false;
    }

}