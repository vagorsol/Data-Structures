import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that stores lists of names and their associated data.
 * 
 * @author ayang
 * Created: May 6, 2021 
 * Modified: May 7, 2021
 */
public class NamesList {
    private int boysTotal = 0;
    private int girlsTotal = 0;
    private LinkedLists<Names> boyNames = new LinkedLists<>();
    private LinkedLists<Names> girlNames = new LinkedLists<>();   

    public NamesList(){
    }

    /**
     * Read a given file name and stores and/or updates the data in the corresponding Linked Lists. Once finished,
     * reorder and update the lists as well.
     * 
     * @param filename to be read
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void namesReader(String filename) throws IOException, FileNotFoundException{
        try (BufferedReader br = new BufferedReader(new FileReader(filename));) {
            String year = filename.substring(36, 40);  
            while(br.ready()){
                String line = br.readLine();
                String[] partsOfLine = line.split(","); //split the line at every comma
               // create two Names object: one for boys and one for girls
                Names boyName = new Names(partsOfLine[1], "male", Integer.parseInt(partsOfLine[2]), year); 
                Names girlName = new Names(partsOfLine[3], "female", Integer.parseInt(partsOfLine[4]), year);
                
                // check if the name already exists. if it does, update it. if not, create a new one.
                if(boysNameSearch(boyName.getName())== null){
                    boyNames.addLast(boyName);
                }
                if(girlNameSearch(girlName.getName()) == null){
                    girlNames.addLast(girlName);
                }
                // if the name exists, update the year and total amount
                if(boysNameSearch(boyName.getName()) != null){
                    Names updateBoy = boysNameSearch(boyName.getName());
                    updateBoy.addNumbers(boyName.getNumber());
                    boolean addTheYear = true;
                    ArrayList<String> yearsList = updateBoy.getYears();
                    for(int i = 0; i < yearsList.size(); i++){
                        if(yearsList.get(i) == year)
                            addTheYear = false;
                    }
                    // only add the year if that year hasn't already been added
                    if(addTheYear)
                        updateBoy.addYear(year);
                }
                if(girlNameSearch(girlName.getName()) != null){
                    Names updateGirl = girlNameSearch(girlName.getName());
                    updateGirl.addNumbers(boyName.getNumber());
                    boolean addTheYear = true;
                    ArrayList<String> yearsList = updateGirl.getYears();
                    for(int i = 0; i < yearsList.size(); i++){
                        if(yearsList.get(i) == year)
                            addTheYear = false;
                    }
                    // only add the year if that year hasn't already been added
                    if(addTheYear)
                        updateGirl.addYear(year);
                }
                // update the total number of babies
                boysTotal = boysTotal + Integer.parseInt(partsOfLine[2]);
                girlsTotal = girlsTotal + Integer.parseInt(partsOfLine[4]);
            }
        }
        // sort the lists here
        insertSort(boyNames);
        insertSort(girlNames);

        // update percentage for each name
        update(boyNames, boysTotal);
        update(girlNames, girlsTotal);
    }

    /**
     * Update the percentage and rank of all names in the provided list
     * @param listOfNames to update the information for
     */
    private void update(LinkedLists<Names> listOfNames, int total){
        for(int i = 0; i < listOfNames.size(); i++){
            Names temp = (Names) listOfNames.get(i);
            temp.percentTotal(total);
            temp.updateRank(i + 1);
            listOfNames.set(i, temp);
        }
    }

    /**
     * Given a name, search for the node that holds the data for that name and return the data
     * @param name to look for
     * @return data for that name, null if that name odoes not exist in the list
     */
    public Names boysNameSearch(String name){
        // first, check the list of boy names
        for(int i = 0; i < boyNames.size(); i++){
            if(0 == name.compareToIgnoreCase(((Names) boyNames.get(i)).getName())){
                return (Names) boyNames.get(i);
            }
        }
        
        // if the name is not in either list, return null;
        return null;
    }

    public Names girlNameSearch(String name){
        for(int i = 0; i < girlNames.size(); i++){
            if(0 == name.compareToIgnoreCase(((Names) girlNames.get(i)).getName())){
                return (Names) girlNames.get(i);
            }
        }
        return null;
    }
    /**
     * Do insertion sort, in place, on the list of names. This changes the order of elements 
     * in the linked list (changing the position of the data, not the nodes themselves) 
     * @param listOfNames to sort
    */ 
    private void insertSort(LinkedLists<Names> listOfNames){
        for(int loc = 0; loc < listOfNames.size(); loc++){
            int p = 0;
            // keep moving along while the name at p is less than the name at loc 
            while(0 > (((Names) listOfNames.get(p)).getName()).compareToIgnoreCase(((Names) listOfNames.get(loc)).getName()) && p < loc){
                p++;
            }
            // move the elements over to move p into where loc is
            Names temp = (Names) listOfNames.get(loc);
            for(int j = loc; j > p; j--){
                listOfNames.set(j, listOfNames.get(j - 1)); 
            }
            // insert the original element at loc at p 
            listOfNames.set(p, temp);
        }
    }

    public static void main(String[] args){
        Names marry = new Names("Mary", "female", 11921, "2020");
        marry.updateRank(2);
        marry.percentTotal(1000000000);
        // testing get and set
        // boyNames.addLast(marry);
        // System.out.println(((Names) boyNames.get(0)).getName());
    }
}
