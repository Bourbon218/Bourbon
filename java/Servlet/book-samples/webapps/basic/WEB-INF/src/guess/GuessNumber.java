package guess;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GuessNumber {

    /**
     * 当たりの数字
     */
    private String numberText;

    /**
     * 試行回数
     */
    private int count;

    /**
     * 入力数字
     */
    private String inputText;

    /**
     * 入力エラーメッセージ。
     * 入力エラーがあるかの判定にも利用
     */
    private String inputErrorMessage;

    /**
     * ヒントのヒット数
     */
    private int hit;

    /**
     * ヒントのエラー数
     */
    private int error;

    
    /**
     * セッションスコープから、
     * このクラスのインスタンスを取得します。
     */
    public static GuessNumber getInstanceFromSession(
            HttpServletRequest request) {
        HttpSession session = request.getSession();
        GuessNumber guess = (GuessNumber) session
                .getAttribute("guess");
        if (guess == null) {
            guess = new GuessNumber();
            session.setAttribute("guess", guess);
        }
        return guess;
    }

    
    GuessNumber() {
        reset();
    }

    
    /**
     * このオブジェクトを初期化します
     */
    public void reset() {
        numberText = "";
        for (int i = 0; i < 4; i++) {
            while (true) {
                String oneNumber = createOneNumber();
                if (numberText.indexOf(oneNumber) == -1) {
                    numberText += oneNumber;
                    break;
                }
            }
        }

        count = 0;
        inputErrorMessage = null;
    }


    /**
     * デバッグ用
     */
    public String toString() {
        return super.toString() 
            + ",number=" + numberText
            + ",input=" + inputText 
            + ",hit=" + hit
            + ",error=" + error;
    }

    
    /**
     * 入力テキストをセットする
     * テキストのエラーがないかチェックする
     */
    public void setInput(String text) {

        inputText = text;

        if (text == null || !text.matches("\\d{4}")) {
            inputErrorMessage = inputText
                    + " は4桁の数字ではありません";
            return;
        }

        inputErrorMessage = null;
    }
    
    /**
     * 入力エラーがあるか？
     */
    public boolean isInputError() {
        return inputErrorMessage != null;
    }
    
    /**
     * 数字の判定を行い、ヒット、エラー数を計算する
     */
    public void challenge() {
        count++;

        hit = 0;
        error = 0;

        for (int i = 0; i < inputText.length(); i++) {
            char c = inputText.charAt(i);

            if (numberText.charAt(i) == c) {
                hit++;
            } else if (numberText.indexOf(c) != -1) {

                error++;
            }
        }
    }

    /**
     * すべてヒットか？
     */
    public boolean isAllHit() {
        return hit == 4;
    }

    

      
    //ゲッタ、その他のメソッド群
  
    
    public int getCount() {
        return count;
    }

    public String getNumber() {
        return numberText;
    }

    public String getInput() {
        return inputText;
    }
    
    public String getInputErrorMessage() {
        return inputErrorMessage;
    }

    public int getHit() {
        return hit;
    }

    public int getError() {
        return error;
    }

    private String createOneNumber() {
        int n = new Random().nextInt(10);
        return String.valueOf(n);
    }

}
