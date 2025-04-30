Super Event Ticketing System
A Java-based console application for managing event ticket bookings, built with a strong focus on Object-Oriented Programming (OOP) principles. This project was developed as part of the Further Programming 1 course at RMIT University.

📦 Features
List all available events

Search for events by keyword (case-insensitive)

Book tickets (supports both in-person and online options)

View current bookings and total cost

Remove bookings

Checkout and finalize ticket purchases

Input validation and user-friendly error messages

⚙️ How It Works
This application runs entirely in the console using a menu-driven interface.

Users can search for events by keyword, choose online or in-person attendance, and select ticket quantity.

Bookings are stored temporarily and reflected in the seat availability during the session.

The checkout process finalizes the bookings and updates event status accordingly.

🧠 OOP Design Highlights
This system is designed with clean object-oriented principles:

Encapsulation: All fields are private, accessed via well-defined getters and setters.

Inheritance: Core behavior abstracted into a Manager<T> superclass for reusability.

Abstraction: Uses an interface (Searchable<T>) to support flexible event searching.

Cohesion & Modularity: Well-separated responsibilities across classes such as Event, Booking, and EventManager.

🛠 Technologies Used
Java

No external libraries (per assignment constraints)

📂 Project Structure
css
Copy
src/
└── FPAssignment1/
    ├── Event.java
    ├── Booking.java
    ├── EventManager.java
    ├── Manager.java
    ├── Searchable.java
    └── Main.java
    
🚀 Running the Program
Open the project in Eclipse or your Java IDE.

Run Main.java to start the console app.

Use the menu options to book events, view current bookings, remove entries, or proceed to checkout.

