import java.util.ArrayList;

/**
 * A class (object type) that holds the names and associated information from
 * the files given.
 * 
 * @author ayang
 * Created: May 1, 2021
 * Modified: May 6, 2021
 */
public class Names implements Comparable<Names> {

    private String name;
    private String gender;
    // the total number of babies with the given name
    private int number;
    // the percentage of babies given that name
    private double percentage;
    // list of all of the years the baby's name appeared in
    private ArrayList<String> years = new ArrayList<>();
    // alphabetical rank of the name
    private int rank;

    public Names(){
    }

    /**
     * Creates a new item of type Names given a name, gender, number of babies with that name,
     * year the name occurs in , and alphabetical rank of that name.
     * 
     * @param name 
     * @param gender
     * @param number of babies with that name
     * @param year that the baby name appears in
     * @param rank alphabetical rank of the name
     */
    public Names(String name, String gender, int number, String year){
        this.name = name;
        this.gender = gender;
        this.number = number;
        years.add(year);
    }

    /**
     * Add an aditional year to the Array List of years the name occured in.
     * @param year to be added to the array list
     */
    public void addYear(String year){
        years.add(year);
    }

    /**
     * Add an amount to the number of babies with the given name
     * @param num2Add
     */
    public void addNumbers(int num2Add){
        this.number =  number + num2Add;
    }

    /**
     * find the percentage of babies given that name for all years.
     * 
     * @param total the total number of babies
     */
    public void percentTotal(int total){
        percentage = (double) number / (double) total;
    }

    /**
     * If the rank of the name has changed, update it
     * @param rank updated
     */
    public void updateRank(int rank){
        this.rank = rank;
    }
    
    /**
     * Compares two names, and find which one comes first, alphabetically
     * @param name to be compared to
     * @return 0 if the two names are the same, -1 if the name is alphabetically before the name
     * given to be compared to, and 1 if the name is alphabetically after the name given to be 
     * compared to.
     */
    @Override
    public int compareTo(Names name){
        int comp = (this.name).compareToIgnoreCase(name.getName());
        return comp;
    }

    /**
     * Returns the name
     * @return name
     */
    public String getName(){
        return name; 
    }

    /**
     * Returns the gender associated with the name
     * @return gender associated with the name
     */
    public String getGender(){
        return gender;
    }

    /**
     * Returns the total number of babies with that name
     * @return total number of babies with that name
     */
    public int getNumber(){
        return number;
    }

    /**
     * Returns the percentage of the name's occurance out of all names given
     * @return percentage of babies with the name
     */
    public double getPercent(){
        return percentage;
    }

    /**
     * Returns the years the name appears in
     * @return years the name appears in
     */
    public ArrayList<String> getYears(){
        return years; 
    }

    /**
     * Returns the rank of the name
     * @return rank of the name
     */
    public int getRank(){
        return rank;
    }

    public static void main(String[] args){
        Names m = new Names("noelle", "female", 3, "2020");
        Names n = new Names("Noelle", "female", 2, "2021");
        m.updateRank(4);
        System.out.println(m.compareTo(n));
        System.out.println(m.getRank());
    }
}