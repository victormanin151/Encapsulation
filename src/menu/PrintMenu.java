package menu;
import user.User;

public class PrintMenu{
    public static void printMenu(User currentUser) {

        if (currentUser == null){
            System.out.println("\nWelcome to the Hotel Reservation System!");
            System.out.println("Please choose an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");
        } else{
            System.out.println("\nWelcome, " + currentUser.getUsername() + "!");
            System.out.println("Please choose an option:");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
            System.out.print("Your choice: ");
        }
    }
}

