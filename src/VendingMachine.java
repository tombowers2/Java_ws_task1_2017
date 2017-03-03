
import java.util.Scanner;

public class VendingMachine {
    public static ProductLine[] setup() {
        //creates array of product line objets
        ProductLine[] items = new ProductLine[4];
        items[0] = new ProductLine(5, 80, "Coke");
        items[1] = new ProductLine(5, 70, "Pepsi");
        items[2] = new ProductLine(5, 60, "Lilt");
        items[3] = new ProductLine(5, 50, "Sprite");
        return items;
    }

    public static void restock(ProductLine[] items) {
        //sets stock of all product line objects to 5
        for (ProductLine item : items) item.setStock(5);
    }

    public static void menu(Scanner numPad, ProductLine[] items) {
        //printing menu
        System.out.println(">java VendingMachine");
        System.out.println("");
        System.out.println("");
        for(int i = 0; i < items.length; i++){
        	System.out.println("[" + i + "] : " + items[i].getNAME() + ", Price : " + items[i].getCost() + " pence, " + items[i].getStock() + " remaining");
        }
        System.out.println("[4] : System maintenance");
        System.out.println("");
        System.out.println("Please make your selection:");
        //geting number input
        int selection = numPad.nextInt();
        //recall menu if out of valid range
        while (selection < 0 || selection > 4) {
            System.out.println("Invalid Input");
            menu(numPad, items);
        }
        if (selection == 4) {
            //calls the maintinence menu
            int mSelection = maintenanceMenu(numPad);
            while (mSelection != 0 && mSelection != 1) {
                System.out.println("Invalid Input");
                mSelection = maintenanceMenu(numPad);
            }
            if (mSelection == 0) {
                restock(items);
                System.out.println("Successfully restocked");
                menu(numPad, items);
            } else System.exit(0);
        } else {
            //if valid input check stock them call vend method
            if (items[selection].getStock() > 0) {
                //when there is stock start vending methoid
                vend(numPad, items, selection);
            } else {
                //if out of stock error message then back to menu
                System.out.println("Out of stock please choose something else");
                menu(numPad, items);
            }
        }

    }
    //vending method
    public static void vend(Scanner numPad, ProductLine[] items, int selection) {
    	//reduces stock count for selected item
      		items[selection].setStock(items[selection].getStock() - 1);
        
        //resets money and payment
        int moneyPaid = 0;
        int payment = 1;
        //getting money input
        while ((items[selection].getCost() > moneyPaid) && (payment != 0)) {
            System.out.println("Please enter " + (items[selection].getCost() - moneyPaid) + " pence");
            payment = numPad.nextInt();
            moneyPaid = moneyPaid += payment;
        }
        //when enough money is paid
        if (moneyPaid == items[selection].getCost()){ 
            System.out.println("Please take your refreshing drink of " + items[selection].getNAME());
            menu(numPad, items);
        } else if (payment == 0) {
            //if 0 money is entered cancel transaction
            System.out.println("Transaction canceled returning to menu...");
            //incereases stock count for selected item as it wasnt dispensed
      		items[selection].setStock(items[selection].getStock() + 1);
            
            menu(numPad, items);
        } else {
            System.out.println("Please take your " + (moneyPaid - items[selection].getCost()) + " pence of change");
            menu(numPad, items);
        }
    }

    //maintinance menu
    public static int maintenanceMenu(Scanner numPad){
        System.out.println("[0] : Restock the machine");
        System.out.println("[1] : Shut down the machine");
        System.out.println("Please make your selection:");
        int selection = numPad.nextInt();
        return selection;
    }

    //main method
    public static void main(String[] args) {
        //initialises scanner
        Scanner numPad = new Scanner(System.in);
        //creates items array
        ProductLine items[] = setup();
        //calls menu to start loop
        menu(numPad, items);

    }
}
