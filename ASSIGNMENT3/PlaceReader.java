import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Desc: 
 *      A program for Assignment 3. This programs reads the given file and
 *      stores the data.
 * @author ayang 
 * Created: March 2, 2021
 * Modified: March 3, 2021
 * Modified: March 4, 2021
 */
public class PlaceReader {
    private ArrayList<Place> allZips = new ArrayList<Place>(1); 

    /**
     * Constructor for class PlaceReader()
     */
    public PlaceReader(){
    }
    
    /**
     * Reads a zip code file, parsing and storing every line.
     * @param filename
     * @throws FileNotFoundException
     */
    public void readZipCodes(String filename) throws FileNotFoundException, IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(filename));) {
            while(br.ready()){
                String line = br.readLine();
                String[] partsOfLine = line.split(","); // split the line at every comma
                
                // remove all quotation marks from the line
                for(int j = 0; j < partsOfLine.length; j++){
                    partsOfLine[j] = partsOfLine[j].replaceAll("\"", "");
                }
                
                if (partsOfLine.length < 5) {
                    // if the line is a "Generic" place, store it as such
                    allZips.add(new Place(partsOfLine[0], partsOfLine[2], partsOfLine[3]));
                }
                else if (partsOfLine.length >= 5 && partsOfLine.length < 10){
                    // if the line is a Located Place, store it as such
                    allZips.add(new LocatedPlace(partsOfLine[0], partsOfLine[2], partsOfLine[3], partsOfLine[5], partsOfLine[6]));
                }
                else {
                    // if the line is a Populated Place, store it as such
                    allZips.add(new PopulatedPlace(partsOfLine[0], partsOfLine[2], partsOfLine[3], partsOfLine[5], partsOfLine[6], partsOfLine[10]));
                }

                // read the next line
                line = br.readLine();
            }
            br.close(); // stop reading once all the lines are read
        } 
    } // readZipCodes()

    /**
     * Find a Place record given a zip code
     * @param zipCode the zip code to find.
     */
    public Place lookupZip(String zipCode){
        for(int i = 0; i < allZips.size(); i++) {
            if (zipCode.equals(allZips.get(i).getZip())){
                return allZips.get(i);
            }
        }
        return null;
    } // lookupZip() 
    
    public static void main(String[] args) {
        /**
         * Call specific lines of a miniature
         * version of ziplocs.csv
         * to test if readZipCodes() and lookupZip()
         * work as intended.
         */
        try{
            PlaceReader place = new PlaceReader(); 
            place.readZipCodes("testloc.txt");;
            Place p = place.lookupZip("10029");
            System.out.println(p);
        } catch (FileNotFoundException fnf) {
            System.err.println("File Not " + fnf);
        } catch (IOException ioe) {
            System.err.println("Reading problem" + ioe);
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException");
        }
    } // main()
}
