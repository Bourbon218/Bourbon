import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {

		ServerSocket serversocket = null;
		Socket socket = null;
		BufferedReader in = null;
		BufferedWriter out = null;	    


		int count = 0; //サーバソケットへのアクセス回数
		//boolan receiving = true; //サーバソケットを待ち受け状態にする判定に使用
		int addition = 0; //足し算結果を代入する変数


		try{

			/*サーバソケット作成(port番号)*/    
			serversocket = new ServerSocket(9191);

			/*サーバソケットを待ち受け状態にするループ処理*/
			while(true/*receiving*/){

				System.out.println("Server is Waiting for Message From Client....");

				/*ソケットを待ち受け状態にする(ループ処理で常に待ち受け状態にする)*/
				socket = serversocket.accept();

				InetAddress addr = serversocket.getInetAddress();

				System.out.println( addr + " is Connected!");

				/*送信用バッファの作成*/
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

				/*受信用バッファの作成*/
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				/*メッセージが来るまでループ*/
				while(socket.getInputStream().available()==0);

				/*ソケットから読み込み*/
				char[] num = new char[socket.getInputStream().available()];
				in.read(num);

				/*読み込んだchar[]型のデータをStringに変換*/
				String nums = new String(num);

				/*String型に変換したデータをint型に変換*/
				int number = Integer.parseInt(nums);

				/*読み込んだデータを足していく*/
				addition += number;

				/*countフラグ*/
				count++; 

				/*データを2回読み込んだときの処理*/
				if(count >= 2){

					/*クライアントソケットへのメッセージ返信*/
					String response = String.valueOf(addition);

					/*文字を書き込む時にはwriteを使用*/
					out.write(response);

					/*書き込んだ文字を送信先に送信*/
					out.flush();

					System.out.println("Server send Response to Client.");

					/*ソケットの待ち受け状態を終了*/
					break;

					///*ソケットを待ち受け状態にしているループ処理を終了*/
					//receiving = false;

				}		  

				System.out.println("Server Received a Request From Client!"); 

			}

		}catch(IOException e){

			System.out.println(e.getMessage());

		}finally{

			/*ソケット類をクローズして後始末*/
			try {

				if (serversocket != null)  serversocket.close();
				if (socket != null) socket.close();
				if (in != null) in.close();
				if (out != null) out.close();

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}
		}
	}
}
