package wiki;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fw.DBManager;
import fw.ResultSetBeanMapping;



public class WikiPageDAO {
    
	/**
	 * ResultSetの一行をWikiPageのインスタンスに変換するクラス
	 * ここでしか利用されないので、
	 * 無名クラスとして、この場所でインスタンス化します。
	 */
    private ResultSetBeanMapping<WikiPage> allMapping = new ResultSetBeanMapping<WikiPage>(){

        public WikiPage createFromResultSet(ResultSet rs) 
        	throws SQLException {
    		
            WikiPage page = new WikiPage();
    		page.setName(rs.getString("name"));
    		page.setContent(rs.getString("content"));
    		page.setUpdateTime(rs.getTimestamp("update_time"));
    		return page;
        }
        
    };
    
    
    /**
     * このクラスのインスタンスを取得します。
     */
    public static WikiPageDAO getInstance(){
        return new WikiPageDAO();
    }


    /**
     * 全件検索を行います。
     * 戻り値はWikiPageオブジェクトのListです。
     */
	public List<WikiPage> findAll() throws SQLException {

		String sql = "select * from wiki_page"
		    	+ " order by update_time desc";

		return DBManager.simpleFind(sql, allMapping);
	}
	
	
    /**
     * 指定した言葉に部分一致するレコードを検索します。
     * 戻り値はWikiPageオブジェクトのListです。
     */
	public List<WikiPage> findByWord(String word) throws SQLException{
		String sql = "select * from wiki_page"
		    + " where content like '%" +word+ "%'";
		
		return DBManager.simpleFind(sql, allMapping);
	}
	
	
    /**
     * 指定した名前に一致するレコードを検索します。
     */
	public WikiPage findByName(String name) throws SQLException{
		String sql = "select * from wiki_page"
		    + " where name='" +name+ "'";

		
		List<WikiPage> list = DBManager.simpleFind(sql, allMapping);
		if(list.size() == 0){
		    return null;
		}else if(list.size() > 1){
		    throw new IllegalArgumentException("record > 1");
		}
		
		return list.get(0);
	}
	
	
	/**
	 * 指定したWikiPageを元にINSERTを実行します。
	 */
	public void insert(WikiPage page) throws SQLException {
	    String sql = "insert into wiki_page (name, content)" 
	        + " values("
	        	+ "'" + page.getName() + "'"
	        	+ ",'" + page.getContent() + "'"
	        + ")"
	        ;	    
	    DBManager.simpleUpdate(sql);	
	}
	
		
	/**
	 * 指定したWikiPageを元にUPDATEを実行します。
	 */
	public void update(WikiPage page) throws SQLException {
	    String sql = "update wiki_page " 
	        + " set "
	        	+ " content='" + page.getContent() + "'"
	        + " where name='" + page.getName() + "'";
	    DBManager.simpleUpdate(sql);	    
	}
		
	
	/**
	 * 指定したWikiPageを元にDELETEを実行します。
	 */
	public void delete(WikiPage page) throws SQLException {
	    String sql = "delete from wiki_page where name='" 
	        + page.getName() + "'";
	    DBManager.simpleUpdate(sql);
	}
	
	
	//動作確認用
	public static void main(String[] args) throws Exception{
	    WikiPageDAO dao = new WikiPageDAO();	    
	    List<WikiPage> list = dao.findAll();
	    for(int i=0; i<list.size(); i++){
	        WikiPage page = list.get(i);
	        System.out.println(page);
	    }
	}
}
