package room;

public class Room {
    private String roomNumber;
    private String type;
    private double pricePerNight;
    private double cancellationFee;
    private RoomStatus status;
    private int maxOccupancy;
    private String[] amenities;

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public String toString() {
        return "Room " + roomNumber + " (" + type + ") - $" + pricePerNight + "/night, Status: " + status;
    }

    public String getType() {
        return type;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

}
