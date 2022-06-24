/**
 * An implementation of a stack using an underlying array
 * 
 * @param <K> Generic
 * @author gtowell
 * @modified ayang 
 * Created: Feb 14, 2020
 * Modified: Oct 6, 2020
 * Modified: March 28, 2021
 */
public class ArrayStackHW5<K> implements StackIntf<K> {
    /** The default capacity of the stack */
    private static final int DEFAULT_CAPACITY = 50;
    /** The number of items in the stack */
    private int size;
    /** The array used to hold the stack items */
    private K[] underlyingArray;

    /**
     * Create a stack with the default capacity
     */
    public ArrayStackHW5() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Create a stack with the given capacity
     * 
     * @param capacity the max number items the stack can hold.
     */
    @SuppressWarnings("unchecked")
    public ArrayStackHW5(int capacity) {
        size = 0;
        underlyingArray = (K[]) new Object[capacity];
    }

    /**
     * Tests if this stack is empty.
     * 
     * @return true if and only if this stack contains no items; false otherwise.
     */
    @Override
    public boolean empty() {
        return size == 0;
    }

    /**
     * Pushes an item onto the top of this stack. Returns null if the element could
     * not be added.
     * 
     * @param e the element to be added to the stack
     * @return the added element.
     */
    @Override
    public K push(K e) {
        // System.out.println(underlyingArray.length);
        if (size < underlyingArray.length) {
            underlyingArray[size] = e;
            size++;
            return e; 
        }
        return null;
    }

    /**
     * Looks at the object at the top of this stack without removing it from the
     * stack.
     * 
     * @return the item at the top of the stack
     */
    @Override
    public K peek() {
        if (size - 1 <= 0){
            return null;
        }
        return underlyingArray[size - 1]; 
    }

    /**
     * Removes the object at the top of this stack and returns that object as the
     * value of this function.
     * 
     * @return The removed object
     */
    @Override
    public K pop() {
        if (size < 1)
            return null;
        K ret = underlyingArray[size - 1];
        // underlyingArray[size] = null;
        size--;
        return ret;
    }

    /**
     * Gives the number of items in the stack
     * 
     * @return The number of items in the stack.
     */
    @Override
    public int size() {
        return size;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{ ");
        for (int i = 0; i < size; i++) {
            sb.append(underlyingArray[i]);
            sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Illustrate stack operations and usage. 
     * 
     * @param args
     */
    public static void main(String[] args) {
        ArrayStackHW5<Integer> stack = new ArrayStackHW5<>();
        stack.push(5);
        System.out.println("Push 5: " + stack);   // {5}
        stack.push(3);
        System.out.println("Push 3: " + stack);
        System.out.println("Size: " + stack.size()); // {5,3}
        System.out.println("Pop:" + stack.pop() + " " + stack); // {5}
        System.out.println(stack.empty()); // false
        System.out.println("Pop:" + stack.pop() + " " + stack); //{}
        System.out.println(stack.empty()); // true

        for (int i = 0; i< 50; i ++){
            stack.push(i);
            System.out.println("Push: " + stack.peek() + " " + i);
        }

        for (int i = 0; i< 50; i ++){
            stack.pop();
            System.out.println("Push: " + stack.peek() + " " + i);
        }
    }
}