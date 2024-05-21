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

    //ticket array to list all the booked seat details and person details.
    public static Ticket[] tickets=new Ticket[52];

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
                        buy_seat();
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
    /*this method prints the menu
     */
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

    //    buy seat method
    public static void buy_seat() {
        System.out.println("Enter seat row and number that you want to book.\n");
        while (true) {
            try{
                System.out.print("Enter seat row letter (A/B/C/D) :"); //get the seat row letter user want to book
                char seat_row = Character.toUpperCase(input.next().charAt(0));

                //check is it a valid roe letter or not? if it is a valid roe letter it returns a rowindex number
                int rowIndex = -1;
                switch (seat_row) {
                    case 'A':
                        rowIndex = 0;
                        break;
                    case 'B':
                        rowIndex = 1;
                        break;
                    case 'C':
                        rowIndex = 2;
                        break;
                    case 'D':
                        rowIndex = 3;
                        break;
                    default:
                        System.out.println("Invalid seat row. Enter valid seat row.");
                        continue; //again asks row?
                }

                System.out.print("Enter seat number :");//get seat number
                int seat_column = input.nextInt();

                //check the entered seat number is between the range, if the input is wrong again asks the qn
                if (seat_column < 1 || seat_column > seat_order[rowIndex].length) {
                    System.out.println("Invalid seat number. Please enter row and seat number again");
                    continue; //if the user enter wrong input again asks for row and seat column
                }
//                according to the rowindex number and seat number this checks the 2D array and the required index is 0 programs asks
//                the name,email and surname .from these details person class create an object
                if (seat_order[rowIndex][seat_column - 1] == 0) {
                    seat_order[rowIndex][seat_column - 1] = 1; //if seat is not booked it books suite seat using user inputs
                    System.out.println("\nEnter your pesonal details to book the seat\n");
                    //collect person data
                    System.out.print("Enter your name : ");
                    String name = input.next();
                    System.out.print("Enter your Surname : ");
                    String surname = input.next();
                    System.out.print("Enter a email address : ");
                    String email = input.next();
                    //call the person object and create a new person using entered details
                    Person person = new Person(name, surname, email);

                    //ticket price
                    int price = calculate_price(seat_column);

//                     creating a new ticket
                    Ticket ticket = new Ticket(seat_row, seat_column, price, person); //call ticket object
                    ticket.save(); //save method called to save the text file
                    System.out.println("\nYour seat successfully booked and ticket downloaded");
//                  through the collected data ticket class creates ticket and from this for loop those data insert in to list
                    for (int y = 0; y <= tickets.length; y++) {
                        if (tickets[y] == null) {
                            tickets[y] = new Ticket(seat_row, seat_column, price, person); //insert data into the ticket array
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println("Sorry Seat already booked!");
                }
//                when the user enter a letter for the seat_number the program crashes, to prevent it this try catch used
            }catch (InputMismatchException e){
                System.out.println("\nEnter a valid input for seat number!\n");
                input.next();
            }

        }
    }
}
