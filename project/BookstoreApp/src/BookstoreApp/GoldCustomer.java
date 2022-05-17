package BookstoreApp;


public class GoldCustomer extends Customer {
    
    public GoldCustomer (String username, String password){ // Custructor for creating a customer with zero points
        super(username, password);
    }
    
    public GoldCustomer (String username, String password, int points){ // Custructor for creating a customer with some points
        super(username, password, points);
    }
    
    @Override // Overrides getStatus to be the current status of the customer 
    public String getStatus(){
        return "Gold";
    }
}
