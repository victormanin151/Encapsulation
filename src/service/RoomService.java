package service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import room.Room;

import java.io.FileReader;
import java.util.List;

public class RoomService {
    public static void viewRooms() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader reader = new FileReader("rooms.json");

            List<Room> rooms = gson.fromJson(reader, new TypeToken<List<Room>>() {}.getType());

            System.out.println("Available Rooms:");
            for (Room room : rooms) {
                System.out.println(room.toString());
            }

            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading rooms file: " + e.getMessage());
        }
    }
}