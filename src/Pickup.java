import java.util.concurrent.ThreadLocalRandom;

// Based on Professor Montgomery's VehicleDecorator PDF example from Piazza
public class Pickup extends Vehicle{

    private static int id = 0;

    Pickup(){
        name = GenerateName();
        cost = GenerateCost();
        saleBonus = 30;
        repairBonus = 20;
        washBonus = 10;
        winBonus = 20;
        salePrice = cost * 2;
        type = FNCD.VehicleType.PICKUP;
        description = "Pickup";
    }

    public double Cost(){
        return salePrice;
    }

    private String GenerateName(){ // unique name
        String n =  "Pickup" + "_" + id;
        id++;
        return n;
    }

    private int GenerateCost(){
        int c = ThreadLocalRandom.current().nextInt(10000,40001); // cost for pickup between $10,000 and $40,000 randomly
        return c;
    }

}
