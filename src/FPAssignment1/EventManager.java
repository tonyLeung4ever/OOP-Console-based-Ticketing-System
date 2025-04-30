package FPAssignment1;

import java.util.ArrayList;
import java.util.List;

// EventManager class manages the events and bookings, and implements the Searchable interface for event search functionality.
// It provides methods for booking events, viewing all events, searching for events, and handling bookings.

public class EventManager extends Manager<Event> implements Searchable<Event> {

    // List to hold all events and a list to track booked events
    private List<Event> events;
    private List<Booking> bookedEvents;
    
    // A flag to track whether the checkout has been completed
    private boolean hasCheckedOut = false;

    // Constructor to initialize events and bookings
    public EventManager() {
        this.events = new ArrayList<>();
        
        // Adding some sample events to the event list
        events.add(new Event("Jazz Night with Joe", 10.5, 3, true));
        events.add(new Event("Youtube Rock Concert", 25.25, 0, true));
        events.add(new Event("Mozart Chamber Music", 50.0, 2, false));
        events.add(new Event("Harry Potter Concert", 100.0, 0, false));
        
        this.bookedEvents = new ArrayList<>();
    }

    // Override to return all events as an array
    @Override
    public Event[] getAll() {
        return events.toArray(new Event[0]);
    }

    // Implement search for a single event by keyword
    @Override
    public Event search(String keyword) {
        for (Event event : events) {
            if (event.getName().toLowerCase().contains(keyword.toLowerCase())) {
                return event;
            }
        }
        return null;
    }

    // Implement search for all events matching a keyword
    @Override
    public List<Event> searchAll(String keyword) {
        List<Event> matches = new ArrayList<>();
        for (Event event : events) {
            if (event.getName().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(event);
            }
        }
        return matches;
    }

    // Method to book an event, either online or in-person, with quantity and availability checks
    @Override
    public boolean book(Event event, int quantity, boolean online) {
        if (online) {
            // No seat constraints for online events
            if (event.onlineOrNot()) {
                bookedEvents.add(new Booking(event, quantity, online));  // Book the event
                return true;
            } else {
                return false; // Can't book online for an event that doesn't support online
            }
        } else {
            // In-person booking: check if enough seats are available
            if (event.getSeats() >= quantity) {
                bookedEvents.add(new Booking(event, quantity, online));  // Book the event
                return true;
            } else {
                return false; // Not enough seats available for in-person booking
            }
        }
    }

    // Return all booked items as an array
    @Override
    public Event[] getBookedItems() {
        return bookedEvents.toArray(new Event[0]);
    }
    
    // Return the list of all booked events
    @Override
    public List<Booking> getBookedEvents() {
        return bookedEvents;
    }

    // Remove a specific booked event by event object
    @Override
    public boolean removeBookedItem(Event item) {
        return bookedEvents.remove(item);
    }

    // Remove a booked event by index and restore seats if it's an in-person event
    @Override
    public boolean removeBooking(int index) {
        if (index < 0 || index >= bookedEvents.size()) {
            return false;
        }

        Booking removed = bookedEvents.remove(index);

        // Restore seats if in-person booking
        if (!removed.isOnline()) {
            Event event = removed.getEvent();
            event.setSeats(event.getSeats() + removed.getQuantity());
        }

        return true;
    }
    
    // Clear all bookings
    @Override
    public void clearBookings() {
        bookedEvents.clear();
    }
    
    // Return whether checkout has been completed
    @Override
    public boolean hasCheckedOut() {
        return hasCheckedOut;
    }
    
    // Set the checkout status
    @Override
    public void setHasCheckedOut(boolean hasCheckedOut) {
        this.hasCheckedOut = hasCheckedOut;
    }
}
