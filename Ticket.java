import  java.io.*;
public class Ticket {
    private char row;
    private int seat;
    private int price;
    private Person person;

    //Ticket constructer
    public Ticket(char row,int seat, int price,Person person){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person=person;
    }

    public char getRow(){
            return row;
        }

    public void setRow(char row){
        this.row=row;
    }

    public int getSeat(){
        return seat;
    }
    public void setSeat(int seat){
        this.seat=seat;
    }

    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price=price;
    }

    public Person getPerson(){
        return person;
    }
    public void setPerson(Person person){
        this.person=person;
    }

    public void print_tickets(){
        System.out.println("Row   : "+ row);
        System.out.println("Seat  : "+ seat);
        System.out.println("Price : £"+ price);
        person.print_person_info();

    }

    //create a txt file to each booked ticket
    //get text file name from seat row and number and write ticket information
    public void save() {
        String txt_filename = row + String.valueOf(seat) + ".txt";
        File file = new File(txt_filename);
        try {
            boolean file_created = file.createNewFile();
            if(file_created) {
                FileWriter txt_file = new FileWriter(txt_filename);
                txt_file.write("-Ticket Information-\n\n");
                txt_file.write("Seat    : "+row + seat + "\n");
                txt_file.write("Price   : £"+ price + "\n");
                txt_file.write("\nPersonal Information :-\n");
                txt_file.write("Name    : "+person.getName()+ "\n");
                txt_file.write("Surname : "+person.getSurname()+"\n");
                txt_file.write("Email   : "+person.getEmail()+"\n");
                txt_file.write("\nSafe Flight !");
                txt_file.close();
            }
        } catch (IOException e) {
            System.err.println("Error occurred!");
        }
    }
}

