package booking;

import user.User;

public class Booking {

    private String bookingId;
    private String username;
    private String roomNumber;
    private String roomType;
    private double numberOfNights;
    private double totalPrice;

    public Booking(String bookingId, String username, String roomNumber, String roomType,
                   double numberOfNights, double totalPrice) {
        this.bookingId = bookingId;
        this.username = username;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.numberOfNights = numberOfNights;
        this.totalPrice = totalPrice;
    }

    public String toString() {
        return "Booking ID: " + bookingId + "\nUser: " + username + "\nRoom: " + roomNumber +
                " (" + roomType + ")\nBooked for " + (int) numberOfNights + " night(s)." +
                "\nTotal: $" + totalPrice*numberOfNights;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getBookingId(){
        return bookingId;
    }

    public double getNumberOfNights(){
        return numberOfNights;
    }

    public Object getUsername() {
        return username;
    }
}
