package firsttry;

public class Bezahlung {
    public static void main(String[] args) {

    }
    // Count the words in a string
    public static int count(String a){
        String[] words = a.replaceAll("<[^>]*>","").split("\\s+"); // Remove HTML tags and split the string into words
        System.out.println(words.length); // Print the count of words
        return words.length; // Return the count of words
    }
}
