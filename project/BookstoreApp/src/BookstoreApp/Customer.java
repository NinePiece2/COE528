package BookstoreApp;

public abstract class Customer {
    
    final private String username; // Instance Variables
    final private String password;
    private int points;
    
    public abstract String getStatus();
    
    public Customer (String username, String password){ // Custructor for creating a customer with zero points
        this.username = username;
        this.password = password;
        this.points = 0;
    }
    
    public Customer (String username, String password, int points){ // Custructor for creating a customer with some points
        this.username = username;
        this.password = password;
        this.points = points;
    }
    
    /*
     * Checks if the username and password given match the username and password of the customer
     * @return true if they match, false otherwise
     */
    public boolean login(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }
    
    /*
     * Adds points based on the value of the books that were purchased
     * @throws an exception if the price is negative or zero
     */    
    public void buyBook(double price) throws Exception{
        if (price > 0){
            points += price*10;
        }
        else {
            throw new Exception("ERROR: Price Cannot be negative or zero");
        }
    }
    
    /*
     * Adds or deducts points based on the value of the books that were purchased
     * @throws an exception if the price is negative or zero
     * @return the price after discounts are added from points
     */  
    public double buyBookWithPoints(double price) throws Exception{ 
        double temp;
        if (price > 0){
            if (points <= price*100){ // If the users purchase is more than the value of the points they have
                temp = price - points/100  ; 
                points = (int)temp * 10;
            }
            else{ // If the user has more points than the value of their purchase
                points = points - (int)price*100;
                temp = 0;
            }
        }
        else{
            throw new Exception("ERROR: Price Cannot be negative or zero");
        }
        return temp;
    }
    
    public int getPoints(){ // Gets the points of a customer so it can be displayed to them
        return this.points;
    }
    
    public String getUsername(){ // These two getters are needed so that the username and password can be shown in the ownerCustomerScreen
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }  
}
