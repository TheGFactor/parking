package me.sgrochowski.garage;

import java.util.Random;
import java.util.Scanner;

/**
 * Garage OUT (Out KIOST/Terminal) Created by Sam Grochowski
 */

public class garageOut {
    private static Scanner in = new Scanner(System.in);
    private static String input = "";
    private static Random rng = new Random();
    private static Tickets ticket = new Tickets();

    public static void main(String[] args){
        mainMenu(); //Starts the program
    }
    /**
     * The checkOut method prompts users to enter their vehicle ID, then passes it on to the ticket check out method. Should the method say the ID is invalid, it will prompt the user for a new one. Otherwise, it will display a confirmation to the user that they are clocked out, a reminder of when they clocked in, the time they clocked out, and the total charge for the session.
     */
    private static void checkOut(){
        Boolean proceed = true;
        System.out.println("Best Value Parking Garage\n=========================\n");
        do {

            System.out.println("Please enter in a ticket number.");
            input = in.nextLine();
            Integer[] data = ticket.checkOut(input, (rng.nextInt(11) + 1));
            if(data[0] == -1){
                System.out.println("Best Value Parking Garage\n=========================\n");
                System.out.println("Ticket not found. Please enter a valid ticket number.");
                proceed = true;
            }
            else{
                System.out.println("Best Value Parking Garage\n=========================\n");
                System.out.println("Reciept for vehicle ID " + data[0] + "\n" + ((12 - data[1]) + data[2]) + " hours parked  " + data[1] + "-" + data[2] + "pm\n$" + data[3] + "\n\n\n\n");
                proceed=false;
            }
        }while(proceed);

        mainMenu();//Returns the user to the main menu
    }
    /**
     * The main menu can be called multiple times in this program to re-show the main menu. The main menu allows the user to choose between two options, error checks to see if what they input was valid, then will call the method with the proper instructions.
     */
    private static void mainMenu(){
        Boolean proceed = true;
        System.out.println("Best Value Parking Garage\n=========================\n");
        do {

            System.out.println("1 - Check Out\n2 - Lost Ticket");
            input = in.nextLine();

            switch(input){
                case "1": checkOut(); proceed = false; break;
                case "2": lostTix(); proceed = false; break;
                default:
                    System.out.println("Best Value Parking Garage\n=========================\n");
                    System.out.println("Invalid Input. Please enter a valid input.");break;
            }
        }while(proceed);
    }

    /**
     * The lostTix method prompts users to enter their vehicle ID, then passes it on to the ticket check out method. Should the method say the ID is invalid, it will prompt the user for a new one. Otherwise, it will display a confirmation to the user that they are clocked out and display the lost ticket fee.
     */
    private static void lostTix(){
        Boolean proceed = true;
        System.out.println("Best Value Parking Garage\n=========================\n");
        do {

            System.out.println("Please enter in a ticket number.");
            input = in.nextLine();
            int data = ticket.lostTix(Integer.parseInt(input));
            if(data == -1){
                System.out.println("Best Value Parking Garage\n=========================\n");
                System.out.println("Ticket not found. Please enter a valid ticket number.");
                proceed = true;
            }
            else{
                System.out.println("Best Value Parking Garage\n=========================\n");
                System.out.println("Lost ticket for vehicle ID " + data + "\n" + "$25 lost ticket fee assessed.\n\n\n\n");
                proceed=false;
            }
        }while(proceed);

        mainMenu();//Returns the user to the main menu
    }
}
