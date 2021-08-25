package whitespaceproblemthree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import static whitespaceproblemthree.WhitespaceProblemThree.productValueMap;
//import static whitespaceproblemthree.WhitespaceProblemThree.wordNumMap;

/**
 * Class: ParseNotes - Reads each note from passed-in array of Strings
 *          and processes them appropriately
 * 
 *          Extends AnswerQuestions Class and uses that code to process
 *          the "Notes" that are determined to be questions
 * 
 * @author Len Lutz
 */
public class ParseNotes extends AnswerQuestions {
    public static Map<String, String> wordNumMap = new HashMap<>();
    public static Map<String, Double> productValueMap = new HashMap<>();

    ParseNotes(ArrayList<String> notes) {
        // loop through notes and decide what to do for each one
        notes.forEach(note -> {
            // if note is asking a question
            if(note.charAt(note.length() -1) == '?') {
                AnswerQuestions(note);
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
                        && (romanNumerals.contains(tokens[2].toUpperCase()))) {
                    // check for VALID Roman Numeral
                    if (tokens[2].length() > 1){
                        System.out.println("The line \"" + note + 
                            "\" contains invalid Roman Numeral \"" +
                            tokens[2] +"\"\n\t" +
                            "Must contain only single upper case I, V, X, L , C, D, or M" );
                    }
                    else {
                        wordNumMap.put(tokens[0].toLowerCase(), tokens[2]);
                    }
                }
                // must be assigning product names and values
                else {
                    storeValues(tokens);
                }
            }
        });
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
                romanNumber += wordNumMap.get(tokens[loop].toLowerCase());
            }
            
            // calculate cost of single item (make sure there aren't anything
            // other than numbers or a decinmal point in the string being parsed)
            double costOfOne = Double.parseDouble(tokens[tokens.length -2].replaceAll("[^0-9.]", "")) /
                    (RomanToArabic.convert(romanNumber) * 1.0);
            // add product name and value to map
            productValueMap.put(tokens[tokens.length -4].toLowerCase(), costOfOne);
        }
        else {
            // something went wrong
            System.out.println("Error: Unable to parse note.");
        }
    }
}
