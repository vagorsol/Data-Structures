/**
 * An implementation of QuickSort that utlilitizes Hybrid Quicksort
 * 
 * @author ayang
 * Created: April 22, 2021
 * Modified: April 25, 2021
 * Modified: April 27, 2021
 * Modified: April 29, 2021
 */
public class QuickSort extends SortBase {
    // the size at which to switch from recursion to insert sort
    private static final int partMIN = 30;

    /**
     * Do Quick sort, in place, on the provided array.
     * This changes the order of items in the provided array 
     * 
     * @param array the array to be sorted
     * @param beginIndex start of part of array to be sorted
     * @param endIndex end of part of the array to be sorted
     */
    private void quickSort(int[] array, int beginIndex, int endIndex){
        // if no more elements in the given array can be sorted, return.
        if((endIndex - beginIndex) < 1)
            return;
        // find the index where the previous sort ended (or begin if it's the first iteration)
        int partIndex = partition(array, beginIndex, endIndex);

        //if the length of the part to sort is greater than partMIN, do quicksort
        if((partIndex - 1 - beginIndex) > partMIN){
            // do two new sorts on the less than and greater than groups from the previous sort
            quickSort(array, beginIndex, partIndex - 1);
            quickSort(array, partIndex + 1, endIndex);
        }
        // otherwise (if it's less than or equal to) do insert sort on the rest
        else{
            insertSort(array, beginIndex, partIndex - 1);
            insertSort(array, partIndex + 1, endIndex);
        }
        
    }

    /**
     * Within a given section of an array, sort all elements into three groups
     * (less than, equal to, greater than) based on the element at endIndex.
     * 
     * @param array
     * @param beginIndex
     * @param endIndex
     * @return loc where endIndex finishes at
     */
    private int partition(int[] array, int beginIndex, int endIndex){
        int elem = array[endIndex];
        int loc = beginIndex;

        for(int i = beginIndex; i < endIndex; i++){
            /** if it's smallder than endIndex, move it to the "lesser than elem"
             * section at the front of the array.
            */
            if(array[i] < elem){
                // swap elements at i and loc
                if(array[i] < array[loc]){
                    int temp = array[loc];
                    array[loc] = array[i];
                    array[i] = temp;
                }
                // loc + 1 so the next elem less than endIndex is after the previous elem (i)
                loc++;
            }
        }
        /** all unmoved elements are greater than elem, so swap the elements at 
         * loc and endIndex so that the elements greater than endIndex are after it. 
         */
        int temp = array[loc];
        array[loc] = array[endIndex];
        array[endIndex] = temp;
        return loc;
    }

    /**
     * Do insertion sort, in place, on the provided array.
     * This changes the order of items in the provided array 
     * 
     * @param array
     * @param beginIndex
     * @param endIndex
     */
    private void insertSort(int[] array, int beginIndex, int endIndex){
        for(int loc = beginIndex + 1; loc <= endIndex; loc++){
            // for all elements of the given segment, compare the elem at loc to all other elems
            int p = beginIndex;
            while(array[p] < array[loc] && p < loc){
                // keep moving along while the element at p is less than the element at loc 
                p++;
            }
            int temp = array[loc];
            // move the elements over to move p into where loc is
            for(int j = loc; j > p; j--){
                array[j] = array[j - 1];
            }
            // insert the original element at loc at p 
            array[p] = temp;
        }
    }

    /**
     * Receives an array of integers and sorts that array, putting the contents of the array
     * into sorted order. Here, it does so by calling quickSort, with beginIndex and endIndex
     * as the beginning and ending of the array respectively. 
     * 
     * @param array the array to be sorted
     */
    @Override
    public void sortInPlace(int[] array){
        quickSort(array, 0, array.length - 1);
        // insertSort(array, 0, array.length - 1);
    }

   public static void main(String[] args){
        // create an array with a given amount of random integers to be quickSort'ed
        QuickSort ins = new QuickSort();
        // int[] arr = {6, 3, 4, 2, 1};
        // int [] arr = {14, 6, 18, 2, 13, 7, 8, 9, 3, 17, 5, 10, 11, 12, 16, 0, 2 ,5};
        // int[] arr = {5,7,9,2,4,1,3}; 
        int[] arr = ins.fyShuffle(100);
        System.out.println(ins.stringArray(arr));

        // begin timing
        long nntime = System.nanoTime();
        // do and finish the quick sort
        ins.sortInPlace(arr);
        // stop timing
        long eetime = System.nanoTime();

        // print out the result of the quick sort (the sorted array)
        System.out.println(ins.stringArray(arr));
        // print out the total length of time sorting took
        System.out.println((eetime-nntime)/100000.0);
    }
}