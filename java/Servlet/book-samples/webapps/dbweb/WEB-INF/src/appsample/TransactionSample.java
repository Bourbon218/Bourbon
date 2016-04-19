package appsample;

import java.sql.Connection;
import java.sql.Statement;


public class TransactionSample {

    public static void main(String[] args) throws Exception {

        Connection con = DBManager.getConnection();

        con.setAutoCommit(false);
        Statement smt = con.createStatement();

        smt.executeUpdate("UPDATE ACCOUNT "
                + "SET MONEY=MONEY-1000 WHERE ID=1");
        smt.executeUpdate("UPDATE ACCOUNT "
                + "SET MONEY=MONEY+1000 WHERE ID=2");

        smt.close();
        con.commit();
        con.close();
    }
}
