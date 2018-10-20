package me.sgrochowski.garage;

import java.util.Random;
import java.util.Scanner;

/**
 *  Garage IN (IN Terminal/Kiosk) Created by Sam Grochowski
 */

public class garageIn {
    private static Scanner in = new Scanner(System.in);
    private static String input = "";
    private static Random rng = new Random();
    private static Tickets ticket = new Tickets();

    /**
     * The Main function in this program does nothing except to call the main menu.
     * @param args
     */
    public static void main(String[] args){
        mainMenu(); //Starts the program
    }

    /**
     * The main menu can be called multiple times in this program to re-show the main menu. The main menu allows the user to choose between two options, error checks to see if what they input was valid, then will call the method with the proper instructions.
     */
    private static void mainMenu(){
        Boolean proceed = true; //Looping control variable- will allow the program to loop through if there is an invalid entry
        System.out.println("Best Value Parking Garage\n=========================\n");
        do {

            System.out.println("1 - Check In\n3 - Close Garage");
            input = in.nextLine();

            switch(input){
                case "1": checkIn(); proceed = false; break;
                case "3": garageReport(); proceed = false; break;
                default:
                    System.out.println("Best Value Parking Garage\n=========================\n");
                    System.out.println("Invalid Input. Please enter a valid input.");break;
            }
        }while(proceed);
    }

    /**
     * Check-in creates a new randomly generated time and calls the ticket object created at the top of this class to create a new ticket. When the ticket is created, this method prints a confirmation number with what ticket ID was assigned to this user.
     */
    private static void checkIn(){

        int newTicket = ticket.newTicket((rng.nextInt(6) + 7));
        System.out.println("\n\n\nTicket ID " + newTicket + " generated.\n\n\n");
        mainMenu();//Returns the user to the main menu
    }

    /**
     *Prints an end-of-day report. The endOfDay method of the Ticket object is called, it scrubs the header for all info, returns an array of data to the program, and resets the dataset for the next business day. This report just displays that data.
     */
    private static void garageReport(){
        String[] nums = ticket.endOfDay();
        System.out.println("Best Value Parking Garage\n=========================\n");
        System.out.println("End of Day Report" +
                "\n" +
                "$" + nums[2] + " collected from " + nums[1] + " tickets.\n$" + (Integer.parseInt(nums[3]) * 25) + " collected from " + nums[3] + " lost tickets.\n\n$" + (Integer.parseInt(nums[2]) + (Integer.parseInt(nums[3]) * 25)) + " collected overall.");

    }
}
