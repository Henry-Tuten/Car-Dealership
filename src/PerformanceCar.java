import java.util.concurrent.ThreadLocalRandom;

public class PerformanceCar extends Vehicle{

    private static int id = 0;

    PerformanceCar(){ // unique name and random cost generated, and values for bonuses that seem reasonable
        name = GenerateName();
        cost = GenerateCost();
        saleBonus = 50;
        repairBonus = 40;
        washBonus = 20;
        winBonus = 20;
        salePrice = cost * 2;
        type = FNCD.VehicleType.PERFORMANCE_CAR;
        description = "Performance Car";
    }

    public double Cost(){
        return salePrice;
    }

    private String GenerateName(){ // unique name based on unique static id
        String n =  "PerformanceCar" + "_" + id;
        id++;
        return n;
    }

    private int GenerateCost(){ // random cost between $20,000 and $40,000
        int c = ThreadLocalRandom.current().nextInt(20000,40001);
        return c;
    }

}



