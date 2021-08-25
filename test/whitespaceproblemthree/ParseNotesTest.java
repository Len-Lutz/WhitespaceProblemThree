package whitespaceproblemthree;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
//import org.junit.jupiter.api.DisplayName;
import static whitespaceproblemthree.ParseNotes.productValueMap;
import static whitespaceproblemthree.ParseNotes.wordNumMap;


/**
 *
 * @author len
 */
public class ParseNotesTest {
    public static ArrayList<String> notes = new ArrayList<>();

    @Before
    public void setupEach() {
        notes.clear();
        notes.add("glob is I");
        notes.add("prok is V");
        notes.add("pish is X");
        notes.add("tegj is L");
        notes.add("frak is C");
        notes.add("tink = D");
        notes.add("lots = M");
        notes.add("glob glob Silver is 34.5 Credits");
        notes.add("glob prok Gold is 57800 Credits");
        notes.add("pish pish Iron = 3910 Credits");
        notes.add("prok glob Dirt is 105,000 Credits");
    }

    @Test
//    @DisplayName("Test filling maps")
    public void testFillMaps() {
        ParseNotes pn = new ParseNotes(notes);

        assertFalse(wordNumMap.isEmpty());
        assertEquals(7, wordNumMap.size());
        assertEquals(0, wordNumMap.get("glob").indexOf('I'));

        assertFalse(productValueMap.isEmpty());
        assertEquals(4, productValueMap.size());
        Double result = productValueMap.get("iron");
        assertTrue(result == 195.5);
    }

    @Test
//    @DisplayName("Test Answer Questions")
    public void testAnswerQuestions() {
        notes.add("how much is pish tegj glob glob ?");
        notes.add("how much is pish tegj glib glob ?");
        notes.add("how many Credits is prok Iron ?");
        notes.add("Who am I ?");

        // This test should ALWAYS pass and print to the console the following lines:
        //   "pish tegj glob glob (XLII) is 42"
        //   "The question "how much is pish tegj glib glob ?" contains unknown word "glib""
        //   "prok Iron is 977.5 Credits"
        //   "I do not understand the question "who am I ?""

        ParseNotes pn = new ParseNotes(notes);
    }
}
