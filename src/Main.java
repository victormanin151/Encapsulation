import java.util.Scanner;
import menu.PrintMenu;
import static service.RoomService.viewRooms;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean run = true;
        while(run){
            PrintMenu.printMenu();
            int choice = Integer.parseInt(s.nextLine());
            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    registerUser();
                    break;
                case 5:
                    loginUser();
                    break;
                case 6:
                    run = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

//    private static void loginUser() {
//    }
//
//    private static void registerUser() {
//    }
//
//    private static void cancelBooking() {
//    }
//
//    private static void bookRoom() {
//    }
//
//    private static void viewRooms() {
//    }

}
