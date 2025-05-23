package service;
import booking.Booking;
import com.google.gson.GsonBuilder;
import room.Room;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import room.RoomStatus;
import user.User;

import java.io.*;
import java.util.*;

public class BookingService {
    public static void bookRoom(Scanner s, User currentUser) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {

            System.out.print("Enter room type (Deluxe, Suite, Single, Double): ");
            String roomType = s.nextLine();

            System.out.print("Enter number of nights:  ");
            double numberOfNights = Double.parseDouble(s.nextLine());

            FileReader reader = new FileReader("rooms.json");
            List<Room> rooms = gson.fromJson(reader, new TypeToken<List<Room>>() {}.getType());
            reader.close();

            List<Room> availableRooms = new ArrayList<>();
            for (Room room : rooms) {
                if (room.getType().equalsIgnoreCase(roomType) && room.getStatus() == RoomStatus.AVAILABLE) {
                    availableRooms.add(room);
                }
            }

            if (availableRooms.isEmpty()) {
                System.out.println("No available rooms of that type.");
                return;
            }

            System.out.println("Available rooms:");
            for (Room room : availableRooms) {
                System.out.println(room.getRoomNumber() + " - $" + room.getPricePerNight() + "/night");
            }

            System.out.print("Enter the room number you want to book: ");
            String chosenRoomNumber = s.nextLine();

            Room selectedRoom = null;
            for (Room room : availableRooms) {
                if (room.getRoomNumber().equalsIgnoreCase(chosenRoomNumber)) {
                    selectedRoom = room;
                    break;
                }
            }

            if (selectedRoom == null) {
                System.out.println("Invalid room number.");
                return;
            }

            double totalPrice = selectedRoom.getPricePerNight();

            String bookingId = UUID.randomUUID().toString();
            Booking booking = new Booking(
                    bookingId,
                    currentUser.getUsername(),
                    selectedRoom.getRoomNumber(),
                    selectedRoom.getType(),
                    numberOfNights,
                    totalPrice
            );

            List<Booking> bookings = new ArrayList<>();
            File bookingsFile = new File("bookings.json");
            if (bookingsFile.exists()) {
                FileReader bookingReader = new FileReader(bookingsFile);
                bookings = gson.fromJson(bookingReader, new TypeToken<List<Booking>>() {}.getType());
                bookingReader.close();
            }
            if (bookings == null) {
                bookings = new ArrayList<>();
            }

            bookings.add(booking);
            FileWriter writer = new FileWriter("bookings.json");
            gson.toJson(bookings, writer);
            writer.close();

            for (Room room : rooms) {
                if (room.getRoomNumber().equalsIgnoreCase(selectedRoom.getRoomNumber())) {
                    room.setStatus(RoomStatus.BOOKED);
                    break;
                }
            }

            FileWriter roomWriter = new FileWriter("rooms.json");
            gson.toJson(rooms, roomWriter);
            roomWriter.close();

            System.out.println("Booking successful!");
            System.out.println(booking);

        } catch (Exception e) {
            System.out.println("Error while booking: " + e.getMessage());
        }
    }
    public static void cancelBooking(Scanner s, User currentUser) {
        Gson gson = new Gson();

        try {
            File bookingsFile = new File("bookings.json");
            File roomsFile = new File("rooms.json");

            if (!bookingsFile.exists()) {
                System.out.println("No bookings found.");
                return;
            }

            FileReader bookingReader = new FileReader(bookingsFile);
            List<Booking> bookings = gson.fromJson(bookingReader, new TypeToken<List<Booking>>() {}.getType());
            bookingReader.close();

            if (bookings == null || bookings.isEmpty()) {
                System.out.println("No bookings to cancel.");
                return;
            }

            List<Booking> userBookings = new ArrayList<>();
            for (Booking b : bookings) {
                if (b.getUsername().equals(currentUser.getUsername())) {
                    userBookings.add(b);
                }
            }

            if (userBookings.isEmpty()) {
                System.out.println("You have no bookings to cancel.");
                return;
            }

            System.out.println("Your bookings:");
            for (Booking b : userBookings) {
                System.out.println("Booking ID: " + b.getBookingId() + " | Room: " + b.getRoomNumber() +
                        " | for " + b.getNumberOfNights());
            }

            System.out.print("Enter the Booking ID to cancel: ");
            String bookingIdToCancel = s.nextLine();

            Booking bookingToCancel = null;
            for (Booking b : bookings) {
                if (b.getBookingId().equals(bookingIdToCancel) && b.getUsername().equals(currentUser.getUsername())) {
                    bookingToCancel = b;
                    break;
                }
            }

            if (bookingToCancel == null) {
                System.out.println("Booking not found.");
                return;
            }

            bookings.remove(bookingToCancel);

            FileReader roomReader = new FileReader(roomsFile);
            List<Room> rooms = gson.fromJson(roomReader, new TypeToken<List<Room>>() {}.getType());
            roomReader.close();

            for (Room r : rooms) {
                if (r.getRoomNumber().equals(bookingToCancel.getRoomNumber())) {
                    r.setStatus(RoomStatus.AVAILABLE);
                    break;
                }
            }

            FileWriter bookingWriter = new FileWriter(bookingsFile);
            gson.toJson(bookings, bookingWriter);
            bookingWriter.close();

            FileWriter roomWriter = new FileWriter(roomsFile);
            gson.toJson(rooms, roomWriter);
            roomWriter.close();

            System.out.println("Booking cancelled successfully.");

        } catch (Exception e) {
            System.out.println("Error cancelling booking: " + e.getMessage());
        }
    }

}

