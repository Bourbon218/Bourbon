package appsample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class SelectSample {

	public static void main(String[] args) throws Exception{
		
		Connection con = DBManager.getConnection();
		Statement smt = con.createStatement();
		ResultSet rs = smt.executeQuery("SELECT * FROM ACCOUNT");

		while(rs.next()){
			System.out.println(
				"ID=" + rs.getInt("ID")
				+ ",TITLE=" + rs.getString("NAME")
				+ ",PRICE=" + rs.getInt("MONEY")
			);
		}

		smt.close();
		con.close();
	}
}
