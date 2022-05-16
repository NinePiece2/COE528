package lab3;
import java.util.ArrayList; 

/**
 *
 * @author Romit Sagu
 */

public class QueueOfDistinctStrings {
    // Overview: QueueOfDistinctStrings are mutable, bounded
    // collection of distinct strings that operate in
    // FIFO (First-In-First-Out) order.
    //
    // The abstraction function is:
    // a) Write the abstraction function here
    // AF(c) = an abstract QueueOfDistinctStrings d     (where c is a Java QueueOfDistinctStrings Object)
    //         where c.items.get(0) is the front of the queue and c.items.get(c.items.size() - 1) is the end of the queue
    //         Finally c.items is an arrayList representation of the collection of strings in the queue.
    //
    // The rep invariant is:
    // b) Write the rep invariant here
    // RI(c) = false if c.items.get(i).equals(c.items.get(n)) where n is all indexes of the arrayList that are not i. ie. {"ab", "ab", "cd"} or {"ae", "dd", "ae"}
    //       = ture otherwise. ie {"a", "b", "c"} or {"ae", "de", "sd"}
    //
    //
    //the rep
    private ArrayList<String> items;
    // constructor
    public QueueOfDistinctStrings () {
        // EFFECTS: Creates a new QueueOfDistinctStrings object
        items = new ArrayList<String>();
    }
    
    
    // MODIFIES: this
    // EFFECTS: Appends the element at the end of the queue
    // if the element is not in the queue, otherwise
    // does nothing.
    public void enqueue(String element) throws Exception {
        if(element == null) 
            throw new Exception();
        if(false == items.contains(element))
            items.add(element);
    }
    public String dequeue() throws Exception {
        // MODIFIES: this
        // EFFECTS: Removes an element from the front of the queue
        if (items.size() == 0) throw new Exception();
            return items.remove(0);
        }
    public boolean repOK() {
        // EFFECTS: Returns true if the rep invariant holds for this
        // object; otherwise returns false
        // c) Write the code for the repOK() here
        for (int i = 0; i < this.items.size(); i++){
            for (int j = 0; j < this.items.size(); j++){
                if (this.items.get(i).equals(this.items.get(j)) && i != j)
                    return false;
            }
        }
        return true;
    
    
    }
    public String toString() {
        // EFFECTS: Returns a string that contains the strings in the
        // queue, the front element and the end element.
        // Implements the abstraction function.
        // d) Write the code for the toString() here
        
        String s = "";
        for (int i = 0; i < this.items.size() - 1; i++){
            s += this.items.get(i) + ", ";
        }
        s += this.items.get(this.items.size()-1);
        
        return "The collection is {" + s + "}. The front element is " + this.items.get(0) + ". The end element is " + this.items.get(this.items.size() - 1);
    }
    
    
    // Used for testing
    /*public static void main (String[] args){
        QueueOfDistinctStrings instance = new QueueOfDistinctStrings();
        try{
        instance.enqueue("AA");
        instance.enqueue("BB");
        instance.enqueue("CC");
        instance.dequeue();
        }
        catch (Exception e){
            System.out.println(e);
        }
        instance.items.add("BB");
        System.out.println(instance.repOK());
        System.out.println(instance);
        

    }*/
}