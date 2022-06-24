import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * 
 * @author ayang
 * Created: March 6, 2021
 * Modified: March 14, 2021
 */

public class BookReader {
    private String fileName;
    private Map<String, Integer> wordFreq = new HashMap<>();
    private String topWord1 = null;
    private String topWord2 = null;
    private String topWord3 = null;

    /**
     * Constructor for class BookReader. 
     * Takes a filename as an argument. 
     * @param fileName
     */
    public BookReader(String fileName) {
        this.fileName = fileName;          
    }

    /**
     * Reads and returns each word and stores how many times
     * each word occurs.
     * Notes: used code from lec07 slide13
     * @return betterLine
     */
    public String nextWords(BufferedReader br) throws FileNotFoundException, IOException, NullPointerException {
            String line = br.readLine();
            if (line != null){
                // if there is a paragraph break, then go to the next line
                if(line.isEmpty())
                    br.readLine();
                // clean the punctuation and convert to lowercase
                String betterLine = line.trim().toLowerCase().replaceAll("[\\.\\'?!,\\\"]", "");
                // file and count each instance of words
                String[] partsOfLine = betterLine.split(" ");

                for(int i = 0; i < partsOfLine.length; i++) { 
                    if(partsOfLine[i].length() > 0){               
                        int wordCount = 0;
                        // if that word isn't logged yet, log it
                        if (wordFreq.containsKey(partsOfLine[i]))
                            wordCount = wordFreq.get(partsOfLine[i]);
                        // increase the word's frequency by 1
                        wordFreq.put(partsOfLine[i], wordCount + 1);
                    }
                }
                return betterLine; 
            }
            else
                return null;
    }


    /**
     * returns the number of instances for a particular word
     * @param word
     * @return number of instances
     */
    public int getWordCount(String word) {
        return wordFreq.get(word);
    }


    /** 
     * print out all key and value pairs 
     * in wordFreq.  
     */
    public void allWords(){ 
        for(String keys : wordFreq.keySet()){
            System.out.println(keys + ": " + wordFreq.get(keys));
        }
    }

    /**
     * Find and return the top three most frequent words.
     */
    public String toString(){
        int twCount1 = 0;
        int twCount2 = 0;
        int twCount3 = 0;

        for(String keys : wordFreq.keySet()) {
            if(wordFreq.get(keys) > twCount1) {
                topWord1 = keys; 
                twCount1 = wordFreq.get(keys);
            }
        }  

        for(String keys : wordFreq.keySet()){
            if(wordFreq.get(keys) > twCount2 && wordFreq.get(keys) < twCount1){
                topWord2 = keys;
                twCount2 = wordFreq.get(keys);
            }
        }

        for(String keys : wordFreq.keySet()){
            if(wordFreq.get(keys) > twCount3 && wordFreq.get(keys) < twCount2){
                topWord3 = keys;
                twCount3 = wordFreq.get(keys);
            }
        }

        return topWord1 + " " + wordFreq.get(topWord1) + "\n" 
                + topWord2 + " " + wordFreq.get(topWord2) + "\n"
                + topWord3 + " " + wordFreq.get(topWord3);
    }

    public static void main(String[] args){
        BookReader bookReader = new BookReader("ham.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(bookReader.fileName));) {
                while (true){
                    String line = bookReader.nextWords(br);
                    if (line == null)
                        break;
                    // System.out.println(line);
                } 
                // bookReader.allWords();
                System.out.println(bookReader);
            } catch(FileNotFoundException fnf){
                System.err.println("File Not Found " + fnf);
            } catch(IOException ioe){
                System.err.println("Reading Problem " + ioe);
            } catch(NullPointerException e) {
                System.err.println("Caught NullPointerException ");
            }
        }
    }
    
