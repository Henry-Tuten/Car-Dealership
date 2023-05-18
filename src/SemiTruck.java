import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SemiTruck extends Vehicle{

    private static int id = 0;

    SemiTruck(){
        name = GenerateName();
        cost = GenerateCost();
        saleBonus = 55;
        repairBonus = 45;
        washBonus = 25;
//        winBonus = 30;
        salePrice = cost * 2;
        type = FNCD.VehicleType.SEMI_TRUCK;
        description = "Semi Truck";
    }

    public double Cost(){
        return salePrice;
    }

    private String GenerateName(){ // unique name
        String n =  "SemiTruck" + "_" + id;
        id++;
        return n;
    }

    private int GenerateCost(){
        int c = ThreadLocalRandom.current().nextInt(40000,50001); // cost for pickup between $10,000 and $40,000 randomly
        return c;
    }

}
