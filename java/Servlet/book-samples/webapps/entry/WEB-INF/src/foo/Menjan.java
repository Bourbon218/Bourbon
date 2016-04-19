package foo;
import java.util.Random;

public class Menjan {
    final static String[] words = new String[]{
            "シャー専用","ザク","はじめての","チュー",
            "極道の","妻たち","さよなら","三角","おはよう",
            "101回目の","プロポーズ","池中玄太","80キロ",
            "姉さん","ピンチです","白い","赤い", 
        };

    public String getSingleWord() {
        int index = new Random().nextInt(words.length);
        return words[index];
    }

	public String getWord(){
		return getSingleWord() + " " + getSingleWord();
	}

}
