import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Limo extends Vehicle{

    private static int id = 0;

    Limo(){
        name = GenerateName();
        cost = GenerateCost();
        saleBonus = 55;
        repairBonus = 45;
        washBonus = 25;
//        winBonus = 30;
        salePrice = cost * 2;
        type = FNCD.VehicleType.LIMO;
        description = "Limo";
    }

    public double Cost(){
        return salePrice;
    }

    private String GenerateName(){ // unique name
        String n =  "Limo" + "_" + id;
        id++;
        return n;
    }

    private int GenerateCost(){
        int c = ThreadLocalRandom.current().nextInt(50000,100001); // cost for limo between 50,000 and 100,000
        return c;
    }

}
