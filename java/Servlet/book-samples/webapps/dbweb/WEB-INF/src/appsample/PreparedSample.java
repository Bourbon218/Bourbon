package appsample;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class PreparedSample {

    public static void main(String[] args) throws Exception {

    	String sql = "UPDATE ACCOUNT SET MONEY=? WHERE NAME=?";
    	
        Connection con = DBManager.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setInt(1, 3000);
        ps.setString(2, "ƒ{ƒu");
        
        int count = ps.executeUpdate();
        
        System.out.println("update count : " + count);

        ps.close();
        con.close();
    }
}
