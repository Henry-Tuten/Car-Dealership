import java.util.concurrent.ThreadLocalRandom;

public class Chemical extends WashBehavior{

    private double brokenChance = 10;

    Chemical(){
        setDcChance(80);
        setDsChance(10);
        setDdChance(10);
        setCdChance(10);
        setCsChance(20);
        setCcChance(70);
    }

    public void Wash(Vehicle v, Intern i, FNCD f){
        // System.out.println("Intern washing with chemical wash behavior");
        f.jo.NotifyObserver("Intern washing with Chemical wash behavior", 0, 0);

        // clean vehicle v based on chances, then add bonus here
        int num = ThreadLocalRandom.current().nextInt(1,101); // 1-100
        if(v.GetState() == "Dirty"){
            if(num <= dcChance){
                v.SetState("Clean");
                // System.out.println("Intern washed " + v.GetType() + " " + v.GetName() + " and made it Clean");
                f.jo.NotifyObserver("Intern washed " + v.GetType() + " " + v.GetName() + " and made it Clean", 0, 0);
            }
            else if(num <= dsChance + dcChance){
                v.SetState("Sparkling");
                // System.out.println("Intern washed " + v.GetType() + " " + v.GetName() + " and made it Sparkling (earned $" + v.GetWashBonus() + " bonus)");
                f.jo.NotifyObserver("Intern washed " + v.GetType() + " " + v.GetName() + " and made it Sparkling (earned $" + v.GetWashBonus() + " bonus)", v.GetWashBonus(), 0);
                f.ModifyBudget(-v.GetWashBonus());
                i.GainMoney(v.GetWashBonus());
            }
            else{
                // System.out.println("Level of cleanliness did not change...");
                f.jo.NotifyObserver("Level of cleanliness did not change...", 0, 0);
            }
        }
        else{ // otherwise clean, shouldn't ever have a sparkling one to clean
            if(num <= cdChance){
                v.SetState("Dirty");
                // System.out.println("Intern washed " + v.GetType()+ " " + v.GetName() + " and it went from clean to Dirty...");
                f.jo.NotifyObserver("Intern washed " + v.GetType()+ " " + v.GetName() + " and it went from clean to Dirty...", 0, 0);
            }
            else if(num <= csChance + cdChance){
                v.SetState("Sparkling");
                // System.out.println("Intern washed " + v.GetType() + " " + v.GetName() + " and made it Sparkling (earned $" + v.GetWashBonus() + " bonus)");
                f.jo.NotifyObserver("Intern washed " + v.GetType() + " " + v.GetName() + " and made it Sparkling (earned $" + v.GetWashBonus() + " bonus)", v.GetWashBonus(), 0);
                f.ModifyBudget(-v.GetWashBonus());
                i.GainMoney(v.GetWashBonus());
            }
            else{
                // System.out.println("Level of cleanliness did not change...");
                f.jo.NotifyObserver("Level of cleanliness did not change...", 0, 0);
            }
        }

        // 10 % chance of becoming broken
        num = ThreadLocalRandom.current().nextInt(1,101); // 1-100
        if(num <= 10){
            v.SetCondition("Broken");
            // System.out.print("Vehicle also became broken from being washed");
            f.jo.NotifyObserver("Vehicle also became broken from being washed", 0, 0);
        }

    }

}


