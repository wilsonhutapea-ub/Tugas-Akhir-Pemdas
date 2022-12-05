import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TugasAkhir {
    static String[] goods = {
            "Colorful Nvidia GTX 1650",
            "ASRock AMD RX 6600",
            "MSI Nvidia RTX 3050",
            "ROG Strix Nvidia RTX 3080",
            "Gigabyte AMD RX 7900 XTX",
            "Zotac Nvidia RTX 4090"
    };
    static int[] stock = {10,14,7,4,2,1};

    static int[] price = {2599000, 4150000, 5150000, 14450000, 16200000,30999000};
    static String name;



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean cont = true, error = false;
        String ansStr;
        int ansInt = 0, pointer = 0, orderQty = 0, totalPrice;
        int[] orderQtyArr = new int[goods.length];

        printHeader();

        System.out.println("Ralph: Hi there!");
        System.out.println("Your response: (type 1 or 2)");
        // TODO Hi, Ralph!
        System.out.print("1. Hi, Ralph!\n2. Hmmm...\n> ");
        ansStr = sc.nextLine();
        System.out.println("Ralph: Hi!");
        System.out.println("Ralph: I'm Ralph, your personal shopping companion. I will \nhelp you buy GPUs in this store.");
        System.out.print("Ralph: How should I call you, friend?\n> ");
        name = sc.nextLine();
        System.out.printf("Ralph: Nice to meet you %s.\n", name);
        System.out.println("Ralph: So in the scale of 1 to 10, how excited are you \nto buy PC parts from our store ?");
        System.out.println("(1 = Not excited, 10 = Very excited)");
        // TODO if outside 1-10

        System.out.printf("%s: ",name);


        ansInt = getInt(1,10);

//        do{
//            do{
//                error = false;
//                try{
//                    ansStr = sc.nextLine();
//                    ansInt = Integer.parseInt(ansStr);
//                } catch (NumberFormatException e){
//                    System.out.printf("\nRalph: It seems your input is invalid. Input must be of Integer type.\n%s: ",name);
//                    error = true;
//                }
//            } while (error == true);
//
//            if(ansInt < 1 || ansInt > 10){
//                System.out.printf("Ralph: It seems your input is invalid. Input must be 1-10.\n%s: ",name);
//            }
//        } while (ansInt < 1 || ansInt > 10);



//        System.out.println(ansInt);

        System.out.println();
        if(ansInt >= 1 && ansInt <= 3){
            System.out.println("Ralph: :(");
        } else if(ansInt >= 4 && ansInt <= 6) {
            System.out.println("Ralph: C'mon, cheer up a bit.");
        } else if(ansInt >= 7 && ansInt <= 10){
            System.out.println("Ralph: I like your spirit.");
        } else {
            System.out.println(":(");
            System.out.println("alr bye");
            System.exit(0);
        }

        System.out.println("Ralph: Here is our price list. Feel free to take a look.");

        do{
            printMenu();

            do{
                // TODO if input outside 1-6, don't continue, repeat.
                System.out.printf("\nRalph: Which GPU would you like to order ? (type in the \nnumber)\n%s: ",name);

                ansInt = getInt(1,6);
//                do{
//                    try{
//                        ansStr = sc.nextLine();
//                        ansInt = Integer.parseInt(ansStr);
//                    } catch (NumberFormatException e){
//                        System.out.println("\nRalph: It seems your input is invalid. Input must be of Integer type.");
//                        ansInt = 1;
//                    }
//
//                    if(ansInt < 1 || ansInt > 6){
//                        System.out.printf("Ralph: It seems your input is invalid. Input must be 1-6.\n%s: ",name);
//                    }
//                } while (ansInt < 1 || ansInt > 6);

                pointer = ansInt - 1;
                System.out.printf("\nRalph: Ah! So you want to order the %s.\n",goods[pointer]);
                System.out.println("Ralph: Good choice!");

                if(stock[pointer] == 0){
//                    System.out.printf("\nRalph: Ah! So you want to order the %s.\n",goods[pointer]);
//                    System.out.println("Ralph: Good choice!");
                    System.out.printf("Ralph: But unfortunately %s is \ncurrently out of stock.\n",goods[pointer]);
                }
            } while (stock[pointer] == 0);


//            System.out.println("\nRalph: Ah! so you want to order the " + goods[pointer] + ".");
//            System.out.println("Ralph: Good choice!\n");

            if(stock[pointer] == 1){
                System.out.println("Ralph: You're in luck.");
                System.out.println("Ralph: Only 1 is currently in stock!");
            } else
                System.out.println(stock[pointer] + " are currently in stock!");


            orderQty = 0;

            do{
                System.out.print("\nRalph: How many would you like to order?\n> ");
                do {
                    try{
                        ansStr = sc.nextLine();
                        orderQty = Integer.parseInt(ansStr);
                    } catch (InputMismatchException e) {
                        System.out.println("Ralph: It seems your input is invalid. Input must be of Integer type");
                        orderQty = 9999;
                    }
                } while (orderQty > stock[pointer]);

                if(orderQty > stock[pointer]){
                    System.out.print("Ralph: Sorry. Only " + stock[pointer]);
                    if(stock[pointer] == 1){
                        System.out.print(" is ");
                    } else
                        System.out.print(" are ");
                    System.out.println("currently in stock!");
                } else if(orderQty < 1)
                    System.out.printf("Ralph: %s, order quantity must be positive.\n",name);
            } while(orderQty > stock[pointer] || orderQty < 1);


            // stock
            orderQtyArr[pointer] += orderQty;

            stock[pointer] -= orderQty;
            totalPrice = printCart(orderQtyArr);

            // TODO if ansStr does not equal y/n, repeat input
            System.out.println("Do you want to order again? (y/n)");
            //TODO if all stock == 0, print special output & output all stock r in your card already :D
            do{
                ansStr = sc.nextLine();
                if(!ansStr.contains("y") && !ansStr.contains("n")){
                    System.out.printf("Ralph: It seems your input is invalid. Input must contain y or n.\n%s: ",name);
                }

            } while (!ansStr.contains("y") && !ansStr.contains("n"));


            if(!ansStr.contains("y")){
                cont = false;
                System.out.println("Ralph: Alright. Now let's talk about payment.");
                System.out.println("Ralph: There is 10% tax.");
                System.out.println("Ralph: There is 5% discount if you pay with credit card.");
                System.out.printf("Ralph: %s, what is your preferred method of payment?\n", name);
                System.out.println("1. Cash\n2. Card");
                System.out.printf("%s: ",name);

                do{
                    ansInt = sc.nextInt();
                    if(ansInt < 1 || ansInt > 2){
                        System.out.print("Ralph: Invalid input. Please try again.\n> ");
                    }
                } while(ansInt < 1 || ansInt > 2);
                boolean disc = false;
                if(ansInt == 2){
                    int totalPrice2 = (int) (totalPrice*0.95);
                    disc = true;
                }
                printInvoice(orderQtyArr, disc);
            }

        } while(cont);

        sc.close();
    }

    static void printInvoice(int[] orderQtyArr, boolean disc){
        int typeCount = 0; // berapa jenis barang yang dipesan
        int totalPrice = 0;
        int grandTotal;
        int disc_Int = 0;

        System.out.println("Ralph: Here is your invoice.");
        System.out.println("======================== INVOICE ==========================");
        System.out.printf("|%-3s| %-14s | %5s | %12s |\n",
                "No.",
                "       Product Name       ",
                "Qty",
                " Total Price  ");
        printDivider();

        for (int i = 0; i < orderQtyArr.length; i++) {
            if(orderQtyArr[i] == 0)
                continue;
            System.out.printf("| %d | %-26s | %5d | Rp %,11d |\n",
                    typeCount+1,
                    goods[i],
                    orderQtyArr[i],
                    price[i]*orderQtyArr[i]
            );
            totalPrice += price[i]*orderQtyArr[i];
            typeCount++;
        }

        if(disc) {
            grandTotal = (int) (totalPrice * 0.95);
            disc_Int = (int) (0.05 * totalPrice);
        }
        else {
            grandTotal = (int) (totalPrice);
        }
        int totalPriceAfterDisc = totalPrice-disc_Int;
        int tax = (int)(totalPriceAfterDisc*0.1);
        grandTotal = totalPriceAfterDisc+tax;
        // TODO CASH if cash, no CASH if no cash.
        // TODO discount & tax arrangement?
        printDivider();
        System.out.printf("| %38s | Rp %,11d |\n","TOTAL",totalPrice);
        System.out.printf("| %38s | Rp %(,11d |\n","DISCOUNT",disc_Int);
        System.out.printf("| %38s | Rp %,11d |\n","TAX",tax);
        printDivider();
        System.out.printf("| %38s | Rp %,11d |\n","GRAND TOTAL",grandTotal);
        printDivider();
    }
    static int printCart(int[] orderQtyArr){
        int typeCount = 0;
        int totalPrice = 0;
        System.out.println("\nRalph: Here is your cart. Please recheck your order.\n");
        System.out.println("========================== CART ===========================");
        System.out.printf("|%-3s| %-14s | %5s | %12s |\n",
                "No.",
                "       Product Name       ",
                "Qty",
                " Total Price  ");
        printDivider();

        for (int i = 0; i < orderQtyArr.length; i++) {
            if(orderQtyArr[i] == 0)
                continue;
            System.out.printf("| %d | %-26s | %5d | Rp %,11d |\n",
                    typeCount+1,
                    goods[i],
                    orderQtyArr[i],
                    price[i]*orderQtyArr[i]
            );
            totalPrice += price[i]*orderQtyArr[i];
            typeCount++;
        }

        printDivider();
        System.out.printf("| %38s | Rp %,11d |\n","TOTAL",totalPrice);
        printDivider();
        return totalPrice;
    }

    static void printMenu(){

        System.out.println("\n======================= LIST HARGA ========================");
        System.out.printf("|%-3s| %-14s | %5s | %12s |\n",
                "No.",
                "       Product Name       ",
                "Stock",
                "     Price    ");
        printDivider();

        for (int i = 0; i < goods.length; i++) {
            System.out.printf("| %d | %-26s | %5d | Rp %,11d |\n",
                    i+1,
                    goods[i],
                    stock[i],
                    price[i]
            );
        }
        printDivider();

    }

    static void printDivider(){
        System.out.println("===========================================================");
    }

    static void printHeader(){
        System.out.println("======================= WELCOME TO ========================");
//        System.out.println("================= RAYWILFRED GAMING SHOP ==================");
        System.out.println("================ RAYWILFRED COMPUTER SHOP =================");
        System.out.println("\nHere you can buy PC parts like CPU, SSD, Mobo, and build \nyour dream PC.\n");
    }

    static int getInt(int batasBawah, int batasAtas){
        Scanner sc = new Scanner(System.in);
        int result = 0;
        boolean error;

        do{
            do{
                error = false;
                try{
                    String ansStr = sc.nextLine();
                    result = Integer.parseInt(ansStr);
                } catch (NumberFormatException e){
                    System.out.printf("\nRalph: It seems your input is invalid. Input must be of \nInteger type.\n%s: ",name);
                    error = true;
                }
            } while (error);

            if(result < batasBawah || result > batasAtas){
                System.out.printf("Ralph: It seems your input is invalid. Input must be %d-%d.\n",batasBawah,batasAtas);
                System.out.printf("%s: ", name);
            }
        } while (result < batasBawah || result > batasAtas);

        return result;
    }
}
