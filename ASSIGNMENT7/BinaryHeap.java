import java.util.Random;

/**
 * An implementation of the Priority Queue data structure 
 * that uess Binary Heaping (two branches per parent).
 * 
 * @author gtowell
 * @modified ayang
 * Created APRIL 6, 2020
 * Modified: April 12, 2021
 * Modified April 21, 2021
 */
@SuppressWarnings("unchecked")
public class BinaryHeap<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V>
{
	// default capacity
	private static final int CAPACITY = 1032;
	// the underlying array which will hold all of the values
	protected Pair<K,V>[] backArray;
	// tracks the size of the backing array
    private int size;

	/**
	 * Constructor for BinaryHeap where if there is no specified size,
	 * then the default capacity is used. 
	 */
	public BinaryHeap() {
        this(CAPACITY);
	}
	
	/**
	 * Constructor for BinaryHeap where the capacity given is set 
	 * as the size of the backArray 
	 */
	public BinaryHeap(int capacity) {
        backArray = new Pair[capacity];
	}

	/**
	 * Returns the size of the queue
	 * 
	 * @return size of queue
	 */
    @Override
    public int size()
    {
		return size;
    }

	/**
	 * Checks if the heap is empty or not
	 * 
	 * @return true iff heap is empty
	 */
    @Override
    public boolean isEmpty()
    {
		return size == 0;
    }

	/**
	 * Inserts the specified element into the queue if it is possible to do so 
	 * immediately without violating capacity restrictions, treturning true iff 
	 * it succeeded, false otherwise
	 * 
	 * @param key
	 * @param value
	 * @return true on success, false on failure
	 */
    @Override
    public boolean offer(K key, V value)
	{
		// check if the heap is full or not
		if (size >= (backArray.length - 1))
			return false;
		// set the new value to be at the end of the heap	
		int loc = size++;
		backArray[loc] = new Pair<K, V>(key, value);
		// find the value of the parent
		int upp = (loc - 1) / 2;
		while (loc != 0) {
			/** If the value of the newly added pair is greater than its parent,
			 * 	then swap the two. Otherwise, stop. */
			if (0 > backArray[loc].compareTo(backArray[upp])) {
				Pair<K, V> tmp = backArray[upp];
				backArray[upp] = backArray[loc];
				backArray[loc] = tmp;
				loc = upp;
				upp = (loc - 1) / 2;
			} else {
				break;
			}
		}
		return true;
	}
	
	/**
	 * Removes the first element in the heap, then
	 * rearranges the heap internally to maintain its
	 * structure ("heapness")
	 */
	private void removeTop()
    {
		// decrease the size of the heap
		size--;
		// replace the root with the element at the bottom of the heap
		backArray[0] = backArray[size];
		backArray[size]=null;
		// start restoring heapness from the new root
		int parentLoc = 0; 
		while (true) 
		{
	    	int bestChildLoc;   
	    	int childLoc1 = parentLoc*2+1; 
	    	int childLoc2 = parentLoc*2+2;
			// if there are no children, then stop 
	    	if (childLoc1 >= size) 
				break;
			// if there is only one child on a row, then set bestChildLoc to that child         
	    	if (childLoc2 >= size)
	    	{
				bestChildLoc=childLoc1; 
	    	}
			// otherwise, check to see which child is smaller, and set bestChildLoc to that
	    	else
	    	{
				int cmp = backArray[childLoc1].compareTo(backArray[childLoc2]);
				if (cmp<=0)
		    		bestChildLoc=childLoc1;
				else
		    		bestChildLoc=childLoc2;
			}
			/** if one of the children is smaller than its parent, then repeat the restoration 
			 * of heapness using that child's location as the parent */
	    	if (0 > backArray[bestChildLoc].compareTo(backArray[parentLoc]))
	    	{
				Pair<K,V> tmp = backArray[bestChildLoc];
				backArray[bestChildLoc] = backArray[parentLoc];
				backArray[parentLoc] = tmp;
				parentLoc = bestChildLoc;
	    	}
			// otherwise, you stop
	    	else {
				break;
	    	}
		}
    }

	/**
	 * Retrieves and removes the value of the root of the heap.
	 * 
	 * @return the removed element, or null if the heap is empty 
	 */
	@Override
	public V poll() {
		if (isEmpty())
			return null;
		Pair<K,V> tmp = backArray[0];
		removeTop();
		return tmp.theV;
	}

	/**
	 * Retrives, but does not remove, the value of the root of the heap.
	 * 
	 * @return the value at the root of the heap or null if the heap is empty
	 */
	@Override
	public V peek() {
		if (isEmpty())
			return null;
		return backArray[0].theV;
	}


	public static void main(String[] args) {
        BinaryHeap<Integer, String> pq = new BinaryHeap<>(CAPACITY);
		// test a phrase with one set of priority keys
        pq.offer(1,"Jane");
        pq.offer(10,"WET");
        pq.offer(5, "WAS");
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println();

		// test the same phrase, but in a different order
        pq = new BinaryHeap<>(CAPACITY);
        pq.offer(2,"Jane");
        pq.offer(1,"WET");
        pq.offer(5, "WAS");
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
		
		Random r = new Random();
		BinaryHeap<Integer, Integer> pqi = new BinaryHeap<>(CAPACITY);
		pqi.poll();
		// put in integers 0-9 in a random order into a BinaryHeap
		for (int i=0; i<10; i++)
			pqi.offer(r.nextInt(), i);
		// while there are items left in the heap, remove & println the top item
		while (pqi.peek()!=null)
			System.out.println(pqi.poll());

    }

}
