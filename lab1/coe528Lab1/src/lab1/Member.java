package lab1;

/**
 *
 * @author Romit Sagu
 */
public class Member extends Passenger {
    int yearsOfMembership; // Instance variable intilization
    
    public Member(String name, int age, int yearsOfMembership){ // This constructor passes the name and age to the 
        super(name, age);                                       // parent class and sets the instace variable
        this.yearsOfMembership = yearsOfMembership;
        
    }
    
    // This method overrides the applyDiscount method from the parent class and modifies the price to have
    // a 50% discount if they've been a member for more than 5 years, 10% if they've been a member for more
    // than 1 year but less than or equal to 5 years.
    @Override
    public double applyDiscount(double p){
        if (yearsOfMembership > 5){
            return p*.5;
        }
        else if (yearsOfMembership > 1 && yearsOfMembership<= 5){
            return p*.9;
        }
        else{
            return p;
        }
    }
}
