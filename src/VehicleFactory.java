public class VehicleFactory { // simple factory pattern

    public static Vehicle GenerateVehicle(FNCD.VehicleType vehicleType){ // static, access from anywhere
        switch(vehicleType){ // based on the vehicle type enum which is cleaner than using a string
            case MONSTER_TRUCK:
                return new MonsterTruck();
            case MOTORCYCLE:
                return new Motorcycle();
            case PERFORMANCE_CAR:
                return new PerformanceCar();
            case PICKUP:
                return new Pickup();
            case CAR:
                return new Car();
            case ELECTRIC_CAR:
                return new ElectricCar();
            case HYBRID:
                return new Hybrid();
            case SEMI_TRUCK:
                return new SemiTruck();
            default: // default to limo, or whatever is left over
                return new Limo();
        }
    }
}
