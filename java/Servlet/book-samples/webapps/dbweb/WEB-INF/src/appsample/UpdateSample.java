package appsample;

import java.sql.Connection;
import java.sql.Statement;



public class UpdateSample {

    public static void main(String[] args) throws Exception {

        Connection con = DBManager.getConnection();
        Statement smt = con.createStatement();
        int count = smt.executeUpdate("UPDATE ACCOUNT SET MONEY=5000");

        System.out.println("update count : " + count);

        smt.close();
        con.close();
    }
}
