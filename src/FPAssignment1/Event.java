package FPAssignment1;

// Event class represents an event with details such as name, price, number of seats, 
// and whether it's available online or not.
public class Event {

    // Instance variables to store the event details
    private String name;        // Name of the event
    private double price;       // Price of the event
    private int numSeats;       // Number of seats available for the event
    private boolean onlineOrNot; // Boolean flag to indicate if the event is online or not

    // Constructor to initialize the event with the provided values
    public Event(String name, double price, int numSeats, boolean onlineOrNot) {
        this.name = name;
        this.price = price;
        this.numSeats = numSeats;
        this.onlineOrNot = onlineOrNot;
    }

    // Getter method to retrieve the event's name
    public String getName() { 
        return name;
    }

    // Getter method to retrieve the event's price
    public double getPrice() { 
        return price;
    }

    // Getter method to retrieve the number of seats available for the event
    public int getSeats() { 
        return numSeats;
    }

    // Setter method to set/update the number of seats for the event
    public void setSeats(int qty) { 
        numSeats = qty;
    }

    // Getter method to check if the event is available online or not
    public boolean onlineOrNot() { 
        return onlineOrNot;
    }

    // Overriding the toString method to provide a string representation of the event
    @Override
    public String toString() {
        return name + " | $" + String.format("%.2f", price) + " | " + numSeats + " seats | " + (onlineOrNot ? "Online Available" : "No Online");
    }
}
