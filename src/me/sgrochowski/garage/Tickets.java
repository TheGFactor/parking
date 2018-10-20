package me.sgrochowski.garage;

import java.util.ArrayList;

/**
 * Tickets class, created by Sam Grochowski
 */
public class Tickets {
    private static int ticketNumber;
    private static int inTime;
    private static int outTime;


    public Tickets(){}

    /**
     * The new ticket class creates a ticket and logs it onto the system. It looks at the data header in the csv file, determines what the new ticket number will be, then creates a new entry into the data with the new ticket ID and the time that was passed in.
     * @param time The (currently randomly generated) time that the ticket was "created"
     * @return Returns the ticket that was just generated to confirm for the user.
     */
    public int newTicket(int time){
        inTime = time;
        FileInput input = new FileInput("tickets.csv");
        String currentLine;
        ArrayList<String> currents = new ArrayList<>();
        String[] fields = input.readFile().split(",");
        int newTix = Integer.parseInt(fields[0]) + 1;
        while((currentLine = input.readFile()) != null){
            currents.add(currentLine + "\r");
        }
        input.closeFile();
        FileOutput output = new FileOutput("tickets.csv");
        output.fileWrite(newTix + "," + fields[1] + "," + fields[2] + "," + fields[3] + "\r");
        for(String s:currents){
            output.fileWrite(s);
        }
        output.fileWrite(newTix + "," + time);
        output.fileClose();
        return newTix;
    }

    /**
     * The checkOut method takes in a ticket ID, scans the data sheet, and tries to find that ID. If there is not an ID match, it returns -1, signifying that the user entered an invalid ticket. Otherwise, it removes the ticket from the data list, calculates how long the car was in there, and updates all header information so it can be accessed later.
     * @param tixNum The ticket input that the user placed in
     * @param outTime A randomly generated time signifying when the user left the garage.
     * @return Returns an array of numbers including ticket number (-1 if invalid), in time, out time, and the total cost of the visit.
     */
    public Integer[] checkOut(String tixNum, int outTime){
        Integer[] data = new Integer[] {-1, 0, outTime, 0};

        FileInput input = new FileInput("tickets.csv");
        String[] header = input.readFile().split(",");
        String[] eval = new String[]{""};
        String currentLine = null;
        ArrayList<String> currents = new ArrayList<>();
        Boolean found = false;
        int numTix = Integer.parseInt(header[1]);

        while((currentLine = input.readFile()) != null){
            eval = currentLine.split(",");

            if(eval[0].equals(tixNum)){
                data[0] = Integer.parseInt(eval[0]);
                data[1] = Integer.parseInt(eval[1]);
                int totalTime = 12 - Integer.parseInt(eval[1]) + outTime;

                if((totalTime) <= 3){
                    data[3] = 5;
                }
                else if((5 + (totalTime - 3)) > 15){
                    data[3] = 15;
                }
                else{
                    data[3] = 5 + (totalTime - 3);
                }
                numTix = Integer.parseInt(header[1]) + 1;
            }

            else{
                currents.add(eval[0] + "," + eval[1] + "\r");
            }
        }

        input.closeFile();
        FileOutput output = new FileOutput("tickets.csv");

        int newTotal = Integer.parseInt(header[2]) + data[3];

        output.fileWrite(header[0] + "," + numTix + "," + newTotal + "," + header[3] + "\r");
        for(String s:currents){
            output.fileWrite(s);
        }
        output.fileClose();
        return data;
    }

    /**
     * lostTix is used when a user lost their ticket. They enter their vehicle ID. This program then reads through the data file. If it can not find the user supplied ID, it will return a -1 value to the driver so it can prompt for another input. if it is found, it is removed from the datasheet and the fee is assessed
      * @param tixNum The user input ticket number
     * @return Returns a single value (-1 if invalid input, the ticket number if it is valid)
     */
    public int lostTix(int tixNum){
        int data = -1;
        FileInput input = new FileInput("tickets.csv");
        String[] header = input.readFile().split(",");
        String[] eval = new String[]{""};
        String currentLine = null;
        ArrayList<String> currents = new ArrayList<>();
        int numTix = Integer.parseInt(header[3]);

        while((currentLine = input.readFile()) != null){
            eval = currentLine.split(",");

            if(Integer.parseInt(eval[0]) == tixNum){
                data = tixNum;
                numTix++;
            }

            else{
                currents.add("," + eval[1] + "\r");
            }
        }

        input.closeFile();
        FileOutput output = new FileOutput("tickets.csv");

        output.fileWrite(header[0] + "," + header[1] + "," + header[2] + "," + numTix + "\r");
        for(String s:currents){
            output.fileWrite(s);
        }
        output.fileClose();
        return data;
    }

    /**
     * The endOfDay method passes all header information back to the driver and then clears the file, resetting it for the next business day
     * @return The header information, containing the number of tickets sold, how many were lost and clocked out, and the total income from those tickets.
     */

    public String[] endOfDay(){
        FileInput input = new FileInput("tickets.csv");
        String[] fields = input.readFile().split(",");
        input.closeFile();

        FileOutput output = new FileOutput("tickets.csv");
        output.fileWrite("0,0,0,0\r");
        output.fileClose();

        return fields;
    }



}
