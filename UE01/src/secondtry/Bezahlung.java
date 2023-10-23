package secondtry;
 //JAVA DOC MACHEN

/**
 * This class is the best counter. It counts the words in a string.
 * It does not use enums.
 * @autor Created by Burhan Özbek on 10.10.2023
 * Version 1.0
 * @since 10.10.2023
 */
public class Bezahlung {
    public static void main(String[] args) {

    }
    // Count the words in a string

    /**
     * Counts the words in a string
     * @param a
     * @return words.length
     */
    public static int count(String a){
        String[] words = a.replaceAll("<[^>]*>","").split("\\s+"); // Remove HTML tags and split the string into words
        System.out.println(words.length); // Print the count of words
        return words.length; // Return the count of words
    }

    /**
     * Counts the words in a string
     * @param a
     * @return
     */
    public static int todo(String a) {
        System.out.println("AAAAAAA");
        int wordCounter = 0; // Initialize the word counter
        int htmltagflag = 0; // Initialize the HTML tag counter
        boolean stringflag = false; // Initialize the string flag
        boolean wordflag = false; // Initialize the word flag
        boolean escapeFlag = false; // Initialize the escape flag

        for (int i = 0; i < a.length(); i++) {
            String currentChar = a.charAt(i) + "";

            if (escapeFlag) {
                // If the previous character was an escape character, ignore the current character
                escapeFlag = false;
            } else if (currentChar.equals("\\")) {
                escapeFlag = true;
            } else {
                if (currentChar.matches("\\w") && !wordflag && htmltagflag == 0) {
                    wordflag = true;
                    wordCounter++;
                } else if (currentChar.matches("[\\s:.\"]") && htmltagflag == 0) {
                    wordflag = false;
                } else if (currentChar.equals("<") && !stringflag) {
                    wordflag = false;
                    htmltagflag++;
                } else if (currentChar.equals(">") && !stringflag) {
                    wordflag = false;
                    htmltagflag--;
                } else if (currentChar.equals("\"") && htmltagflag > 0) {
                    wordflag = false;
                    stringflag = !stringflag;
                }
            }
        }
        return wordCounter;

    }
}
