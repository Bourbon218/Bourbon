import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		Socket clientsocket = null;
		BufferedWriter out = null;
		BufferedReader in = null;

		int accesscount = 0; //クライアントソケットがサーバソケットにアクセスする回数

		Scanner sc = new Scanner(System.in);

		try{

			while(true){

				clientsocket = new Socket("localhost", 9191);
				out = new BufferedWriter(new OutputStreamWriter(clientsocket.getOutputStream()));
				in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));

				/*数字を入力*/
				System.out.print("Input :");
				int number = sc.nextInt();

				/*入力した数字(int型)をString型に変換*/
				String num = String.valueOf(number);

				/*サーバにメッセージを送る*/
				if (clientsocket != null && out != null && in != null) {

					/*メッセージ送信*/
					out.write(num);

					/*書き込んだ文字を送信先に送信*/
					out.flush();

				}

				/*サーバソケットにアクセスしたのでアクセスカウントを足す*/
				accesscount++;

				/*accesscountが2回以上になったのでループ処理を終了*/
				if(accesscount>=2) break;

			}

			/* サーバーからのメッセージを受け取り画面に表示します*/
			while((clientsocket.getInputStream().available() == 0));
			char[] answer = new char[clientsocket.getInputStream().available()];
			in.read(answer);
			String message = new String(answer);
			int response = Integer.parseInt(message);
			System.out.println("response : " + response );

		}catch(UnknownHostException e){

			System.err.println("Trying to connect to unknown host: " + e);

		}catch(IOException e){

			System.err.println("IOException: " + e);

		}finally{

			/*ソケット類をクローズして後始末*/
			try {

				if (clientsocket != null)  clientsocket.close();
				if (in != null) in.close();
				if (out != null) out.close();

			} catch (Exception e) {

				System.out.println(e.getMessage());

			}
		}
	}

}
