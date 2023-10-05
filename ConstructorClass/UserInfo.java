package ConstructorClass;

public class UserInfo {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    // Constructor to initialize user information
    public UserInfo(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getter methods to access user information
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Setter methods to modify user information if needed
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        // Override the toString method to provide a string representation of the user information
        return "Username: " + username + "\nPassword: " + password +
               "\nFirst Name: " + firstName + "\nLast Name: " + lastName;
    }
}
