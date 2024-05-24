<<<<<<< HEAD
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


}

