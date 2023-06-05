import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
class Activity 
{
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private Set<Passenger> passengers;

    public Activity(String name, String description, double cost, int capacity) 
    {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.passengers = new HashSet<>();
    }

    public boolean addPassenger(Passenger passenger) 
    {
        if (passengers.size() < capacity) {
            passengers.add(passenger);
            passenger.addActivity(this);
            return true;
        }
        return false;
    }

    public int getSpacesAvailable() 
    {
        return capacity - passengers.size();
    }

    public String getName() 
    {
        return name;
    }

    public String getDescription() 
    {
        return description;
    }

    public double getCost() 
    {
        return cost;
    }

    public int getCapacity() 
    {
        return capacity;
    }

    public Set<Passenger> getPassengers() 
    {
        return passengers;
    }
}

class Destination 
{
    private String name;
    private List<Activity> activities;

    public Destination(String name) 
    {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) 
    {
        activities.add(activity);
    }

    public String getName() 
    {
        return name;
    }

    public List<Activity> getActivities() 
    {
        return activities;
    }
}

class Passenger {
    private String name;
    private int passengerNumber;
    private Set<Activity> activities;

    public Passenger(String name, int passengerNumber) 
    {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.activities = new HashSet<>();
    }

    public void addActivity(Activity activity) 
    {
        activities.add(activity);
    }

    public String getName() 
    {
        return name;
    }

    public int getPassengerNumber() 
    {
        return passengerNumber;
    }

    public Set<Activity> getActivities() 
    {
        return activities;
    }
}

class StandardPassenger extends Passenger 
{
    private double balance;

    public StandardPassenger(String name, int passengerNumber, double balance)
    {
        super(name, passengerNumber);
        this.balance = balance;
    }

    public double getBalance() 
    {
        return balance;
    }
    
    public boolean signUpForActivity(Activity activity) 
    {
        if (balance >= activity.getCost()) 
        {
            balance -= activity.getCost();
            addActivity(activity);
            return true;
        }
        return false;
    }
}

class GoldPassenger extends Passenger 
{
    private double balance;

    public GoldPassenger(String name, int passengerNumber, double balance) 
    {
        super(name, passengerNumber);
        this.balance = balance;
    }

    public double getBalance() 
    {
        return balance;
    }

    public boolean signUpForActivity(Activity activity) 
    {
        if (balance >= activity.getCost()) 
        {
            double discountedCost = activity.getCost() * 0.9; // Apply 10% discount
            balance -= discountedCost;
            addActivity(activity);
            return true;
        }
        return false;
    }
}

class PremiumPassenger extends Passenger 
{
    public PremiumPassenger(String name, int passengerNumber) 
    {
        super(name, passengerNumber);
    }
}

class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Destination> destinations;
    private Set<Passenger> passengers;

    public TravelPackage(String name, int passengerCapacity) 
    {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.destinations = new ArrayList<>();
        this.passengers = new HashSet<>();
    }

    public void addDestination(Destination destination) 
    {
        destinations.add(destination);
    }

    public boolean addPassenger(Passenger passenger) 
    {
        if (passengers.size() < passengerCapacity) 
        {
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    public void printItinerary() 
    {
        System.out.println("Travel Package: " + name);
        for (Destination destination : destinations) 
        {
            System.out.println("Destination: " + destination.getName());
            for (Activity activity : destination.getActivities()) 
            {
                System.out.println("Activity: " + activity.getName());
                System.out.println("Description: " + activity.getDescription());
                System.out.println("Cost: " + activity.getCost());
                System.out.println("Capacity: " + activity.getCapacity());
            }
        }
    }

    public void printPassengerList() 
    {
        System.out.println("Travel Package: " + name);
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Number of Passengers: " + passengers.size());
        for (Passenger passenger : passengers) 
        {
            System.out.println("Passenger Name: " + passenger.getName());
            System.out.println("Passenger Number: " + passenger.getPassengerNumber());
        }
    }

    public void printPassengerDetails(int passengerNumber) {
        for (Passenger passenger : passengers) {
            if (passenger.getPassengerNumber() == passengerNumber) 
            {
                System.out.println("Name: " + passenger.getName());
                System.out.println("Passenger Number: " + passenger.getPassengerNumber());
                if (passenger instanceof StandardPassenger || passenger instanceof GoldPassenger) 
                {
                    double balance = ((StandardPassenger) passenger).getBalance();
                    System.out.println("Balance: " + balance);
                }
                for (Activity activity : passenger.getActivities()) 
                {
                    System.out.println("Activity: " + activity.getName());
                    for (Destination destination : destinations) {
                        if (destination.getActivities().contains(activity)) 
                        {
                            System.out.println("Destination: " + destination.getName());
                            System.out.println("Price: " + activity.getCost());
                        }
                    }
                }
            }
        }
    }

    public void printAvailableActivities() 
    {
        for (Destination destination : destinations) 
        {
            for (Activity activity : destination.getActivities()) 
            {
                int spacesAvailable = activity.getSpacesAvailable();
                System.out.println("Activity: " + activity.getName());
                System.out.println("Destination: " + destination.getName());
                System.out.println("Spaces Available: " + spacesAvailable);
            }
        }
    }
}

public class NymbleTask 
{
    public static void main(String[] args) 
    {
        // Create activities
        Activity activity1 = new Activity("Hiking", "Enjoy a scenic hike in the mountains", 20, 10);
        Activity activity2 = new Activity("Sightseeing Tour", "Explore the city's landmarks", 30, 15);

        // Create destinations and add activities
        Destination destination1 = new Destination("Mountain Retreat");
        destination1.addActivity(activity1);

        Destination destination2 = new Destination("City Tour");
        destination2.addActivity(activity2);

        // Create passengers
        StandardPassenger passenger1 = new StandardPassenger("John Doe", 1, 50);
        GoldPassenger passenger2 = new GoldPassenger("Jane Smith", 2, 100);
        PremiumPassenger passenger3 = new PremiumPassenger("Mike Johnson", 3);

        // Create a travel package and add destinations
        TravelPackage travelPackage = new TravelPackage("Weekend Getaway", 10);
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

        // Print itinerary
        travelPackage.printItinerary();

        // Print passenger list
        travelPackage.printPassengerList();

        // Print details of an individual passenger
        travelPackage.printPassengerDetails(1);

        // Print available activities
        travelPackage.printAvailableActivities();
    }
}
