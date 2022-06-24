/**
 * Desc:
 *      A Class for Assignment 1. 
 *      It holds information read from a CSV file,
 *      and allows other programs to call specific 
 *      pieces of information (zip, town, or state).
 * @author ayang
 * Created: February 24, 2021
 * Modified: February 25, 2021
 * Modified: February 26, 2021
 */
import java.util.Scanner;

public class Place {
    private String zip;
    private String town;
    private String state;

    /**
    * Creates a place with zip, town name, and state
    */
    public Place(String zip, String town, String state){
        this.zip = zip;
        this.town = town;
        this.state = state; 
    }
     
    /**
     * Accessor for zip
     * @return zip
     */
    public String getZip(){
        return zip; 
    }

    /**
     * Accessor for town
     * @return town
     */
    public String getTown(){
        return town;
    }

    /**
     * Accessort for state
     * @return state
     */
    public String getState(){
        return state;
    }

    public String toString(){
        return "zip: " + zip + "\n" + town + ", " + state;
    }
    public static void main(String[] args){
        /**
         * Test to see if Place() works as desired
         * by entering in values and having it repeat
         * said values back out.
        */
        try (Scanner scanner = new Scanner(System.in);) {
            System.out.print("Enter zip: ");
            String zip = scanner.next();
            System.out.print("Enter town: ");
            String town = scanner.next();
            System.out.print("Enter state: ");
            String state = scanner.next();
            Place p = new Place(zip, town, state);
            System.out.println(p);
        }
    }
} // class Place()