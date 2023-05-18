// Based on Professor Montgomery's VehicleDecorator PDF example from Piazza.
public abstract class VDecorator extends Vehicle { // decorator pattern implementation, with ExtendedWarranty, RoadRescueCoverage, Undercoating, and SatelliteRadio decorators
    
    static double basePrice = 0;

    abstract String GetDescription();

}
