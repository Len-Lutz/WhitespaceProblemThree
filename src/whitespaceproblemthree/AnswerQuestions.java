package whitespaceproblemthree;

import static whitespaceproblemthree.ParseNotes.productValueMap;
import static whitespaceproblemthree.ParseNotes.wordNumMap;

/**
 * Class: AnswerQuestions - Abstract Class that contains the routine for 
 *  answering a question passed in as a string
 * 
 * @author Len Lutz
 */
public abstract class AnswerQuestions {
    /** 
     * Method: answerQuestion - parses line to determine type of question and
     *         either answers the question or prints out an error message
     * 
     * @param note - string containing line to be parsed
     */
    public static void AnswerQuestions(String note) {
        // if there is not a space before the question mark, add one for
        // consistency when tokenizing string
        if (note.charAt(note.length() -2) != ' ') {
                StringBuilder sb = new StringBuilder(note);
                sb.insert(note.length() -1, ' ');
                note = sb.toString();
        }
        String tokens[] = note.split(" ");
        
        // check if question is asking for translation of a number
        if (note.toLowerCase().startsWith("how much is")) {
            String romanNumber = "";
            for (int loop = 3 ; loop < (tokens.length - 1); loop++) {
                // verify that the token is a valid key in wordNumMap
                // if not valid, provide error and return
                if (!wordNumMap.containsKey(tokens[loop].toLowerCase())) {
                    // starting the output with "\r" removes any words that
                    // have already been printed from previous loop
                    System.out.println("\rThe question \"" + note + 
                            "\" contains unknown word \"" +
                            tokens[loop] +"\"");
                    return;
                }

                // build Roman Number from tokens
                romanNumber += wordNumMap.get(tokens[loop].toLowerCase());
                
                // print tokens (word indicating Roman Numeral) as part of reply
                System.out.print(tokens[loop] + " ");
            }
            // print the Roman Numeral that we built with the answer 
            System.out.println("(" + romanNumber + ") is " 
                    + RomanToArabic.convert(romanNumber));
        }
        // check if question is asking for value of specified quantity of product
        else if (note.toLowerCase().startsWith("how many credits is")) {
            String romanNumber = "";
            // verify that the token is a valid key in wordNumMap
            // if not valid, provide error and return
            for (int loop = 4 ; loop < (tokens.length - 2); loop++) {
                // verify that each word is a valid indicator of a Roman Numeral
                // if not, show error and continue to next note
                if (!wordNumMap.containsKey(tokens[loop].toLowerCase())) {
                    System.out.println("\rQuestion \"" + note + 
                            "\"\n\tcontains unknown word \"" +
                            tokens[loop] +"\"");
                    return;
                }

                // build Roman Number from tokens
                romanNumber += wordNumMap.get(tokens[loop].toLowerCase());

                // print tokens as part of reply
                System.out.print(tokens[loop] + " ");
            }
            // calculate value of spcified quantity of product
            double total = (RomanToArabic.convert(romanNumber) * 1.0) * 
                    productValueMap.get(tokens[tokens.length -2].toLowerCase());
            // print answer
            System.out.println(tokens[tokens.length -2] + " is " + total + " Credits");
            
        } else {
            // question invalid or not formatted correctly
            System.out.println("I do not understand the question \"" + note + "\"");
        }
    }
}
