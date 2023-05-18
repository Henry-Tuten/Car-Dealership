public class ChangeFNCD extends CommandActions{

    public void Execute(){
        if(f.name == f1.name){ // just swaps them based on the name
            f = f2;
        }
        else{
            f = f1;
        }
        s = (Salesperson) f.staff.get(FNCD.StaffType.SALESPERSON).get(0); // swap to that FNCDs staff as well
        System.out.println("Swapped to FNCD " + f.name);
    }

}
