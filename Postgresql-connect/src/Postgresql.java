import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;;

public class Postgresql {


    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "ops");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully:\n");
        try {
            stmt = c.createStatement();
            String sql = "SELECT * FROM players;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int playerID = rs.getInt("player_id");
                String playerName = rs.getString("player_name");
                int playerScore = rs.getInt("player_score");
                System.out.printf("id\tname\tscore\n"
                        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                        + "%s\t%s\t%s\n", playerID, playerName, playerScore);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
