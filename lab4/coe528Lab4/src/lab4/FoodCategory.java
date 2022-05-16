package lab4;
import java.util.ArrayList;

/**
 *
 * @author Romit Sagu
 */
public class FoodCategory extends FoodComponent{
    private String name; // Instance Variables
    private double total;
    
    private ArrayList <FoodComponent> components; // Instance Collection
    
    public FoodCategory(String name){ // Constructor
        this.name = name;
        components = new ArrayList <FoodComponent>();
    }
    
    public String getName(){ // Gettr for the name of the catagory
        return this.name;
    }
    
    public void add (FoodComponent comp){ // Adds and removes the provided FoodComponent from the collection
        components.add(comp);
    }
    
    public void remove (FoodComponent comp){
        components.remove(comp);
    }
    
    @Override
    public double getPrice(){ // Retuns the total price of the Catagory
        total = 0;
        for(int i = 0; i < components.size(); i++){
            total += components.get(i).getPrice();
        }
        return this.total;
    }
    
    @Override
    public void print(int level){ // Returns the name of the food catagory, its total price and the FoodComponent in the collection with the proper formatting
        for (int i = 0; i < level; i++){
            System.out.print("\t");
        }
        System.out.print("FoodCategory (" + getName() + ", " + getPrice() + ") contains:\n");
        
        for (int j = 0; j < components.size(); j++){
            components.get(j).print(level + 1);
        }
    }
}
