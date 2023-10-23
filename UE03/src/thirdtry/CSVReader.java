package thirdtry;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Burhan Özbek on 10.10.2023
 */

public class CSVReader {

    private static int totalCount;
    private static char bufferChar;
    public static List<String> split(String input){

        totalCount = 0;
        //"ok","ok, ok",ok"ok","nicht"ok,"nicht ok
        //• ein Feldbegrenzer mitten in einem Feld ohne Feldbegrenzer ist zulässig
        //• Text nach dem abschließenden Feldbegrenzer ist nicht zulässig (Exception)
        //• ein fehlender Feldbegrenzer am Ende ist auch ein Fehler
        enum CSVReaderFSM {
            INIT {
                public CSVReaderFSM processNext(char currentChar) {
                    if (currentChar == '"') {
                        return QUOTE;
                    }
                    if (Character.isLetter(currentChar)) {
                        return WORD;

                    }
                    return INIT;
                }
            },
            WORD {
                public CSVReaderFSM processNext(char currentChar) {
                    if (currentChar == '"') {
                        return QUOTE;
                    }
                    if (currentChar == ',') {
                        return INIT;
                    }
                    if (Character.isLetter(currentChar)) {
                        return WORD;
                    }
                    return WORD;
                }
            },
            QUOTE{
                public CSVReaderFSM processNext(char currentChar) {
                    if (currentChar == '"') {
                        return WORD;
                    }
                    if (Character.isLetter(currentChar)) {
                        return WORD;
                    }
                    return QUOTE;
                }
            };
            public abstract CSVReaderFSM processNext(char currentChar);
        }

        CSVReaderFSM state = CSVReaderFSM.INIT;
        char[] charArray = input.toCharArray();
        for (char newChar : charArray) {
            state = state.processNext(newChar);
            bufferChar = newChar;
        }

        List<String> result = new ArrayList<>();
        result.add("Element1");
        result.add("Element2");
        return result;
    }
}
