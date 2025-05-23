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
                " (" + roomType + ")\n:for " + numberOfNights + "number of night." +
                "\nTotal: $" + totalPrice*numberOfNights;
    }
}
