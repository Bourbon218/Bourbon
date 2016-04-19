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

		try{

			serversocket = new ServerSocket(9191); //サーバソケット作成(port番号)

			while(true){

				socket = serversocket.accept(); //ソケットを待ち受け状態にする(ループ処理で常に待ち受け状態にする)
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // 送信用バッファの作成
				in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 受信用バッファの作成

				while(socket.getInputStream().available()==0); //メッセージが来るまでループ

				// ソケットから読み込み
				char[]	cline = new char[socket.getInputStream().available()];
				in.read(cline);
				String name = new String(cline);
				//System.out.println(cline);

				// ソケットへのメッセージ返信
				System.out.println(name + " is Connected");
				String response = "Hello, I'm " + getHostName();
				out.write(response); // 文字を書き込む時にはwriteを使用
				out.flush(); // 書き込んだ文字を送信先に送信


			}

		}catch(IOException e){

			System.out.println(e.getMessage());

		}finally{

			try { // ソケット類をクローズして後始末

				if (serversocket != null)  serversocket.close();
				if (socket != null) socket.close();
				if (in != null) in.close();
				if (out != null) out.close();

			} catch (Exception e) {

				System.out.println(e.getMessage());

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
