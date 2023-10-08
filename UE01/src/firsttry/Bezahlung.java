package firsttry;
import java.util.regex.*;

public class Bezahlung {
    public static void main(String[] args) {

    }
    // Count the words in a string
    public static int count(String a){
        String[] words = a.replaceAll("<[^>]*>","").split("\\s+"); // Remove HTML tags and split the string into words
        System.out.println(words.length); // Print the count of words
        return words.length; // Return the count of words
    }

    public static int todo(String a){


        int wordCounter = 0; // Initialize the word counter
        int htmltagflag = 0; // Initialize the HTML tag counter
        boolean stringflag = false; // Initialize the string flag
        boolean wordflag = false; // Initialize the word flag

        for (int i = 0; i < a.length(); i++) {
            String currentChar = a.charAt(i) + "";

            if (!currentChar.matches("\\\\")) {
                if (currentChar.matches("\\w") && !wordflag && htmltagflag == 0) {
                    wordflag = true;
                    wordCounter++;
                } else if (currentChar.matches("[\\s:.\"]") && htmltagflag == 0) {
                    wordflag = false;
                } else if (currentChar.matches("<") && !stringflag) {
                    wordflag = false;
                    htmltagflag++;
                } else if (currentChar.matches(">") && !stringflag) {
                    wordflag = false;
                    htmltagflag--;
                } else if (currentChar.matches("\"") && htmltagflag > 0) {
                    wordflag = false;
                    stringflag = !stringflag;
                }
            }
        }
        return wordCounter;

        /*
        System.out.println();
        System.out.println("ÜBUNG BEGINNT");

        //crate a regex pattern to find all "

        Character regex = '"';
        Pattern pattern = Pattern.compile(Character.toString(regex));
        Matcher matcher = pattern.matcher(a);
        int countofbigger = 0;
        while (matcher.find()) {
            countofbigger++;
        }
        //print countofbigger
        boolean couttobiggeradd = countofbigger % 2 != 0;
        System.out.print("countofbigger: ");
        System.out.print(countofbigger);
        System.out.println();


        //assertEquals(4, todo(" \\\"null\\\" eins<img alt=\"<bild \\\" keinwort keinwort\" keinwort>zwei \"drei\""));
        int htmltagcount = 0;
        int wordnum = 0;
        boolean isword = false;
        boolean isblocked = false;
        for (int i = 0; i < a.length(); i++) {
            //if a character a lette
            if (a.charAt(i) == '"'){
                if (isblocked == false){
                    if (couttobiggeradd != true && countofbigger != 1){
                    isblocked = true;
                    countofbigger--;
                    System.out.println("isblocked" + i);
                    }
                }
                else{
                        isblocked = false;
                        countofbigger--;
                        System.out.println("unblocked" + i);
                }
            }
            //write me a if statement that looks if the countofbigger is odd or even then if it is add when countofbigger is 1 then isblocked = false;
            if (couttobiggeradd == true && countofbigger == 1){
                isblocked = false;
                System.out.println("unblocked" + i + "BUT COMBO");
            }
            if (isblocked == false || countofbigger == 1) {
                if (Character.isLetter(a.charAt(i))) {
                    if (htmltagcount == 0) {
                        if (isword == false) {
                            wordnum++;
                            System.out.println(a.charAt(i));
                            isword = true;
                        }
                    }
                } else {
                    isword = false;
                }
                if (a.charAt(i) == '<' && htmltagcount < 1) {

                    htmltagcount++;
                    System.out.println(i);
                    //}
                    isword = false;
                }
                if (a.charAt(i) == '>' && htmltagcount > 0) {
                    System.out.println(i);
                    htmltagcount--;
                    isword = false;
                }
            }
        }
        System.out.print("htmltagcount: ");
        System.out.print(htmltagcount);
        System.out.println();
        System.out.println(isword);
        System.out.println("ÜBUNG ZU ENDE");

        return wordnum;


         */
    }
}
