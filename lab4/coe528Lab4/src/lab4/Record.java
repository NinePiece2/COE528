package lab4;
import java.util.Scanner; 
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Romit Sagu
 */
public class Record {
    // Name of the associated file
    private String filename;
    private static Record instance;
    
    
    private Record(String n) { // Constructor
        filename = n;
    }
    
    public static Record getInstance(){ // Gives the instance of the class and if there isn't one, one is created
        if (instance == null)
            instance = new Record("record.txt");
        return instance;
    }
    
    public String getFileName(){ // Getter for the filename
        return filename;
    } 
    
    // Effects: Reads and prints the contents of the associated
    // file to the standard output.
    public void read() {
        try {
            int i = 0;
            FileReader temp = new FileReader(filename);
            Scanner temp1 = new Scanner (temp);
            while (temp1.hasNextLine()){
                System.out.println(temp1.nextLine());
            }
            temp.close();
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    // Effects: Appends the specified message, msg, to the
    // associated file.
    public void write(String msg) {
        try {
            // Write the code here
            FileWriter temp = new FileWriter(filename, true);
            temp.write(msg);
            temp.close();
            
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    
    
    public static void main(String[] args) {
        // Fill the blank below that obtains the sole instance
        // of the Record class.
        // (You should not invoke the Record constructor here.)
        Record r = getInstance();
        
        // Do not modify the code below
        r.write("Hello-1\n");
        r.write("Hello-2\n");
        
        System.out.println("Currently the file record.txt " +
                           "contains the following lines:");
        r.read();
    }
}