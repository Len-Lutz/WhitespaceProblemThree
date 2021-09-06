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
        // cannot have more than three of the same character in a row
        String s = "IIII";
        int expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // cannot have more than three of the same character in a row
        s = "XXXX";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // cannot have more than three of the same character in a row
        s = "CCCC";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // cannot have more than three of the same character in a row
        s = "MMMM";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }
    
    @Test()
//    @DisplayName("More than one smaller value cannot be before larger value should return -1")
    public void moreThanOneSmallerValueCannotBeBeforeLargerValueShouldReturnMinusOne() {
        // cannot have more than one smaller value character preceeding larger value character
        String s = "XXL";
        int expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // cannot have more than one smaller value character preceeding larger value character
        s = "IIV";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // cannot have more than one smaller value character preceeding larger value character
        s = "IIX";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }

    @Test()
//    @DisplayName("D, L, and V can never be repeated")
    public void DLVCanNeverBeRepeated() {
        // 'D' can never be repeated
        String s = "DD";
        int expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'L' can never be repeated
        s = "LL";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'V' can never be repeated
        s = "VV";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }

    @Test()
//    @DisplayName("V, L, and D can never be subtracted")
    public void VDLCanNeverBeSubtracted() {
        // 'V' can never be subtracted
        String s = "VX";
        int expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'L' can never be subtracted
        s = "LC";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'D' can never be subtracted
        s = "DM";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }

    @Test()
//    @DisplayName("I can be subtracted from V and X only")
    public void ICanBeSubtractedFromVandXOnly() {
        // 'I' can be subtracted from 'V'
        String s = "IV";
        int expResult = 4;
        System.out.print("Sent valid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'I' can be subtracted from 'X'
        s = "IX";
        expResult = 9;
        System.out.print("Sent valid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'I' can never be subtracted from 'L'
        s = "IL";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'I' can never be subtracted from 'C'
        s = "IC";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'I' can never be subtracted from 'D'
        s = "ID";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'I' can never be subtracted from 'M'
        s = "IM";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }

    @Test()
//    @DisplayName("X can be subtracted from L and C only")
    public void XCanBeSubtractedFromLandCOnly() {
        // 'X' can be subtracted from 'L'
        String s = "XL";
        int expResult = 40;
        System.out.print("Sent valid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'X' can be subtracted from 'C'
        s = "XC";
        expResult = 90;
        System.out.print("Sent valid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'X' can never be subtracted from 'D'
        s = "XD";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'X' can never be subtracted from 'M'
        s = "XM";
        expResult = -1;
        System.out.print("Sent invalid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }

    @Test()
//    @DisplayName("C can be subtracted from D and M only")
    public void CCanBeSubtractedFromDandMOnly() {
        // 'C' can be subtracted from 'D'
        String s = "CD";
        int expResult = 400;
        System.out.print("Sent valid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);

        // 'C' can be subtracted from 'M'
        s = "CM";
        expResult = 900;
        System.out.print("Sent valid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }

    @Test()
//    @DisplayName("Valid string (MMMCMXCIX) should return 3999")
    public void validStringShouldReturn3999() {
        String s = "MMMCMXCIX";
        int expResult = 3999;
        System.out.print("Sent valid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }
    
    @Test()
//    @DisplayName("Valid string (XLII) should return 42")
    public void validStringShouldReturn42() {
        String s = "XLII";
        int expResult = 42;
        System.out.print("Sent valid string \"" + s + 
                "\". Expecting back " + expResult + ", ");
        int result = RomanToArabic.convert(s);
        System.out.println("Got back: " + result);
        assertEquals(expResult, result);
    }
}
