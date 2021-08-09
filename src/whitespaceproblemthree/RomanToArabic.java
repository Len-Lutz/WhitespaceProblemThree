package whitespaceproblemthree;

/**
 * Class: RomanToArabic - Converts a Roman Numeral to Decimal Integer (Arabic)
 * 
 * @author Len Lutz  (with lots of help from stackoverflow.com)
 */
public class RomanToArabic {
    /**
     * recursively parse through a string to convert 
     * the Roman Numeral to Decimal
     * 
     * @param s - the string containing the Roman Numeral to be converted
     */
    private static int convertRec(String s) {
        if (s.isEmpty()) return 0;  // ends recursion
        if      (s.startsWith("M"))  return 1000 + convertRec(s.substring(1));
        else if (s.startsWith("CM")) return 900  + convertRec(s.substring(2));
        else if (s.startsWith("D"))  return 500  + convertRec(s.substring(1));
        else if (s.startsWith("CD")) return 400  + convertRec(s.substring(2));
        else if (s.startsWith("C"))  return 100  + convertRec(s.substring(1));
        else if (s.startsWith("XC")) return 90   + convertRec(s.substring(2));
        else if (s.startsWith("L"))  return 50   + convertRec(s.substring(1));
        else if (s.startsWith("XL")) return 40   + convertRec(s.substring(2));
        else if (s.startsWith("X"))  return 10   + convertRec(s.substring(1));
        else if (s.startsWith("IX")) return 9    + convertRec(s.substring(2));
        else if (s.startsWith("V"))  return 5    + convertRec(s.substring(1));
        else if (s.startsWith("IV")) return 4    + convertRec(s.substring(2));
        else if (s.startsWith("I"))  return 1    + convertRec(s.substring(1));
        
        // should NEVER get here, but we want to cover ALL bases
        throw new IllegalArgumentException("Unexpected roman numerals");
    }

    /**
     * Public method called to process string containing Roman Numeral
     * we use a regex expression to check for a VALID Roman Numeral
     * if the string is valid, we pass the string to convertRec for processing
     *
     * @param s - the string containing the Roman Numeral to be converted
     * 
     * @return if string is empty or null, return 0
     *         if string does not contain a valid Roman numeral, return -1
     *         if string is valid, return integer provided by convertRec()
     */
    public static int convert(String s) {
        if (s == null || s.isEmpty()) return 0;
        if(!s.matches("^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"))
            return -1;
        return convertRec(s);
    }
}
