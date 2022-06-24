/**
 * An implementation of (doubly) Linked Lists. Uses the code from class.
 * 
 * @author ayang
 * Created: May 1, 2021
 * Modified: May 7, 2021
 */
public class LinkedLists <T extends Comparable<T>>{
    
    /**
     * The node class. Each element in the linked list is an instance of Node
     */
    protected class Node<E extends Comparable<T>>{
        // the data item in the node
        public Comparable<E> data;
        // the next item in the linked list
        public Node<T> next;
        // the previous item in the linked list
        public Node<T> prev;
        // Node constructor. takes a data item and the preceding and trailing nodes
        public Node(Comparable<E> data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    // the start/first element of the linked list
    private Node<T> head = null;
    // the end/last element of the linked list
    private Node<T> tail = null;
    // the number of items in the linked list
    private int size = 0;
    
    /**
     * The number of items in the linked list
     * @return the number of items in teh linked list
     */
    public int size(){
        return size; 
    }
    
    /**
     * Returns true iff the linked list has no elements
     * @return true iff the linked list has no elements
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Get the first element in the linked list
     * @return the first element in the linked list, or null if the list is empty
     */
    public Comparable<T> first(){
        if(head == null)
            return null;
        return head.data;
    }

    /**
     * Get the last element in the linked list
     * @return the last element in the linked list, or null if the list is empty 
     */
    public Comparable<T> last(){
        if(head == null)
            return null;
        return tail.data;
    }

    /**
     * Add an element at the front of the list
     * @param c the element to be added at the front of the list
     */
    public void addFirst(Comparable<T> c){
        Node<T> newNode = new Node<>(c);
        if(head == null){
            head = newNode;
            tail = newNode;
            size = 1;
            return;
        }
        // have the head's prev pointer point to the new node, and the new node's next point to the head
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        size++;
    }

    /**
     * Add an element at the end of the list
     * @param c the element to be added at the end of the list
     */
    public void addLast(Comparable<T> c){
        Node<T> newNode = new Node<>(c);
        // if the list is empty, set the new node to be the head and tail
        if(head == null){
            head = newNode;
            tail = newNode;
            size = 1;
            return;
        }
        // have the tail's next pointer point to the new node, and the new node's prev to point to the tail
        newNode.prev = tail; 
        tail.next = newNode;
        // set the new node to be the tail
        tail = newNode;
        size++;
    }

    /** 
     * Remove the first item from the list
     * @return the first item, or null if the list is empty
    */
    public Comparable<T> removeFirst(){
        if(head == null)
            return null;
        Comparable<T> ret = head.data;
        // set head to be the next item in the linked list
        head = head.next;
        if(head == null)
            tail = null;
        else
            head.prev = null;
        size--;
        return ret; 
    }

    /**
     * Remove the last item from the list
     * 
     * @return the last item, or null if the list is empty
     */
    @SuppressWarnings("unchecked")
    public Comparable<T> removeLast(){
        if(head == null)
            return null;
        Comparable<T> ret = tail.data;
        // if there is only one element in the linked list, set both tail and head to null
        if(head == tail) {
            head = null; 
            tail = null;
            size = 0;
            return (T) ret;
        }
        tail = tail.prev;
        tail.next = null;
        size--;
        return ret;
    }

    /**
     * Remove the specified element from the list
     * 
     * @param r the item to be removed
     * @return the removed item, or null if the item was not on the list
     */
    public Comparable<T> remove(Comparable<T> r){
        Node<T> curr = find(r); 
        if (curr == null)
            return null;
        size--;
        // if curr isn't the head, set the item before curr to point to the item after curr
        if(curr.prev != null)
            curr.prev.next = curr.next;
        // if curr isn't the tail, set the item after curr to point to the item before curr
        if(curr.next != null)
            curr.next.prev = curr.prev;
        // if curr is the tail, set the new tail to be the element after curr
        if(curr == head)
            head = curr.next;
        // if curr is the tail, set the new tail to be the element previous to curr
        if(curr == tail)
            tail = curr.prev;
        return r;
    }

    /**
     * Given an index and data, update the data of the item at that index so
     * it is the given data
     * @param index
     * @param c data to update
     * @return updated data
     */
    public Comparable<T> set(int index, Comparable<T> c){
        Node<T> n = search(index);
        n.data = c;
        return n.data;
    }

    /**
     * Given an index, find the item at that location
     * @param index
     * @return data at that index
     */
    public Comparable<T> get(int index){
        Node<T> n = search(index);
        return n.data;
    }


    /**
     * Given an index, go through the Linked List and find the item at that position.
     * @param index
     * @return node at that index
     */
    private Node<T> search(int index){
        if(index > size || size == 0)
            return null;
        if(index == 0)
            return head;
        if(index == size - 1)
            return tail;
        Node<T> n = head;
        for(int i = 0; i < index; i++){
            n = n.next;
        }
        return n.prev;
    }

    /**
     * Searches through the linked list for a given element and returns 
     * the node containing the element if that element is in the linked list

     * @param look4
     * @return the node containing look4, or null if look4 is not in the linked list
     */
    @SuppressWarnings("unchecked")
    private Node<T> find(Comparable<T> look4){
        Node<T> curr = head;
        while (curr != null){
            if(0 == curr.data.compareTo((T) look4)){
                break;
            }
            curr = curr.next;
        }
        return curr; 
    }
    
    /**
     * Checks to see if a given element is in the linked list 
     * @param iD
     * @return true iff iD is in the linked list, false otherwise
     */
    public boolean contains(Comparable<T> iD){
        Node<T> nod = find(iD);
        return (nod != null);
    }

    /**
     * Converts the content of the linked list into a format that can be printed out
     * @return a list of the elements in the linked list
     */
    public String toString(){
        StringBuffer sb = new StringBuffer();
        for(Node<T> n = head; n != null; n = n.next){
            sb.append(n.data.toString());
            // this line is literally just for personal aesthetic preference
            if(n.next != null)
                sb.append("\n");
        }
        return sb.toString();
    } 
    public static void main(String[] args){
        LinkedLists<Integer> ll = new LinkedLists<>();
        ll.addFirst(3);
        // ll.add(1, 7);
        ll.addLast(43);
        ll.addLast(200);
        ll.addLast(201);

        // ll.remove(43);
        // ll.removeLast();
        // ll.removeFirst();

        // System.out.println(ll.contains(5)?"YES":"NO");
        System.out.println(ll.toString());
    }


}