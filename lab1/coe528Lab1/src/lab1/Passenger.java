package lab1;

/**
 *
 * @author Romit Sagu
 */
public abstract class Passenger { // Instance variable intilization
    String name;
    int age;
    
    public Passenger(String name, int age){ // The constructor assigning the instace variables 
        this.name = name;
        this.age = age;
    }
    
    public abstract double applyDiscount(double p); // This method is needed in both child classes and is needed to appy a discount in the manager class
}
