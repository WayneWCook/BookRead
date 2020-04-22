/* BookRead.java : Defines the entry point for the console application.
 * Author: Wayne Cook
 * This shows how to read from and write to a file.
 * Test Class FileIO interface.
 *
 * Creation date: 6 March 2018 - Initial design implemented.
 * Modified: 7 March 2018 - added check for already requested seat.
 * Modified: 7 March 2018 - allowed user to choose the number of rows.
 *                          Corrected row/index mismatch on seat selection.
 * Modified: 8 March 2018 - Added matrix multiplication
 * Modified: 15 November 2018 - Update for CSC160 (Java) class. Divided into appropriate Classes.
 * Modified: 21 April 2020 for current Java class to add appending to file.
 */

import java.util.ArrayList;

public class BookRead {
    public static void main(String[] args) {
        FileIO fileIO = new FileIO("PhoneBook.txt");
        KBIO kbio = new KBIO();
        String lastName, firstName, phoneNumber;
        // Dispaly the file for the first time
        displayFile(fileIO);
        // Now ask for additional lines.
        boolean moreInput = true;
        do {
            System.out.println("Add addional lines to the file. End with empty line to last name.");
            lastName = kbio.requestInput("Enter the person's last name");
            if (lastName.length() > 0) {
                firstName = kbio.requestInput("Enter the person's first name");
                do {
                    phoneNumber = kbio.requestInput("Enter the person's phone number (must be ten digits)");
                } while (phoneNumber.length() != 10);
                // Now write the line.
                fileIO.appendLine(lastName + "," + firstName + "," + phoneNumber);
            }
            else moreInput = false;
        } while (moreInput);
        // Dispaly the file for again
        displayFile(fileIO);
    }

    // Read and parse the file.
    static void displayFile(FileIO fileIO) {
        ArrayList<String> output = fileIO.readFile(), lineSplit = new ArrayList<String>();
        System.out.println("The file contains:");
        for (int i=0; i < output.size(); i++) {
            System.out.println(output.get(i));
        }
        System.out.println("The file reformatted is:");
        for (int i=0; i < output.size(); i++) {
            fileIO.splitLine(lineSplit, output.get(i), ',');
            String temp = lineSplit.get(2);
            if (temp.length() == 10) {
                System.out.println(lineSplit.get(1) + " " + lineSplit.get(0) + " (" + temp.substring(0,3) +
                        ") " + temp.substring(3,6) + "-" + temp.substring(6,10));
            } else {
                System.out.println("You have an invalid number for: " + lineSplit.get(1) + " " + lineSplit.get(0));
            }
        }

    }

}