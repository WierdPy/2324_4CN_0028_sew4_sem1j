package thirdtry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Burhan Özbek on 10.10.2023
 */
//"ok","ok, ok",ok"ok","nicht"ok,"nicht ok
    //WAS SOLL ICH DARAUS VERTSTEHEN

/**
 * CSV-line and splits it into a String array
 */
public class CSVReader {

    /**
     * whitespaces should be skipped
     */
    boolean skipinitialspace;
    /**
     * splits the data -> character
     */
    char delimiter; //,
    /**
     * field delimiter -> character
     * String fängt mit dem Zeichen an und auf
     */
    char doublequote;

    /**
     * save current Word for List
     */
    String currentWord = "";

    /**
     * adding data sets -> List
     */
    List<String> list = new ArrayList<>();

    /**
     * @param skipinitialspace if Whitespace should be skipped
     * @param delimiter        Trennzeichen zwischen Feldern
     * @param doublequote      Feldbegrenzer
     *                         Default Constructor
     */
    public CSVReader(boolean skipinitialspace, char delimiter, char doublequote) {
        this.skipinitialspace = skipinitialspace;
        this.delimiter = delimiter;
        this.doublequote = doublequote;
    }

    /**
     * Statemachine
     */
    private enum State {
        NOCELL {
            @Override
            CSVReader.State handleChar(char c, CSVReader csv) {
                if (Character.isWhitespace(c) && csv.skipinitialspace) {
                    return NOCELL;
                }
                if (c == csv.doublequote) {
                    return STRINGCELL;
                }
                if (c == csv.delimiter) {
                    csv.list.add(csv.currentWord);
                    //csv.currentWord = "";
                    return NOCELL;
                }
                csv.currentWord += c;
                return CELL;
            }
        },
        CELL {
            @Override
            CSVReader.State handleChar(char c, CSVReader csv) {
                if (c == csv.delimiter) {
                    csv.list.add(csv.currentWord);
                    csv.currentWord = "";
                    return NOCELL;
                }
//                if (c == csv.doublequote) {
//                    return STRINGCELL;
//                }
                csv.currentWord += c;
                return CELL;
            }
        },
        STRINGCELL {
            @Override
            CSVReader.State handleChar(char c, CSVReader csv) {
                if (c == csv.doublequote) {
                    return STRINGEND;
                }
                csv.currentWord += c;
                return STRINGCELL;
            }
        },
        //für doppelt " gedacht
        STRINGEND {
            @Override
            CSVReader.State handleChar(char c, CSVReader csv) {
                if (c == csv.doublequote) {
                    csv.currentWord += '"';
                    return STRINGCELL;
                }
                if (c == csv.delimiter) {
                    csv.list.add(csv.currentWord);
                    csv.currentWord = "";
                    return NOCELL;
                }
                csv.currentWord += c;
                throw new IllegalArgumentException();
            }
        };

        /**
         * @param c   Character
         * @param cvs CSV File
         * @return States
         */
        abstract CSVReader.State handleChar(char c, CSVReader cvs);
    }

    /**
     * splitet CSV line zu String Array
     * @param line
     * @return String[]
     */
    public String[] split(String line) {
        State state = State.NOCELL;
        this.currentWord = "";
        this.list = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            state = state.handleChar(line.charAt(i), this);
        }
        this.list.add(currentWord);
        if (state == State.STRINGCELL) {
            throw new IllegalArgumentException();
        }
        return list.toArray(new String[0]);
    }
}
