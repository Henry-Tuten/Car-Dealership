import java.util.concurrent.ThreadLocalRandom;

public class MonsterTruck extends Vehicle{

    private static int id = 0;
    // create list of monster trucks from this, then index into it with the id to make sure it is unique. Second variable for if we have to reuse, which is also a static int but only increments after looping through whole thing

    MonsterTruck(){
        name = GenerateName();
        cost = GenerateCost();
        saleBonus = 55;
        repairBonus = 45;
        washBonus = 25;
        winBonus = 40;
        salePrice = cost * 2;
        type = FNCD.VehicleType.MONSTER_TRUCK;
        description = "Monster Truck";
    }

    public double Cost(){
        return salePrice;
    }

    private String GenerateName(){ // unique name, from https://www.rookieroad.com/monster-trucks/list-a-z-2027269/
        String n =  "MonsterTruck" + "_" + id; // need a unique stage name
        id++;
        return n;
    }

    private int GenerateCost(){
        int c = ThreadLocalRandom.current().nextInt(10000,40001);
        return c;
    }

}
