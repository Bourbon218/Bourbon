/*サーバクラス*/
import java.rmi.Naming;

public class RMIServer {

	public RMIServer(){} //コンストラクタ

	public static void main(String[] args) {

		try {
			Hello obj = new Hello();
			Naming.bind("RMIServer",obj);

			System.err.println("Server ready");

		} catch (Exception e) {

			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();

		}

	}

}
