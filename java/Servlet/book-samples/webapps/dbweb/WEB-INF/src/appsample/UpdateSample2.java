package appsample;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateSample2 {

    public static void main(String[] args) throws Exception {

        Connection con = null;
        Statement smt = null;

        try {
            con = DBManager.getConnection();
            smt = con.createStatement();
            int count = smt.executeUpdate("UPDATE ACCOUNT SET MONEY=5000");

            System.out.println("update count : " + count);

            smt.close();
            con.close();

        } finally {

            if (smt != null) {
                try {
                    smt.close();
                } catch (SQLException ignore) {
                    //‰½‚à‚µ‚È‚¢
                }
            }
            if (con != null) {

                try {
                    con.close();
                } catch (SQLException ignore) {
                    //‰½‚à‚µ‚È‚¢
                }
            }
        }
    }
}
