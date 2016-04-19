package websample;

import java.sql.SQLException;
import java.util.List;


public class AccountDAO {

	/**
	 * 全件検索を行う。
	 * 戻り値はAccountオブジェクトのList
	 */
    public List<Account> findAll() {
    	String sql = "SELECT * FROM ACCOUNT";
        try {
            return DBManager3.simpleFind(sql, 
            		new AccountMapping() );
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } 
    }
        

    /**
     * 引数のAccountオブジェクトを元にINSERT処理を行う
     */
    public int insert(Account account){
    	String sql = "INSERT INTO ACCOUNT(ID, NAME, MONEY)" 
    		+ " VALUES(" 
			+ account.getId()
			+ ",'" +account.getName()
			+ "'," +account.getMoney()
			+ ")"
			;
        try {
            return DBManager3.simpleUpdate(sql);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    

    //必要に応じてメソッドを追加していく
}
