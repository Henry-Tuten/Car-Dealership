import java.util.concurrent.ThreadLocalRandom;

// inheritance from staff, a lot of properties re-used but certain ones specific to mechanic
public class Mechanic extends Staff{

    private static int id = 0; // Identity: when constructor is called, GenerateName() creates a unique name since id is incremented and shared across all instances of Mechanic

    Mechanic(){
        salary = 200;
        name = GenerateName();
    }

    Mechanic(String n, int days, int earn){ // for converting from an intern
        salary = 200;
        name = n;
        daysWorked = days;
        earned = earn;
    }

    public void Repair(Vehicle v, FNCD f){
        int num = ThreadLocalRandom.current().nextInt(1,101); // 1-100
        if(num <= 80){ // 80% chance of fixing any vehicle in general
            if(v.GetCondition() == "Broken"){
                v.SetCondition("Used");
                int newPrice = (int) (v.GetSalePrice() * 1.5);
                v.SetSalePrice(newPrice); // fixed from used -> like new means 50% increase in salesPrice
                // System.out.println("Mechanic fixed Broken " + v.GetType() + " " + v.GetName() + " " + "and made it Used (earned $" + v.GetRepairBonus() + " bonus)");
                f.sm.NotifyObserver("Mechanic fixed Broken " + v.GetType() + " " + v.GetName() + " " + "and made it Used (earned $" + v.GetRepairBonus() + " bonus)", v.GetRepairBonus(), 0);
            }
            else{ // condition is used
                v.SetCondition("Like New");
                int newPrice = (int) (v.GetSalePrice() * 1.25);
                v.SetSalePrice(newPrice); // fixed from used -> like new means 25% increase in salesPrice
                // System.out.println("Mechanic fixed Used " + v.GetType() + " " + name + " " + "and made it Like New (earned $" + v.GetRepairBonus() + " bonus)");
                f.sm.NotifyObserver("Mechanic fixed Used " + v.GetType() + " " + name + " " + "and made it Like New (earned $" + v.GetRepairBonus() + " bonus)", v.GetRepairBonus(), 0);
            }
            if(v.GetState() == "Sparkling"){ // goes down one tier of cleanliness
                v.SetState("Clean");
            }
            else{
                v.SetState("Dirty"); // if either clean or dirty, new state is dirty
            }
            f.ModifyBudget(-v.GetRepairBonus());
            // add bonus to this mechanic
            this.GainMoney(v.GetRepairBonus());
        }
    }

    private String GenerateName(){
        String n =  "Mechanic" + "_" + id;
        id++;
        return n;
    }

}
