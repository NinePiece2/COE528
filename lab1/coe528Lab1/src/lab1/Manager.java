package lab1;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Romit Sagu
 */
public class Manager {
    private static Scanner user = new Scanner(System.in); // Instance variable initialization
    int numOfFlights;
    private ArrayList <Flight> flights;
    private ArrayList <Ticket> ticketsIssued;
    private Flight temp;   
    
    public Manager(){ // This constructor initializes the two instance arrayLists that are used to show current flights and booked tickets
        flights = new ArrayList <Flight>();
        ticketsIssued = new ArrayList <Ticket>();
    }
    
    
    // This methood is used to create flights and addd them to the flights arrayList
    public void createFlights(){
        System.out.println("Enter the number of flights: ");
        numOfFlights = user.nextInt();
        user.nextLine();
        System.out.println();
        
        for (int i = 0; i < numOfFlights; i++){ // This asks the user to input the nessecarry information for the flights for the number of flights that the user has inputed
            System.out.println("Enter the flight number, origin, destination, departure time, capacity \nand original price in order on new lines: ");
            
            temp = new Flight(Integer.parseInt(user.nextLine()), user.nextLine(), user.nextLine(), user.nextLine(), Integer.parseInt(user.nextLine()), Double.parseDouble(user.nextLine()));
            this.flights.add(temp);
            System.out.println(); // Used for formating 
        }
        
        System.out.println(); // Used for formating 
    }
    
    // This method is used to display the flights that are avalable with the origin and destination given as well as if there is space on the flight
    public void displayAvailableFlights(String origin, String destination){
        System.out.println("The Flights with the given parameters are:");
        for (int i = 0; i < numOfFlights; i++){
            if ((this.flights.get(i).getOrigin().equalsIgnoreCase(origin)) &&(this.flights.get(i).getDestination().equalsIgnoreCase(destination)) && this.flights.get(i).getNumberOfSeatsLeft() > 0){
                System.out.println(this.flights.get(i));
            }
            
        }
        System.out.println(); // Used for formating 
    }
    
    // This method is used to get the flight that is being refrenced based off its flightNumber
    // To do this the arraylist of flights is searched for a flightNumber that is matching the given number
    public Flight getFlight(int flightNumber){
        for (int i = 0; i < numOfFlights; i++){
            if ((this.flights.get(i).getFlightNumber() == flightNumber)){
                return this.flights.get(i);
            }
        }
        throw new IllegalArgumentException("Flight " + flightNumber + " not found."); // Throws an exception if an incorrect value is entered
    }
    
    // This method is used to book a seat based on the given flight number and passenger
    public void bookSeat(int flightNumber, Passenger p){
        Flight tempFlight = getFlight(flightNumber);
        Ticket tempTicket;
        if (tempFlight.bookASeat()){ // Uses the bookASeat() method from the Flight class to see if there are spots avalable on the flight
            tempTicket = new Ticket(p, tempFlight, p.applyDiscount(tempFlight.getOriginalPrice())); // Uses the given parameters to create a temp ticket before it is added to the arraylsit
        }
        else{
            throw new IllegalArgumentException("Flight " + tempFlight.getFlightNumber() + " is fully booked.");
        }
        
        ticketsIssued.add(tempTicket); // If there are spots avalable the ticket is added to the arraylsit
    }
    
    public static void main(String[] args){
        Manager instance = new Manager(); // Creates an instance of the Manager class
        instance.createFlights(); // Calls the createFlights() method to add flights to the arraylist
        
        
        String input, ori, dest, name, memberOrNo; // Variable initialization needed for the main to be run
        int flightNum, age, years;
        Passenger tempPass;
        System.out.println("**** Welcomne to the Ticket Booker ****");
        
        do{ // A do-while loop is used so that the user can book multiple tickets ---> There is a TestInput.txt which can be used to test the program
            System.out.println("Enter the Origin and Destination in order on new lines: "); // Asks the user for the origin and destination 
            ori = user.nextLine();  // Gets the input                                          so that the avalable flights can be displayed to the user
            dest = user.nextLine();
            
            System.out.println("The Avalable flights are: \n");
            instance.displayAvailableFlights(ori, dest);
            
            System.out.println("Enter the number of the flight you wish to book a ticket for:"); // Asks the user for the number of the flight the want to book a ticket for
            flightNum = user.nextInt(); // Gets the input
            user.nextLine(); // Helps the progam as nextInt() does not read the newline (\n) character
            
            System.out.println("Enter the passengers name, age and whether or not the passenger is a member (y/n): "); // Asks for the necessary infornmation to create a ticket
            
            name = user.nextLine(); // Gets the input
            age = user.nextInt();
            user.nextLine(); // Helps the progam as nextInt() does not read the newline (\n) character
            memberOrNo = user.nextLine();
            
            if (memberOrNo.equalsIgnoreCase("y")){ // If the passenger is a member there is an additional input that is needed
                System.out.println("Enter the number of years the passenger has has their membership rounded to the nearest whole number: ");
                years = user.nextInt(); // Gets the value
                user.nextLine(); // Helps the progam as nextInt() does not read the newline (\n) character
                
                tempPass = new Member(name, age, years); // Creates a new passenger that is a member 
            }
            else if (memberOrNo.equalsIgnoreCase("n")){
                tempPass = new NonMember(name, age); // Creates a new passenger that is not a member
            }
            else{
                throw new IllegalArgumentException("ERROR: y or n was not entered");
            }
            
            instance.bookSeat(flightNum, tempPass); // Books the seat with the passenger and flight information that was gathered 
            
            System.out.println("\nCurrently booked Tickets: "); // Shows the currently bokked tickets 
            for (int i = 0; i < instance.ticketsIssued.size(); i++){
                System.out.println(instance.ticketsIssued.get(i));
            }
            
            System.out.println();
            
            System.out.println("Enter anything to book another ticket or 'n' to exit"); // Allows the user to book another ticket
            input = user.nextLine();
            
            System.out.println();
            
        } while(!input.equalsIgnoreCase("n")); // If the user enters a 'n' or 'N' the program will end
        
    }
}
