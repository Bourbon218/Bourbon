import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {

		Socket clientsocket = null;
		BufferedWriter out = null;
		BufferedReader in = null;

		try{

			clientsocket = new Socket("localhost", 9191);
			out = new BufferedWriter(new OutputStreamWriter(clientsocket.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));

			// サーバーにメッセージを送る
			if (clientsocket != null && out != null && in != null) {
				// メッセージを送ります
				out.write(getUserName()); // 何かしらの文字を書かないと送出されないので，ここでは改行文字を入れた．
				out.flush(); // 書き込んだ文字を送信先に送信

				// サーバーからのメッセージを受け取り画面に表示します
				while((clientsocket.getInputStream().available() == 0));
				char[]	cline = new char[clientsocket.getInputStream().available()];
				in.read(cline);
				//System.out.println("Client Name :" + getUserName());
				System.out.println("Server says: \"" + new String(cline) + "\"");

			}


		}catch(UnknownHostException e){

			System.err.println("Trying to connect to unknown host: " + e);

		}catch(IOException e){

			System.err.println("IOException: " + e);

		}finally{

			try { // 終わった後の後始末。必ずクローズしましょう。

				if (clientsocket != null)  clientsocket.close();
				if (in != null) in.close();
				if (out != null) out.close();

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}
		}
	}

	public static String getUserName() {
		try {
			//return InetAddress.getLocalHost().getHostName();
			return System.getProperty("user.name");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "UnknownHost";
	}



}
