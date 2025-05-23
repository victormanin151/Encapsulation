import java.util.Scanner;
import menu.PrintMenu;
import static service.RoomService.viewRooms;

import service.BookingService;
import service.UserService;
import user.User;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean run = true;
        User currentUser = null;
        while (run) {
            PrintMenu.printMenu(currentUser);
            String input = s.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            if (currentUser == null) {
                switch (choice) {
                    case 1:
                        UserService.registerUser(s);
                        break;
                    case 2:
                        currentUser = UserService.loginUser(s);
                        break;
                    case 3:
                        run = false;
                        System.out.println("Exiting program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                switch (choice) {
                    case 1:
                        viewRooms();
                        break;
                    case 2:
                        BookingService.bookRoom(s, currentUser);
                        break;
                    case 3:
                        System.out.println("Show existing bookings");
                        break;
                    case 4:
                        BookingService.cancelBooking(s, currentUser);
                        break;
                    case 5:
                        System.out.println("Logging out...");
                        currentUser = null;
                        break;
                    case 6:
                        run = false;
                        System.out.println("Exiting program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
