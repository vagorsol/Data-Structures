import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Given file(s) and a name, return statistic about the babies 
 * born with that name from the given files.
 * 
 * @author ayang
 * Created: May 1, 2021
 * Modified: May 7, 2021
 */
public class Main {
    public static void main(String[] args){
        String[] myArgs = {"/home/gtowell/Public206/data/a9/names1990.csv"};
        if (args.length == 0){
            args = myArgs;
        }
        
        try (Scanner scanner = new Scanner(System.in);) {
            NamesList listOfNames = new NamesList();
            // get all of the inputed files; a for loop for cases where more than one file is given
            for(int i = 0; i < args.length; i++){
                listOfNames.namesReader(args[i]);
            }
            
            do{
                System.out.print("Name " + "(q to quit): ");
                String ui = scanner.nextLine();

                // if the user enters the quit code, stop the loop
                if(ui.equals("q")){
                    System.out.println("Goodbye!");
                    break;
                }

                Names uiNamesMale = listOfNames.boysNameSearch(ui);
                Names uiNamesFemale = listOfNames.girlNameSearch(ui);

                if(uiNamesMale == null && uiNamesFemale == null){
                    System.out.println("Name does not exist");
                    System.out.println();
                }
                else{
                    // formatting
                    System.out.println();
                    
                    //check the gender usage of the name, and print from the boy's/girl's name list accordingly
                    if(uiNamesMale != null){
                        System.out.println("Used as a Boy's name");
                        System.out.println(uiNamesMale.getName());
                        System.out.println("Total Babies: " + uiNamesMale.getNumber());
                        System.out.println("Years in top 1000: " + uiNamesMale.getYears());
                        System.out.println("Percentage: " + uiNamesMale.getPercent());
                        System.out.println("Alphabetical Position: " + uiNamesMale.getRank());
                        // more formatting
                        System.out.println();
                    }
                    if(uiNamesFemale != null) {
                        System.out.println("Used as a Girl's name");
                        System.out.println(uiNamesFemale.getName());
                        System.out.println("Total Babies: " + uiNamesFemale.getNumber());
                        System.out.println("Years in top 1000: " + uiNamesFemale.getYears());
                        System.out.println("Percentage: " + uiNamesFemale.getPercent());
                        System.out.println("Alphabetical Position: " + uiNamesFemale.getRank());
                        // more formatting
                        System.out.println();
                    } 
                }
            } while(true);
        } catch(FileNotFoundException fnf){
            System.err.println("Could not open the file" + fnf);
            return;
        } catch(IOException e){
            System.err.println("Reading Problem" + e);
        }
    }
}