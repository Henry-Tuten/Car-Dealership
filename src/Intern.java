import java.util.concurrent.ThreadLocalRandom;

public class Intern extends Staff{

    private static int id = 0;
    private WashBehavior wb;

    Intern(){
        salary = 100;
        name = GenerateName();
        wb = GenerateWashBehavior();
    }

    private WashBehavior GenerateWashBehavior(){
        int num = ThreadLocalRandom.current().nextInt(0,3); // 0, 1 or 2, equal chance of each wash behavior
        if(num == 0){
            return new Chemical();
        }
        else if(num == 1){
            return new ElbowGrease();
        }
        else{
            return new Detailed();
        }
    }

    public void PerformWash(Vehicle v, FNCD f){
        wb.Wash(v, this, f);
    }

    // an intern will have this name format even after changing to salesperson or mechanic
    private String GenerateName(){
        String n =  "Intern" + "_" + id;
        id++;
        return n;
    }

}
