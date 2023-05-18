import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Actions { // essentially a utility class, where we do all of the actions for the day

    public static void UserBuy(FNCD f1, FNCD f2){
        boolean active = true;
        Scanner scan = new Scanner(System.in);
        CommandBroker broker = new CommandBroker(f1, f2);
        f1.fm.NotifyObserver("User buying with command pattern ", 0, 0);
        while(active){
            // print out selection, then get input. Switch statement for string choices, which will instantiate and execute commands. If not valid command, just prints that.
            System.out.println("Select a command:");
            System.out.println("1) Switch FNCD");
            System.out.println("2) Ask salesperson's name");
            System.out.println("3) As salesperson for time");
            System.out.println("4) Ask for a different salesperson");
            System.out.println("5) Ask for current inventory");
            System.out.println("6) Ask for details on a specific item");
            System.out.println("7) Buy an inventory item");
            System.out.println("8) End interaction");
            String choice = scan.nextLine();
            switch(choice){
                case "1":
                    CommandActions select = new ChangeFNCD(); // switches to FNCD that it isn't currently on
                    broker.AddAction(select);
                    broker.ExecuteActions();
                    break;
                case "2":
                    CommandActions askName = new GetName(); // just prints out name
                    broker.AddAction(askName);
                    broker.ExecuteActions();
                    break;
                case "3":
                    CommandActions askTime = new GetTime(); // prints out time
                    broker.AddAction(askTime);
                    broker.ExecuteActions();
                    break;
                case "4":
                    CommandActions changePerson = new ChangeSalesperson();
                    broker.AddAction(changePerson);
                    broker.ExecuteActions();
                    break;
                case "5":
                    CommandActions getInventory = new GetInventory();
                    broker.AddAction(getInventory);
                    broker.ExecuteActions();
                    break;
                case "6":
                    CommandActions getItemDetails = new GetInventoryDetails();
                    broker.AddAction(getItemDetails);
                    broker.ExecuteActions();
                    break;
                case "7":
                    CommandActions buyItem = new BuyItem();
                    broker.AddAction(buyItem);
                    broker.ExecuteActions();
                    break;
                case "8":
                    CommandActions end = new EndInteraction();
                    broker.AddAction(end);
                    broker.ExecuteActions();
                    active = false;
                    break;
                default:
                    System.out.println("Please enter a valid command");
            }
        }
    }

    public static void Racing(FNCD f){
        System.out.println("");
        //System.out.println("Racing at FNCD " + f.name + "...");
        f.rr.NotifyObserver("Racing at FNCD " + f.name + "...", 0, 0);
        // 20 total vehicles (3 from us) sent. Randomly determine positions that the cars come in. If they are top 3, they "win", and if bottom 5, vehicle gets damaged
        ArrayList<Integer> positions = new ArrayList<>(); //
        for(int i=1; i<=20; i++){
            positions.add(i);
        }
        Collections.shuffle(positions); // now positions 1-20 randomly shuffled, so can just use the first three for our purposes

        ArrayList<Vehicle> possibleRacers; // in switch statement, set this to the vehicle we want
        ArrayList<Vehicle> allRacers = new ArrayList<>(); // list of the cars that get chosen

        int num = ThreadLocalRandom.current().nextInt(0, 5); // between performance car, pickup, motorcycle, monster truck, and hybrid. Car, electric car, semi truck, and limo don't race
        switch(num){ // choose one of the valid types of cars to race
            case 0:
                possibleRacers = f.vehicles.get(FNCD.VehicleType.PERFORMANCE_CAR);
                break;
            case 1:
                possibleRacers = f.vehicles.get(FNCD.VehicleType.PICKUP);
                break;
            case 2:
                possibleRacers = f.vehicles.get(FNCD.VehicleType.MOTORCYCLE);
                break;
            case 3:
                possibleRacers = f.vehicles.get(FNCD.VehicleType.MONSTER_TRUCK);
                break;
            default:
                possibleRacers = f.vehicles.get(FNCD.VehicleType.HYBRID);
                break;
        }

        for(Vehicle v : possibleRacers){
            if(v.GetCondition() != "Broken" && allRacers.size() < 3){ // vehicle has to not be broken, and only want 3 of them max
                allRacers.add(v);
            }
        }

        int index = 0;
        ArrayList<Staff> injuredDrivers = new ArrayList<>();
        for(Vehicle v : allRacers){
            Staff d = f.staff.get(FNCD.StaffType.DRIVER).get(index); // driver
            // System.out.println("Driver " + d.GetName() + " got position " + positions.get(index) + " with car " + v.GetName());
            f.rr.NotifyObserver("Driver " + d.GetName() + " got position " + positions.get(index) + " with car " + v.GetName(), 0, 0);
            if(positions.get(index) < 4){
                v.IncrementRacesWon();
                // d.IncrementRacesWon();
                d.GainMoney(v.GetRaceBonus());
                // System.out.println("Driver " + d.GetName() + " won the race with car " + v.GetName());
                f.rr.NotifyObserver("Driver " + d.GetName() + " won the race with car " + v.GetName(), 0, 0);
            }
            else if(positions.get(index) > 15) // last 5 positions
            {
                v.SetCondition("Broken");
                // System.out.println("The car " + v.GetName() + " was damaged in the race");
                f.rr.NotifyObserver("The car " + v.GetName() + " was damaged in the race", 0, 0);
                num = ThreadLocalRandom.current().nextInt(1, 101);
                if(num <= 30){
                    // System.out.println("Driver " + d.GetName() + " was injured in the race");
                    f.rr.NotifyObserver("Driver " + d.GetName() + " was injured in the race", 0, 0);
                    // d.SetInjured(true);
                    f.departedStaff.add(d);
                    injuredDrivers.add(d);
                }
            }
            index++;
        }
        for(Staff d : injuredDrivers){ // remove these after they race as not to affect the race itself
            f.staff.get(FNCD.StaffType.DRIVER).remove(d);
        }
    }

    public static void Opening(FNCD f){

//        System.out.println("");
        // System.out.printf("Opening at FNCD %s... (current budget $%d)\n", f.name, f.budget);
        f.fm.NotifyObserver("Opening at FNCD " + f.name + "... (current budget $" + f.budget + ")", 0, 0);

        while(f.staff.get(FNCD.StaffType.SALESPERSON).size() < 3) // first check from interns, then hire
        {
            if(f.staff.get(FNCD.StaffType.INTERN).size() > 0){ // make sure there are enough interns to hire from (there always should be)
                Staff i = f.staff.get(FNCD.StaffType.INTERN).get(0);
                // System.out.println("Intern " + i.GetName() + " was promoted to Salesperson");
                f.sm.NotifyObserver("Intern " + i.GetName() + " was promoted to Salesperson", 0, 0);
                Salesperson s = new Salesperson(i.GetName(), i.GetDaysWorked(), i.GetEarned());
                f.staff.get(FNCD.StaffType.SALESPERSON).add(s); // transfer intern from this to salesperson
                f.staff.get(FNCD.StaffType.INTERN).remove(0);
            }
            else{
                Salesperson s = (Salesperson) StaffFactory.GenerateStaff(FNCD.StaffType.SALESPERSON); // factory generate the salesperson
                f.staff.get(FNCD.StaffType.SALESPERSON).add(s);
                // System.out.println("Hired salesperson " + s.GetName());
                f.sm.NotifyObserver("Hired salesperson " + s.GetName(), 0, 0);
            }
        }
        while(f.staff.get(FNCD.StaffType.MECHANIC).size() < 3) // first check from interns, then hire
        {
            if(f.staff.get(FNCD.StaffType.INTERN).size() > 0){ // make sure there are enough interns to hire from (there always should be)
                Staff i = f.staff.get(FNCD.StaffType.INTERN).get(0);
                // System.out.println("Intern " + i.GetName() + " was promoted to Salesperson");
                f.sm.NotifyObserver("Intern " + i.GetName() + " was promoted to Mechanic", 0, 0);
                Mechanic m = new Mechanic(i.GetName(), i.GetDaysWorked(), i.GetEarned());
                f.staff.get(FNCD.StaffType.MECHANIC).add(m); // transfer intern from this to mechanic
                f.staff.get(FNCD.StaffType.INTERN).remove(0);
            }
            else{
                Mechanic m = (Mechanic) StaffFactory.GenerateStaff(FNCD.StaffType.MECHANIC); // factory generate the salesperson
                f.staff.get(FNCD.StaffType.MECHANIC).add(m);
                // System.out.println("Hired salesperson " + m.GetName());
                f.sm.NotifyObserver("Hired mechanic " + m.GetName(), 0, 0);
            }
        }
        while(f.staff.get(FNCD.StaffType.DRIVER).size() < 3){ // will not replace with an intern, only a specifically trained driver
            Driver d = (Driver) StaffFactory.GenerateStaff(FNCD.StaffType.DRIVER); // factory to generate driver
            f.staff.get(FNCD.StaffType.DRIVER).add(d);
            // System.out.println("Hired driver " + d.GetName());
            f.sm.NotifyObserver("Hired driver " + d.GetName(), 0, 0);
        }
        while(f.staff.get(FNCD.StaffType.INTERN).size() < 3) // this stays like this
        {
            Intern i = (Intern) StaffFactory.GenerateStaff(FNCD.StaffType.INTERN); // factory pattern generate intern
            f.staff.get(FNCD.StaffType.INTERN).add(i);
            // System.out.println("Hired Intern " + i.GetName());
            f.sm.NotifyObserver("Hired intern " + i.GetName(), 0, 0);
        }

        // loop through all the array lists of vehicle types, based on the enum
        for(FNCD.VehicleType t : FNCD.VehicleType.values()){
            ArrayList<Vehicle> vehicles = f.vehicles.get(t); // gets array list of vehicles, based on current enum
            while(vehicles == null || vehicles.size() < 6){ // on the initial generation when empty or size is less than 6
                Vehicle v;
                v = VehicleFactory.GenerateVehicle(t); // much more concise than a switch statement here, which is what we do in the factory pattern. Only have to tell it the type we want
                f.vehicles.get(t).add(v);
                f.ModifyBudget(-v.GetCost());
                // System.out.printf("Purchased %s, %s %s for $%d\n", v.GetCondition(), v.GetState(), v.GetType(), v.GetCost());
                f.fm.NotifyObserver("Purchased " + v.GetCondition() + " " + v.GetState() + " " + v.GetType() + " for $" + v.GetCost(), 0, -v.GetCost());
            }
        }
    }

    public static void Washing(FNCD f){
        System.out.println("");
        // System.out.println("Washing at FNCD " + f.name + "...");
        f.fm.NotifyObserver("Washing at FNCD " + f.name + "...", 0, 0);
        for(int i=0; i<3; i++){ // will always be 3 interns at this point
            Intern intern = (Intern)f.staff.get(FNCD.StaffType.INTERN).get(i); // cast to intern type?
            for(int j=0; j<2; j++){ // each intern washes 2 cars
                Vehicle dirtyVehicle = GetRandomWash("Dirty", f); // find the next dirty one and next clean one. If no dirty ones, take from random clean list
                Vehicle cleanVehicle = GetRandomWash("Clean", f);
                if(dirtyVehicle != null){
                    intern.PerformWash(dirtyVehicle, f);
                }
                else if(cleanVehicle != null){
                    intern.PerformWash(cleanVehicle, f);
                }
                else{
                    // System.out.println("All vehicles are Sparkling");
                    f.jo.NotifyObserver("All vehicles are Sparkling", 0, 0);
                    return;
                }
            }
        }
    }

    public static void Repairing(FNCD f){
        System.out.println("");
        // System.out.println("Repairing at FNCD " + f.name +  "...");
        f.fm.NotifyObserver("Repairing at FNCD " + f.name + "...", 0, 0);
        for(int i=0; i<3; i++){ // 3 mechanics at this point
            Mechanic mechanic = (Mechanic)f.staff.get(FNCD.StaffType.MECHANIC).get(i); // each mechanic will try to fix twice
            for(int j=0; j<2; j++){
                Vehicle brokenVehicle = GetRandomFix("Broken", f); // same structure as washing. Try to fix broken ones first, then used if there are no broken
                Vehicle usedVehicle = GetRandomFix("Used", f);
                if(brokenVehicle != null){
                    mechanic.Repair(brokenVehicle, f); // decent example of abstraction for repairing a vehicle
                }
                else if(usedVehicle != null){
                    mechanic.Repair(usedVehicle, f);
                }
                else{
                    // System.out.println("All vehicles are repaired");
                    f.jo.NotifyObserver("All vehicles are repaired", 0, 0);
                    return;
                }
            }
        }
    }

    public static void Selling(FNCD f){
        System.out.println("");
        // System.out.println("Selling at FNCD " + f.name  +  "...");
        f.fm.NotifyObserver("Selling at FNCD " + f.name + "...", 0, 0);
        int buyers = 0;
        String day = f.days[f.currentDay % 7];

        if(day == "Friday" || day == "Saturday"){
            buyers = ThreadLocalRandom.current().nextInt(2,9); // 2-8 buyers
        }
        else{
            buyers = ThreadLocalRandom.current().nextInt(0,6); // 0 to 5 buyers
        }

        for(int i=0; i<buyers; i++) // sell based on number of buyers
        {
            int index = ThreadLocalRandom.current().nextInt(0,3);
            Staff salesperson = f.staff.get(FNCD.StaffType.SALESPERSON).get(index); // random salesperson chosen
            Buyer b = new Buyer(); // generates random preferences
            Vehicle v = GetBestVehicle(b, f); // get best vehicle helper,
            FNCD.VehicleType t = b.GetVehicleType();
            if(v != null){
                // figure out probability to buy, based on the buyer, state of the car, and if it matches their desired type (after finding a single car that works, go into this)
                int probability = 0; // start with 0, then add all the modifiers
                if(b.GetBuyType() == "Just Looking"){ // 10% base for just looking, 40% base for wants one, and needs one is 70% chance of buying base
                    probability += 10;
                }
                else if(b.GetBuyType() == "Wants One"){
                    probability += 40;
                }
                else{
                    probability += 70;
                }
                if(v.GetCondition() == "Like New"){ // like new condition and sparkling state both add 10% chance of buying
                    probability += 10;
                }
                if(v.GetState() == "Sparkling"){
                    probability += 10;
                }
                if(v.type != b.GetVehicleType()){ // if there are no cars of the type they want, 20% less chance of buying
                    probability -= 20;
                }
                int num = ThreadLocalRandom.current().nextInt(1,101); // from 1 to 100
                if(num < probability){ // do the actual random calculation. Do they buy it?
                    // extended warranty 25%, undercoating 10%, road rescue 5%, radio 40%. All % of vehicle price
                    num = ThreadLocalRandom.current().nextInt(1, 101); // extended warranty 25%
                    if(num <= 25){
                        v = new ExtendedWarranty(v);
                        // System.out.println("Extended warranty added");
                        f.jo.NotifyObserver("Extended warranty added", 0, 0);
                    }
                    num = ThreadLocalRandom.current().nextInt(1, 101); // undercoating 10%
                    if(num <= 10){
                        v = new Undercoating(v);
                        // System.out.println("Undercoating added");
                        f.jo.NotifyObserver("Undercoating added", 0, 0);
                    }
                    num = ThreadLocalRandom.current().nextInt(1, 101); // road rescue coverage 5%
                    if(num <= 5){
                        v = new RoadRescueCoverage(v);
                        // System.out.println("Road rescue coverage added");
                        f.jo.NotifyObserver("Road rescue coverage added", 0, 0);
                    }
                    num = ThreadLocalRandom.current().nextInt(1, 101); // satellite radio 40%
                    if(num <= 40){
                        v = new SatelliteRadio(v);
                        // System.out.println("Satellite radio added");
                        f.jo.NotifyObserver("Satellite radio added", 0, 0);
                    }

                    // System.out.println("Full description of everything bought is " + v.GetDescription() + " with a total cost of $"  + v.Cost());
                    f.jo.NotifyObserver("Full description of everything bought is " + v.GetDescription() + " with a total cost of $"  + v.Cost(), 0, (int)v.Cost());
                    f.jo.NotifyObserver("Salesperson has gained bonus for selling: $" + v.GetSaleBonus(), v.GetSaleBonus(), 0);

                    f.ModifyBudget((int)v.Cost());
                    salesperson.GainMoney(v.GetSaleBonus());
                    v.sold = true;
                    f.soldVehicles.add(v);
                    f.dailySales += v.GetSalePrice(); // update budget, daily sales, sale bonus
                    // System.out.println("Buyer has purchased " + v.GetCondition() + " " + v.GetState() + " " + v.GetType() + " from " + salesperson.GetName() + " for $" + v.Cost() + " (earned $" + v.GetSaleBonus() + ")");
                    f.jo.NotifyObserver("Buyer has purchased " + v.GetCondition() + " " + v.GetState() + " " + v.GetType() + " from " + salesperson.GetName() + " for $" + v.Cost() + " (earned $" + v.GetSaleBonus() + ")" ,0,0);
                    f.vehicles.get(t).remove(v);
                }
            }
        }
    }

    public static void Ending(FNCD f){

        System.out.println("");
        // System.out.println("Closing at FNCD " + f.name + "...");
        f.fm.NotifyObserver("Closing at FNCD " + f.name + "...",0,0);

        // Add salary to each worker, subtract from budget
        for(int i = 0; i<3; i++){
            Staff salesperson = f.staff.get(FNCD.StaffType.SALESPERSON).get(i);
            Staff mechanic = f.staff.get(FNCD.StaffType.MECHANIC).get(i);
            Staff intern = f.staff.get(FNCD.StaffType.INTERN).get(i);
            salesperson.GainMoney(salesperson.salary);
            f.ModifyBudget(-salesperson.salary);
            mechanic.GainMoney(mechanic.salary);
            f.ModifyBudget(-mechanic.salary);
            intern.GainMoney(intern.salary);
            f.ModifyBudget(-intern.salary);
            f.sm.NotifyObserver("", salesperson.salary, -salesperson.salary);
            f.sm.NotifyObserver("", mechanic.salary, -mechanic.salary);
            f.sm.NotifyObserver("", intern.salary, -intern.salary);
        }
        // 10% for each staff type that one may quit. Remove and put in departedStaff list
        for(int i = 0; i<3; i++) { // drivers don't quit, they only quit if they get injured
            int num = ThreadLocalRandom.current().nextInt(1,101); // from 1 to 100
            if(num <= 10) // staff quits. do this for each type of staff
            {
                if(i == 0){
                    Staff s = f.staff.get(FNCD.StaffType.SALESPERSON).get(0);
                    // System.out.println("Salesperson " + s.GetName() + " quit");
                    f.sm.NotifyObserver("Salesperson " + s.GetName() + " quit",0,0);
                    f.departedStaff.add(s);
                    f.staff.get(FNCD.StaffType.SALESPERSON).remove(0);
                }
                else if(i == 1){
                    Staff s = f.staff.get(FNCD.StaffType.MECHANIC).get(0);
                    // System.out.println("Mechanic " + s.GetName() + " quit");
                    f.sm.NotifyObserver("Mechanic " + s.GetName() + " quit",0,0);
                    f.departedStaff.add(s);
                    f.staff.get(FNCD.StaffType.MECHANIC).remove(0);
                }
                else{
                    Staff s = f.staff.get(FNCD.StaffType.INTERN).get(0);
                    // System.out.println("Intern " + s.GetName() + " quit");
                    f.sm.NotifyObserver("Intern " + s.GetName() + " quit",0,0);
                    f.departedStaff.add(s);
                    f.staff.get(FNCD.StaffType.INTERN).remove(0);
                }
            }
        }

        // report of staff members (working & quit), inventory, total budget and total daily sales
        System.out.println("");
        // System.out.println("Current working employees are: "); // add descriptors for daysWorked and amount earned
        f.sm.NotifyObserver("Current working employees are: ", 0, 0);

        for(FNCD.StaffType t : FNCD.StaffType.values()){
            ArrayList<Staff> staff = f.staff.get(t);
            for(Staff s : staff){
                s.daysWorked += 1;
                // System.out.println(s.GetName() + ", worked " + s.GetDaysWorked() + " days, earned $" + s.GetEarned()); // can include a staff type and GetStaffType() in staff like vehicle
                f.sm.NotifyObserver(s.GetName() + ", worked " + s.GetDaysWorked() + " days, earned $" + s.GetEarned(), 0, 0);
            }
        }

        System.out.println("");
        // System.out.println("Departed employees are: "); // print out list of all employees that quit
        f.sm.NotifyObserver("Departed employees are: ",0,0);
        for(int i=0; i<f.departedStaff.size(); i++){
            System.out.println(f.departedStaff.get(i).GetName());
        }

        System.out.println("");
        // System.out.println("Current inventory is: ");
        f.sm.NotifyObserver("Current inventory is: ",0,0);

        for(FNCD.VehicleType t : FNCD.VehicleType.values()){
            ArrayList<Vehicle> vehicles = f.vehicles.get(t);
            for(Vehicle v : vehicles){
                // System.out.println("Vehicle - " + v.GetName() + ", " + v.GetCondition() + ", " + v.GetState() + ", Saleprice - $" + v.GetSalePrice());
                f.sm.NotifyObserver("Vehicle - " + v.GetName() + ", " + v.GetCondition() + ", " + v.GetState() + ", Saleprice - $" + v.GetSalePrice(), 0, 0);
            }
        }

        System.out.println("");
        System.out.printf("Total sales today were $%d\n", f.dailySales);
        System.out.printf("Total budget remaining is $%d\n", f.budget);
        System.out.println("");

    }

    private static Vehicle GetRandomWash(String state, FNCD f){ // helper function to get a list of all the dirty vehicles, which will be chosen randomly
        ArrayList<Vehicle> choices = new ArrayList<>(); // list of all options that are in the state

        // iterate through all the possible cars in the inventory
        for(FNCD.VehicleType t : FNCD.VehicleType.values()){
            ArrayList<Vehicle> vehicles = f.vehicles.get(t); // get an array of a certain vehicle type
            for(Vehicle v : vehicles){
                if(v.GetState() == state){ // add if the state is either normal clean or sparkling
                    choices.add(v);
                }
            }
        }

        if(choices.size() == 0){ // if there are no dirty or clean cars (only sparkling), don't do anything
            return null;
        }
        else{
            int index = ThreadLocalRandom.current().nextInt(0,choices.size()); // IF EMPTY? Returns null?
            return choices.get(index);
        }
    }

    private static Vehicle GetRandomFix(String condition, FNCD f){
        ArrayList<Vehicle> choices = new ArrayList<>(); // list of all options that are in the state

        // iterate through all the possible cars in the inventory
        for(FNCD.VehicleType t : FNCD.VehicleType.values()){
            ArrayList<Vehicle> vehicles = f.vehicles.get(t); // get an array of a certain vehicle type
            for(Vehicle v : vehicles){
                if(v.GetCondition() == condition){ // add if the condition is broken or used
                    choices.add(v);
                }
            }
        }

        if(choices.size() == 0){ // if there are no broken or used, don't do anything
            return null;
        }
        else{
            int index = ThreadLocalRandom.current().nextInt(0,choices.size());
            return choices.get(index);
        }
    }

    private static Vehicle GetBestVehicle(Buyer buyer, FNCD f){

        Vehicle firstChoice = null; // of the type the buyer wants
        Vehicle secondChoice = null; // not of the type the buyer wants
        double maxFirst = Double.NEGATIVE_INFINITY; // maximum prices found to sell
        double maxSecond = Double.NEGATIVE_INFINITY;

        // go through all of the vehicles in the whole inventory. If the first choice (equals the type they want), otherwise from second list of most expensive
        for(FNCD.VehicleType t : FNCD.VehicleType.values()){
            ArrayList<Vehicle> vehicles = f.vehicles.get(t);
            for(Vehicle v : vehicles){ // check each vehicle
                if(v.GetType() == buyer.GetVehicleType()){ // is of the type the buyer wants
                    if(v.GetCondition() != "Broken" && v.GetSalePrice() > maxFirst){
                        firstChoice = v;
                    }
                }
                else{ // not type the buyer wants, so just want maximum priced not broken one
                    if(v.GetCondition() != "Broken" && v.GetSalePrice() > maxSecond){
                        secondChoice = v;
                    }
                }
            }
        }

        if(firstChoice != null){
            return firstChoice;
        }
        else if(secondChoice != null){
            return secondChoice; // there should always be a valid second choice...
        }
        else{
            System.out.println("No functioning vehicles available");
            return null;
        }
    }
}
