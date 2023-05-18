// Based on Professor Montgomery's VehicleDecorator PDF example from Piazza
public class ExtendedWarranty extends VDecorator {

    Vehicle v;

    ExtendedWarranty(Vehicle v) {
        this.v = v;
        if(basePrice == 0){
            basePrice = v.Cost();
        }
    }

    double Cost(){
        return v.Cost() + basePrice * 0.2;
    }

    public String GetDescription() {
         return v.GetDescription() + " + extended warranty";
     }

}
