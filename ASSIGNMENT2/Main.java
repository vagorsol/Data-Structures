/**
 * Desc:
 *      The main program for Assignment 1.
 *      This program reads the file specified in line 17,
 *      and stores them. Then it takes user-inputed zipcodes,
 *      searches for them in the read file, and then outputs
 *      1) the corresponding town and state, 2) an error message,
 *      or 3) good bye message if "00000" is inputed and then ends. 
 * @author ayang
 * Created: February 24, 2021
 * Modified: February 25, 2021
 * Modified: February 26, 2021
 */
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try (Scanner scanner = new Scanner(System.in);) {
            Places place = new Places();
            place.readZipCodes("/home/gtowell/Public/206/a2/uszipcodes.csv"); // read the zip codes
            do{
                System.out.print("zipcode: ");
                String zipCode = scanner.nextLine();

                // ends the program when "00000" is inputed
                if (zipCode.equals("00000")){
                    System.out.println("Good Bye!");
                    break;
                }
                
                Place uiPlace = place.lookupZip(zipCode); // the zip code the user inputed 

                // if the zipcode does not exist, output this message
                if (uiPlace == null){
                    System.out.println("No such zipcode");
                }
                else {
                    System.out.println(uiPlace.getTown() + ", " + uiPlace.getState());
                }
            } while (true); // program will keep going until user exits
            
        } catch (FileNotFoundException fnf){
            System.err.println("Could not open the file" + fnf);
        }
    }
}
