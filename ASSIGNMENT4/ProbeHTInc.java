import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

/**
 * An incomplete Hashtable using probing. The get and put functions are stubs only.
 * They must be implemented. A completed implementation need not handle
 * deletions.  Therefore, the implementation need not worry about tombstones,
 * rehashing to reduce the size of the table, or rehashing to reduce the number 
 * of tombstones
 * 
 * @param <K> the type of key
 * @param <V> the type of value 
 * @author gtowell 
 * @modified ayang
 * Created:  Sep 27, 2020 
 * Modified: Oct 2, 2020
 * Modified: March 14, 2021
 */
public class ProbeHTInc<K, V> implements Map206Interface<K, V> {

    /**
     * Small inner class to group together key,value pairs
     */
    protected class Pair<L, W> {
        /** The key, cannot be changed */
        final L key;
        /**
         * The value. It can be changed as a second put with the key will change the
         * value
         */
        W value;

        /**
         * Initialize the node
         */
        public Pair(L ll, W ww) {
            key = ll;
            value = ww;
        }

        /** Print the node, and all subsequent nodes in the linked list */
        public String toString() {
            return "<" + key + ", " + value + ">";
        }
    }

    /** A Constant .. One of the cases in which static are acceptable
     * This one specifies the maximum number of tombstones allowed before 
     * rehashing for tombstone accumulation
     */
    /** When the hashtable needs to grow, by what factor should it grow */
    private static final double GROWTH_RATE = 2.0;
    /** How full the table should be before initiating rehash/growth */
    private static final double MAX_OCCUPANCY = 0.60;
    /** The default size of the backing array */
    private static int DEFAULT_CAPACITY = 1009;
   /** The array in which the hashtable stores data */
    private Pair<K, V>[] backingArray;
    /** The number of active items in the hashtable */
    private int itemCount;
    
 
    /** Default initialization */
    public ProbeHTInc() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initialize a hashtable of the given size
     * 
     * @param size the size of the hashtable to create
     */
    @SuppressWarnings("unchecked")
    public ProbeHTInc(int size) {
        // Cannot make an array in which you mention a parameterized type.
        // So just make the generic array. This is a narrowing cast so it does not
        // even need to be explicitly case.
        backingArray = new Pair[size];
        itemCount = 0;
     }

    /**
     * The hash function. Just uses the java object hashvalue. 
     * @param key the Key to be hashed
     * @return the hash value
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % backingArray.length;
    }

    /**
     * The number of active items in the hashtable
     * @return The number of active items in the hashtable
     */
    public int size() {
        return itemCount;
    }

    /**
     * Add a key-value pair to the hashtable. If the key is already in the
     * hashtable, then the old value is replaced. Otherwise this adds a new
     * key-value pair
     * Be sure to update itemCount as needed.
     * 
     * @param key   the key
     * @param value the value
     */
    public void put(K key, V value) {
        Pair<K,V> pair = new Pair<>(key, value);
        // if the pair does not exist, create it
        if (!containsKey(key)){
            Pair<K,V> np = new Pair<>(key, value);
            // backingArray.add(np);
            itemCount++;
        } else {
            // update the value
            pair.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * Rehash the current table. This should be done rarely as it is expensive
     * @param newSize the size of the table after rehashing
     */
    private void rehash(int newSize) {
        Pair<K, V>[] oldArray = backingArray;
        itemCount = 0;
        backingArray = new Pair[newSize];
        for (int i = 0; i < oldArray.length; i++) {
            if (oldArray[i] != null) {
                put(oldArray[i].key, oldArray[i].value);
            }
        }
    }

    /**
     * Get the value associated with the key
     * @param key the key whose value is sought
     * @return the associated value, or null
     */
    public V get(K key) {
        
        return null;
    }

    

    @Override
    /**
     * Does the hashtable contain the key
     * @param key the key
     * @return true iff the key is in the hashtable
     */
    public boolean containsKey(K key) {
        return null != get(key);
    }

    @Override
    /**
     * The complete set of keys active in the hashtable.
     * @return a set containing all of the keys in the hashtable
     */
    public Set<K> keySet() {
        TreeSet<K> set = new TreeSet<>();
        for (Pair<K,V> pr : backingArray) {
            if (pr!=null) {
                set.add(pr.key);
            }
        } 
        return set;
    }


    /**
     * 
     */
    public static void main(String[] args) {
    }


}