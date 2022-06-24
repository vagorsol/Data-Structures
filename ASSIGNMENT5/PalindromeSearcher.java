import java.io.IOException;
import java.util.ArrayList;

/**
 * Searches a text for palindromes of lengths 2-50 and
 * returns a list of them.
 * 
 * @author ayang
 * Created: March 28, 2021
 * Modified: March 29, 2021
 * Modified: March 30, 2021
 */
public class PalindromeSearcher {
    private ArrayList<String[]> allPals = new ArrayList<>(); // stores all found palindromes 
    private ArrayList<String> file = new ArrayList<>();

    /**
     * Create a string from the given file.
     * Uses File2String.java
     * 
     * @param filename
     * @throws IOException
     */
    public PalindromeSearcher(String filename) throws IOException {
        File2String fn = new File2String(filename);
        String text = fn.getString(); // turn the contents of the file into a string
        // System.out.println(text);
        char[] charTS = new char[text.length()]; // set the array to the size of the String
        charTS = text.toCharArray(); // each element of array filetxt is a character of String file
        // System.out.println(charTS);
        for(int i = 0; i < charTS.length; i++){
            file.add(String.valueOf(charTS[i]));
            // System.out.println(file.get(i));
        }     
    } // PalindromeSearcher()

    /**
     * Goes through the file and checks for palindromes
     * 
     * @param filename
     * @throws IOException
     */
    public void palindromeFinder() throws IOException {
        for (int i = 0; i < file.size() - 1; i++) {
            // loops through for all locations
            for (int j = 2; j <= Math.min(50, file.size() - i); j++){
                // loopse through for all sizes
                if(this.palindromeChecker(j, i)){
                    String[] palTempArra = new String[j];
                    StringBuffer pal = new StringBuffer();

                    // have the palindrome be a string
                    for (int k = 0; k < j; k++){
                        palTempArra[k] = file.get(i+k).toString();
                        pal.append(palTempArra[k]);
                    }

                    // check to see if a palindrome of that size is already stored
                    if (duplicationChecker(pal.toString())){
                        // if it has not already been stored, store it
                        String[] palData = new String[3];
                        palData[0] = Integer.toString(j);
                        palData[1] = Integer.toString(i);
                        palData[2] = pal.toString();
                        // System.out.println(palData + " " + 1);
                        allPals.add(palData);
                    }
                }
            }

        }
    } // palindromeFinder()

    /**
     * For a given palindrome size and starting location,
     * checks to see if a palindrome exists.
     * 
     * @param size
     * @param location
     * @return true (if it is a palindrome) 
     * or false (if it is not a palindrome)
     */
    private boolean palindromeChecker(int size, int location) {
        // store the "forward" version of the string
        String[] forwardString = new String[size]; 
        // store the "backwards" version of the string
        ArrayStackHW5<String> backwardsString = new ArrayStackHW5<String>(size);

        // put in the elements (in order) for the forward and backwards version
        for (int i = location; i < location + size; i++) {
            forwardString[i - location] = file.get(i).toString();
            backwardsString.push(file.get(i).toString());
        }

        // check to see if the forwards and backwards version of the string are equal
        for(int i = 0; i < size; i++){
            // System.out.println(backwardsString.peek() + "peek");
            if(!forwardString[i].equals(backwardsString.pop())){
                // if the forwards and backwards are not equal, return false
                return false;
            }
        }
        // if the forwards and backwards version of the string are equivalent, return true
        return true; 
    } // palindromeChecker()

    /**
     * Checks if a palindrome of that same size has been stored before
     * 
     * @return true (if the palindrome isn't already stored)
     * or false (if the palidrome is already stored)
     */
    private boolean duplicationChecker(String palindrome) {
        for (int i = 0; i < allPals.size(); i++ ) {
            String[] checkPal = allPals.get(i);
            if (palindrome.equals(checkPal[0])){
                return false;
            }
        }
        return true;
    } // duplicationChecker()

    public String toString() {
        StringBuffer ret = new StringBuffer();
        // System.out.println(allPals);
        for (int i = 0; i< allPals.size(); i++){
            String[] p = allPals.get(i);
            ret.append(p[0]);
            ret.append(" ");
            ret.append(p[1]);
            ret.append(" ");
            ret.append(p[2]);

            // this part is just so that there isn't an extra new line at the end
            if (i + 1 < allPals.size())
                ret.append("\n");
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        // get the filename from the command line
        String[] defaultArgs = {"filename"};
        String filename = args[0];
        if (args[0].length() == 0) {
            args = defaultArgs;
        }

        // String filename = "palindromeTest.txt";
        try{
            PalindromeSearcher palindromes = new PalindromeSearcher(filename);
            palindromes.palindromeFinder();
            System.out.println(palindromes);

        } catch (IOException e) {
            System.err.println("Reading problem" + e);
            return;
        } 
    } // main()
} // class PalindromeSearcher()