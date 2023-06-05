import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class NymbleTaskTest 
{

    private Activity activity1;
    private Activity activity2;
    private Destination destination1;
    private Destination destination2;
    private StandardPassenger passenger1;
    private GoldPassenger passenger2;
    private PremiumPassenger passenger3;
    private TravelPackage travelPackage;

    @BeforeEach
    void setUp() 
    {
        // Create activities
        activity1 = new Activity("Hiking", "Enjoy a scenic hike in the mountains", 20, 10);
        activity2 = new Activity("Sightseeing Tour", "Explore the city's landmarks", 30, 15);

        // Create destinations and add activities
        destination1 = new Destination("Mountain Retreat");
        destination1.addActivity(activity1);

        destination2 = new Destination("City Tour");
        destination2.addActivity(activity2);

        // Create passengers
        passenger1 = new StandardPassenger("John Doe", 1, 50);
        passenger2 = new GoldPassenger("Jane Smith", 2, 100);
        passenger3 = new PremiumPassenger("Mike Johnson", 3);

        // Create a travel package and add destinations
        travelPackage = new TravelPackage("Weekend Getaway", 10);
        travelPackage.addDestination(destination1);
        travelPackage.addDestination(destination2);

        // Add passengers to the travel package
        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);
        travelPackage.addPassenger(passenger3);

        // Add activities to passengers
        passenger1.signUpForActivity(activity1);
        passenger2.signUpForActivity(activity2);
        passenger3.addActivity(activity1);
    }

    @Test
    void addPassenger() 
    {
        assertTrue(activity1.addPassenger(passenger1));
        assertTrue(activity1.addPassenger(passenger2));
    }

    @Test
    void getSpacesAvailable() 
    {
        assertEquals(10, activity1.getSpacesAvailable());
        activity1.addPassenger(passenger1);
        assertEquals(9, activity1.getSpacesAvailable());
    }

    @Test
    void signUpForActivity() 
    {
        assertTrue(passenger1.signUpForActivity(activity2));
        assertFalse(passenger1.signUpForActivity(activity1));
    }

    @Test
    void printItinerary() 
    {
        //assertNotNull(travelPackage.getActivities());
        assertNotNull(destination1.getActivities());
        assertNotNull(destination2.getActivities());
        assertEquals("Hiking", destination1.getActivities().get(0).getName());
        assertEquals("Sightseeing Tour", destination2.getActivities().get(0).getName());
    }

}    