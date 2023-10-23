package secondtry;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Burhan Özbek on 10.10.2023
 * <br>
 * This class is the best counter. It counts the words in a string.
 */
public class BestCounter {

    private static int totalCount;
    private static int nestLevel;
    private static char bufferChar;
    //todo: codunt the words in a string

    /**
     * <h1>
     * Counts the words in a string
     * </h1>
     * <br>
     * Counts the words in any given string. It works with " and < >.
     * Take a look at the text file for more information. (BestCounterTest.java)
     * <br>
     * @param input
     * @return totalCount (of words)
     */
    public static int todo(String input) {
        totalCount = 0;
        System.out.println("BEST COUNTER");
        //todo: implement the FSM (NoWord, Word, Tag, TagQuote)
        enum WordCountFSM {
            //todo: implement the states
            NO_WORD {
                @Override
                public WordCountFSM processNextChar(char currentChar) {
                    if (currentChar == '<' /*&& Character.isLetter(currentChar)*/) {
                        nestLevel++;
                        return TAG;
                    }
                    if (Character.isLetter(currentChar)) {
                        totalCount++;
                        return WORD;
                    }
                    return NO_WORD;
                }
            },
            WORD {
                @Override
                public WordCountFSM processNextChar(char currentChar) {
                    if (currentChar == '<') {
                        nestLevel++;
                        return TAG;
                    }
                    if (!Character.isLetter(currentChar)) {
                        return NO_WORD;
                    }
                    return WORD;
                }
            },
            TAG {
                @Override
                public WordCountFSM processNextChar(char currentChar) {
                    if (bufferChar == '\\') {
                        return TAG;
                    }
                    if (currentChar == '"') {
                        return TAG_QUOTE;
                    }
                    if (currentChar == '<') {
                        nestLevel++;
                        return TAG;
                    }
                    if (currentChar == '>') {
                        nestLevel--;
                        if (nestLevel == 0) {
                            return NO_WORD;
                        } else {
                            return TAG;
                        }
                    }
                    return TAG;
                }
            },
            TAG_QUOTE {
                @Override
                public WordCountFSM processNextChar(char currentChar) {
                    if (bufferChar == '\\') {
                        return TAG_QUOTE;
                    }
                    if (currentChar == '"') {
                        return TAG;
                    }
                    return TAG_QUOTE;
                }
            };
            //todo: implement the transitions
            public abstract WordCountFSM processNextChar(char currentChar);
        }

        nestLevel = 0;
        WordCountFSM fsmCounter = WordCountFSM.NO_WORD;
        char[] charArray = input.toCharArray();
        //todo: implement the loop over the characters
        for (char newChar : charArray) {
            fsmCounter = fsmCounter.processNextChar(newChar);
            bufferChar = newChar;
        }
       // return the total count
        return totalCount;
    }

    /**
     * Counts the words in a file
     * @param path
     * @return totalCount (of words)
     */
    public int countWordsFile (String path) {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return todo(text);
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        BestCounter bestCounter = new BestCounter();

        System.out.println(bestCounter.countWordsFile("C:\\Users\\burha\\OneDrive\\Desktop\\Schule\\sew4_sem1j\\UE02\\src\\secondtry\\crsto12.html"));

    }

}
