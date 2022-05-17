package BookstoreApp;

import javafx.application.Application; // Importing the libraries needed to run the GUI and the program itself
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.*;
import javafx.stage.WindowEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import java.util.Scanner; 
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import javafx.beans.value.ObservableValue;


public class BookstoreMainPage extends Application {
    
    private Button loginButton = new Button ("Login"); // variables that are used for the single instance of the application
    private Label usernameLabel = new Label("Username:");
    private Label passwordLabel = new Label("Password:");
    private TextField usernameTextField = new TextField();
    private TextField passwordTextField = new TextField();
    private Label welcome = new Label("Welcome to the BookStore App");
    private Label welcome2 = new Label();
    
    private Button ownerBooks = new Button ("Books");
    private Button ownerCustomers = new Button ("Customers");
    private Button logOut = new Button ("Logout");
    
    private Label customersLabel = new Label("Customers");
    private Label booksLabel = new Label("Books"); 
    
    private ObservableList<Books> booksTableData = FXCollections.observableArrayList(); // These two arraylists are important as they cointain the Books and SetCustomer
    private ObservableList<SetCustomer> customerTableData = FXCollections.observableArrayList(); // objects that are needed for the program to run.
    private HBox hb, hb2;
    private Button back = new Button("Back");
    private Button del = new Button("Delete");
    
    private Button buy = new Button("Buy");
    private Button redeemAndBuy = new Button("Redeem points and Buy");
    private boolean valid = false;
    private int customerIndex;

    private double totalCost;
    
    @Override //Override the start method in the Application class
    public void start(Stage primaryStage) {
        //Sets up the booksTableData and customerTableData arrayList to have data from the text files books.txt amd customers.txt
        try {
            FileReader temp = new FileReader("books.txt");
            FileReader temp2 = new FileReader("customers.txt");
            Scanner temp1 = new Scanner (temp);
            Scanner temp3 = new Scanner (temp2);
            while (temp1.hasNextLine()){
                String [] split = temp1.nextLine().split(" @ ", 2); // Needed to differentiate between the different book parameters
                booksTableData.add(new Books(split[0],Double.parseDouble(split[1]))); // Creates a new Boooks object and adds it to the apropriate arraylist
            }
            temp.close();
            
            while (temp3.hasNextLine()){
                String [] split = temp3.nextLine().split(" @ ", 3);
                customerTableData.add(new SetCustomer(split[0], split[1], Integer.parseInt(split[2]))); // Creates a new SetCustomer object and adds it to the customerTableData arraylist
            }
            temp2.close();
        }
        catch (FileNotFoundException e) { // Creates the files if they have not yet been created.
            try{
                FileWriter temp = new FileWriter("books.txt", true);
                temp.close();
                FileWriter temp2 = new FileWriter("customers.txt", true);
                temp2.close();
            }
            catch (IOException c) {
                System.out.println("An error occurred.");
                c.printStackTrace();
            
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        
        primaryStage.setTitle("Bookstore App"); // Sets up the main stage title, size and shows the main stage.
        primaryStage.setScene(new Scene(mainMenu(), 400, 200));
        primaryStage.show(); 
        
        
        loginButton.setOnAction( // When the login button is pressed.
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                   for (customerIndex = 0; customerIndex < customerTableData.size(); customerIndex++){ // Checks if the input enterd is for a valid customer and gets the index of the customer object in the 
                       if (customerTableData.get(customerIndex).getInstance().login(usernameTextField.getText(), passwordTextField.getText())){ // customerTableData arraylist.
                           valid = true;
                           break;
                       }
                   }
                   
                   //System.out.println("CustomerIndex = " + customerIndex);
                   
                   if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()){ // Shows the user an error if they did not enter a username or password.
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setContentText("Enter a Username and/or a password.");
                        alert.show();   
                   }
                   else if(usernameTextField.getText().equals("admin") && passwordTextField.getText().equals("admin")){ // Checks if the user has the admin username and password.
                        primaryStage.setScene(new Scene(ownerStartScreen(), 400, 200)); //Shows the ownerStartScreen
                        usernameTextField.clear(); // Clears the username and password textfields so when the user logs out they don't have to manually if the programa is kept running.
                        passwordTextField.clear();
                   }
                   else if(valid){
                        primaryStage.setScene(new Scene(customersStartScreen(), 450, 600));
                        usernameTextField.clear();
                        passwordTextField.clear();                       
                   }
                   else{ // Shows the user an error if they ener an incorrect username or password.
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setContentText("INCORRECT Username and/or password.");
                        alert.show(); 
                   }
                }
            }    
        );
        
         
        ownerBooks.setOnAction( // If the owner wants to see the ownerBooks page it is displayed
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    primaryStage.setScene(new Scene(ownerBooks(), 450, 600));
                }
            } 
        );
        
        ownerCustomers.setOnAction( // If the owner wantws to see the ownerCustomer page it is displayed
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    primaryStage.setScene(new Scene(ownerCustomers(), 450, 600));
                }
            } 
        );
        
        back.setOnAction( // If the owner wants to go back to the ownerStartScreen from the ownerBooks or the ownerCustomer page
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    primaryStage.setScene(new Scene(ownerStartScreen(), 400, 200));
                }
            } 
        );
        
        buy.setOnAction( // If a customer wants to buy the books selected
	        new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        for (int i = 0; i < booksTableData.size(); i++){ // Gets the total cost of the books selected
                            if (booksTableData.get(i).getSelect().get()){
                                totalCost += booksTableData.get(i).getPrice();
                            }
                        }
                        if (totalCost == 0){ // If no books are selected an error is snown
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setContentText("Select the Books you wish to buy.");
                            alert.show();  
                        }
                        else if (totalCost > 0){ // Tells the customer object that the customer purcaced books for the total price
                            try{
                                customerTableData.get(customerIndex).getInstance().buyBook(totalCost);
                            }
                            catch(Exception f){
                                System.out.println(f);
                            }
                            primaryStage.setScene(new Scene(customerCostScreen(), 400, 200)); // Shows the customerCostScreen
                            for (int k = 0; k < booksTableData.size(); k++){ // Ask if we should delete the book after because there is only one copy // Removes the selected books as there is only one copy
                                if (booksTableData.get(k).getSelect().get()){
                                    booksTableData.remove(k);
                                    k = -1;
                                }
                            }
                        }
                    }
                } 
        );
        
        redeemAndBuy.setOnAction( // If a customer wants to buy the books using points
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    for (int i = 0; i < booksTableData.size(); i++){ // Gets the total cost of the books selected
                            if (booksTableData.get(i).getSelect().get()){
                                totalCost += booksTableData.get(i).getPrice();
                            }
                        }
                        if (totalCost == 0){ // If no books are selected an error is snown
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setContentText("Select the Books you wish to buy.");
                            alert.show();  
                        }
                        else if(customerTableData.get(customerIndex).getInstance().getPoints() == 0){ // If the customer doesn't have any points an error is shown
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setContentText("Option not avalable as you have 0 points.");
                            alert.show();
                        }
                        else if (totalCost > 0){ // Tells the customer object that the customer purcaced books for the total price
                            try{
                                totalCost = customerTableData.get(customerIndex).getInstance().buyBookWithPoints(totalCost);
                            }
                            catch(Exception f){
                                System.out.println(f);
                            }
                            primaryStage.setScene(new Scene(customerCostScreen(), 400, 200)); // Shows the customerCostScreen
                            for (int k = 0; k < booksTableData.size(); k++){ // Ask if we should delete the book after because there is only one copy // Removes the selected books as there is only one copy
                                if (booksTableData.get(k).getSelect().get()){
                                    booksTableData.remove(k);
                                    k = -1;
                                }
                            }
                        }
                }
            } 
        );
        
        
        logOut.setOnAction( // If the logout button is pressed the user is brought back to the mainMenu
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    primaryStage.setScene(new Scene(mainMenu(), 400, 200));
                }
            } 
        );
        
        
        
        //called when the "close" icon [x] at the window's top-right is clicked
        primaryStage.setOnCloseRequest(
            new EventHandler<WindowEvent>() {
                 @Override
                 public void handle(WindowEvent e) {
                    try { // Saves the Books object and SetCustomer object data to the text files
                        FileWriter temp = new FileWriter("books.txt");
                        FileWriter temp2 = new FileWriter("customers.txt");
                        for (int i = 0; i < booksTableData.size(); i++){
                            temp.write(booksTableData.get(i).getName() + " @ " + booksTableData.get(i).getPrice() + "\n");
                        }
                        temp.close();
                        
                        for (int i = 0; i < customerTableData.size(); i++){
                            temp2.write(customerTableData.get(i).getInstance().getUsername() + " @ " + customerTableData.get(i).getInstance().getPassword() + " @ " + customerTableData.get(i).getInstance().getPoints() + "\n");
                        }
                        temp2.close();
                        
                   } catch (IOException f) {
                       System.out.println("An error occurred.");
                       f.printStackTrace();
                   }
                     
                }  
            }
        );
    }
    
    
    public GridPane mainMenu(){ // returns the mainMenu elements
        GridPane temp = new GridPane();
        temp.setAlignment(Pos.CENTER);
        
        temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
        temp.add(welcome, 0, 0);
        temp.add(usernameLabel, 0,1);
        temp.add(usernameTextField, 1, 1);
        
        temp.add(passwordLabel, 0, 2);
        temp.add(passwordTextField, 1, 2);
        
        temp.add(loginButton, 1, 3);
        
        return temp;
    }
    
    public GridPane ownerStartScreen(){ // Returns the ownerStartScreen pane
        GridPane temp = new GridPane();
        temp.setAlignment(Pos.CENTER);
        
        temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
        
        welcome.setText("Welcome Owner");
        temp.add(welcome, 0, 0);
        temp.add(ownerBooks, 0, 1);
        temp.add(ownerCustomers, 0, 2);
        temp.add(logOut, 0, 3);
        
        return temp;
    }
    
    public GridPane ownerBooks(){ // Returns the ownerBooks pane
        GridPane temp = new GridPane(); // Variables that are needed to create the pane
        TableView<Books> booksTable = new TableView<Books>();
        hb = new HBox();
        hb2 = new HBox();
        
                
        temp.setPadding(new Insets(10, 0, 0, 10));
        temp.setHgap(5);
        temp.setVgap(5);
        
        booksLabel.setFont(new Font("Arial", 20)); // Displays a label at the top of the page
        temp.add(booksLabel, 0, 0);
        
        booksTable.setEditable(false);
                
        TableColumn name = new TableColumn("Book Name"); // Creates a column named Book Name and populates it with the result of the getName instance method.
        name.setMinWidth(300);
        name.setCellValueFactory(
            new PropertyValueFactory<Books, String>("name")); 
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        TableColumn price = new TableColumn("Book Price");// Creates a column named Book Price and populates it with the result of the getPrice instance method
        price.setMinWidth(100);
        price.setCellValueFactory(
            new PropertyValueFactory<Books, Double>("price")); 
        price.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter())); // Needed to convert the double that is returned to a String
        
        
        booksTable.setItems(booksTableData);
        booksTable.getColumns().addAll(name, price);
        
        TextField nameOfNewBook = new TextField(); // The text fields to add a new book
        nameOfNewBook.setPromptText("Book Name");
        nameOfNewBook.setMaxWidth(nameOfNewBook.getPrefWidth());
        
        TextField priceOfNewBook = new TextField();
        priceOfNewBook.setMaxWidth(priceOfNewBook.getPrefWidth());
        priceOfNewBook.setPromptText("Book Price");
        
        Button addButton = new Button("Add");
        
        
        
        addButton.setOnAction(new EventHandler<ActionEvent>() { // When the add button is pressed a new Books object is created based on the parameters passed
            @Override
            public void handle(ActionEvent e) {
                double priceDouble = Double.parseDouble(priceOfNewBook.getText());
                if (priceDouble <= 0){ // Checks if the books price is valid
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Books price must be greater than zero.");
                    alert.show();      
                }
                else{
                    booksTableData.add(new Books(
                            nameOfNewBook.getText(),
                            priceDouble));
                    nameOfNewBook.clear(); // Clears the textfields
                    priceOfNewBook.clear();
                }
            }
        });
        
        del.setOnAction(new EventHandler<ActionEvent>() { // When the delete button is pressed the selected book is deleted from the arraylist
            @Override
            public void handle(ActionEvent e) {
                try{
                    booksTableData.remove(booksTableData.indexOf(booksTable.getSelectionModel().getSelectedItem()));
                }
                catch (java.lang.ArrayIndexOutOfBoundsException a){ // If no book is selected an error is shown
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Select a Book to Delete.");
                    alert.show();      
                }
            }
        });
        
        hb.getChildren().addAll(nameOfNewBook, priceOfNewBook, addButton);
        hb2.getChildren().addAll(back, del);
        
        temp.add(booksTable, 0, 1);
        temp.add(hb, 0, 2);
        temp.add(hb2, 0, 3);
        
        
        return temp;
    }
    
    public GridPane ownerCustomers(){ // Returns the ownerCustomers pane
        GridPane temp = new GridPane(); // Variables needed to create the pane
        TableView<SetCustomer> customersTable = new TableView<SetCustomer>();
        hb = new HBox();
        hb2 = new HBox();
        
        temp.setPadding(new Insets(10, 0, 0, 10));
        temp.setHgap(5);
        temp.setVgap(5);
        
        customersLabel.setFont(new Font("Arial", 20)); // Displays a label at the top of the page
        temp.add(customersLabel, 0, 0);
        
        customersTable.setEditable(false);
        
        TableColumn username = new TableColumn("Username"); // Creates a column named Username and populates it with the result of the getUsername instance method.
        username.setMinWidth(150);
        username.setCellValueFactory(
            new PropertyValueFactory<SetCustomer, String>("username")); 
        username.setCellFactory(TextFieldTableCell.forTableColumn());
        
        TableColumn password = new TableColumn("Password"); // Creates a column named Password and populates it with the result of the getPassword instance method.
        password.setMinWidth(150);
        password.setCellValueFactory(
                new PropertyValueFactory<SetCustomer, String>("password"));                             
        password.setCellFactory(TextFieldTableCell.forTableColumn());
        
        TableColumn points = new TableColumn("Points"); // Creates a column named Points and populates it with the result of the getPoints instance method.
        points.setMinWidth(100);
        points.setCellValueFactory(
            new PropertyValueFactory<SetCustomer, Integer>("points")); 
        points.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter())); // Needed to convert the getPoints integer to a string
        
        
        customersTable.setItems(customerTableData);
        customersTable.getColumns().addAll(username, password, points);
        
        TextField usernameOfNewCustomer = new TextField(); // The textfields needed to add a new customer
        usernameOfNewCustomer.setPromptText("Username");
        usernameOfNewCustomer.setMaxWidth(usernameOfNewCustomer.getPrefWidth());
        
        TextField passwordOfNewCustomer = new TextField();
        passwordOfNewCustomer.setPromptText("Password");
        passwordOfNewCustomer.setMaxWidth(usernameOfNewCustomer.getPrefWidth());
        
        Button addButton = new Button("Add");
        
        
        addButton.setOnAction(new EventHandler<ActionEvent>() { // If the add button is pressed
            @Override
            public void handle(ActionEvent e) {
                String newUsername = usernameOfNewCustomer.getText(); 
                String newPassword = passwordOfNewCustomer.getText();
                boolean hasSpaces = false;
                boolean notUnique = false;
                
                for (int i = 0; i < newUsername.length(); i++){ // Checks wheather the username has spaces in it
                    if (Character.isWhitespace(newUsername.charAt(i)))
                        hasSpaces = true;
                }
                
                for (int i = 0; i < newPassword.length(); i++){ // Checks wheather the password has spaces in it
                    if (Character.isWhitespace(newPassword.charAt(i)))
                        hasSpaces = true;
                }
                
                for (int i = 0; i < customerTableData.size(); i++){ // Checks wheather the username is already taken
                    if (customerTableData.get(i).getInstance().getUsername().equals(newUsername))
                        notUnique = true;
                }
                
                if (hasSpaces || newUsername.isEmpty() || newPassword.isEmpty()){ // Displays the approprate error message based ont he above checks
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Username and/or password cannot contain spaces.");
                    alert.show(); 
                }
                else if(notUnique){
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Username already taken.");
                    alert.show();      
                }
                else{ // Creates the new customer to the arraylist
                    customerTableData.add(new SetCustomer(
                            newUsername,
                            newPassword));
                    usernameOfNewCustomer.clear();
                    passwordOfNewCustomer.clear();
                }
            }
        });
        
        del.setOnAction(new EventHandler<ActionEvent>() { // If the delete button is pressed the selected customer is deleted
            @Override
            public void handle(ActionEvent e) {
                try{
                    customerTableData.remove(customerTableData.indexOf(customersTable.getSelectionModel().getSelectedItem()));
                }
                catch (java.lang.ArrayIndexOutOfBoundsException a){ // Shows an error message if no customer is selected
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setContentText("Select a Customer to Delete.");
                    alert.show();      
                }
            }
        });
                
        hb.getChildren().addAll(usernameOfNewCustomer, passwordOfNewCustomer, addButton);
        hb2.getChildren().addAll(back, del);
        
        temp.add(customersTable, 0, 1);
        temp.add(hb, 0, 2);
        temp.add(hb2, 0, 3);
        
        return temp;
    }
    
    public GridPane customersStartScreen(){ // Returns the customerStartScreen pane
        GridPane temp = new GridPane(); // Variables needed to create the pane
        TableView<Books> customerBuyTable = new TableView<Books>();
        hb = new HBox();
        totalCost = 0;
        
        
        temp.setAlignment(Pos.CENTER);
        
        temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
        
        customerTableData.get(customerIndex).insert();
        
        // Displays the welcome message based on current amount of points and status
        welcome2.setText("Welcome " + customerTableData.get(customerIndex).getUsername() + ". " + 
                "You have " + customerTableData.get(customerIndex).getPoints() + " points. Your status is " + 
                customerTableData.get(customerIndex).getInstance().getStatus() + ".");
        temp.add(welcome2, 0, 0);
        
        customerBuyTable.setEditable(true);
        
        TableColumn bookName = new TableColumn("Book Name"); // Creates a column named Book Name and populates it with the result of the getName instance method.
        bookName.setMinWidth(175);
        bookName.setCellValueFactory(
                new PropertyValueFactory<Books, String>("name"));                             
        bookName.setCellFactory(TextFieldTableCell.forTableColumn());
        bookName.setEditable(false);
        
        TableColumn bookPrice = new TableColumn("Book Price"); // Creates a column named Book Price and populates it with the result of the getPrice instance method.
        bookPrice.setMinWidth(175);
        bookPrice.setCellValueFactory(
            new PropertyValueFactory<Books, Integer>("price")); 
        bookPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter())); // Needed to convert the getPrice double into a string
        bookPrice.setEditable(false);
        
        
        TableColumn<Books, Boolean> select = new TableColumn<>("Select"); // Creates a column named Select and is populated with checkboxes that customers can select to choose books they want to buy
        select.setMinWidth(25);
        select.setCellFactory(CheckBoxTableCell.forTableColumn(select));
        select.setCellValueFactory(
        new Callback<CellDataFeatures<Books,Boolean>,ObservableValue<Boolean>>() // Changes the value of the select booleabn value in the Books object to show that the customer wants to buy it
        {
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<Books, Boolean> p)
            {   
                return p.getValue().getSelect();
            }   
        });
        
        
        customerBuyTable.setItems(booksTableData);
        customerBuyTable.getColumns().addAll(bookName, bookPrice, select);
        
        
        hb.getChildren().addAll(buy, redeemAndBuy, logOut);
        
               
        
        temp.add(customerBuyTable, 0, 1);
        
        temp.add(hb, 0, 2);
        
        
        return temp;
    }
    
    public GridPane customerCostScreen(){ // Returns the customerCostScreen pane
        GridPane temp = new GridPane();
        
        temp.setAlignment(Pos.CENTER);
        
        temp.setPadding(new Insets(11, 12, 13, 14));
        temp.setHgap(5);
        temp.setVgap(5);
        
        customerTableData.get(customerIndex).insert(); // Makes sure the customer has the correct status
        
        Label totalCostLabel = new Label("Total Cost: " + totalCost); // Shows the total cost
        Label pointsLabel = new Label("Points: " + customerTableData.get(customerIndex).getPoints() + // Shows the points and status after the transaction
                ", Status: " + customerTableData.get(customerIndex).getInstance().getStatus());
        
        temp.add(totalCostLabel, 0, 0);
        temp.add(pointsLabel, 0, 1);
        temp.add(logOut, 0, 2);
        
        return temp;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}