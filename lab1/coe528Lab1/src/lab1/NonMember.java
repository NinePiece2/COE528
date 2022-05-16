package lab1;

/**
 *
 * @author Romit Sagu
 */
public class NonMember extends Passenger{
    public NonMember(String name, int age){ // This constructor passes the name and age to the parent class
        super(name, age);
    }
    
    
    // This method overrides the applyDiscount method in the parent class and applies a 10% discount
    // if the passenger is over 65 years old.
    @Override
    public double applyDiscount(double p){
        if (this.age > 65){
            return p*.9;
        }
        
        return p;
        
    }
}
