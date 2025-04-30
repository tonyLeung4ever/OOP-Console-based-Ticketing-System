package FPAssignment1;

import java.util.Scanner;

// Main class serves as the entry point of the Super Event system, allowing users to interact with the event management system.
// The program runs a menu-driven interface, where users can manage events, make bookings, and handle other functionalities.

public class Main {
    public static void main(String args[]) {
        // Creating a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        
        // Creating an instance of EventManager to manage event-related functionalities
        EventManager eventManager = new EventManager();
        
        // Displaying the welcome message to the user
        System.out.println("===============================================\n");
        System.out.println("Welcome to Super Event!\n");
        System.out.println("===============================================");
        
        // Creating an instance of the Menu class to display the menu options
        Menu menu = new Menu();
        
        // Infinite loop to keep the program running and show the menu until the user chooses to quit
        while (true) {
            // Display the menu to the user
            menu.displayMenu();
            
            // Reading the user's choice
            String choice = scanner.nextLine();
            
            // Handling the user's choice using a switch statement
            switch (choice) {
                case "1":
                    // List all available events
                    menu.listAllEvents(eventManager);
                    break;
                case "2":
                    // Allow the user to book a ticket for an event
                    menu.bookTicket(eventManager, scanner);
                    break;
                case "3":
                    // View all the bookings made by the user
                    menu.viewMyBookings(eventManager);
                    break;
                case "4":
                    // Allow the user to remove a booking
                    menu.removeBooking(eventManager, scanner);
                    break;
                case "5":
                    // Allow the user to proceed to checkout
                    menu.checkout(eventManager, scanner);
                    break;
                case "6":
                    // Exit the program
                    menu.quit(eventManager, scanner);
                    break;
                default:
                    // Inform the user if they entered an invalid option
                    System.out.println("Invalid input! Please try again.");
            }
        }
    }
}
