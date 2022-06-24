/**
 * Desc:
 *      A program for Assignment 3.
 *      This programs defines data
 *      that has longitude and lattitude
 *      but not population.
 * @author ayang
 * Created: March 2, 2021
 * Modified: March 3, 2021
 * Modified: March 4, 2021
 */
public class LocatedPlace extends Place {
    protected String lattitude;
    protected String longitude;

    /**
     * Constructor for LocatedPlace(). Creates a place that has
     * zip, town name, state, lattitude, and longitude
     * @param zip
     * @param town
     * @param state
     * @param lattitude
     * @param longitude
     */
    public LocatedPlace(String zip, String town, String state, String lattitude, String longitude) {
        super(zip, town, state);
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    /**
     * Accesor for longitude
     * @return longitude
     */
    public String getLongitude() {
        return longitude; 
    } // getLongitude

    /**
     * Accessor for lattitue
     * @return lattitude
     */
    public String getLattitude() {
        return lattitude;
    } // getLattitude()

    /**
     * toString method for class LocatedPlace()
     */
    @Override
    public String toString() {
        return town + "," + state + " " + zip + "\t Lat: " 
                + lattitude + " Lon: " + longitude;
    } // toString()

    public static void main(String[] args) {
        /**
         * Test to see if LocatedPlace() works as desired
         * by entering in values and having it repeat
         * said values back out.
        */
        LocatedPlace lp = new LocatedPlace("00000", "chicago","illonise", "10", "12");
        System.out.println(lp);
    } // main()
} // class LocatedPlace()
