import java.util.Scanner;

public class BuyItem extends CommandActions{

    public void Execute(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the name of the inventory item you want to buy");
        String name = scan.nextLine();
        boolean found = s.BuyInventoryItem(f, name);
        if(!found){
            System.out.println("No inventory item by that name was found");
        }
    }

}
