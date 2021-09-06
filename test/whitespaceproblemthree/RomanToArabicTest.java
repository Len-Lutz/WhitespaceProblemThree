package whitespaceproblemthree;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class: RomanToArabicTest - Test routines for RomanToArabic 
 * 
 * @author Len Lutz
 */
public class RomanToArabicTest {
    
    /**
     * Test of convert method, of class RomanToArabic.
     */
    @Test()
//    @DisplayName("Blank string should return 0")
    public void blankStringShouldReturnZero() {
        System.out.print("Sent empty string. Expecting back: 0, ");
        String s = "";
        int expResult = 0;
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }
    
    @Test()
//    @DisplayName("Four of same character should return -1")
    public void fourOfSameCharacterShouldReturnMinusOne() {
        System.out.print("Sent invalid string \"IIII\". Expecting back -1, ");
        // cannot have more than three of the same character in a row
        String s = "IIII";
        int expResult = -1;
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }
    
    @Test()
//    @DisplayName("More than one smaller value cannot be before larger value should return -1")
    public void moreThanOneSmallerValueCannotBeBeforeLargerValueShouldReturnMinusOne() {
        System.out.print("Sent invalid string \"XXL\". Expecting back -1, ");
        // cannot have more than one smaller value character preceeding larger value character
        String s = "XXL";
        int expResult = -1;
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }
    
    @Test()
//    @DisplayName("Valid string (MMMCMXCIX) should return 3999")
    public void validStringShouldReturn3999() {
        System.out.print("Sent valid string \"MMMCMXCIX\".  Expecting back 3999, ");
        String s = "MMMCMXCIX";
        int expResult = 3999;
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }
    
    @Test()
//    @DisplayName("Valid string (XLII) should return 42")
    public void validStringShouldReturn42() {
        System.out.print("Sent valid string \"XLII\". Expecting back 42, ");
        String s = "XLII";
        int expResult = 42;
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }
}
