package validate;

public class Eto {

    final static String[] etoArray = new String[]{
            "子(ね)",
            "丑(うし)",
            "寅(とら)",
            "卯(う)",
            "辰(たつ)",
            "巳(み)",
            "午(うま)",
            "未(ひつじ)",
            "申(さる)",
            "酉(とり)",
            "戌(いぬ)",
            "亥(い)",
    };
    
    /**
     * エラーメッセージ
     */
    private String error;

    /**
     * 西暦(入力文字列)
     */
    private String yearStr;

    /**
     * 干支
     */
    private String etoText;
        

    public void setYearStr(String yearStr){       
        this.yearStr = yearStr;
    }
    
    public void validate(){
        if(yearStr == null || yearStr.length() == 0){
            error = "何か入れてね";
            return;
        }
        
        try{
            //数値チェック＆数値に変換
            int year = Integer.parseInt(yearStr);
            //妥当性チェック
            if(year < 0){
                error = "紀元前の人はサポート外です";
            }else{
            	int index = (year + 8) % 12;   
            	etoText = etoArray[index];
            }
        }catch(NumberFormatException e){
            error = "年を入れてください";
        }
    }
    
    /**
     * エラー文字列を取得する。
     * エラーがない場合はnull
     */
    public String getError(){
        return error;
    }
    
    /**
     * 干支の文字列を取得する
     */
    public String getEtoText(){        
        return etoText;
    }
}
