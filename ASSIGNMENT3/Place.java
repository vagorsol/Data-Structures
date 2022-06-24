/**
 * Desc:
 *      A program for Assignment 3.
 *      This programs defines data
 *      that has neither longitude, lattitude
 *      nor population.
 * @author ayang
 * Created: March 2, 2021
 * Modified: March 3, 2021
 * Modified: March 4: 2021
 */
public class Place {
    protected String zip;
    protected String town;
    protected String state;

    /**
     * Constructor for Place(). Creates a 
     * place with zip, town name, and state
     * @param zip
     * @param town
     * @param state
     */
    public Place(String zip, String town, String state) {
        this.zip = zip;
        this.town = town;
        this.state = state;
    } // Place()

    /**
     * Accessor for zip
     * @return zip
     */
    public String getZip() {
        return zip;
    } // getZip()

    /**
     * Accessor for state
     * @return state
     */
    public String getState() {
        return state;
    } // getState()

    /**
     * Accessor for town
     * @return town
     */
    public String getTown() {
        return town;
    } // getTown()

    /**
     * toString method for class Place()
     */
    public String toString() {
        return town + "," + state + " " + zip;
    } // toString()
    
    public static void main(String[] args) {
        /**
         * Test to see if Place() works as desired
         * by entering in values and having it repeat
         * said values back out.
        */
        Place p = new Place("00000", "chicago","illonise");
        System.out.println(p);
    } // main()
} // class Place()