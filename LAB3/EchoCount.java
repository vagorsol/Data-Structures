/* 
   * Desc:
   *    This program reads the file specified in the main and reads
   *    how many lines are in the file.
   * @author gtowell
   * @modified ayang
   * Modified: February 12, 2021
   * Modified: February 16, 2021
*/

// import the necessary classes
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EchoCount {
    public static void main(String[] args) {
        EchoCount ec = new EchoCount(); 
        ec.echo("EchoCount.java"); // read the file "EchoCount.java"
    } // main()

    public EchoCount() {
        // constructor of class EchoCount
    } // EchoCount()

    public void echo(String stringfilename) {
        /** test to see if the file is readable (try)
         *  if it is, read how many lines there are in it
        */
        try (BufferedReader br = new BufferedReader(new FileReader(stringfilename));) {
            int lineCount = 0;

            /** while there are lines, "read" the next 
            *   line and increase the line count by one */
            while (br.ready()) {
                br.readLine();
                lineCount++;
            }
            System.out.println("Lines read " + lineCount); // output how many lines there are
        } 

        /** catch blocks: set up so that in the case of the 
         *  specified exception happening, the program will 
         *  execute the code in the catch block
        */
        // case: if there is some sort of i/o exception (error), print this out
        catch (FileNotFoundException fnf) {
            System.err.println("Could not open the file" + fnf);
        } 

        // case: if the file cannot be found, print this out
        catch (IOException ioe) {
            System.err.println("Reading problem" + ioe);
        } 
    } // echo()
} // class EchoCount