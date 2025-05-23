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
}

