package thirdtry;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

/**
 * liest CSV File ab
 */
public class CSVFileReader implements AutoCloseable, Iterable<String[]> {

    /**
     * next line
     */
    String next;
    /**
     * Reader for CSV File
     */
    BufferedReader reader;

    /**
     * CSVReader -> Input
     */
    boolean skipinitialwhite;
    char delimiter;
    char doublequote;

    /**
     * Constructor -> Default
     *
     * @param path
     * @param skipinitialwhite
     * @param delimiter
     * @param doublequote
     * @throws FileNotFoundException
     */
    public CSVFileReader(String path, boolean skipinitialwhite, char delimiter, char doublequote) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(path));
        this.skipinitialwhite = skipinitialwhite;
        this.doublequote = doublequote;
        this.delimiter = delimiter;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }

    @Override
    public Iterator<String[]> iterator() {
        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                try {
                    return reader.ready();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String[] next() {
                return CSVFileReader.this.next();
            }
        };
    }

    /**
     * liest next line in CSV File
     * @return Output of split()
     */
    public String[] next() {
        if (hasNext()) {
            CSVReader csv = new CSVReader(skipinitialwhite, delimiter, doublequote);
            return csv.split(next);
        }
        return null;
    }

    /**
     * überprüft, ob es next line hat
     * @return boolean
     */
    public boolean hasNext() {
        try {
            next = reader.readLine();
        } catch (IOException e) {
            return false;
        }
        return next != null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] line : this) {
            stringBuilder.append(Arrays.toString(line));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
