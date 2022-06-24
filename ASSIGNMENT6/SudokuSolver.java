import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A program that uses recursion to solve Sudoku puzzles
 * 
 * @author ayang
 * Created: April 4, 2021
 * Modified: April 9, 2021
 * Modified: April 10, 2021
 * Modified: April 11, 2021
 */
public class SudokuSolver{
    // the original puzzle
    private Integer[][] puzzle = new Integer[9][9];
    // a copy of the original that the program uses instead of the original
    private Integer[][] tempPuzzle = new Integer[9][9];
    
    /**
     * A constructor of SudokuSolver that 
     * reads the given puzzle, and then stores it.
     * 
     * @param fileName
     */
    public SudokuSolver(String fileName) throws FileNotFoundException, IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(fileName));){
            int row = 0;
            while(br.ready()){
                String line = br.readLine();
                String[] partsOfLine = line.split(",");
                for(int i = 0; i < partsOfLine.length; i++){
                    puzzle[row][i] = Integer.parseInt(partsOfLine[i]);
                    tempPuzzle[row][i] = Integer.parseInt(partsOfLine[i]);
                }
                row++;
            }  
        }
    }
    
    /**
     * Uses recursion to figure out which number to put in 
     * each location.
     * 
     * @param puzzle
     * @param xloc
     * @param yloc
     * @return completed puzzle
     */
    public Integer[][] solve(Integer[][] p, int xloc, int yloc){
        if (isSolved(p)){
            tempPuzzle = p;
            return p;
        }
        if (!isSolvable(p))
            return null;
        if(yloc > 8){
            xloc++;
            yloc = 0;
        }
        if (xloc > 8){
            return null;
        }
        if (p[xloc][yloc] != 0){
            return solve(p, xloc, yloc + 1);
        }
        else {
            List<Integer> legalMoves = new ArrayList<>();
            legalMoves = legalMovesAt(p, xloc, yloc);

            Integer[][] newPuzzle = solveUtil(0, legalMoves, p, xloc, yloc);
            if(newPuzzle != null)
                return newPuzzle;
            /*for(int i =0; i < legalMoves.size(); i++){
                p[xloc][yloc] = legalMoves.get(i);
                Integer[][] newPuzzle = solve(copy(p), xloc, yloc + 1);
                if(newPuzzle != null)
                    return newPuzzle;
            }*/  
        }
        return null;
    }

    /**
     * The recursive utility for solve. For each legal move,
     * it creates a copy of the puzzle, and checks if it is 
     * correct.
     * 
     * @param i
     * @param legalMoves
     * @param p
     * @param xloc
     * @param yloc
     * @return newPuzzle
     */
    private Integer[][] solveUtil(int i, List<Integer> legalMoves, Integer[][] p, int xloc, int yloc){
        p[xloc][yloc] = legalMoves.get(i);
        Integer[][] newPuzzle = solve(copy(p), xloc, yloc + 1);
        if(newPuzzle != null)
            return newPuzzle;
        i++;
        if(i == legalMoves.size())
            return newPuzzle = null;
        return solveUtil(i, legalMoves, p, xloc, yloc);
    }
    
    /**
     * return true iff the puzzle is completely solved,
     * false otherwise
     * 
     * @param s
     * @return true iff puzzle is completely solved
     */
    private boolean isSolved(Integer[][] p){
        /*for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(p[i][j] == 0)
                    return false;
            }
        }
        return true;*/

        return isSolvedUtil(p, 0, 0);
    }

    /**
     * Recursive utility for isSolved. For every spot, 
     * checks if it is filled in. If not, then the puzzle is not 
     * completed.
     * 
     * @param p
     * @param xloc
     * @param yloc
     * @return true iff the puzzle is completely solved
     */
    private boolean isSolvedUtil(Integer[][] p, int xloc, int yloc){
        if(p[xloc][yloc] == 0)
            return false;
        yloc++;
        if(yloc > 8){
            yloc = 0;
            xloc++;
        } 
        if(xloc > 8)
            xloc = 0;
        // if nothing is zero, then you arrive at "0,0" and return true
        if(xloc == 0 && yloc == 0)
            return true;
        return isSolvedUtil(p, xloc, yloc);
    }

    /**
     * returns true iff the puzzle still might be solvable,
     * false otherwise
     * 
     * @param p
     * @return true iff the puzzle still might be solvable
     */
    private boolean isSolvable(Integer[][] p){
        /*for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(p[i][j] == 0){
                    if(legalMovesAt(p, i, j).size() == 0)
                        return false;
                }
            }
        }*/
        return isSolvableUtil(p, 0, 0);
    }

    /**
     * Recursive utility for isSolvable. For every spot, 
     * checks if it can be filled in. If not, then the puzzle is not 
     * solvable.
     * 
     * @param p
     * @param xloc
     * @param yloc
     * @return true iff the puzzle can be solved
     */
    private boolean isSolvableUtil(Integer[][] p, int xloc, int yloc){
        if(legalMovesAt(p, xloc, yloc).size() == 0)
            return false;
        yloc++;
        if(yloc > 8){
            yloc = 0;
            xloc++;
        } 
        if(xloc > 8)
            xloc = 0;
        // if nothing is zero, then you arrive at "0,0" and return true
        if(xloc == 0 && yloc == 0)
            return true;
        return isSolvableUtil(p, xloc, yloc);
    }

    /**
     * return a new instance of the puzzle that
     * is an exact copy of the provide puzzle.
     * 
     * @param puzzle (to be copied)
     * @return ret (copy of puzzle)
     */
    private Integer[][] copy(Integer[][] puzzle){
        Integer[][] ret = new Integer[9][9];
        ret = puzzle.clone();
        return ret;
    }

    /**
     * returns the list of numbers that can be 
     * legally put into the position given the 
     * board
     * 
     * @param p
     * @param xloc
     * @param yloc
     * @return list of all legal numbers
     */
    private List<Integer> legalMovesAt(Integer[][] p, int xloc, int yloc){
        List<Integer> legalMoves = new ArrayList<Integer>(); 
        /*for(int i = 1; i <= 9; i++){
            if(isLegal(i, xloc, yloc, p)){
                legalMoves.add(i);
            }
        }
        return legalMoves;*/
        return legalMovesAtUtil(p, 1, xloc, yloc, legalMoves);
    }

    /**
     * Recursive utility for legalMovesAt. Adds all legal
     * numbers to a list, and then returns it.
     * 
     * @param p
     * @param xloc
     * @param yloc
     * @return list of all legal numbers
     */
    private List<Integer> legalMovesAtUtil(Integer[][] p, int number, int xloc, int yloc, List<Integer> legalMoves){
        if(isLegal(number, xloc, yloc, p))
            legalMoves.add(number);
        number++;
        if(number == 10)
            return legalMoves;
        return legalMovesAtUtil(p, number, xloc, yloc, legalMoves);
    }

    /**
     * test if, in the puzzle, if the number is legal
     * 
     * @param number
     * @param row
     * @param column
     * @return true iff the number is legal
     */
    private boolean isLegal(int number, int row, int column, Integer[][] puzzle){
        // check if the number is legal for the row and column
        for(int i = 0; i < 9; i++){
            if((number == puzzle[row][i]) && (i != column))
                return false;
            if((number == puzzle[i][column]) && (i != row))
                return false;
        }
        
        // define the quadrant
        int rowUpper = (row/3) * 3;
        int rowLower = rowUpper + 2;
        int columnLeft = (column/3) * 3;
        int columnRight = columnLeft + 2; 

        // check if the number is legal for it's 3x3 section
        for(int i = rowUpper; i <= rowLower; i++){
            for(int j = columnLeft; j <= columnRight; j++){
                if((number == puzzle[i][j]) && (row != i) && (column != j)){
                    return false; 
                }  
            }
        }
        
        return true; 
    }

    /**
     * toString method for SudokuSolver that
     * will return the completed solution of 
     * the given puzzle.
     * 
     * @return completed puzzle
     */
    public String toString(){
        StringBuffer ret = new StringBuffer();
        /* 
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                ret.append(tempPuzzle[i][j]);
                if(j == 8){
                    ret.append("\n");
                }
                else{
                    ret.append(",");
                }
            }
        }
        return ret.toString(); */
        return toStringUtil(ret, 0, 0);
  
    }

    /**
     * Recursive utility for toString(). Adds
     * each element of the puzzle (and syntax) 
     * to a string, and then returns that string.
     * 
     * @param ret
     * @param xloc
     * @param yloc
     * @return completed puzzle
     */ 
    private String toStringUtil(StringBuffer ret, int xloc, int yloc){
        ret.append(tempPuzzle[xloc][yloc]);
        // add the syntax
        if(yloc == 8 && xloc != 8)
            ret.append("\n");
        if(yloc != 8) 
            ret.append(",");
        // check where you are, making ajdustments as needed
        yloc++;
        if(yloc > 8){
            yloc = 0;
            xloc++;
        } 
        if(xloc > 8)
            xloc = 0;
        // if nothing is zero, then you arrive at "0,0" and return true
        if(xloc == 0 && yloc == 0)  
            return ret.toString();
        return toStringUtil(ret, xloc, yloc);
    }

    public static void main(String[] args){
        // get the puzzle from the command line
        String[] defaultArgs = {"fileName"};
        String fileName = args[0];
        if (args[0].length() == 0) {
            args = defaultArgs;
        }

        SudokuSolver p;
        try {
            p = new SudokuSolver(fileName);
            p.solve(p.tempPuzzle, 0, 0);
            System.out.println(p);
        } catch (FileNotFoundException fnf){
            System.err.println("File Not Found" + fnf);
            return; 
        } catch (IOException e) {
            System.err.println("Reading problem" + e);
            return;
        } 
    } 
}