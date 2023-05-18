//decorator pattern implementation
public class RoadRescueCoverage extends VDecorator {

    Vehicle v;

    RoadRescueCoverage(Vehicle v) {
        this.v = v;
        if(basePrice == 0){
            basePrice = v.Cost();
        }
    }

    double Cost(){
        return v.Cost() + basePrice * 0.02;
    }

    public String GetDescription() {
        return v.GetDescription() + " + road rescue coverage";
    }
}
