package BookstoreApp;

import javafx.beans.property.BooleanProperty; // Libraries needed for the BooleanProperty select to be used for the checkbox
import javafx.beans.property.SimpleBooleanProperty;

public class Books {
    private String name; // Instance variables
    private double price;
    private BooleanProperty select;
    
    public Books (String name, double price){ // Books constructor
        this.name = new String(name);
        this.price = price;
        this.select = new SimpleBooleanProperty(false);
    }

    public String getName() { // gets instance variables
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }   
    
    public BooleanProperty getSelect(){
        return this.select;
    }
    
     public void setSelect(BooleanProperty select){ // Sets the boolean value of the Select checkbox
        this.select = select;
    }
}
