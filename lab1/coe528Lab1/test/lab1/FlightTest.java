package lab1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author romit
 */
public class FlightTest {
    
    public FlightTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testConstructor(){
        System.out.println("Constructor");
        Flight instance = new Flight(1030, "Toronto", "Kolkata", 
                "03/02/99 7:50 pm", 150, 1000);
        String expResult = "Flight 1030, Toronto to Kolkata, 03/02/99 7:50 pm, original price: 1000.0$";
        assertEquals(expResult, instance.toString());
    }  
    
    @Test (expected=IllegalArgumentException.class)
    public void testInvalidConstructor(){
        System.out.println("Constructor Fail");
        Flight instance = new Flight(1030, "Toronto", "Toronto", 
                "03/02/99 7:50 pm", 150, 1000);
    }
    
    @Test
    public void testBookASeat(){
        System.out.println("testBookASeat");
        Flight instance = new Flight(1030, "Toronto", "Kolkata", 
                "03/02/99 7:50 pm", 150, 1000);
        assertTrue(instance.bookASeat());
    }
    
    @Test
    public void testSettersAndGetters(){
        System.out.println("testSettersAndGetters");
        Flight instance = new Flight(1030, "Toronto", "Kolkata", 
                "03/02/99 7:50 pm", 150, 1000);
        instance.setFlightNumber(10);
        instance.setOrigin("Tokyo");
        instance.setDestination("Shanghai");
        instance.setDepartureTime("1/01/11 11:11 am");
        instance.setCapacity(175);
        instance.setOriginalPrice(200);
        instance.setNumberOfSeatsLeft(100);
        
        assertEquals(10, instance.getFlightNumber());
        assertEquals("Tokyo", instance.getOrigin());
        assertEquals("Shanghai", instance.getDestination());
        assertEquals("1/01/11 11:11 am", instance.getDepartureTime());
        assertEquals(175, instance.getCapacity());
        assertEquals(200.0, instance.getOriginalPrice(), 0.01);
        assertEquals(100, instance.getNumberOfSeatsLeft());
    }
}
