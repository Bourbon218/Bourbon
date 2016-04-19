package websample;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapping implements ResultSetBeanMapping<Account> {

	public Account createFromResultSet(ResultSet rs) 
	throws SQLException {

		Account account = new Account();
		account.setId(rs.getInt("ID"));
		account.setName(rs.getString("NAME"));
		account.setMoney(rs.getInt("MONEY"));

		return account;
	}

}
