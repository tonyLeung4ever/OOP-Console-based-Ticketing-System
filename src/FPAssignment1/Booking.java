package FPAssignment1;

// Booking class represents a booking for an event, including the event itself, 
// the quantity of tickets booked, and whether the event is being booked online or not.
public class Booking {

    // Instance variables to store the booking details
    private Event event;        // The event being booked
    private int quantity;       // The number of tickets being booked
    private boolean online;     // Whether the booking is online or not

    // Constructor to initialize the booking with the provided event, quantity, and online status
    public Booking(Event event, int quantity, boolean online) {
        this.event = event;
        this.quantity = quantity;
        this.online = online;
    }

    // Getter method to retrieve the event associated with the booking
    public Event getEvent() {
        return event;
    }

    // Getter method to retrieve the quantity of tickets booked
    public int getQuantity() {
        return quantity;
    }

    // Getter method to check if the booking is online or not
    public boolean isOnline() {
        return online;
    }

    // Method to calculate the total cost of the booking by multiplying 
    // the quantity of tickets with the event's price
    public double getTotalCost() {
        return quantity * event.getPrice();
    }
}
