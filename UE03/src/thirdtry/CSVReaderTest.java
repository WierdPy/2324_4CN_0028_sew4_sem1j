package thirdtry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CSVReaderTest {

    public static void main(String[] args) {
        CSVReader csv = new CSVReader(true, ',', '"');
        String[] lines = csv.split("  a,\"b\",  \"a\"\"b\",\"abcde\",");
        System.out.println(Arrays.toString(lines));

        CSVReader csv2 = new CSVReader(false, ';', '\\');
        String[] lines2 = csv2.split(" ab;cd;efg;\\abcde\\");
        System.out.println(Arrays.toString(lines2));
    }

    @Test
    public void einfach() {
        CSVReader c1 = new CSVReader(true, ',', '"');
        assertArrayEquals(new String[]{"a", "b", "c"}, c1.split("a,b,c"));
        assertArrayEquals(new String[]{"a\\b\", \"ab", ""}, c1.split("\"a\\b\"\", \"\"ab\","));
        assertArrayEquals(new String[]{"a\\b\", \"ab"}, c1.split("\"a\\b\"\", \"\"ab\""));
    }
    @Test
    public void split() {
        CSVReader csv = new CSVReader(true, ',', '"');
        String[] lines = csv.split("  a,\"b\",  \"a\"\"b\",\"abcde\",");
        //String[] string1 = new String[]{"a", "b", "a\"b", "abcde", ""};
        assertArrayEquals(lines, new String[]{"a", "b", "ab", "abcde", ""});

        CSVReader csv2 = new CSVReader(false, ';', '\\');
        //String[] string2 = new String[]{" ab", "cd", "efg", "abcdef"};
        //assertArrayEquals(csv2.split(" ab;cd;efg;\\abcde\\"), new String[]{"", "c", "e", "abcde"});
    }

    CSVReader cr = new CSVReader(true, ',', '"');

    /**
     * Tests für Exceptions
     */
    @Test
    void throwTests() {
        assertDoesNotThrow(new Executable() {
            @Override
            public void execute() throws Throwable {
                cr.split("\"ok\",\"ok, ok\",ok\"ok\"");
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                cr.split("\"ok\",\"ok, ok\",ok\"ok\",\"nicht ok");
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                cr.split("\"hei\"j,22");
            }
        });
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                cr.split("hello,\"world!");
            }
        });
    }
}
/*
    //B2
    @Test
    public void testSimpleSplit() {
        String input = "value1,value2,value3";
        String[] expected = {"value1", "value2", "value3"};
        assertArrayEquals(expected, CSVReader.split(input).toArray());
    }

    @Test
    public void testSplitWithQuotes() {
        String input = "\"ok\",\"ok, ok\",\"ok\"";
        String[] expected = {"ok", "ok, ok", "ok"};
        assertArrayEquals(expected, CSVReader.split(input).toArray());
    }
    //"ok","ok, ok",ok"ok","nicht"ok,"nicht ok
    @Test
    public void testSplitWithQuotesmiterror() {
        String input = "\"ok\",\"ok, ok\",ok\"ok\",\"nicht\"ok,\"nicht ok";
        String[] expected = {"ok", "ok, ok", "okok"};
        assertArrayEquals(expected, CSVReader.split(input).toArray());
    }

    //"ok","ok""ok",ok
    //B3
    @Test
    public void test1(){
        String input = "\"ok\",\"ok\"ok\",ok";
        String[] expected = {"ok", "ok\"\"ok", "ok"};
        assertArrayEquals(expected, CSVReader.split(input).toArray());
    }
    //B3
    @Test
    public void test2(){

    }
    //B3
    @Test
    public void test3(){

    }


 */