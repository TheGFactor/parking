package me.sgrochowski.garage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 *  File (CSV) Reader for Unit 1 Project. Created by Sam Grochowski- 9/27/2018.
 */

public class FileInput {

    private BufferedReader input;
    private String inputFile;
    private int ticketNumber = 0;

    /**
     * Ensures that a proper file in input and prepares for when the files will need to be utilized.
     *
     * @param fileName
     */
    public FileInput(String fileName) {
        this.inputFile = fileName;
        try { //Attempt to read file with the input file name
            input = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException c) { //In case it could not be read as above
            System.out.println("Error opening file- " + fileName + " could not be found/opened./n" + c);
        }
    }

    /**
     * readFile passes
     *
     * @return
     */
    public String readFile() {
        try {
            String line = input.readLine();
            return line;
        } catch (Exception e) {
            System.out.println("File Error: " + e + ".");
            return null;
        }
    }

    /**
     * This safely closes the file, ending the thread.
     */
    public void closeFile() {
        if (input != null) {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

