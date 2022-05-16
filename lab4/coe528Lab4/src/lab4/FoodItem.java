package lab4;

/**
 *
 * @author Romit Sagu
 */
public class FoodItem extends FoodComponent{
    private String name; // Instance Variables
    private double price;
    
    public FoodItem(String name, double price){ // Constructor
        this.name = name;
        this.price = price;
    }
    
    public String getName(){ // Getters for the name of the FoodItem and the price of the FoodItem
        return this.name;
    }
    
    @Override
    public double getPrice(){
        return this.price;
    }
    
    @Override
    public void print(int level){ // Prints the name of the FoodItem and its price with the propper formatting
        for (int i = 0; i < level; i++){
            System.out.print("\t");
        }
        System.out.print("FoodItem: " + getName() + ", " + getPrice() + "\n");
    }
}
