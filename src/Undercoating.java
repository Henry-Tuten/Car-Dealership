//decorator pattern implementation
// Based on Professor Montgomery's VehicleDecorator PDF example from Piazza
public class Undercoating extends VDecorator {

    Vehicle v;

    Undercoating(Vehicle v) {
        this.v = v;
        if(basePrice == 0){
            basePrice = v.Cost();
        }
    }

    double Cost(){
        return v.Cost() + basePrice * 0.05;
    }

    public String GetDescription() {
        return v.GetDescription() + " + undercoating";
    }

    
}
