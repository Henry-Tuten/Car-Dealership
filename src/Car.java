import java.util.concurrent.ThreadLocalRandom;

public class Car extends Vehicle{

    private static int id = 0;

    Car(){ // unique name and random cost generated, and values for bonuses that seem reasonable
        name = GenerateName();
        cost = GenerateCost();
        saleBonus = 40;
        repairBonus = 30;
        washBonus = 15;
        salePrice = cost * 2;
        type = FNCD.VehicleType.CAR;
        description = "Car";
    }

    public double Cost(){
        return salePrice;
    }

    private String GenerateName(){ // Identity for name, every car will be unique becaues of id
        String n =  "Car" + "_" + id;
        id++;
        return n;
    }

    private int GenerateCost(){
        int c = ThreadLocalRandom.current().nextInt(10000,20001); // cost for car between $10,000 and $20,000
        return c;
    }

}
