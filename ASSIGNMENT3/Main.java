import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Desc: The main program for Assignment 3. This programs reads user-inputed
 * zipcodes, searches for it, and returns the corresponding data.
 * 
 * @author ayang 
 * Created: March 2, 2021
 * Modified: March 4, 2021
 */
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);) {
            PlaceReader place = new PlaceReader();
            place.readZipCodes("/home/gtowell/Public/206/a3/ziplocs.csv"); // read the zipcodes

            do {
                System.out.print("Enter a zip code to lookup (00000 to quit): ");
                String zipCode = scanner.nextLine();
                if (zipCode.equals("00000")){
                    System.out.println("Goodbye");
                    break;
                }

                // get the data for the zipcode the user inputed
                Place uiPlace = place.lookupZip(zipCode); 
                
                // if the zipcode does not exist, output this message
                if (uiPlace == null){
                    System.out.println("No such zipcode");
                }
                else {
                    System.out.println(uiPlace);
                }

            } while (true); // program will keep going until user exits

        } catch (FileNotFoundException fnf) {
            System.err.println("Could not open the file" + fnf);
            return;
        } catch (IOException e) {
            System.err.println("Reading problem" + e);
            return;
        }
    }
}
