package Model;

public class SharedUsernameModel {
    private static String username;
    private static String fullname;
    private static String firstName;
    private static String lastName;
    private static String password;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String sharedusername) {
        username = sharedusername;
    }

	public static String getFullname() {
		return fullname;
	}

	public static void setFullname(String sharedfullname) {
		fullname = sharedfullname;
	}

	public static String getFirstName() {
		return firstName;
	}

	public static void setFirstName(String sharedfirstName) {
		firstName = sharedfirstName;
	}
	
	public static String getlastName() {
		return lastName;
	}

	public static void setlastName(String sharedlastName) {
		lastName = sharedlastName;
	}
	
	public static String getpassword() {
		return password;
	}

	public static void setpassword(String sharedpassword) {
		password = sharedpassword;
	}
}

