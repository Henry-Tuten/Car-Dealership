import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Motorcycle extends Vehicle{

    private static int id = 0;
    double engineSize;

    Motorcycle(){
        name = GenerateName();
        cost = GenerateCost();
        saleBonus = 55;
        repairBonus = 45;
        washBonus = 25;
        winBonus = 30;
        salePrice = cost * 2;
        type = FNCD.VehicleType.MOTORCYCLE;
        description = "Motorcycle";
        engineSize = GenerateEngineSize();
    }

    public double Cost(){
        return salePrice;
    }

    // https://stackoverflow.com/questions/31754209/can-random-nextgaussian-sample-values-from-a-distribution-with-different-mean
    private double GenerateEngineSize(){
        Random r = new Random();
        double sample = r.nextGaussian() * 300 + 700; // 300 std. dev., and 700 mean
        return sample;
    }

    private String GenerateName(){ // unique name
        String n =  "Motorcycle" + "_" + id;
        id++;
        return n;
    }

    private int GenerateCost(){
        int c = ThreadLocalRandom.current().nextInt(10000,40001); // cost for pickup between $10,000 and $40,000 randomly
        return c;
    }

}
