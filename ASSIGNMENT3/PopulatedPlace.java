/**
 * Desc:
 *      A program for Assignment 3.
 *      This programs defines data
 *      that has longitude, lattitude,
 *      and population.
 * @author ayang
 * Created: March 2, 2021
 * Modified: March 3, 2021
 * Modified: March 4, 2021
 */
public class PopulatedPlace extends LocatedPlace {
    private String population;

    /**
     * Constructor for LocatedPlace(). Creates a place that has
     * zip, town name, state, lattitude, longitude, and
     * population
     * @param zip
     * @param town
     * @param state
     * @param lattitude
     * @param longitude
     * @param population
     */
    public PopulatedPlace(String zip, String town, String state, String lattitude, String longitude, String population) {
        super(zip, town, state, lattitude, longitude);
        this.population = population; 
    } // PopulatedPlace()
    
    /**
     * Accessor for population
     * @return population
     */
    public String getPopulation() {
        return population;
    } // getPopulation()

    /**
     * toString method for class PopulatedPlace()
     */
    @Override
    public String toString() {
        return town + "," + state + " " + zip + "\t Pop:" + population + " Lat: " 
                + lattitude + " Lon: " + longitude;
    } // toString()

    public static void main(String[] args) {
        /**
         * Test to see if PopulatedPlace() works as desired
         * by entering in values and having it repeat
         * said values back out.
        */
        PopulatedPlace pp = new PopulatedPlace("00000", "chicago","illonise", "10", "12", "66");
        System.out.println(pp);
    }
}