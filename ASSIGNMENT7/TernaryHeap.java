/**
 * An implementation of the Priority Queue data structure 
 * that uess Ternary Heaping (three branches per parent).
 * 
 * @author ayang
 * Created: April 17, 2021
 * Modified: April 21, 2021
 */
@SuppressWarnings("unchecked")
public class TernaryHeap<K extends Comparable<K>, V> extends BinaryHeap<K, V> {

    // default capacity
	private static final int CAPACITY = 1032;
	// the underlying array which will hold all of the values
	protected Pair<K,V>[] backArray;
	// tracks the size of the backing array
    private int size;

    /**
	 * Constructor for TernaryryHeap where if there is no specified size,
	 * then the default capacity is used. 
	 */
    public TernaryHeap(){
        this(CAPACITY);
    }

    /**
	 * Constructor for TernaryHeap where the capacity given is set 
	 * as the size of the backArray 
	 */
    public TernaryHeap(int capacity) {
        backArray = new Pair[capacity];
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
    public boolean offer(K key, V value) {
		// check if the heap is full or not
		if (size >= (backArray.length - 1))
			return false;
		// set the new value to be at the end of the heap	
		int loc = size++;
		backArray[loc] = new Pair<K, V>(key, value);
		// find the value of the parent
		int upp = (loc - 1) / 3;
		while (loc != 0) {
			/** If the value of the newly added pair is greater than its parent,
			 * 	then swap the two. Otherwise, stop. */
			if (0 > backArray[loc].compareTo(backArray[upp])) {
				Pair<K, V> tmp = backArray[upp];
				backArray[upp] = backArray[loc];
				backArray[loc] = tmp;
				loc = upp;
				upp = (loc - 1) / 3;
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
	private void removeTop() {
		// decrease the size of the heap
		size--;
		// replace the root with the element at the bottom of the heap
		backArray[0] = backArray[size];
		backArray[size]=null;
		// start restoring heapness from the new root
		int parentLoc = 0; 
		while (true) {
	    	int bestChildLoc;   
	    	int childLoc1 = parentLoc * 3 + 1; 
	    	int childLoc2 = parentLoc * 3 + 2;
            int childLoc3 = parentLoc * 3 + 3;

			// if there are no children, then stop 
	    	if (childLoc1 >= size) 
				break;
			// if there is only one child on a row, then set bestChildLoc to that child         
	    	if (childLoc2 >= size) {
				bestChildLoc = childLoc1; 
	    	}
			// if there are only two childern in a row, compare between only those two
			if(childLoc3 >= size) {
				if(backArray[childLoc1] != null && backArray[childLoc2] != null){
					int cmp = backArray[childLoc1].compareTo(backArray[childLoc2]);
					if (cmp<=0)
		    			bestChildLoc = childLoc1;
					else
		    			bestChildLoc = childLoc2;
				}
				// if only one is not null, check to see which one is not null
				else if(backArray[childLoc1] != null)
					bestChildLoc = childLoc1;
				else	
					bestChildLoc = childLoc2;
			}
			// otherwise, check to see which child is smaller, and set bestChildLoc to that
	    	else {
				int cmp = backArray[childLoc1].compareTo(backArray[childLoc2]);
				if (cmp <= 0){
					// second comparison with Child1 and Child3 (child1<child2)
                    int cmp2 = backArray[childLoc1].compareTo(backArray[childLoc3]);
                    if(cmp2 <= 0)
		    		    bestChildLoc = childLoc1;
                    else 
                        bestChildLoc = childLoc3;
                }
				else{
					// second comparision with Child2 and Child3 (child 2<child1)
                    int cmp3 = backArray[childLoc2].compareTo(backArray[childLoc3]);
                    if(cmp3 <= 0)
                        bestChildLoc = childLoc2;
                    else
                        bestChildLoc = childLoc3;
                }
			}
			/** if the smallest child is smaller than its parent, then repeat the restoration 
			 * of heapness using that child's location as the parent */
	    	if (0 > backArray[bestChildLoc].compareTo(backArray[parentLoc])) {
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

    @Override
    public boolean isEmpty() {
		return size == 0;
    }
	
    public static void main(String[] args){
        TernaryHeap<Integer, String> pq = new TernaryHeap<>(CAPACITY);
		// test a short phrase with a set of priority keys
        pq.offer(1, "Jane");
        pq.offer(10, "WET");
        pq.offer(5, "WAS");
        pq.offer(7, "VERY");
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());

		// check with the alphabet if it polls it out correctly
		TernaryHeap<Integer, Character> pqAlphabet = new TernaryHeap<>(CAPACITY);
		int charVal = 97;
		for(int i = 0; i < 26; i++){
			pqAlphabet.offer(i, (char) charVal);
			charVal++;
		}
		while(pqAlphabet.peek() != null){
			System.out.print(pqAlphabet.poll());
		}
		// new line for formatting
		System.out.println();

		// check with the alphabet with same priority keys, but inputed in a different order 
		TernaryHeap<Integer, Character> pqc = new TernaryHeap<>(CAPACITY);
		for(int i = 0; i <26; i++){
			int r = (i + 13) % 26;
			pqc.offer(r, (char) (65 + r));
			System.out.println("key = " + r + ", val = " + ((char)(65 + r)));
		}
		while(pqc.peek() != null){
			System.out.print(pqc.poll());
		}
		// new line for formatting
		System.out.println();
    }
}