package secondtry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Burhan Özbek on 10.10.2023
 * This is a test class for the BestCounter class
 */
public class BestCounterTestMitSwitching {

    /**
     * These are the test cases for the count method.
     * It starts with easy test cases and gets harder.
     */

    BestCounterMitSwitching W = new BestCounterMitSwitching();

    @Test
    public void leicht() {
        // Test case 1: Check if count method returns 1 for input "eins"
        assertEquals(1, W.count("eins "));
        System.out.println("ASIFASIUHfAS");

        // Test case 2: Check if count method returns 1 for input "eins "
        assertEquals(1, W.count("eins "));

        // Test case 3: Check if count method returns 3 for input "ein erster Text"
        assertEquals(3, W.count("ein erster Text"));



    }




    @Test
    public void testEmptyString() {
        assertEquals(0, W.count(""));
    }

    @Test
    public void testWhitespaceString() {
        assertEquals(0, W.count(" "));
        assertEquals(0, W.count("  "));
    }

    @Test
    public void testSingleWord() {
        assertEquals(1, W.count("eins"));
        assertEquals(1, W.count(" eins"));
        assertEquals(1, W.count("eins "));
        assertEquals(1, W.count(" eins "));
        assertEquals(1, W.count(" eins  "));
        assertEquals(1, W.count("  eins "));
        assertEquals(1, W.count("  eins  "));
    }

    @Test
    public void testSingleWordWithPunctuation() {
        assertEquals(1, W.count("eins:"));
        assertEquals(1, W.count(":eins"));
        assertEquals(1, W.count(":eins:"));
        assertEquals(1, W.count(" eins  "));
        assertEquals(1, W.count(" eins : "));
        assertEquals(1, W.count(": eins :"));
        assertEquals(3, W.count("ein erster Text"));
        assertEquals(3, W.count(" ein  erster   Text      "));
        assertEquals(3, W.count("ein:erster.Text"));
    }

    @Test
    public void testSingleCharacter() {
        assertEquals(1, W.count("a"));
        assertEquals(1, W.count(" a"));
        assertEquals(1, W.count("a "));
        assertEquals(1, W.count(" a "));
    }

    @Test
    public void testWithHtmlTags() {
        assertEquals(1, W.count(" eins  <html> "));
        assertEquals(1, W.count(" eins  < html> "));
        assertEquals(1, W.count(" eins  <html > "));
        assertEquals(1, W.count(" eins  < html > "));
        assertEquals(4, W.count(" eins <html> zwei<html>drei <html> vier"));
        assertEquals(2, W.count(" eins <html> zwei "));
        assertEquals(2, W.count(" eins <html>zwei "));
        assertEquals(2, W.count(" eins<html> zwei "));
        assertEquals(2, W.count(" eins<html>zwei "));
        assertEquals(2, W.count(" eins<img alt=\"xxx\" > zwei"));
        assertEquals(2, W.count(" eins<img alt=\"xxx yyy\" > zwei"));


    }

    @Test
    public void testWithQuotedText() {
        assertEquals(2, W.count(" eins \"zwei\" "));
        assertEquals(2, W.count(" eins\"zwei\" "));
        assertEquals(2, W.count(" eins \"zwei\""));
        assertEquals(3, W.count(" eins \"zwei\"drei"));
        assertEquals(3, W.count(" eins \"zwei\" drei"));
    }

    @Test
    public void testTrickyHtml() {
        assertEquals(1, W.count(" eins<html"));

        assertEquals(2, W.count(" eins<img alt=\"<bild>\" > zwei"));

        assertEquals(2, W.count(" eins<img alt=\"bild>\" > zwei"));


        assertEquals(2, W.count(" eins<img alt=\"<bild>\" keinwort> zwei"));

        assertEquals(2, W.count(" eins<img alt=\"<bild>\" src=\"bild.png\" >zwei"));

        assertEquals(2, W.count(" eins<img alt=\"<bild\" keinwort>zwei"));

        assertEquals(1, W.count(" eins<img alt=\"<bild\" keinwort"));
        assertEquals(2, W.count(" eins<img alt=\"<bild\" keinwort> zwei"));
        assertEquals(1, W.count(" eins<img alt=\"<bild keinwort> keinwort"));

        assertEquals(2, W.count(" eins<img alt=\"<bild keinwort keinwort\">zwei"));
        assertEquals(2, W.count(" eins<img alt=\"<bild keinwort< keinwort\">zwei"));




    }


    @Test
    public void testEscapingCharacters() {
        assertEquals(2, W.count(" eins<img alt=\"<bild \\\" keinwort> keinwort\" keinwort>zwei"));
        assertEquals(2, W.count(" eins<img alt=\"<bild \\\" keinwort<keinwort\" keinwort>zwei"));
        assertEquals(2, W.count(" eins<img alt=\"<bild \\\" keinwort keinwort\" keinwort>zwei"));
        assertEquals(4, W.count(" \\\"null\\\" eins<img alt=\"<bild \\\" keinwort keinwort\" keinwort>zwei \"drei\""));
    }
}