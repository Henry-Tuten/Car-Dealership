// Based on Professor Montgomery's VehicleDecorator PDF example from Piazza
public class SatelliteRadio extends VDecorator{

    Vehicle v;

    SatelliteRadio(Vehicle v) {
        this.v = v;
        if(basePrice == 0){
            basePrice = v.Cost();
        }
    }

    double Cost(){
        return v.Cost() + basePrice * 0.05;
    }

    public String GetDescription() {
        return v.GetDescription() + " + satellite radio";
    }
    
}
