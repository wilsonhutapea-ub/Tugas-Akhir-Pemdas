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

    //TODO if all stock == 0, print special output & output all stock r in your card already :D

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean cont = true;
        String ansStr;
        int ans_Int, pointer = 0, orderQty = 0, totalPrice;
        int[] orderQtyArr = new int[6];

        printHeader();

        System.out.println("Ralph: Hi there!");
        System.out.println("I'm Raywilfred, but you can call me Ralph. I will help \nyou buy a GPU in this store.");
        // TODO Hi, Ralph!
        System.out.println("How should I call you, friend?");
        String name = sc.nextLine();
        System.out.printf("Ralph: Nice to meet you %s.\n", name);
        System.out.println("Ralph: So in the scale of 1 to 10, how interested are you \nto take home your dream GPU ?");

        String ans = "yo";
//        String ans = "10";
        int ansInt = 0;
        try{
            ans = sc.nextLine();
            ansInt = Integer.parseInt(ans);
        } catch (NumberFormatException e){
            System.out.println("NFE");
            System.exit(0);
        }

        System.out.println(ansInt);

        if(ansInt >= 1 && ansInt <= 3){
            System.out.println(":(");
        } else if(ansInt >= 4 && ansInt <= 6) {
            System.out.println("ok..");
        } else if(ansInt >= 7 && ansInt <= 10){
            System.out.println("I like your spirit");
        } else if(ansInt > 10){
            System.out.println("Outstanding spirit!");
        } else {
            System.out.println(":(");
            System.out.println("alr bye");
            System.exit(0);
        }

        System.out.println("Here is our price list. Feel free to take a look.");

        do{
            printMenu();

            do{
                // TODO if input outside 1-6, dont continue, repeat.
                System.out.print("\nWhich GPU would you like to order ? (type in the number)\n> ");
                ans_Int = sc.nextInt();
                //disini
                if(ans_Int < 1 || ans_Int > 6){
                    while(ans_Int < 1 || ans_Int > 6){
                        System.out.println("Input must be 1-6");
                        System.out.print("> ");
                        ans_Int = sc.nextInt();
                        // TODO change into nextLine
                    }

                }
                pointer = ans_Int-1;
                if(ans_Int>goods.length || ans_Int<1){
                    System.out.println("Sorry, invalid input.");
                    break;
                }
                if(stock[pointer] == 0){
                    System.out.println("\nAh! so you wanna order the " + goods[pointer] + ".");
                    System.out.println("Good choice!");
                    System.out.println("But unfortunately " + goods[pointer] + " is currently out of stock.");
                }
            } while(ans_Int>goods.length || ans_Int<1 || stock[pointer] == 0);

            if(stock[pointer] != 0){
                System.out.println("\nAh! so you wanna order the " + goods[pointer] + ".");
                System.out.println("Good choice!\n");

                if(stock[pointer] == 1){
                    System.out.println("You're in luck.");
                    System.out.println("Only 1 is currently in stock!");
                } else
                    System.out.println(stock[pointer] + " are currently in stock!");
            }

            orderQty = 0;

            if(stock[pointer] != 0){
                do{
                    System.out.print("\nHow many would you like to order?\n> ");
                    orderQty = sc.nextInt();
                    if(orderQty > stock[pointer]){
                        System.out.print("Sorry. Only " + stock[pointer]);
                        if(stock[pointer] == 1){
                            System.out.print(" is ");
                        } else
                            System.out.print(" are ");
                        System.out.println("currently in stock!");
                    } else if(orderQty < 1)
                        System.out.println("Order quantity must be positive.");
                } while(orderQty > stock[pointer] || orderQty < 1);
            }


            // stock
            orderQtyArr[pointer] += orderQty;

//            System.out.printf("order quantity : %d\n\n", orderQty);
            stock[pointer] -= orderQty;
            totalPrice = printCart(orderQtyArr);
//            System.out.println(totalPrice);
            // TODO if ansStr does not equal y/n, repeat input
            System.out.println("Do you want to order again? (y/n)");
            sc.nextLine();
            ansStr = sc.nextLine();
            if(ansStr.equalsIgnoreCase("n")){
                System.out.println("Alright. Now let's talk about payment.");
                System.out.println("There is 10% tax.");
                System.out.println("There is 5% discount if you pay with credit card.");
                System.out.println("Please choose your payment method:");
                System.out.println("1. Cash\n2. Card\n> ");

                do{
                    ans_Int = sc.nextInt();
                    if(ans_Int < 1 || ans_Int > 2){
                        System.out.print("Invalid input. Please try again.\n> ");
                    }
                } while(ans_Int < 1 || ans_Int > 2);
                boolean disc = false;
                if(ans_Int == 2){
                    int totalPrice2 = (int) (totalPrice*0.95);
                    disc = true;
                }
                printInvoice(orderQtyArr, disc);
                System.exit(0);
            }

        } while(cont);

        sc.close();
    }

    static void printInvoice(int[] orderQtyArr, boolean disc){
        int typeCount = 0; // berapa jenis barang yang dipesan
        int totalPrice = 0;
        int grandTotal;

        System.out.println("Here is your invoice.");
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

        if(disc)
            grandTotal = (int) (totalPrice * 0.95);
        else {
            grandTotal = (int) (totalPrice);
        }
        int disc_Int = (int) (0.05 * totalPrice);
        int totalPriceAfterDisc = totalPrice-disc_Int;
        int tax = (int)(totalPriceAfterDisc*0.1);
        grandTotal = totalPriceAfterDisc+tax;
        // TODO CASH if cash, no CASH if no cash.
        // TODO discount & tax arrangement?
        printDivider();
        System.out.printf("| %38s | Rp %,11d |\n","TOTAL",totalPrice);
        System.out.printf("| %38s | Rp (%,9d) |\n","DISCOUNT",disc_Int);
        System.out.printf("| %38s | Rp %,11d |\n","TAX",tax);
        printDivider();
        System.out.printf("| %38s | Rp %,11d |\n","GRAND TOTAL",grandTotal);
        printDivider();
    }
    static int printCart(int[] orderQtyArr){
        int typeCount = 0;
        int totalPrice = 0;
        System.out.println("Here is your cart. Please recheck your order.\n");
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
        System.out.println("================= RAYWILFRED GAMING SHOP ==================");
        System.out.println("");
        System.out.println("Here you can buy PC parts like CPU, SSD, Mobo, and many \nmore.");
        System.out.println("");
    }

}
