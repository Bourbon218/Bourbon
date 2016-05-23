/***
 ** g28610(杉山研)　鴫原颯矢
 ** 記憶パターンから反転させる成分の数(noise)を指定する場合
 ***/

import java.util.Random;

public class Hopfield {

	public static void main(String[] args) {

		/* コマンドライン引数(args[0]とargs[1])に値が代入されていない場合 */
		if(args.length < 2){
			System.out.println("Usage: Hopfield ニューロン数 反転させる成分数");
			System.exit(0);
		}

		int N;                                //ニューロン数
		int noise;                            //記憶パターンから反転させる数
		N = Integer.parseInt(args[0]);        //コマンドライン引数からニューロン数代入
		noise = Integer.parseInt(args[1]);    //コマンドライン引数から反転数代入

		int nline=8;                          //改行するポイント(デフォルトで8)
		int i,j,no;                           //カウント変数
		int step;                             //ステップ数
		double[] x = new double[N];           //ニューロンの出力状態
		double[] pat = new double[N];         //記憶パターン
		double[][] w = new double[N][N];      //結合荷重
		double tmp;                           //総入力
		int percent;                          //一致度
		boolean[] reversal = new boolean[N];  //反転可能かどうかの判定(一度反転させたら反転させない)

		Random rnd = new Random();            //乱数発生させるインスタンス		

		//改行するポイントの設定
		for(int a=1; a<=N/2; a++){
			for(int b=1; (b<=N)||(b<a); b++){
				if( (a*b) == N ){             //a*b=cのとき
					if(a==b){ nline = b; }    //aとbが等しいとき
				}
			}
		}

		//反転可能判定の初期化
		for(i=0; i<N; i++){ reversal[i] = true; }

		/* 記憶パターン初期化 */
		/**※注意1記憶パターンとしてわかりやすいパターンを使用すること**/
		/**記憶パターンその1**/
		for(i=0; i<N/2; i++){ pat[i] = 1.0; }   //記憶パターンの前半部分を1.0で初期化
		for(i=N/2; i<N; i++){ pat[i] = -1.0; }  //記憶パターンの後半部分を-1.0で初期化

		/* 記憶パターン表示 */
		System.out.println("---pattern---");
		for(i=0; i<N; i++){
			if(pat[i] > 0.0){ System.out.print("■ "); }        //記憶パターンが0.0より大きいとき"@ "と表示
			else{ System.out.print("□ "); }                    //記憶パターンが0.0以外のとき"- "と表示
			if( (i+1)%nline == 0 ){ System.out.println(""); }  //8個のニューロンの出力状態を表示したら改行
		}
		System.out.println("");


		/* 結合荷重(w[i][j])をを定義どおり記憶パターン(pat[i][j])を用いて求める */
		for(i=0; i<N; i++){
			for(j=0; j<N; j++){
				if( i!= j ){ w[i][j] = pat[i]*pat[j]/N; }
				else{  w[i][j] = 0.0; }
			}
		}

		/**
		 * ニューロンの出力状態の初期値をnoiseの数だけ反転させる
		 * 反転させる場所はランダム
		 * **/
		for(i=0; i<N; i++){ x[i] = pat[i]; }  //記憶パターンをニューロンの出力状態に代入
		for(i=0; i<noise; i++){
			//乱数発生
			int rand = rnd.nextInt(N);
			if(rand <=0){rand *= -1; }

			//反転させるか判定
			if(reversal[rand]){
				if(x[rand] == -1.0){ x[rand] = 1.0; }
				else{ x[rand] = -1.0; }
				reversal[rand] = false;  //反転させたら再び反転させないようにfalseにする
			}
			else{ i--; }
		}

		System.out.println("記憶パターンからの反転数　:" + noise);
		System.out.println("");

		//一致度計算
		percent = (int)((double)(N-noise)/N*100);

		/* 記憶パターン表示 */
		System.out.println("-initial state-");                 //初期状態
		for(i=0; i<N; i++){
			if(x[i] > 0.0){ System.out.print("■ "); }          //ニューロンの出力状態が0.0より大きいとき"@ "と表示
			else{ System.out.print("□ "); }                    //ニューロンの出力状態が0.0以外のとき"- "と表示
			if( (i+1)%nline == 0 ){ System.out.println(""); }  //8個のニューロンの出力状態を表示したら改行
		}
		System.out.println("");

		//一致度合い(%)
		System.out.println("一致度合い　　:" + percent + "%");
		System.out.println("");

		//update(2-1)
		for(step=0; step<10; step++){
			for(i=0; i<N; i++){ 
				//乱数発生(rnd.nextInt(n)で0~n-1の数字となります)
				int rand = rnd.nextInt();
				if(rand <=0){rand *= -1; }

				no = rand%N; 
				tmp = 0.0;
				for(j=0; j<N; j++){ tmp += w[no][j]*x[j]; }
				x[no] = (tmp >= 0.0 ? 1.0 : -1.0);
			}

			/* 記憶パターン表示 */
			System.out.println("----" + (step+1) + "step----");
			for(i=0; i<N; i++){
				if(x[i] > 0.0){ System.out.print("■ "); }          //ニューロンの出力状態が0.0より大きいとき"@ "と表示
				else{ System.out.print("□ "); }                    //ニューロンの出力状態が0.0以外のとき"- "と表示
				if( (i+1)%nline == 0 ){ System.out.println(""); }  //8個のニューロンの出力状態を表示したら改行
			}
			System.out.println("");
		}
		System.out.println("");

	}

}
