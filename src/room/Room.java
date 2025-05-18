package room;

public class Room {
    private String roomNumber;
    private String type;
    private double pricePerNight;
    private double cancellationFee;
    private RoomStatus status;
    private int maxOccupancy;
    private String[] amenities;

    //public Room (String roomNumber, String type,double pricePerNight)

    public RoomStatus getStatus() {
        return status;
    }

    public String toString() {
        return "Room " + roomNumber + " (" + type + ") - $" + pricePerNight + "/night, Status: " + status;
    }
}
