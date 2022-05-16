package lab1;

/**
 *
 * @author Romit Sagu
 */
public class Ticket {
    private Passenger passenger; // Instance variable initialization
    private Flight flight;
    private double price;
    private int ticketNumber;
    private static int number = 1;
    
    public Ticket(Passenger p, Flight flight, double price){ // The constructor assigning the instance variables of the class
        this.passenger = p;
        this.flight = flight;
        this.price = price;
        this.ticketNumber = number;
        number++;
    }
    
    // Getters for the instance variables
    public Passenger getPassenger() {
        return this.passenger;
    }

    public Flight getFlight() {
        return this.flight;
    }

    public double getPrice() {
        return this.price;
    }

    public int getTicketNumber() {
        return this.ticketNumber;
    }

    public static int getNumber() {
        return number;
    }
    
    
    // Setters for the instance variables
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
    
    // This method overrides the toString method to print the desired output of the Ticket class
    @Override
    public String toString() {
        return getPassenger().name + ", " + getFlight() + ", ticket price: $" + getPrice();
    }
}
