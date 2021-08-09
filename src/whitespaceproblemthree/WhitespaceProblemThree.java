package whitespaceproblemthree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.AccessMode.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * Main class for "Whitespace Technical Assessment" 
 *  Problem 3: Merchant's Guide to the Galaxy
 *  created 7 August 2021
 *
 * @author Len Lutz
 */
public class WhitespaceProblemThree {
    public static Map<String, String> wordNumMap = new HashMap<>();
    public static Map<String, Double> productValueMap = new HashMap<>();

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
     */
    public static void main(String[] args) {
        String filename = "TradeNotes.txt";
        Path filePath;
        ArrayList<String> notes = new ArrayList<>();

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
        if (!getNotes(filePath, notes)) {
            return;
        }
        
        // loop through notes and decide what to do for each one
        notes.forEach(note -> {
            // if note is asking a question
            if(note.charAt(note.length() -1) == '?') {
                answerQuestion(note);
            }
            // check if assigning words to roman numerals
            // or assigning products and values
            else {
                String tokens[] = note.split(" ");
                String romanNumerals = "IVXLCDM"; // list of VALID roman numerals

                // check if assigning words to roman numerals
                if((tokens.length == 3) 
                        && ((tokens[1].compareToIgnoreCase("is") == 0) ||
                            (tokens[1].compareTo("=") == 0))
                        && (romanNumerals.contains(tokens[2]))) {
                    wordNumMap.put(tokens[0], tokens[2]);
                }
                // must be assigning product names and values
                else {
                    storeValues(tokens);
                }
            }
        });
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
    private static boolean getNotes(Path filePath, ArrayList<String> notes) {
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

    /** 
     * Method: answerQuestion - parses line to determine question and
     *         either answers the question or prints out error message
     * 
     * @param note - string containing line to be parsed
     */
    private static void answerQuestion(String note) {
        // if there is not a space before the question mark, add one for
        // consistency when tokenizing string
        if (note.charAt(note.length() -2) != ' ') {
                StringBuilder sb = new StringBuilder(note);
                sb.insert(note.length() -1, ' ');
                note = sb.toString();
        }
        String tokens[] = note.split(" ");
        if (note.toLowerCase().startsWith("how much is")) {
            String romanNumber = "";
            for (int loop = 3 ; loop < (tokens.length - 1); loop++) {
                // verify that the token is a valid key in wordNumMap
                // if not valid, provide error and return
                if (!wordNumMap.containsKey(tokens[loop])) {
                    System.out.println("\rThe question \"" + note + 
                            "\" contains unknown word \"" +
                            tokens[loop] +"\"");
                    return;
                }

                // build Roman Number from tokens
                romanNumber += wordNumMap.get(tokens[loop]);
                // print tokens as part of reply
                System.out.print(tokens[loop] + " ");
            }
            // print answer 
            System.out.println("(" + romanNumber + ") is " 
                    + RomanToArabic.convert(romanNumber));
        } else if (note.toLowerCase().startsWith("how many credits is")) {
            String romanNumber = "";
            // verify that the token is a valid key in wordNumMap
            // if not valid, provide error and return
            for (int loop = 4 ; loop < (tokens.length - 2); loop++) {
                if (!wordNumMap.containsKey(tokens[loop])) {
                    System.out.println("\rQuestion \"" + note + 
                            "\"\n\tcontains unknown word \"" +
                            tokens[loop] +"\"");
                    return;
                }

                // build Roman Number from tokens
                romanNumber += wordNumMap.get(tokens[loop]);
                // print tokens as part of reply
                System.out.print(tokens[loop] + " ");
            }
            double total = (RomanToArabic.convert(romanNumber) * 1.0) * 
                    productValueMap.get(tokens[tokens.length -2]);
            // print answer
            System.out.println(tokens[tokens.length -2] + " is " + total + " Credits");
            
        } else {
            // question invalid or not formatted correctly
            System.out.println("I do not understand the question \"" + note + "\"");
        }
    }

    /**
     * Method: storeValues - parse line to get name and value of product
     * 
     * @param tokens - pre-tokenized note to parse
     */
    private static void storeValues(String[] tokens) {
        // verify this line is defining product and value
        if (tokens[tokens.length -1].equalsIgnoreCase("Credits")) {
            String romanNumber = "";
            for(int loop = 0; loop < (tokens.length - 4); ++loop) {
                // build Roman Number from tokens
                romanNumber += wordNumMap.get(tokens[loop]);
            }
            
            // calculate cost of single item (make sure there aren't anything
            // other than numbers in the string being parsed
            double costOfOne = Double.parseDouble(tokens[tokens.length -2].replaceAll("[^0-9]", "")) /
                    (RomanToArabic.convert(romanNumber) * 1.0);
            // add product name and value to map
            productValueMap.put(tokens[tokens.length -4], costOfOne);
        }
        else {
            // something went wrong
            System.out.println("Unable to parse note.");
        }
    }
}
