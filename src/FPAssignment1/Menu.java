package FPAssignment1;

import java.util.Scanner;
import java.util.List;

public class Menu {

    public void displayMenu() { // Display the main menu options
        System.out.println("\nChoose an option:\n");
        System.out.println("1. List all events");
        System.out.println("2. Book a ticket");
        System.out.println("3. View my Booking");
        System.out.println("4. Remove an event from the booking");
        System.out.println("5. Checkout");
        System.out.println("6. Quit");
        System.out.print("Please select: ");
    }

    public void listAllEvents(EventManager eventManager) { // List all available events
        System.out.println();
        System.out.println("The current events are:");
        System.out.println();
        Event[] events = eventManager.getAll(); // Retrieve all events
        for (int i = 0; i < events.length; i++) { // Display events
            System.out.println((i + 1) + ". " + events[i]);
        }
    }

    public void bookTicket(EventManager eventManager, Scanner scanner) { // Handle ticket booking
        System.out.print("\nEnter a keyword: ");
        String keyword = scanner.nextLine(); // Get keyword for event search
        List<Event> matches = eventManager.searchAll(keyword); // Search events by keyword

        if (matches.isEmpty()) { // No matching events found
            System.out.println("Sorry! No events match your keyword.");
            return;
        }

        System.out.println("\nThe following event(s) are found:");
        for (int i = 0; i < matches.size(); i++) { // Display found events
            System.out.println((i + 1) + ". " + matches.get(i));
        }
        System.out.println((matches.size() + 1) + ". Go back");

        int choice = -1;
        while (true) { // Get user's event selection
            System.out.print("Please select: ");
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input); // Parse input to integer
                if (choice >= 1 && choice <= matches.size() + 1) break; // Valid selection
                System.out.println("Invalid input! Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please try again.");
            }
        }

        if (choice == matches.size() + 1) { // Go back option
            return;
        }

        Event selectedEvent = matches.get(choice - 1); // Get selected event
        boolean attendOnline = false;

        if (selectedEvent.getSeats() == 0) { // No seats available for in-person
            if (selectedEvent.onlineOrNot()) { // Check if online attendance is possible
                while (true) { // Ask if they want to attend online
                    System.out.print("Do you want to attend online (y/n): ");
                    String answer = scanner.nextLine().trim().toLowerCase();
                    if (answer.equals("y")) { // Yes, online
                        attendOnline = true;
                        break;
                    } else if (answer.equals("n")) { // No, in-person
                        System.out.println("Sorry! There are no seats available!");
                        return;
                    } else {
                        System.out.println("Invalid input! Please enter 'y' or 'n'.");
                    }
                }
            } else {
                System.out.println("Sorry! There are no seats available!");
                return;
            }
        } else { // Seats are available for in-person or online
            while (true) { // Ask for mode of attendance (online or in-person)
                System.out.print("Do you want to attend online (y/n): ");
                String answer = scanner.nextLine().trim().toLowerCase();
                if (answer.equals("y")) {
                    attendOnline = true;
                    break;
                } else if (answer.equals("n")) {
                    attendOnline = false;
                    break;
                } else {
                    System.out.println("Invalid input! Please enter 'y' or 'n'.");
                }
            }
        }

        int qty = -1;
        while (true) { // Get ticket quantity
            System.out.print("Enter the quantity of tickets: ");
            String qtyInput = scanner.nextLine();
            try {
                qty = Integer.parseInt(qtyInput);
                if (qty > 0) break; // Valid quantity
                System.out.println("Invalid quantity! Must be at least 1.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a valid integer.");
            }
        }

        boolean booked = eventManager.book(selectedEvent, qty, attendOnline); // Book the ticket
        if (booked) {
            if (!attendOnline) {
                selectedEvent.setSeats(selectedEvent.getSeats() - qty); // Update available seats for in-person
            }
            System.out.println(">> " + qty + " tickets of " + selectedEvent.getName() + " booked! Go back to the main menu.");
        } else {
            System.out.println("Booking failed. Please try again.");
        }
    }

    public void viewMyBookings(EventManager eventManager) { // View user's bookings
        List<Booking> bookings = eventManager.getBookedEvents(); // Get booked events

        if (bookings.isEmpty()) { // No bookings found
            System.out.println("No bookings found.");
            return;
        }

        double totalPrice = 0;
        int totalTickets = 0;

        int index = 1;
        for (Booking booking : bookings) { // Display each booking
            Event event = booking.getEvent();
            int qty = booking.getQuantity();
            boolean isOnline = booking.isOnline();

            totalPrice += qty * event.getPrice();
            totalTickets += qty;

            System.out.printf("%d. %s", index++, event.getName());
            if (qty > 1 || isOnline) {
                System.out.printf("  X  %d  %s", qty, isOnline ? "Online" : "In-person");
            }
            System.out.printf("  : $%.2f\n", qty * event.getPrice());
        }

        System.out.printf("Total: $%.2f for %d tickets\n", totalPrice, totalTickets);
    }

    public void removeBooking(EventManager eventManager, Scanner scanner) { // Remove a booking
        List<Booking> bookings = eventManager.getBookedEvents(); // Get bookings

        if (bookings.isEmpty()) { // No bookings to remove
            System.out.println("No bookings found to remove.");
            return;
        }

        System.out.println("Your current bookings:");
        int index = 1;
        for (Booking booking : bookings) { // Display current bookings
            Event event = booking.getEvent();
            int qty = booking.getQuantity();
            boolean isOnline = booking.isOnline();
            double total = qty * event.getPrice();

            System.out.printf("%d. %s", index++, event.getName());
            if (qty > 1 || isOnline) {
                System.out.printf("  X  %d  %s", qty, isOnline ? "Online" : "In-person");
            }
            System.out.printf("  : $%.2f\n", total);
        }
        System.out.printf("%d. Cancel\n", index); // Option to cancel

        int choice = -1;
        while (true) { // Get user's selection
            System.out.print("Please select: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= bookings.size()) { // Valid selection
                    Booking bookingToRemove = bookings.get(choice - 1);
                    boolean success = eventManager.removeBooking(choice - 1); // Remove booking
                    if (success) {
                        System.out.printf(">> Booking %s removed. Go back to the main menu.\n", bookingToRemove.getEvent().getName());
                    } else {
                        System.out.println("Failed to remove booking.");
                    }
                    break;
                } else if (choice == bookings.size() + 1) {
                    System.out.println("Cancelled. Returning to menu.");
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    public void checkout(EventManager eventManager, Scanner scanner) { // Checkout process
        List<Booking> bookings = eventManager.getBookedEvents(); // Get bookings

        if (bookings.isEmpty()) { // No bookings to checkout
            System.out.println("No bookings to checkout.");
            return;
        }

        double totalPrice = 0;
        int totalTickets = 0;

        System.out.println("Your current bookings:");
        int index = 1;
        for (Booking booking : bookings) { // Display bookings
            Event event = booking.getEvent();
            int qty = booking.getQuantity();
            boolean isOnline = booking.isOnline();

            totalPrice += qty * event.getPrice();
            totalTickets += qty;

            System.out.printf("%d. %s  X  %d  %s\n", index++, event.getName(), qty, isOnline ? "Online" : "In-person");
        }

        System.out.printf("Total: $%.2f for %d tickets\n", totalPrice, totalTickets);
        System.out.print("Proceed? (y/n) ");

        String proceed = scanner.nextLine().trim().toLowerCase();
        if (proceed.equals("y")) { // Proceed with checkout
            System.out.printf(": $%.2f\n", totalPrice);
            System.out.println("Thank you! Tickets have been sent to your registered email address.");
            eventManager.clearBookings(); // Clear bookings
            eventManager.setHasCheckedOut(true);
        } else {
            System.out.println("Checkout cancelled. Returning to main menu.");
        }
    }

    public void quit(EventManager eventManager, Scanner scanner) { // Quit the system
        if (!eventManager.hasCheckedOut() && !eventManager.getBookedEvents().isEmpty()) {
            System.out.println("Go back! You have unpaid booking.");
        } else {
            System.out.println("Goodbye!");
            System.exit(0); // Exit the application
        }
    }
}
