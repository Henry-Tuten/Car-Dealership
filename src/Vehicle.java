import java.util.concurrent.ThreadLocalRandom;

//decorator pattern implementation
public abstract class Vehicle{ // we will never make a vehicle, always will be a specific one, so can make it abstract. Also abstract for decoration

    // encapsulation of these variables, only accessible by this and inherited classes
    protected String condition;
    protected String state;
    protected boolean sold;

    protected int racesWon;

    protected String name;
    // protected String type;
    protected FNCD.VehicleType type;
    protected int saleBonus;
    protected int repairBonus;
    protected int washBonus;
    protected int winBonus;
    protected int cost;
    protected int salePrice; // change all of these monetary values to double

    String description = "Unknown";

    Vehicle(){
        condition = GenerateCondition();
        state = GenerateState();
        sold = false;
        racesWon = 0;
    }

    abstract double Cost(); // will be used to calculate total price

    public void IncrementRacesWon() {
        racesWon++;
        if(racesWon == 1){
            salePrice *= 1.1;
        }
    }

    String GetDescription(){
        return description;
    }

    private String GenerateCondition(){ // randomly chosen between these three conditions
        String conditions[] = {"Like New", "Used", "Broken"};
        int index = ThreadLocalRandom.current().nextInt(0,3);;
        return conditions[index];
    }

    private String GenerateState(){
        String conditions[] = {"Sparkling", "Clean", "Dirty"};
        int num = ThreadLocalRandom.current().nextInt(1,101);
        if(num <= 5){ // 5% chance to be sparkling
            return conditions[0];
        }
        else if(num <= 40){ // %35 to be clean
            return conditions[1];
        }
        else{ // %60 chance to be dirty
            return conditions[2];
        }
    }

    public int GetRaceBonus(){
        return winBonus;
    }

    public String GetCondition(){
        return condition;
    }

    public String GetState(){
        return state;
    }

    public boolean GetSold(){
        return sold;
    }

    public String GetName(){
        return name;
    }

    public int GetSaleBonus(){
        return saleBonus;
    }

    public int GetRepairBonus(){
        return repairBonus;
    }

    public int GetCost(){
        return cost;
    }

    public int GetSalePrice(){
        return salePrice;
    }

    public int GetWashBonus() { return washBonus; }

    public FNCD.VehicleType GetType() { return type; }

    public void SetState(String s){
        state = s;
    }

    public void SetCondition(String s){
        condition = s;
    }

    public void SetSalePrice(int p) { salePrice = p; }

}