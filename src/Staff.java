public class Staff {

    // attributes that are shared among any type of staff
    // encapsulated, only accessible by this and inherited classes, using getters or setters otherwise
    protected String name;
    protected int salary;
    protected int daysWorked;
    protected int earned;

    Staff(){
        daysWorked = 0;
        earned = 0;
    }

    public String GetName(){
        return name;
    }

    public int GetSalary(){
        return salary;
    }

    public int GetDaysWorked(){
        return daysWorked;
    }

    public int GetEarned(){
        return earned;
    }

    public void GainMoney(int money){ // for adding salary to amount earned, or bonuses
        earned += money;
    }

}
