
package tugasakhir;

import java.util.Scanner;

public class TugasAkhir {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        boolean cont = true;

        do{
            String[] goods = {
                "Colorful Nvidia GTX 1650",
                "ASRock AMD RX 6600",
                "MSI Nvidia RTX 3050",
                "ROG Strix Nvidia RTX 3080",
                "Gigabyte AMD RX 7900 XTX",
                "Zotac Nvidia RTX 4090",
            };

            int[] stock = {
                10,
                14,
                7,
                4,
                2,
                1
            };
            
            int[] price = {
                2599000,
                4150000,
                5150000,
                14450000,
                16200000,
                30999000,
            };
            

            header();
            
            
            printMenu(goods, stock, price);

            System.out.println("So.. are you interested to order one ? (yes/no)");
//            String ans = sc.nextLine();
            String ans = "yes";
            if(!ans.equalsIgnoreCase("yes")){
                break;
            }
            
            int ans2;
            int pointer;
            do{
            System.out.println("Which GPU would you like to order ? (type in the number)");
            ans2 = sc.nextInt();
            pointer = ans2-1;
            if(ans2>goods.length || ans2<1)
                System.out.println("Sorry, invalid input.");
            } while(ans2>goods.length || ans2<1);
            
            System.out.println("\nAh! so you wanna order the " + goods[pointer] + ".");
            System.out.println("Good choice!\n");
            
            if(stock[pointer] == 1){
                System.out.println("You're in luck.");
                System.out.println("Only 1 is currently in stock!");
            } else if(stock[pointer] == 0)
                System.out.println("But unfortunately " + goods[pointer] + " is currently out of stock.");
            else
                System.out.println(stock[pointer] + " are currently in stock!");
                
//            System.out.println("");
            
            int order = 0;
            
            do{
                System.out.print("\nHow many would you like to order?\n> ");
                order = sc.nextInt();
                if(order > stock[pointer]){
                    System.out.print("Sorry. Only " + stock[pointer]);
                    if(stock[pointer] == 1){
                        System.out.print(" is ");
                    } else
                    System.out.print(" are ");
                    System.out.println("currently in stock!");
                } else if(order < 1)
                    System.out.println("Order quantity must be positive.");
            } while(order > stock[pointer] || order < 1);
            System.out.println("order quantity : " + order);
            stock[pointer] -= order;
            invoice(goods, stock, price, pointer, order);
            
            System.out.println("Do you want to order again? (yes/no)");
            ans = sc.nextLine();
            if(!ans.equalsIgnoreCase("yes")){
                System.out.println("Thank you, come by another time.");
                System.exit(0);
            }
                
        } while(cont);
        
        sc.close();
    }
    
    static void invoice(String[] goods, int[] stock, int[] price, int pointer, int order){
        System.out.println("Here is your invoice.\n");
        System.out.println("Please recheck your order.\n");
        System.out.println("======================== INVOICE ==========================");
        System.out.printf("|%-3s| %-14s | %5s | %12s |\n",
                "No.",
                "       Product Name       ",
                "Qty",
                " Total Price  ");
        divider();
        
        System.out.printf("| %d | %-26s | %5d | Rp %,11d |\n",
                    1, 
                    goods[pointer],
                    order,
                    price[pointer]*order
            );
        divider();
    }
    
    
    static void printMenu(String[] goods, int[] stock, int[] price){
        
        System.out.println("\nI like your spirit!");
        System.out.println("Here is our price list. Feel free to take a look.\n");
        System.out.println("======================= LIST HARGA ========================");
        System.out.printf("|%-3s| %-14s | %5s | %12s |\n",
                "No.",
                "       Product Name       ",
                "Stock",
                "     Price    ");
        divider();
        
        for (int i = 0; i < goods.length; i++) {
            System.out.printf("| %d | %-26s | %5d | Rp %,11d |\n",
                    i+1, 
                    goods[i],
                    stock[i],
                    price[i]
            );  
        }
        divider();
        
    }
    
    
    
    static void divider(){
        System.out.println("===========================================================");
    }
    
    static void header(){
        System.out.println("======================= WELCOME TO ========================");
        System.out.println("================= RAYWILFRED GAMING SHOP ==================");
        System.out.println("");
        System.out.println("Here you can buy PC parts like CPU, SSD, Mobo, and many\nmore.");
        System.out.println("");
        System.out.println("Hi there! My name is Raywilfred. I will help you buy a GPU\nin this store.");
        System.out.println("So in the scale of 1 to 10, how interested are you to take\nhome your dream GPU ?");
        
        Scanner sc = new Scanner(System.in);
//        String ans = sc.nextLine();
        String ans = "10";
        if(!ans.equalsIgnoreCase("10")){
            System.out.println("Thank you, come by another time.");
            System.exit(0);
        }
    }
    
}
