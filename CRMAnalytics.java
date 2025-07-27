import java.sql.*;

class CRMAnalytics {

    public static void main(String[] args) {

        try {
            String url = "jdbc:mysql://localhost:3306/db1";
            String user = "root";
            String password = "0000";

            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            // 1. Open Opportunities by Stage and Potential Value
            runQuery1(connection, "Open Opportunities by Stage and Potential Value",
                "SELECT Stage, SUM(Value) AS TotalValue " +
                "FROM Opportunity WHERE Stage = 'Open' " +
                "GROUP BY Stage");

            // 2. Leads converted to opportunities per month
            runQuery2(connection, "Leads Converted Per Month",
                "SELECT DATE_FORMAT(ConversionDate, '%Y-%m') AS Month, COUNT(*) AS ConvertedCount " +
                "FROM LeadConversion " + 
                "GROUP BY Month " + 
                "ORDER BY Month");

            // 3. Interaction counts per account (Customer)
            runQuery3(connection, "Interactions Per Account",
                "SELECT CustomerID, COUNT(*) AS InteractionCount " +
                "FROM Interaction " + 
                "GROUP BY CustomerID");

            // 4. Overdue tasks and follow-ups
            runQuery4(connection, "Overdue Tasks by User",
                "SELECT UserID, OverdueCount " + 
                "FROM UserOverdueTasks");

            // 5. Forecast vs actual sales
            runQuery5(connection, "Forecast vs Actual Sales",
                "SELECT UserID, Month, ExpectedSales, ActualSales " +
                "FROM Forecast " + 
                "ORDER BY Month");

            // 6. Top performers by closed deals
            runQuery6(connection, "Top Performers by Closed Deals",
                "SELECT UserID, ClosedWon FROM UserPerformance " +
                "ORDER BY ClosedWon DESC LIMIT 5");

        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    private static void runQuery1(Connection connection, String title, String query) throws SQLException {
        System.out.println("\n~~ " + title + " ~~");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Stage ------ TotalValue");

            while(resultSet.next()) {
                System.out.println(resultSet.getString(1) + " ------ " + resultSet.getInt(2));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    private static void runQuery2(Connection connection, String title, String query) throws SQLException {
        System.out.println("\n~~ " + title + " ~~");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Month ------ ConvertedCount");

            while(resultSet.next()) {
                System.out.println(resultSet.getString(1) + " ------ " + resultSet.getInt(2));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    private static void runQuery3(Connection connection, String title, String query) throws SQLException {
        System.out.println("\n~~ " + title + " ~~");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("CustomerID ------ InteractionCount");

            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " ------ " + resultSet.getInt(2));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    private static void runQuery4(Connection connection, String title, String query) throws SQLException {
        System.out.println("\n~~ " + title + " ~~");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("UserId ------ OverdueCount");

            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " ------ " + resultSet.getInt(2));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    private static void runQuery5(Connection connection, String title, String query) throws SQLException {
        System.out.println("\n~~ " + title + " ~~");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("UserID ------ Month ------ ExpectedSales ------ ActualSales");

            while(resultSet.next()) {
                System.out.println(resultSet.getString(1) + " ------ " + resultSet.getInt(2));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    private static void runQuery6(Connection connection, String title, String query) throws SQLException {
        System.out.println("\n~~ " + title + " ~~");

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("UserID ------ ClosedWon");

            while(resultSet.next()) {
                System.out.println(resultSet.getString(1) + " ------ " + resultSet.getInt(2));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
}