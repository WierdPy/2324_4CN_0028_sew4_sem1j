package thirdtry;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CSVFileReaderTest {

    public static String path = "src/thirdtry/test.csv";

    @Test
    public void testDelimiterOthers() {
        try (CSVFileReader csv
                     = new CSVFileReader(path, false, ';', '"')) {

            assertArrayEquals(csv.next(),
                    new String[]{"", "A", "B", "C", "D", "E", "F", "G", "H"});

            csv.close();
            assertNull(csv.next());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   //fehler wegen / delimiter
    @Test
    void testNextAndClose() throws Exception {
        try (CSVFileReader csv = new CSVFileReader(path, true, '/', '"')) {
            assertEquals(Arrays.toString(csv.next()), Arrays.toString(new String[]{"", "A", "B", "C", "D", "E", "F", "G", "H"}));
        }
    }

    /**
     * @throws Exception Musterlösung -> Ausgabe
     */
    @Test
    void testToString() throws Exception {
        try (CSVFileReader csv = new CSVFileReader(path, true, ';', '"')) {
            String[] muster = csv.next();
            String out = "";
            for (String[] line : csv) {
                out = line[0] + ":";
                for (int i = 1; i < line.length; i++) {
                    if (!line[i].equals("")) {
                        out += " nach " + muster[i] + ":" + line[i] + ",";
                    }
                }
                System.out.println(out.substring(0, out.length() - 1));
            }
        }
    }
}