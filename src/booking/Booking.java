package booking;

public class Booking {
    private String bookingId;
    private String username;
    private String roomNumber;
    private String roomType;
    private String checkInDate;
    private String checkOutDate;
    private double totalPrice;

    public Booking(String bookingId, String username, String roomNumber, String roomType,
                   String checkInDate, String checkOutDate, double totalPrice) {
        this.bookingId = bookingId;
        this.username = username;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
    }

    public String toString() {
        return "Booking ID: " + bookingId + "\nUser: " + username + "\nRoom: " + roomNumber +
                " (" + roomType + ")\nFrom: " + checkInDate + " to " + checkOutDate +
                "\nTotal: $" + totalPrice;
    }
}
