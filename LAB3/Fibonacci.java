/* 
   * Desc:
   *    This program calculates the Fibonacci sequence 
   *    and the golden mean for each set.
   * @author gtowell
   * @modified ayang
   * Modified: February 11, 2021
   * Modified: February 16, 2021
*/

public class Fibonacci {
    public static void main(String[] args){
        int n_2 = 1; 
        int n_1 = 1;
        double goldenMean = 1.0;
        System.out.println(n_1 + " " + n_2 + " " + goldenMean);
        for (int i = 1; i < 20; i++) {
            int nI = n_2 + n_1; // finds the next number
            n_1 = n_2; 
            n_2 = nI;
            goldenMean = (double) n_2 / n_1; // calculates the golden mean
            System.out.println(n_1 + " " + n_2 + " " + goldenMean);
        }
    } // main()
} // class Fibonacci