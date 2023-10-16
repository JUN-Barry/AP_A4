package Model;

import ConstructorClass.SharesCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieChartVisModel {
	
	private static PieChartVisModel instance = null;

    // Singleton pattern, ensures that only one instance of the class exists throughout your application's lifecycle
    private PieChartVisModel() {
    }

    // Public method to access the instance (Singleton pattern)
    public static PieChartVisModel getInstance() {
        if (instance == null) {
            instance = new PieChartVisModel();
        }
        return instance;
    }

    public static List<SharesCategory> PostShareCategory() {
        String sql = "SELECT\n" +
                     "  CASE\n" +
                     "    WHEN shares >= 0 AND shares <= 99 THEN 'Shares 0-99'\n" +
                     "    WHEN shares BETWEEN 100 AND 999 THEN 'Shares 100-999'\n" +
                     "    WHEN shares >= 1000 THEN 'Shares 1000+'\n" +
                     "  END AS category,\n" +
                     "  COUNT(*) AS count\n" +
                     "FROM Post_info\n" +
                     "GROUP BY category";

        List<SharesCategory> data = new ArrayList<>();

        try (Connection con = DataAnalyticsHubConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String category = resultSet.getString("category");
                int count = resultSet.getInt("count");
//                System.out.println(category);
//                System.out.println(count);

                // Create a SharesCategory object and add it to the list
                SharesCategory categoryData = new SharesCategory(category, count);
                data.add(categoryData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}

