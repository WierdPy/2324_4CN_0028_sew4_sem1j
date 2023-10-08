package firsttry;

import org.junit.Test;
import static org.junit.Assert.*;
import static firsttry.Bezahlung.count;
import static firsttry.Bezahlung.todo;

public class BezahlungTest {


    @Test
    public void leicht() {
        // Test case 1: Check if count method returns 1 for input "eins"
        assertEquals(1, todo("eins "));

        // Test case 2: Check if count method returns 1 for input "eins "
        assertEquals(1, todo("eins "));

        // Test case 3: Check if count method returns 3 for input "ein erster Text"
        assertEquals(3, todo("ein erster Text"));


    }



    @Test
    public void testEmptyString() {
        assertEquals(0, todo(""));
    }

    @Test
    public void testWhitespaceString() {
        assertEquals(0, todo(" "));
        assertEquals(0, todo("  "));
    }

    @Test
    public void testSingleWord() {
        assertEquals(1, todo("eins"));
        assertEquals(1, todo(" eins"));
        assertEquals(1, todo("eins "));
        assertEquals(1, todo(" eins "));
        assertEquals(1, todo(" eins  "));
        assertEquals(1, todo("  eins "));
        assertEquals(1, todo("  eins  "));
    }

    @Test
    public void testSingleWordWithPunctuation() {
        assertEquals(1, todo("eins:"));
        assertEquals(1, todo(":eins"));
        assertEquals(1, todo(":eins:"));
        assertEquals(1, todo(" eins  "));
        assertEquals(1, todo(" eins : "));
        assertEquals(1, todo(": eins :"));
        assertEquals(3, todo("ein erster Text"));
        assertEquals(3, todo(" ein  erster   Text      "));
        assertEquals(3, todo("ein:erster.Text"));
    }

    @Test
    public void testSingleCharacter() {
        assertEquals(1, todo("a"));
        assertEquals(1, todo(" a"));
        assertEquals(1, todo("a "));
        assertEquals(1, todo(" a "));
    }

    @Test
    public void testWithHtmlTags() {
        assertEquals(1, todo(" eins  <html> "));

        assertEquals(1, todo(" eins  < html> "));
        assertEquals(1, todo(" eins  <html > "));
        assertEquals(1, todo(" eins  < html > "));
        assertEquals(4, todo(" eins <html> zwei<html>drei <html> vier"));
        assertEquals(2, todo(" eins <html> zwei "));
        assertEquals(2, todo(" eins <html>zwei "));
        assertEquals(2, todo(" eins<html> zwei "));
        assertEquals(2, todo(" eins<html>zwei "));
        assertEquals(2, todo(" eins<img alt=\"xxx\" > zwei"));
        assertEquals(2, todo(" eins<img alt=\"xxx yyy\" > zwei"));


    }

    @Test
    public void testWithQuotedText() {
        assertEquals(2, todo(" eins \"zwei\" "));
        assertEquals(2, todo(" eins\"zwei\" "));
        assertEquals(2, todo(" eins \"zwei\""));
        assertEquals(3, todo(" eins \"zwei\"drei"));
        assertEquals(3, todo(" eins \"zwei\" drei"));
    }

    @Test
    public void testTrickyHtml() {
        //assertEquals(1, todo(" eins<html"));

        //assertEquals(2, todo(" eins<img alt=\"<bild>\" > zwei"));

        //assertEquals(2, todo(" eins<img alt=\"bild>\" > zwei"));


        //assertEquals(2, todo(" eins<img alt=\"<bild>\" keinwort> zwei"));

        //assertEquals(2, todo(" eins<img alt=\"<bild>\" src=\"bild.png\" >zwei"));

        assertEquals(2, todo(" eins<img alt=\"<bild\" keinwort>zwei"));

        assertEquals(1, todo(" eins<img alt=\"<bild\" keinwort"));
        assertEquals(2, todo(" eins<img alt=\"<bild\" keinwort> zwei"));
        assertEquals(1, todo(" eins<img alt=\"<bild keinwort> keinwort"));

        assertEquals(2, todo(" eins<img alt=\"<bild keinwort keinwort\">zwei"));
        assertEquals(2, todo(" eins<img alt=\"<bild keinwort< keinwort\">zwei"));




    }


    @Test
    public void testEscapingCharacters() {
        assertEquals(2, todo(" eins<img alt=\"<bild \\\" keinwort> keinwort\" keinwort>zwei"));
        assertEquals(2, todo(" eins<img alt=\"<bild \\\" keinwort<keinwort\" keinwort>zwei"));
        assertEquals(2, todo(" eins<img alt=\"<bild \\\" keinwort keinwort\" keinwort>zwei"));
        assertEquals(4, todo(" \\\"null\\\" eins<img alt=\"<bild \\\" keinwort keinwort\" keinwort>zwei \"drei\""));
    }






}