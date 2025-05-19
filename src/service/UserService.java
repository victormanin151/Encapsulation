package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import user.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {

    private static final String USERS_FILE = "users.json";

    public static void registerUser(Scanner s) {
        Gson gson = new Gson();

        try {
            System.out.print("Enter a username: ");
            String username = s.nextLine();

            System.out.print("Enter a password: ");
            String password = s.nextLine();

            System.out.print("Enter contact number (optional): ");
            String contactNumber = s.nextLine();

            System.out.print("Enter email (optional): ");
            String email = s.nextLine();

            List<User> users;
            try (FileReader reader = new FileReader(USERS_FILE)) {
                users = gson.fromJson(reader, new TypeToken<List<User>>() {
                }.getType());
                if (users == null) users = new ArrayList<>();
            }
            for (User user : users) {
                if (user.getUsername().equalsIgnoreCase(username)) {
                    System.out.println("Username already exists. Try another.");
                    return;
                }
            }
            User newUser = new User(username, password, contactNumber, email, "user");
            users.add(newUser);

            try (FileWriter writer = new FileWriter(USERS_FILE)) {
                gson.toJson(users, writer);
            }

            System.out.println("Registration successful!");

        } catch (Exception e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }
    public static User loginUser(Scanner s) {
        Gson gson = new Gson();

        System.out.println("=== Login ===");
        System.out.print("Enter username: ");
        String username = s.nextLine();

        System.out.print("Enter password: ");
        String password = s.nextLine();

        try (FileReader reader = new FileReader(USERS_FILE)) {
            List<User> users = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
            if (users != null) {
                for (User user : users) {
                    if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                        System.out.println("Login successful!");
                        return user;
                    }
                }
            }
            System.out.println("Invalid username or password.");
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }

        return null;
    }

}
