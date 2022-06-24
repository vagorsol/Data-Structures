/**
 * Desc:
 *    This program outputs the phrase "Hello, World!"
 *    followed by a count of how many times it has
 *    printed the phrase. 
 * @author ayang
 * Created: February 11, 2021
 * Modified: February 16, 2021
*/

public class HelloWorld {
    public static void main(String[] args) {
        // counts how many times the phrase has been said
        for (int i = 1; i <= 610; i ++) {
            System.out.println("Hello, World! " + i);
        }
    } // main()
} // class HelloWorld