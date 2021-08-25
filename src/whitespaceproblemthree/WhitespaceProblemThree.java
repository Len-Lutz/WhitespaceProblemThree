package whitespaceproblemthree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.AccessMode.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Main class for "Whitespace Technical Assessment" 
 *  Problem 3: Merchant's Guide to the Galaxy
 *  created 7 August 2021
 *
 * @author Len Lutz
 */
public class WhitespaceProblemThree {
    public static ArrayList<String> notes = new ArrayList<>();

    /**
     * method: main - routine that starts the program
     * 
     * @param args - the command line arguments
     *     only 1 (optional) argument is expected - name of the file containing
     *     notes.  Contents and formatting of this information is 
     *     described in the program documentation
     * 
     *     if a file with the provided name is not found, the filename defaults
     *     to "TradeNotes.txt"
     *     if neither file is not found, the program will end
     * 
     *     if the file is found and successfully read, instantiates class
     *     to process notes
     */
    public static void main(String[] args) {
        String filename = "TradeNotes.txt";
        Path filePath;

        try {
            // check if file name has been provided as program arg
            // if not found or not valid, exception is thrown
            filePath = Paths.get(args[0]);
            filePath.getFileSystem().provider().checkAccess(filePath, READ);
        } catch (Exception ex) {
            // exception thrown above, so we try again with the default filename
            try {
                filePath = Paths.get(filename);
                filePath.getFileSystem().provider().checkAccess(filePath, READ);
            } catch (IOException ex1) {
                // default failed, so we provide error message and end program
                System.out.println("Error: File not found or cannot be read.");
                return;
            }
        }

        // if notes cannot be read from file, return (end program)
        if (!getNotes(filePath)) {
            return;
        }
        
        /**
         *  Instantiate class to parse notes
         */
        ParseNotes pn = new ParseNotes(notes);
    }

    /**
     * method: getNotes -  reads in each note from file
     * 
     * @param filePath - fully qualified filename
     * @param notes - ArrayList to store notes in
     * 
     * @return if file was successfully read, return true
     *         if not, return false
     */
    private static boolean getNotes(Path filePath) {
        try {
            // open file, read all lines, create and return list
            File file = new File(filePath.toString());
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()) {
                // remove any extra spaces or "split" might not parse correctly
                // when splitting strings into tokens later
                String tempStr = scan.nextLine().trim().replaceAll("\\s+", " ");
                notes.add(tempStr);
            }
            scan.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("Error: File not found.");
        }
        return false;
    }
}
