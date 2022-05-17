package BookstoreApp;

public class SetCustomer {
    private Customer instance;
    
    public SetCustomer(String username, String password){ // Constructor to set up the Customer with zero points
        instance = new SilverCustomer (username, password);
    }
    
    public SetCustomer(String username, String password, int points){ // Constructor to set up the Customer with some points
        instance = new SilverCustomer (username, password, points);
    }
    
    /*
     *   When run the method insert() checks the points a customer has and changes its state to
     *   Gold if they have more than 1000 or Silver otherwise
     */
    public void insert (){ 
        if (instance.getPoints() > 1000){
            instance = new GoldCustomer (instance.getUsername(), instance.getPassword(), instance.getPoints());
        }
        else{
            instance = new SilverCustomer (instance.getUsername(), instance.getPassword(), instance.getPoints());
        }
    }
    
    public Customer getInstance (){ // Getter for the current Customer
        return instance;
    }
    
    public String getUsername (){ // These three getters are needed so the username, password and points can be showed in the ownerCustomerScreen.
        return instance.getUsername();
    }
    
    public String getPassword (){
        return instance.getPassword();
    }
    
    public int getPoints (){
        return instance.getPoints();
    }
}
