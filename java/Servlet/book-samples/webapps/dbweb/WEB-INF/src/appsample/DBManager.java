package appsample;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBManager  {

	public static Connection getConnection() {
		try {
		    Class.forName("org.hsqldb.jdbcDriver");
		    Connection con = DriverManager.getConnection(
		    		"jdbc:hsqldb:hsql://localhost", "sa", "");

		    return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}	
	
	
	public static void main(String[] args) throws Exception {
        Connection con = getConnection();
        System.out.println("con=" + con);
        con.close();
    }

}
