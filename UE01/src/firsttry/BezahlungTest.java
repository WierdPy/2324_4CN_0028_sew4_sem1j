package firsttry;

import org.junit.Test;
import static org.junit.Assert.*;
import static firsttry.Bezahlung.count;

public class BezahlungTest {

    @Test
    public void testCount() {
        // Test case 1: Check if count method returns 1 for input "eins"
        assertEquals(1, count("eins "));

        // Test case 2: Check if count method returns 1 for input "eins "
        assertEquals(1, count("eins "));

        // Test case 3: Check if count method returns 3 for input "ein erster Text"
        assertEquals(3, count("ein erster Text"));

        // Test case 4: Ignore HTML tags
        assertEquals(2, count(" eins <html>"));// ==> 1

    }
}