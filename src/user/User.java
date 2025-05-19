package user;


public class User {
    private String username;
    private String password;
    private String contactNumber;
    private String email;
    private String role;

    public User() {
    }

    public User(String username, String password, String contactNumber, String email, String role) {
        this.username = username;
        this.password = password;
        this.contactNumber = contactNumber;
        this.email = email;
        this.role = role;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
