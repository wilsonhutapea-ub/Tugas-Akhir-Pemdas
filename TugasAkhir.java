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

        boolean cont = true, invalidInput, disc, soldOut = false;
        String ansStr;
        int ansInt, pointer, orderQty;
        int[] orderQtyArr = new int[goods.length];

        printHeader();

        System.out.println("Ralph: Hi there!");
        System.out.println("Ralph: I'm Ralph, your personal shopping companion. I will \nhelp you buy GPUs in this store.");
        System.out.print("Ralph: How should I call you, friend?\n> ");
        name = sc.nextLine();
        System.out.printf("Ralph: Nice to meet you %s.\n", name);
        System.out.println("Ralph: So in the scale of 1 to 10, how excited are you \nto buy PC parts from our store ?");
        System.out.println("(1 = Not excited, 10 = Very excited)");
        System.out.printf("%s: ",name);

        ansInt = getInt(10);

        System.out.println();
        if(ansInt >= 1 && ansInt <= 6)
            System.out.println("Ralph: Are you having a bad day? Let's forget it for now \nand have fun buying PC parts.");
        else if(ansInt >= 7 && ansInt <= 10)
            System.out.println("Ralph: I like your spirit.");

        System.out.println("\nRalph: Here is our price list. Feel free to take a look.");

        do{
            printMenu();

            do{
                System.out.printf("\nRalph: Which GPU would you like to order ? (type in the \nnumber)\n%s: ",name);

                ansInt = getInt(6);

                pointer = ansInt - 1;
                System.out.printf("\nRalph: Ah! So you want to order the %s.\n",goods[pointer]);
                System.out.println("Ralph: Good choice!");

                if(stock[pointer] == 0){
                    System.out.printf("Ralph: But unfortunately %s is \ncurrently out of stock.\n",goods[pointer]);
                }
            } while (stock[pointer] == 0);

            if(stock[pointer] == 1){
                System.out.println("\nRalph: You're in luck.");
                System.out.println("Ralph: Only 1 is currently in stock!");
            } else
                System.out.println(stock[pointer] + " are currently in stock!");

            do{
                System.out.print("\nRalph: How many would you like to order?\n> ");
                orderQty = getInt();

                if(orderQty > stock[pointer]){
                    System.out.print("Ralph: Sorry. Only " + stock[pointer]);
                    if(stock[pointer] == 1){
                        System.out.print(" is ");
                    } else
                        System.out.print(" are ");
                    System.out.println("currently in stock!");
                }
            } while(orderQty > stock[pointer]);


            // stock
            orderQtyArr[pointer] += orderQty;

            stock[pointer] -= orderQty;

            for (int i : stock) {
                if(i != 0)
                    break;
                soldOut = true;
            }
            
            if(soldOut == false) {
                printCart(orderQtyArr);

                System.out.println("Do you want to order again? (y/n)");
                //TODO if all stock == 0, print special output & output all stock r in your card already :D

                invalidInput = false;
                do{
                    ansStr = sc.nextLine();
                    if(!ansStr.contains("y") && !ansStr.contains("n")){
                        invalidInput = true;
                        System.out.printf("Ralph: It seems your input is invalid. Input must contain \ny or n.\n%s: ",name);
                    }
                } while (invalidInput);
            }



            if(!ansStr.contains("y")){
                cont = false;
                System.out.println("\nRalph: Alright. Now let's talk about payment.");
                System.out.println("Ralph: There is 10% tax, and there is 5% discount if you \npay with credit card.");
                System.out.printf("Ralph: What is your preferred method of payment, %s?\n", name);
                System.out.println("1. Cash\n2. Card");
                System.out.printf("%s: ",name);

                ansInt = getInt(2);

                disc = ansInt == 2;

                printInvoice(orderQtyArr, disc);
            }

        } while(cont);

        sc.close();
    }

    static void printInvoice(int[] orderQtyArr, boolean disc){
        int typeCount = 0; // berapa jenis barang berbeda yang dipesan
        int subTotal = 0;
        int total;
        int disc_Int = 0;

        if(disc) {
            disc_Int = (int) (0.05 * subTotal);
        }

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
            subTotal += price[i]*orderQtyArr[i];
            typeCount++;
        }


        int subTotalAfterDisc = subTotal-disc_Int;
        int tax = (int)(subTotalAfterDisc*0.1);
        total = subTotalAfterDisc+tax;
        // TODO CASH if cash, no CASH if no cash.
        // TODO discount & tax arrangement?
        printDivider();
        System.out.printf("| %38s | Rp %,11d |\n","SUBTOTAL",subTotal);
        System.out.printf("| %38s | Rp %(,11d |\n","DISCOUNT",disc_Int);
        System.out.printf("| %38s | Rp %,11d |\n","TAX",tax);
        printDivider();
        System.out.printf("| %38s | Rp %,11d |\n","TOTAL",total);
        printDivider();
    }

    static void printCart(int[] orderQtyArr){
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
        System.out.println("================ RAYWILFRED COMPUTER SHOP =================");
        System.out.println("\nHere you can buy PC parts like CPU, SSD, Mobo, and build \nyour dream PC.\n");
    }

    static int getInt(int upperLimit){
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
                    System.out.printf("\nRalph: Sorry, input must be of Integer type. Please try \nagain.\n%s: ",name);
                    error = true;
                }
            } while (error);

            if(result < 1 || result > upperLimit){
                System.out.printf("\nRalph: Sorry %s, input must be 1-%d.\n",name,upperLimit);
                System.out.printf("%s: ", name);
            }
        } while (result < 1 || result > upperLimit);

        return result;
    }

    static int getInt(){
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
                    System.out.printf("\nRalph: Sorry, input must be of Integer type. Please try \nagain.\n%s: ",name);
                    error = true;
                }
            } while (error);

            if(result < 1){
                System.out.printf("\nRalph: Sorry %s, input must be 1 or bigger.\n",name);
                System.out.printf("%s: ", name);
            }
        } while (result < 1);

        return result;
    }
}
