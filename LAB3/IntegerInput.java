import java.util.Scanner;

public class IntegerInput {
    public static void main(String[] args) {
        IntegerInput ui = new IntegerInput();
        ui.doUI();
    } // main()

    public IntegerInput() {
    } // IntegerInput()

    // takes integers from user input
    public void doUI() {
        int total = 0;
        int loopCount = 0;
        Scanner ss = new Scanner(System.in);
        while (total < 100 && loopCount < 10) {
            System.out.print("Enter an integer (or Exit to quit)");
            String ll = ss.nextLine();
            if ("Exit".equals(ll))
                break;
            try {
                int ii = Integer.parseInt(ll);
                total += ii;
            } 
            // if user attempts to input not an integer
            catch (NumberFormatException ee) {
                System.err.println(ll + " is not an integer");
            }
            loopCount++;
        }
    } // doUI()
} // class IntegerInput()