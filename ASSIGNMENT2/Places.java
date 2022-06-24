/**
 * Desc:
 *      A Class for Assignment 2. 
 *      It's two main functions are: to read and store
 *      all of the zip codes in a given file; to look up a specific
 *      zip code in said given file and find the corresponding town
 *      and state. 
 * @author ayang
 * Created: February 24, 2021
 * Modified: February 25, 2021
 * Modified: February 26, 2021
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;;

public class Places {
    private Place[] allZips;

    /**
     * Constructor for class Places()
     */
    public Places(){ 
    }
    /**
     * code used while debugging
        public Places(Place aPlace){
        Place[] allZip = new Place[1];
        allZip[0] = new Place(aPlace.getZip(), aPlace.getTown(), aPlace.getState());
    }

    public Places (String zip, String town, String state){
        Place[] allZip = new Place[1];
        allZip[0] = new Place(zip, town, state);
    }

    public void setAllZips(int i, Place newZipLine){
        this.allZips[i] = newZipLine; 
    } // setter setAllZips()

    public void printAll(){
        for(int i = 0; i < allZips.length; i++)
            System.out.println(allZips[i]);
    }
    */

    /**
        * Reads a zip code file, parsing and storing every line
        * @param fileName the zip code file
        * @throws FileNotFoundException if the file does not exist
    */
    public void readZipCodes(String filename) throws FileNotFoundException{
        try (BufferedReader br = new BufferedReader(new FileReader(filename));) {
           String line = br.readLine();
           String[] partsOfLine = line.split(","); //split the line at every comma
           int zipLinesNum = Integer.parseInt(partsOfLine[0]); // gets the number of codes
           allZips = new Place[zipLinesNum]; // sets allZips's size to the number of codes

           for(int i = 0; i < allZips.length; i++){
                line = br.readLine();
                partsOfLine = line.split(",");
                // Place newPlace = new Place(partsOfLine[0], partsOfLine[1], partsOfLine[2]);
                allZips[i] = new Place(partsOfLine[0], partsOfLine[1], partsOfLine[2]); //assign zip, city, and state value to the array
                /**
                 * code used while debugging
                 * allZips[i].setAllZips(i, newPlace); // redundant assignment to ensure correct assignment
                 * System.out.println(allZips[i].getZip() + " " + allZips[i].getTown() + " " + allZips[i].getState());
                 * System.out.println(allZips[i]); 
                */
            }
        } catch (FileNotFoundException fnf) {
            System.err.println("Could not open the file" + fnf);
        } catch (IOException ioe) {
            System.err.println("Reading problem" + ioe);
        } 
    } // readZipCodes()

    /**
     * Find a Place record given a zip code
     * @param zipCode the zip code to find.
     */
    public Place lookupZip(String zipCode){
        // System.out.println(zipCode + "\n" + allZips);
        for(int i = 0; i < allZips.length; i++) {
             // System.out.println(allZips[i].getZip() + " " + zipCode);
            if (zipCode.equals(allZips[i].getZip()))
                // System.out.println(allZips[i]);
                return allZips[i];
        } 
        return null;
    } // lookupZip() 
    
    public static void main(String[] args) {
        /**
         * Run a single-line version of Main.java 
         * to test if readZipCodes() and lookupZip()
         * work as intended.
         */
        try {
            Places places = new Places(); 
            places.readZipCodes("zip.txt");
            // places.printAll();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter zip: ");
            String zip = scanner.nextLine();
            Place place = places.lookupZip(zip);
            System.out.println(place.getTown() + " " + place.getState());
        } catch (FileNotFoundException fnf) {
            System.err.println("File Not " + fnf);
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException");
        }
    } // main ()
}