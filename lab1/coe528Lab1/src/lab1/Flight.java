package lab1;

/**
 *
 * @author Romit Sagu
 */
public class Flight {
    private int flightNumber, capacity, numberOfSeatsLeft; // Instance variables for the class
    private String origin, destination, departureTime;
    private double originalPrice;
    
    
    public Flight (int flightNumber, String origin, String dest, String departTime, //This constructor sets the values for the instance variables based on wheather the input if vaild
        int capacity, double originalPrice){
        
        if (origin.equals(dest)){
            throw new IllegalArgumentException("Origin cannot be the same as the Destination");
        }
        
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = dest;
        this.departureTime = departTime;
        this.capacity = capacity;
        this.originalPrice = originalPrice;
        this.numberOfSeatsLeft = capacity;
        
    }
    
    
    // These methods return the instance variables of the object
    public int getFlightNumber() {
        return this.flightNumber;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getNumberOfSeatsLeft() {
        return this.numberOfSeatsLeft;
    }

    public String getOrigin() {
        return this.origin;
    }

    public String getDestination() {
        return this.destination;
    }

    public String getDepartureTime() {
        return this.departureTime;
    }

    public double getOriginalPrice() {
        return this.originalPrice;
    }
    
    // These methods can be used to set the instance variables of an object if needed
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setNumberOfSeatsLeft(int numberOfSeatsLeft) {
        this.numberOfSeatsLeft = numberOfSeatsLeft;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }
    
    // This method checks if a seat can be booked based on the number of remaining seats
    public boolean bookASeat(){
        if (this.numberOfSeatsLeft > 0){
            numberOfSeatsLeft--;
            return true;
        }
        return false;
    }
    
    // This methods overrides the toString class to print the desired output of the object
    @Override
    public String toString() {
        return "Flight " + getFlightNumber() + ", " + getOrigin() + " to " + 
                getDestination() + ", " + getDepartureTime() + ", original price: " +
                getOriginalPrice() + "$";
    }
}
