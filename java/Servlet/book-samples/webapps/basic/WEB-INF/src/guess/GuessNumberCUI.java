package guess;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GuessNumberCUI {

    public static void main(String[] args) throws Exception {

        GuessNumber guess = new GuessNumber();
 
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("4桁の数字を入力してください");
       
        String line;
        while ((line = in.readLine()) != null) {

            guess.setInput(line);//入力数字をセット
            
            if (guess.isInputError()) {//入力エラー？
                System.out.println(guess
                        .getInputErrorMessage());
                continue;
            } 
      
            guess.challenge();//当たり判定実行
                
            if(guess.isAllHit()){//当たり？
                System.out.println("あたり！");
                break;
                
            }else{//はずれ
                String hint 
                	= guess.getCount() + "回目"
                    + "hit=" + guess.getHit()
                    + ",error=" + guess.getError();
                System.out.println(hint);
            }
        }
    }

}
