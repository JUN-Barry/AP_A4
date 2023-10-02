package Model;



import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckUserTableExist {
	public static void main(String[] args) {
		final String TABLE_NAME = "User_info";
        
		// java try-with-resource, key word
		try (Connection con = DataAnalyticsHubConnection.getConnection()) {

			DatabaseMetaData dbm = con.getMetaData();
			
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME, null);
				
			if(tables != null) {
				if (tables.next()) {
					System.out.println("Table " + TABLE_NAME + " exists.");
				}
				else {
					System.out.println("Table " + TABLE_NAME + " does not exist.");
				}
				tables.close(); // use close method to close ResultSet object
			} else {
				System.out.println("Problem with retrieving database metadata");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
