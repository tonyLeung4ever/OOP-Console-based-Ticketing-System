package FPAssignment1;

import java.util.List;

/**
 * Abstract class for managing events in the booking system.
 * Defines a contract for retrieving available events, booking them,
 * and managing the list of booked events.
 * 
 * This class is extended by specific managers like EventManager.
 */
public abstract class Manager<T> {

    /**
     * Returns all available events in the system.
     * @return An array of all events.
     */
    public abstract T[] getAll();

    /**
     * Attempts to book a given event for a specific quantity,
     * optionally checking whether the event supports an online option.
     * 
     * @param item The event to be booked.
     * @param qty The number of seats to book.
     * @param online True if the booking is for an event that has an online attendance option.
     *               False if booking for in-person attendance.
     * @return True if booking was successful, false otherwise.
     */
    public abstract boolean book(T item, int qty, boolean online);

    /**
     * Returns all events that have been booked (event objects only).
     * @return An array of booked events.
     */
    public abstract T[] getBookedItems();

    /**
     * Returns the full list of Booking objects related to booked events.
     * @return A list of bookings (event + quantity + attendance mode).
     */
    public abstract List<?> getBookedEvents();

    /**
     * Removes a booked event from the list.
     * @param item The event to remove.
     * @return True if successfully removed, false if not found.
     */
    public abstract boolean removeBookedItem(T item);

    /**
     * Removes a booking based on its index in the booked events list.
     * @param index The index of the booking to remove.
     * @return True if removal was successful, false otherwise.
     */
    public abstract boolean removeBooking(int index);

    /**
     * Clears all booked events from the list.
     */
    public abstract void clearBookings();

    /**
     * Checks if the user has completed checkout.
     * @return True if checkout is complete, false otherwise.
     */
    public abstract boolean hasCheckedOut();

    /**
     * Sets the user's checkout status.
     * @param hasCheckedOut True to mark checkout complete, false otherwise.
     */
    public abstract void setHasCheckedOut(boolean hasCheckedOut);
}
