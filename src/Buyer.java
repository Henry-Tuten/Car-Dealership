import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Buyer {

    private String buyType;
    // private String vehicleType;
    private FNCD.VehicleType vehicleType;

    Buyer(){
        buyType = GenerateBuyType();
        vehicleType = GenerateVehicleType();
    }

    private String GenerateBuyType(){ // randomly generate buy type, which later has effect on probability of purchasing
        String buyType[] = {"Just Looking", "Wants One", "Needs One"};
        int num = ThreadLocalRandom.current().nextInt(0,3);
        return buyType[num];
    }

    private FNCD.VehicleType GenerateVehicleType(){ // desired car type chosen randomly
        // String vehicles[] = {"Performance Car", "Car", "Pickup"};
        FNCD.VehicleType[] vehicles = FNCD.VehicleType.values();
        int index = ThreadLocalRandom.current().nextInt(0,vehicles.length);
        return vehicles[index];
    }

    public String GetBuyType(){
        return buyType;
    }

    public FNCD.VehicleType GetVehicleType(){
        return vehicleType;
    }

}
