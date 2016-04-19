package fw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DBManager  {

    private static String url = "jdbc:hsqldb:hsql://localhost";

    /**
     * コネクションを取得します。
     */
    public static Connection getConnection() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection con = DriverManager.getConnection(
                    url, "sa",
                    "");
            return con;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    
    
	/**
	 * 検索SQLを発行して、結果をオブジェクトのListに入れて返す。
	 * ResultSetからListへの変換は、ResultSetBeanMappingが行う
	 */
    public static <T> List<T> simpleFind(String sql, ResultSetBeanMapping<T> mapping) 
    throws SQLException{

    	Connection con = null;
	    Statement smt = null;
	    try{	        
			List<T> list = new ArrayList<T>();
			con = DBManager.getConnection();
			smt = con.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			
			while(rs.next()) {
			    
			    T bean = mapping.createFromResultSet(rs);

			    list.add(bean);
			}
			
			return list;
	        
	    }finally{
            if (smt != null) {
                try {smt.close();} catch (SQLException igonre) {}
            }
            if (con != null) {
                try {con.close();} catch (SQLException igonre) {}
            }
	    }
    }
    
    
	/**
	 * 更新SQLを発行する
	 */
	public static int simpleUpdate(String sql) throws SQLException{

	    System.out.println(sql);
	    
	    Connection con = null;
	    Statement smt = null;
	    try{
	        con = DBManager.getConnection();
	    	smt = con.createStatement();
	    	return smt.executeUpdate(sql);
	    }finally{
            if (smt != null) {
                try {smt.close();} catch (SQLException igonre) {}
            }
            if (con != null) {
                try {con.close();} catch (SQLException igonre) {}
            }
	    }
	}

}
