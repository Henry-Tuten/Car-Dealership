import java.util.Scanner;

public class GetInventoryDetails extends CommandActions{

    public void Execute(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the inventory item you want to get the details of");
        String name = scan.nextLine();
        boolean found = s.GetInventoryDetails(f, name);
        if(!found){
            System.out.println("No inventory item by that name was found");
        }
    }

}
