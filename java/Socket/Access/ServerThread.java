import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

class ClientThread extends Thread {

	private int number;
	private Socket incoming;
	private InputStreamReader myIsr;
	private BufferedReader myBf;
	private PrintWriter myPw;



	public ClientThread(int n, Socket i, InputStreamReader isr, BufferedReader bf, PrintWriter pw) { //コンストラクタ(初期化)
		number = n; //クライアント番号
		incoming = i; //アクセス数
		myIsr = isr; //入力
		myBf = bf; //バッファリーダー
		myPw = pw; //プリントライター
	}

	public void run() {
		try {
			while (true) {
				String comment = myBf.readLine();
				if (comment != null){//入力があるかをチェック
					//生徒が復元を押した場合
					if(comment.equals("load")){
						ServerThread.Load(number);
					}
					else if(comment.equals("exit")){
						System.out.println("Number："+number+" Disconnected from Server!");
						ServerThread.SetFlag(number,false);
						break;
					}
					//  else if(comment.indexOf("user:")!= -1){//ユーザー名
					//	System.out.println(comment.split(":")[1]);

					else{
						ServerThread.Stock(comment);
						ServerThread.SendAll(comment);//メッセージを全員に送信
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exit:"+number);
			ServerThread.SetFlag(number, false);//接続が切れたのでフラグを下げる
		}
	}
}

class ServerThread{

	private static int maxConnection=100;
	private static Socket[] incoming;
	private static boolean[] flag;//接続判定
	private static InputStreamReader[] isr;//入力用の配列
	private static BufferedReader[] in;//バッファリングよるテキスト読み込み用の配列
	private static PrintWriter[] out;//出力用の配列
	private static ClientThread[] myClientThread;//スレッド用の配列
	private static int member;//接続しているメンバーの数
	private static ArrayList<String> rireki = new ArrayList<String>();

	static String[][] hostname=new String[maxConnection][1];

	//メッセージの送信
	public static void SendAll(String str){
		for(int i=1;i<=member;i++){
			if(flag[i] == true){
				out[i].println(str);
				out[i].flush();//全てのデータをすぐに送信
			}
		}
	}



	//フラグの設定（通信の切断の判定)
	public static void SetFlag(int n, boolean value){
		flag[n] = value;
	}

	public static void main(String[] args) {

		Access ac = new Access();

		//ウィンドウの場所
		ac.setBounds(600,0,300,500);
		ac.setVisible(true);


		incoming = new Socket[maxConnection];
		flag = new boolean[maxConnection];
		isr = new InputStreamReader[maxConnection];
		in = new BufferedReader[maxConnection];
		out = new PrintWriter[maxConnection];
		myClientThread = new ClientThread[maxConnection];

		BufferedReader in2 = null;
		BufferedWriter out2 = null;

		int n = 1;
		int i;
		member = 0;

		try {

			System.out.println("Server Start!");

			ServerSocket server = new ServerSocket(10000);

			while (true) {
				//接続されたメンバーのアクセスを許可
				incoming[n] = server.accept(); // ソケットを待ち受け状態にする （ループ内で処理しないと，2回目以降の受け取りができなくなる）

				flag[n] = true;
				System.out.println("Number："+n+" Access Server");
				//必要な入出力ストリームを作成する
				isr[n] = new InputStreamReader(incoming[n].getInputStream());
				in[n] = new BufferedReader(isr[n]);
				out[n] = new PrintWriter(incoming[n].getOutputStream(), true);

				String comment = in[n].readLine();

				if(comment.indexOf("user:")!= -1){//ユーザー名                                                                               
					ac.setUserName(comment);
				}

				myClientThread[n] = new ClientThread(n, incoming[n], isr[n], in[n], out[n]);
				myClientThread[n] .start();//スレッドを開始する
				member = n;//定員の更新

				n++;

			}		

		} catch (Exception e) {
			System.err.println("error: " + e);
		}
	}


	//履歴の蓄積メソッド
	public static void Stock(String st){
		rireki.add(st);
	}

	//履歴の呼び出しメソッド
	public static void Load(int accNum){
		String load="";
		if(flag[accNum]==true){
			for(int i=0;i<rireki.size();++i){
				load=rireki.get(i);
				out[accNum].println(load);
				out[accNum].flush();
			}
		}
	}

	public static String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "UnknownHost";
	}

}