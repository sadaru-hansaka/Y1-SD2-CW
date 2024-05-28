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
                        cancel_seat();
                        break;
                    case 3:
                        find_first_available();
                        break;
                    case 4:
                        show_seating_plan();
                        break;
                    case 5:
                        System.out.println("Ticket Information :-\n");
                        print_tickets_info();
                        break;
                    case 6:
                        System.out.println("Search ticket");
                        search_ticket();
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

    //calculate ticket price
    public static int calculate_price(int seat_column){
        int price = 0;
        if (seat_column <= 5) {
            price = 200;
        } else if (seat_column <= 9) {
            price = 150;
        } else {
            price = 180;
        }
        return price;
    }

    //    cancel a seat
    /*from this method asks for user to enter seat roe and number than user want to cancel and if it is booked, the seat
    /will remove from the 2d array and mark as a not booked. And remove ticket details from the ticket array*/
    public static void cancel_seat() {
        System.out.println("Enter seat row and number that you want to cancel.\n");
        while (true) {
            try{
                System.out.print("Enter seat row letter(A/B/C/D) :");
                char cancel_seat_row = Character.toUpperCase(input.next().charAt(0));

                //check the entered seat row is valid and return an index
                int rowIndex = -1;
                switch (cancel_seat_row) {
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
                        System.out.println("Invalid seat row enter a valid row.");
                        continue;  //again goes to begin
                }

                System.out.print("Enter seat number :");
                int cancel_seat_column = input.nextInt();

                //checks the entered seat number is valid using seating order 2D array
                if (cancel_seat_column < 1 || cancel_seat_column > seat_order[rowIndex].length) {
                    System.out.println("Invalid seat number. Please enter row and seat number again");
                    continue;
                }

                //if the entered seat is booked it change to not booked( 1 to 0 )
                if (cancel_seat_column - 1 <= seat_order[rowIndex].length) {
                    if (seat_order[rowIndex][cancel_seat_column - 1] == 1) {
                        seat_order[rowIndex][cancel_seat_column - 1] = 0;
                        System.out.println("\nSeat Successfully Canceled");
                    } else {
                        System.out.println("\nSorry Seat not booked!");
                    }
                }

                //remove ticket information from the ticket array
                int remove_index = -1;
                for (int n = 0; n < tickets.length; n++) {
                    Ticket ticket = tickets[n];
                    if (tickets[n] != null && ticket.getRow() == cancel_seat_row && ticket.getSeat() == cancel_seat_column) {
                        remove_index = n;
                        break;
                    }
                }
                if (remove_index != -1) {
                    tickets[remove_index] = null;
                }
                break; // breaks the loop

                //when the user inputs a letter to seat number program not crash and display an error msg
            }catch (InputMismatchException e){
                System.out.println("\nEnter a valid input for seat number!\n");
                input.next();
            }
        }
    }

    /*find first available seat
    checks the sea_order array and search for first 0, and get the index according to the index this method displays first available seat
    this checks row by row*/
    public static void find_first_available() {
        System.out.print("\nFirst available seat is : ");
        char[] row_letter = {'A','B','C','D'};
        for (int i = 0; i <= (seat_order.length) - 1; i++) {
            for (int x = 0; x <= (seat_order[i].length) - 1; x++) {
                if (seat_order[i][x] == 0) {
                    System.out.println(row_letter[i] + "" + (x + 1) + "\n");
                    return;
                }
            }
            System.out.println();
        }

    }

    /*show seat planing method
    from this method displays the booked and not booked seats using "O" and "X".
    if the seat is booked this will show as "X"*/
    public static void show_seating_plan() {
        System.out.println("\nThis is the seating plan of this session :-\n");
        System.out.println("O - Available seats\nX - Booked seats");
        for (int i = 0; i <= (seat_order.length) - 1; i++) {
            for (int x = 0; x <= (seat_order[i].length) - 1; x++) {
                int out = seat_order[i][x];
                if (out == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    //print ticket information
    /*from this method program displays all the booked seat's information which is stored to ticket array*/
    public static void print_tickets_info(){
        int total=0;
        for(int k=0;k<=(tickets.length-1);k++){
            if(tickets[k]!=null) {
                tickets[k].print_tickets();
                System.out.println("____________________________________________");
                total=total+tickets[k].getPrice();
            }
        }
        System.out.println("Total sales during this session : £"+total+"\n"); //after print ticket details the total sales of the session displays
    }

    // search ticket method foe input number 5
    //from this method user can see the booked seat details and user details from entering seat row and seat number
    public static void search_ticket(){
        System.out.println("Enter seat row and number that you want to search.\n");
        while (true) {
            try{
                System.out.print("Enter row letter (A/B/C/D) : ");
                char search_row = Character.toUpperCase(input.next().charAt(0));

                int rowIndex = -1;
                switch (search_row) {
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
                        System.out.println("Invalid seat row enter a valid row.");
                        continue;
                }

                System.out.print("Enter seat number : ");
                int search_column = input.nextInt();

                if(search_column < 1 || search_column > seat_order[rowIndex].length) {
                    System.out.println("Invalid seat number. Please enter row and seat number again");
                    continue;
                }

                if (search_column <= seat_order[rowIndex].length) {
                    if (seat_order[rowIndex][search_column - 1] == 1) {

                        int search_index = -1;
                        for (int n = 0; n <= tickets.length; n++) {
                            Ticket ticket = tickets[n];
                            if (tickets[n] != null && ticket.getRow() == search_row && ticket.getSeat() == search_column) {
                                search_index = n;
                                break;
                            }
                        }
                        if (search_index != -1) {
                            tickets[search_index].print_tickets();
                        }

                    } else {
                        System.out.println("\nThis seat is not booked");
                    }
                }
                break;
            }catch (InputMismatchException e){
                System.out.println("\nEnter a valid input for seat number!\n");
                input.next();
            }
        }
    }

}
