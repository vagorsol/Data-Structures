import java.util.Scanner;

/**
 * Simulates undo in an editor using Stacks.
 * 
 * @author ayang
 * Created: March 31, 2021
 */
public class UndoEditor {
    private ArrayStackHW5<Character> input = new ArrayStackHW5<>();

    public UndoEditor() {

    }

    /**
     * Takes in a character. If it is 1, "undo" the 
     * last character. Otherwise, check if it is valid,
     * and then add the character to the stack if it is.
     * 
     * @param c
     */
    public void updateEditor(char c){
        // if the character is 1, "undo"
        if(c == '1'){
            input.pop();
        }
        // otherwise, check if it's valid 
        else if(validCase(c)){
            // if the character is valid, add it to the stack
            input.push(c);
        }
        else
            return;
    }

    /**
     * Checks to see if the input is one of the following "valid"
     * character types:
     * uppercase character, lowercase character, or underscore
     * 
     * @param character
     * @return true if "valid," false if not
     */
    private boolean validCase(char c) {
        // if the character is uppercase
        if(c >= 'A' && c <= 'Z'){
            return true;
        }
        // if the character is lowercase
        if (c >= 'a' && c <= 'z'){
            return true; 
        }
        if(c == '_'){
            return true; 
        }
        return false; 
    }


    public String toString() {
        StringBuffer ret = new StringBuffer();
        int stackSize = input.size();
        for (int i = 0; i < stackSize; i++){
            ret.append(input.pop());
        }
        return ret.toString(); 
    }

    public static void main(String[] args){
        try (Scanner scanner = new Scanner(System.in);){
            UndoEditor text = new UndoEditor();
            boolean looping = true;
            do{
                String line = scanner.nextLine();
                for(int i = 0; i < line.length(); i++){
                    if (line.charAt(i) == '9'){
                        System.out.println(text);
                        looping = false; 
                        break; 
                    }
                    else {
                        text.updateEditor(line.charAt(i));
                    }
                }
            } while(looping);
        }
    } // main()
}
