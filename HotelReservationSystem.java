import java.io.*;
import java.util.*;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean available;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }
}

class Booking {
    String customerName;
    int roomNumber;
    String category;
    double amount;

    public Booking(String customerName, int roomNumber, String category, double amount) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.category = category;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return customerName + "," + roomNumber + "," + category + "," + amount;
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        rooms.add(new Room(101, "Standard", 2000));
        rooms.add(new Room(102, "Deluxe", 3500));
        rooms.add(new Room(103, "Suite", 5000));

        while (true) {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelReservation();
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    saveBookings();
                    System.out.println("Thank You!");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.available) {
                System.out.println(
                        "Room " + room.roomNumber +
                        " | " + room.category +
                        " | ₹" + room.price);
            }
        }
    }

    static void bookRoom() {
        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();
        sc.nextLine();

        for (Room room : rooms) {
            if (room.roomNumber == roomNo && room.available) {

                System.out.print("Enter Customer Name: ");
                String name = sc.nextLine();

                System.out.println("Amount to Pay: ₹" + room.price);
                System.out.println("Payment Successful!");

                Booking booking = new Booking(
                        name,
                        room.roomNumber,
                        room.category,
                        room.price
                );

                bookings.add(booking);
                room.available = false;

                System.out.println("Room Booked Successfully!");
                return;
            }
        }

        System.out.println("Room Not Available!");
    }

    static void cancelReservation() {

        System.out.print("Enter Room Number to Cancel: ");
        int roomNo = sc.nextInt();

        Iterator<Booking> iterator = bookings.iterator();

        while (iterator.hasNext()) {
            Booking booking = iterator.next();

            if (booking.roomNumber == roomNo) {

                iterator.remove();

                for (Room room : rooms) {
                    if (room.roomNumber == roomNo) {
                        room.available = true;
                    }
                }

                System.out.println("Reservation Cancelled!");
                return;
            }
        }

        System.out.println("Booking Not Found!");
    }

    static void viewBookings() {

        System.out.println("\n===== BOOKING DETAILS =====");

        if (bookings.isEmpty()) {
            System.out.println("No Bookings Found!");
            return;
        }

        for (Booking booking : bookings) {
            System.out.println(
                    "Customer: " + booking.customerName +
                    " | Room: " + booking.roomNumber +
                    " | Category: " + booking.category +
                    " | Amount Paid: ₹" + booking.amount
            );
        }
    }

    static void saveBookings() {
        try {
            FileWriter writer = new FileWriter("bookings.txt");

            for (Booking booking : bookings) {
                writer.write(booking.toString() + "\n");
            }

            writer.close();
            System.out.println("Bookings Saved Successfully!");
        } catch (IOException e) {
            System.out.println("Error Saving Bookings!");
        }
    }
}
