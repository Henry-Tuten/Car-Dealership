import java.util.concurrent.ThreadLocalRandom;

public class ElectricCar extends Vehicle{

    private static int id = 0;
    private int range;

    ElectricCar(){
        name = GenerateName();
        cost = GenerateCost();
        saleBonus = 55;
        repairBonus = 45;
        washBonus = 25;
        salePrice = cost * 2;
        type = FNCD.VehicleType.ELECTRIC_CAR;
        description = "Electric Car";
        range = ThreadLocalRandom.current().nextInt(60, 401); // increase this if "like new" or repaired to "like new"
        if(condition == "Like New"){
            range += 100;
        }
    }

    public double Cost(){
        return salePrice;
    }

    private String GenerateName(){ // unique name
        String n =  "ElectricCar" + "_" + id;
        id++;
        return n;
    }

    private int GenerateCost(){
        int c = ThreadLocalRandom.current().nextInt(10000,40001); // cost for pickup between $10,000 and $40,000 randomly
        return c;
    }

}
