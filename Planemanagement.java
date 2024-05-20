import java.util.InputMismatchException;
import java.util.Scanner;

public class Planemanagement {
    public static Scanner input = new Scanner(System.in);//declare "input" for Scanner class

    //create a 2d array for all the seats using rows and column numbers
    public static int[][] seat_order = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    //main class
    public static void main(String[] ags) {
        System.out.println("\nWelcome to the Plane Management application!\n"); //welcome msg , appear when starts the code
        boolean quit = false;
        while (!quit) {
            try {
                //prints the menu
                print_menu();
                System.out.print("Please select an option : ");
                int menu_choice = input.nextInt(); //assign input to menu_choice
                switch (menu_choice) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:
                        System.out.println("Ticket Information :-\n");

                        break;
                    case 6:
                        System.out.println("Search ticket");

                        break;
                    case 0:
                        System.out.println("Thank you for visiting us....");
                        quit = true;
                        break;
                    default:
                        System.out.println("Choose an option from above menu");
                }
                //when user enter a letter or other than a number program don't crash it display the string and loops
            }catch (InputMismatchException e){
                System.out.println("\nEnter a valid input for options !\n ");
                input.next();
            }
        }
    }

    public static void print_menu(){
        System.out.println("***************************************************");
        System.out.println("*                    Menu                         *");
        System.out.println("***************************************************");
        System.out.println("""
                        \t1) Buy a seat
                        \t2) Cancel a seat
                        \t3) Find first available seat
                        \t4) Show seating plan
                        \t5) Print tickets information and total sales
                        \t6) Search tickets
                        \t0) Quit""");
        System.out.println("***************************************************");
    }
}
